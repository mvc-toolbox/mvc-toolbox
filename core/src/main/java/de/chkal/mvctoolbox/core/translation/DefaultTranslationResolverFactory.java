package de.chkal.mvctoolbox.core.translation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.mvc.MvcContext;

/**
 * Factory for initializing the default translation resolver for the {@link de.chkal.mvctoolbox.core.Toolbox}.
 *
 * @author Tobias Erdle
 * @see de.chkal.mvctoolbox.core.Toolbox
 */
@ApplicationScoped
public class DefaultTranslationResolverFactory {

  /**
   * Produce the default {@link TranslationResolver} for the {@link de.chkal.mvctoolbox.core.Toolbox}. This will be
   * an @{@link ApplicationScoped} {@link PropertiesTranslationResolver} which points at a resource bundle named 'messages'
   * in the projects resources root. This will match for the most applications.
   * <p>
   * In case an application needs a more advanced setup or custom {@link TranslationResolver}s, an additional factory with
   * producer methods can be created.
   *
   * @param mvcContext the {@link MvcContext} of the current request
   * @return the default {@link PropertiesTranslationResolver} for the {@link de.chkal.mvctoolbox.core.Toolbox} bean
   */
  @Produces
  @DefaultTranslationResolver
  @ApplicationScoped
  public TranslationResolver getDefaultTranslationResolver(final MvcContext mvcContext) {
    return new PropertiesTranslationResolver("messages", mvcContext);
  }
}
