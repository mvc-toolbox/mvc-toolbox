package de.chkal.mvctoolbox.core.translation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import javax.inject.Qualifier;

/**
 * Qualifier to identify the default bean for resolving translations via the {@link de.chkal.mvctoolbox.core.Toolbox#t(String)}
 * or {@link de.chkal.mvctoolbox.core.Toolbox#t(String, List)} methods.
 *
 * @author Tobias Erdle
 * @see DefaultTranslationResolverFactory
 * @see de.chkal.mvctoolbox.core.Toolbox
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface DefaultTranslationResolver {

}
