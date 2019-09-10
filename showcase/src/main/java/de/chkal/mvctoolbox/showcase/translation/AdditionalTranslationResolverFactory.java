package de.chkal.mvctoolbox.showcase.translation;

import de.chkal.mvctoolbox.core.translation.PropertiesTranslationResolver;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.mvc.MvcContext;

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
  public EmbeddedTranslationResolver embeddedTranslationResolver(MvcContext mvcContext) {
    return new EmbeddedTranslationResolver();
  }

  @Produces
  @ApplicationScoped
  @Named("page1")
  public PropertiesTranslationResolver page1Translations(MvcContext mvcContext) {
    return new PropertiesTranslationResolver("page1", mvcContext);
  }
}
