package de.chkal.mvctoolbox.core.linkto.api;

import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * <p>Provides support to create URIs to be matched by a controller method.
 * Methods of this interface are usually used from a template.</p>
 *
 * <p>Controller methods can be be referenced in three ways:</p>
 * <ul>
 *     <li>The value of the {@link LinkTarget} annotation.</li>
 *     <li>The simple classname and method name.</li>
 *     <li>The fully qualified classname and method name.</li>
 * </ul>
 *
 * <p>The created URI includes context- and application path.</p>
 *
 * @author Florian Hirsch
 */
public interface LinkTo {

	/**
	 * <p>Creates an URI to given target assuming there is no parameter in the URI template.</p>
	 *
	 * @param target value of the {@link LinkTarget} to be matched.
	 * @return the constructed URI including context- and application path.
	 */
	URI target(String target);

	/**
	 * <p>Creates an URI to given target.</p>
	 * <p>Any {@link javax.ws.rs.PathParam} parameter found in the URI template
	 * will be replaced by the values of the supplied List in the same order as provided.
	 * Note that query parameters and matrix parameters are not supported
	 * by this method as their order is not predefined.</p>
	 *
	 * @param target value of the {@link LinkTarget} to be matched.
	 * @param params a list of path parameters.
	 * @return the constructed URI including context- and application path.
	 */
	URI target(String target, List<Object> params);

	/**
	 * <p>Creates an URI to given target.</p>
	 * <p>Any {@link javax.ws.rs.PathParam}, {@link javax.ws.rs.QueryParam}
	 * and {@link javax.ws.rs.MatrixParam} which could apply for given target
	 * method will be replaced if a matching key is found in the supplied Map.</p>
	 * <p>Note that this method does not handle ambiguous parameter names.</p>
	 *
	 * @param target value of the {@link LinkTarget} to be matched.
	 * @param params a map of path-, query- and matrix parameters.
	 * @return the constructed URI including context- and application path.
	 */
	URI target(String target, Map<String, Object> params);

	/**
	 * <p>Creates an URI to given controller method assuming there is no parameter in the URI template.</p>
	 *
	 * @param controller simple classname of the controller or the fully qualified classname to resolve ambiguity.
	 * @param method name of the method.
	 * @return the constructed URI including context- and application path.
	 */
	URI method(String controller, String method);

	/**
	 * <p>Creates an URI to given controller method.</p>
	 * <p>Any {@link javax.ws.rs.PathParam} parameter found in the URI template
	 * will be replaced by the values of the supplied List in the same order as provided.
	 * Note that query parameters and matrix parameters are not supported
	 * by this method as their order is not predefined.</p>
	 *
	 * @param controller simple classname of the controller or the fully qualified classname to resolve ambiguity.
	 * @param method name of the method.
	 * @param params a list of path parameters.
	 * @return the constructed URI including context- and application path.
	 */
	URI method(String controller, String method, List<Object> params);

	/**
	 * <p>Creates an URI to given controller method.</p>
	 * <p>Any {@link javax.ws.rs.PathParam}, {@link javax.ws.rs.QueryParam}
	 * and {@link javax.ws.rs.MatrixParam} which could apply for given target
	 * method will be replaced if a matching key is found in the supplied Map.</p>
	 * <p>Note that this method does not handle ambiguous parameter names.</p>
	 *
	 * @param controller simple classname of the controller or the fully qualified classname to resolve ambiguity.
	 * @param method name of the method.
	 * @param params a map of path-, query- and matrix parameters.
	 * @return the constructed URI including context- and application path.
	 */
	URI method(String controller, String method, Map<String, Object> params);

	/**
	 * <p>Returns a {@link de.chkal.mvctoolbox.core.linkto.api.LinkTo.Builder} initialized with given target.</p>
	 *
	 * @param target value of a {@link LinkTarget} annotation
	 * @return a reference to a Builder
	 */
	Builder builderFor(String target);

	/**
	 * <p>Returns a {@link de.chkal.mvctoolbox.core.linkto.api.LinkTo.Builder} initialized with given controller and method.</p>
	 *
	 * @param controller simple classname of a controller
	 * @param method name of the method to be matched
	 * @return a reference to a Builder
	 */
	Builder builderFor(String controller, String method);

	/**
	 * <p>Returns a {@link de.chkal.mvctoolbox.core.linkto.api.LinkTo.Builder} initialized with given controller and method.</p>
	 *
	 * @param controller a controller class
	 * @param method name of the method to be matched
	 * @return a reference to a Builder
	 */
	Builder builderFor(Class<?> controller, String method);

	/**
	 * <p>Fluent Interface for adding parameters to a URI template retrieved by one of the {@link #builderFor} methods.</p>
	 */
	interface Builder {

		/**
		 * <p>Adds a parameter which will substitute any {@link javax.ws.rs.PathParam},
		 * {@link javax.ws.rs.QueryParam} and {@link javax.ws.rs.MatrixParam} with a matching name.</p>
		 *
		 * @param param name of the paramater
		 * @param value value of the parameter
		 * @return a reference to this Builder
		 */
		Builder param(String param, Object value);

		/**
		 * <p>Adds a map of parameters which will substitute any {@link javax.ws.rs.PathParam},
		 * {@link javax.ws.rs.QueryParam} and {@link javax.ws.rs.MatrixParam} with a matching name.</p>
		 *
		 * @param params a map of params
		 * @return a reference to this Builder
		 */
		Builder params(Map<String, Object> params);

		/**
		 * <p>Builds a URI using the given parameters.</p>
		 *
		 * @return the URI built from the Builder
		 *
		 * @see javax.ws.rs.core.UriBuilder#buildFromMap(Map)
		 */
		URI build();

	}

}
