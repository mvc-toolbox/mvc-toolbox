package de.chkal.mvctoolbox.core.linkto.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Defines an identifier for a target method to be referenced by {@link LinkTo}.</p>
 *
 * <p>This identifier should be used unique per URI template. It can be used multiple times
 * for the same URI template if the template is used by different HTTP methods.</p>
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
