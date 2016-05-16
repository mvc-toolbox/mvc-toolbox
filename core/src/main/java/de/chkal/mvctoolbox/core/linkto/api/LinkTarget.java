package de.chkal.mvctoolbox.core.linkto.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Defines a symbolic name for a target method to be referenced by {@link LinkTo}.</p>
 *
 * <p>The same value of @LinkTarget can only used multiple times on different methods
 * if these methods are using the same URI-template with different HTTP verbs.</p>
 *
 * TODO should we also support annotating target classes as target?
 * TODO could be replaced by adding value() to @Controller?
 *
 * @author Florian Hirsch
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LinkTarget {

	String value();

}
