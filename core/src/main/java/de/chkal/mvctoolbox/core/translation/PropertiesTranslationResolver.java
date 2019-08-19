package de.chkal.mvctoolbox.core.translation;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Priority(TranslationResolver.DEFAULT_PRIORITY)
public class PropertiesTranslationResolver implements TranslationResolver {

  private static final String DEFAULT_RESOURCE_BUNDLE = "messages";

  @Override
  public String resolve(final String key, final Locale locale) {
    Objects.requireNonNull(key, "Translation key mustn't be null");
    Objects.requireNonNull(locale, "Translation locale mustn't be null");

    final ResourceBundle resourceBundle = ResourceBundle.getBundle(DEFAULT_RESOURCE_BUNDLE, locale);

    return resourceBundle.containsKey(key) ? resourceBundle.getString(key) : null;
  }
}
