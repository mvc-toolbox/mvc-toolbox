package de.chkal.mvctoolbox.core.linkto.impl;

import de.chkal.mvctoolbox.core.linkto.api.LinkTarget;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.mvc.MvcContext;
import javax.ws.rs.BeanParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriBuilder;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>A bean which parses and provides all instances of {@link LinkedMethod} for current application.</p>
 *
 * @author Florian Hirsch
 */
@ApplicationScoped
public class LinkedMethods {

	private static final Logger LOG = Logger.getLogger(LinkedMethods.class.getName());

	@Inject
	MvcContext mvcContext;

	@Context
	Application application;

	private Map<String, LinkedMethod> linkedMethods = new HashMap<>();

	@PostConstruct
	public void init() {
		Set<Class<?>> controllers = Stream.concat(
				application.getClasses().stream().filter(LinkToUtils::isController),
				application.getSingletons().stream().map(Object::getClass).filter(LinkToUtils::isController)
		).collect(Collectors.toSet());
		for (Class<?> controller : controllers) {
			for (Method method : controller.getMethods()) {
				if (LinkToUtils.isControllerMethod(method)) {
					LinkedMethod linkedMethod = parseMethod(method, mvcContext.getBasePath());
					register(linkedMethod, method);
				}
			}
		}
		if (LOG.isLoggable(Level.FINER)) {
			String methodsDump = linkedMethods.entrySet().stream().map(e -> String.format("%s: %s", e.getKey(), e.getValue()))
					.collect(Collectors.joining(",\n ", "LinkToTargets resolved following methods:\n[", "]"));
			LOG.finer(methodsDump);
		}
	}

	/**
	 * <p>Parses given method and constructs a {@link LinkedMethod} containing
	 * all the information found in the annotations {@link Path}, {@link QueryParam}
	 * and {@link MatrixParam}.</p>
	 */
	LinkedMethod parseMethod(Method method, String basePath) {
		UriBuilder uriBuilder = UriBuilder.fromPath(basePath);
		Path controllerPath = LinkToUtils.getAnnotation(method.getDeclaringClass(), Path.class);
		if (controllerPath != null) {
			uriBuilder.path(controllerPath.value());
		}
		Path methodPath = LinkToUtils.getAnnotation(method, Path.class);
		if (methodPath != null) {
			uriBuilder.path(methodPath.value());
		}
		LinkedMethod.Builder linkBuilder = LinkedMethod.fromTemplate(uriBuilder.toTemplate());
		// Populate a List with all properties of given target and all parameters of given method
		// except for BeanParams where we need all properties of annotated type.
		List<AnnotatedElement> annotatedElements = LinkToUtils.getFieldsAndAccessors(method.getDeclaringClass());
		Arrays.asList(method.getParameters()).forEach(param -> {
			if (param.isAnnotationPresent(BeanParam.class)) {
				annotatedElements.addAll(LinkToUtils.getFieldsAndAccessors(param.getType()));
			} else {
				annotatedElements.add(param);
			}
		});
		annotatedElements.forEach(accessibleObject -> {
			if (accessibleObject.isAnnotationPresent(QueryParam.class)) {
				linkBuilder.queryParam(accessibleObject.getAnnotation(QueryParam.class).value());
			}
			if (accessibleObject.isAnnotationPresent(MatrixParam.class)) {
				linkBuilder.matrixParam(accessibleObject.getAnnotation(MatrixParam.class).value());
			}
		});
		return linkBuilder.build();
	}

	/**
	 * <p>Registers given {@link LinkedMethod} by:</p>
	 * <ul>
	 *     <li>The value of {@link LinkTarget}</li>
	 *     <li>The simple classname</li>
	 *     <li>The fully qualified classname</li>
	 * </ul>
	 * <p>Logs a warning if a simple classname is ambiguous.</p> 
	 * @throws IllegalArgumentException if a link target is used for different URI templates.
	 */
	void register(LinkedMethod linkedMethod, Method method) {
		Class<?> controller = method.getDeclaringClass();
		// register by @LinkTarget value
		LinkTarget linkTarget = LinkToUtils.getAnnotation(method, LinkTarget.class);
		if (linkTarget != null) {
			String controllerIdentifier = linkTarget.value();
			if (linkedMethods.containsKey(controllerIdentifier)) {
				LinkedMethod storedMethod = linkedMethods.get(controllerIdentifier);
				// Same template is ok for different HTTP methods.
				// We don't check if the same HTTP method is used twice as
				// then we would have a path-matching problem anyway.
				if (!storedMethod.uriTemplate().equals(linkedMethod.uriTemplate())) {
					throw new IllegalArgumentException(String.format("LinkTarget '%s' is ambiguosly used for URI template '%s' and '%s'",
							controllerIdentifier, linkedMethod.uriTemplate(), storedMethod.uriTemplate()));
				}
			}
			linkedMethods.put(controllerIdentifier, linkedMethod);
		}
		// register by simple class and method name
		String simpleIdentifier = identifier(controller, method.getName());
		if (linkedMethods.containsKey(simpleIdentifier)) {
			LOG.warning(String.format(
					"The combination of target '%s' and method '%s' is ambiguosly used. It's unpredictable which one will be used by linkTo(target, method).",
					controller.getSimpleName(), method.getName()));
		} else {
			linkedMethods.put(simpleIdentifier, linkedMethod);
		}
		// register by fully qualified class and method name
		linkedMethods.put(fqIdentifier(controller, method.getName()), linkedMethod);
	}

	/**
	 * @return LinkedMethod for given target
	 */
	LinkedMethod get(String target) {
		if (!linkedMethods.containsKey(target)) {
			throw new IllegalArgumentException(String.format("Could not find a link for target '%s'", target));
		}
		return linkedMethods.get(target);
	}

	/**
	 * @return LinkedMethod for controller method
	 */
	LinkedMethod get(String controller, String method) {
		String identifier = identifier(controller, method);
		if (!linkedMethods.containsKey(identifier)) {
			throw new IllegalArgumentException(String.format("Could not find a link for controller '%s' and method '%s'", controller, method));
		}
		return linkedMethods.get(identifier);
	}

	/**
	 * @return LinkedMethod for controller method
	 */
	LinkedMethod get(Class<?> controller, String method) {
		String identifier = fqIdentifier(controller, method);
		if (!linkedMethods.containsKey(identifier)) {
			throw new IllegalArgumentException(String.format("Could not find a link for controller '%s' and method '%s'", controller, method));
		}
		return linkedMethods.get(identifier);
	}

	/**
	 * @return Controller#method
	 */
	private String identifier(String controller, String method) {
		return String.format("%s#%s", controller, method);
	}

	/**
	 * @return Controller#method
	 */
	private String identifier(Class<?> controller, String method) {
		return identifier(controller.getSimpleName(), method);
	}

	/**
	 * @return f.q.Controller#method
	 */
	private String fqIdentifier(Class<?> controller, String method) {
		return String.format("%s#%s", controller.getName(), method);
	}
	
}
