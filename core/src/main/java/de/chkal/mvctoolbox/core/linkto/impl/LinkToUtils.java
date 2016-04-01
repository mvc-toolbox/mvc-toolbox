package de.chkal.mvctoolbox.core.linkto.impl;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import javax.mvc.annotation.Controller;
import javax.ws.rs.BeanParam;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>Helper methods for the LinkTo functionality.</p>
 *
 * <p>Most of the methods are copied form Ozark as we expect
 * that this functionality will be merged to Ozark.</p>
 *
 * TODO refactor me if not and write some unit tests
 *
 * @author Florian Hirsch
 */
@SuppressWarnings("unchecked")
final class LinkToUtils {

	/**
	 * Taken from {@link org.glassfish.ozark.util.CdiUtils}.
	 */
	static <T> T newBean(BeanManager bm, Class<T> clazz) {
		Set<Bean<?>> beans = bm.getBeans(clazz);
		final Bean<T> bean = (Bean<T>) bm.resolve(beans);
		final CreationalContext<T> ctx = bm.createCreationalContext(bean);
		return (T) bm.getReference(bean, clazz, ctx);
	}

	/**
	 * Taken from {@link org.glassfish.ozark.util.AnnotationUtils}.
	 */
	static <T extends Annotation> T getAnnotation(Class<?> clazz, Class<T> annotationType) {
		final T an = clazz.getDeclaredAnnotation(annotationType);
		if (an != null) {
			return an;
		}
		final BeanManager bm = CDI.current().getBeanManager();
		final AnnotatedType<?> type = bm.createAnnotatedType(clazz);
		return type != null ? type.getAnnotation(annotationType) : null;
	}

	/**
	 * Taken from {@link org.glassfish.ozark.util.AnnotationUtils}.
	 */
	static <T extends Annotation> T getAnnotation(Method method, Class<T> annotationType) {
		// If we reached Object.class, we couldn't find it
		final Class<?> clazz = method.getDeclaringClass();
		if (clazz == Object.class) {
			return null;
		}

		// Check if annotation declared (but not inherited) on method
		T an = method.getDeclaredAnnotation(annotationType);
		if (an != null) {
			return an;
		}

		// Other MVC annotations on this method, then inheritance disabled
		if (hasMvcOrJaxrsAnnotations(method)) {
			return null;
		} else {
			// Search for overridden method in super class
			final Class<?> superClass = method.getDeclaringClass().getSuperclass();
			if (superClass != null) {
				try {
					final Method superMethod = superClass.getDeclaredMethod(method.getName(), method.getParameterTypes());
					an = getAnnotation(superMethod, annotationType);
				} catch (NoSuchMethodException e) {
					// falls through
				}
				if (an != null) {
					return an;
				}
			}

			// Now search for overridden method in super interfaces
			final Class<?>[] interfaces = method.getDeclaringClass().getInterfaces();
			for (Class<?> in : interfaces) {
				try {
					final Method superMethod = in.getDeclaredMethod(method.getName(), method.getParameterTypes());
					an = getAnnotation(superMethod, annotationType);
				} catch (NoSuchMethodException e) {
					// falls through
				}
				if (an != null) {
					return an;
				}
			}

			// Not found, return null
			return null;
		}
	}

	/**
	 * Taken from {@link org.glassfish.ozark.util.AnnotationUtils}.
	 */
	private static boolean hasMvcOrJaxrsAnnotations(Method method) {
		final List<Annotation> ans = Arrays.asList(method.getDeclaredAnnotations());
		return ans.stream().anyMatch(a -> {
			final String an = a.annotationType().getName();
			return an.startsWith("javax.mvc.") || an.startsWith("javax.ws.rs.");
		});
	}

	/**
	 * Taken from {@link org.glassfish.ozark.jersey.OzarkFeature}.
	 */
	static boolean isController(Class<?> c) {
		return getAnnotation(c, Controller.class) != null ||
				Arrays.asList(c.getMethods()).stream().anyMatch(m -> getAnnotation(m, Controller.class) != null);
	}

	/**
	 * Tests if given method is a target method which is true
	 * if a @Controller annotation is found on given method or the declaring class
	 * and if a @HttpMethod annotated annotation is declared or inherited on this method.
	 * TODO check inheritance rules
	 */
	static boolean isControllerMethod(Method method) {
		boolean isController = getAnnotation(method.getDeclaringClass(), Controller.class) != null
								|| getAnnotation(method, Controller.class) != null;
		return isController && isResourceMethod(method);
	}

	/**
	 * Tests if a @HttpMethod annotated annotation is declared or inherited on this method.
	 * TODO check inheritance rules
	 */
	static boolean isResourceMethod(Method method) {
		Class<?> clazz = method.getDeclaringClass();
		while (clazz != null) {
			try {
				Method currentMethod = clazz.getMethod(method.getName(), method.getParameterTypes());
				if (hasResourceMethodAnnotation(currentMethod)) {
					return true;
				}
				if (hasMvcOrJaxrsAnnotations(method)) {
					return false;
				}
			} catch (NoSuchMethodException ignored) {
				// ignored
			}
			clazz = clazz.getSuperclass();
		}
		for (Class<?> in : method.getDeclaringClass().getInterfaces()) {
			try {
				Method currentMethod = in.getMethod(method.getName(), method.getParameterTypes());
				if (hasResourceMethodAnnotation(currentMethod)) {
					return true;
				}
			} catch (NoSuchMethodException ignored) {
				// ignored
			}
		}
		return false;
	}

	/**
	 * Tests if a @HttpMethod annotated annotation is found on given method.
	 */
	private static boolean hasResourceMethodAnnotation(Method method) {
		return Arrays.asList(method.getDeclaredAnnotations()).stream().anyMatch(a -> a.annotationType().getAnnotation(HttpMethod.class) != null);
	}

	/**
	 * @return a List of all Fields and Getters/Setters of given class.
	 */
	public static List<AnnotatedElement> getFieldsAndAccessors(Class<?> clazz) {
		List<AnnotatedElement> properties = new ArrayList<>();
		properties.addAll(Arrays.asList(clazz.getDeclaredFields()));
		try {
			Arrays.asList(Introspector.getBeanInfo(clazz, Object.class).getPropertyDescriptors()).forEach(prop -> {
				if (prop.getReadMethod() != null) {
					properties.add(prop.getReadMethod());
				}
				if (prop.getWriteMethod() != null) {
					properties.add(prop.getWriteMethod());
				}
			});
		} catch (IntrospectionException ex) {
			throw new IllegalArgumentException(String.format("Could not parse properties from class %s", clazz), ex);
		}
		return properties;
	}

	/**
	 * @return given map transformed to a {@link MultivaluedMap}.
	 */
	static MultivaluedMap<String, Object> toMultivaluedMap(Map<String, Object> map) {
		MultivaluedMap<String, Object> result = new MultivaluedHashMap<>();
		map.forEach((k, v) -> {
			// need to iterate on our own as we V is of type Object
			if (v instanceof Iterable) {
				((Iterable) v).forEach(listValue -> result.add(k, listValue));
			} else {
				result.add(k, v);
			}
		});
		return result;
	}

}
