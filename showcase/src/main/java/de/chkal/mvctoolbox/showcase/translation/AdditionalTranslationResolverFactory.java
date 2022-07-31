package de.chkal.mvctoolbox.showcase.translation;

import de.chkal.mvctoolbox.core.translation.PropertiesTranslationResolver;
import de.chkal.mvctoolbox.core.translation.TranslationResolver;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import jakarta.mvc.MvcContext;

/**
 * Example for the ability to add custom {@link de.chkal.mvctoolbox.core.translation.TranslationResolver} by adding
 * a new factory.
 *
 * @author Tobias Erdle
 */
@ApplicationScoped
public class AdditionalTranslationResolverFactory {

  @Produces
  @ApplicationScoped
  @Named("embedded")
  public TranslationResolver embeddedTranslationResolver(MvcContext mvcContext) {
    return new EmbeddedTranslationResolver();
  }

  @Produces
  @ApplicationScoped
  @Named("page1")
  public TranslationResolver page1Translations(MvcContext mvcContext) {
    return new PropertiesTranslationResolver("page1", mvcContext);
  }
}
