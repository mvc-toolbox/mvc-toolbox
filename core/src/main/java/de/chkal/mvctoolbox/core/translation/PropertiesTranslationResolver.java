package de.chkal.mvctoolbox.core.translation;

import jakarta.mvc.MvcContext;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Resolves translations from a resource bundle with a configurable base name. The {@link Locale} for the
 * processed request will be resolved by the applied {@link MvcContext}.
 *
 * @author Tobias Erdle
 */
public class PropertiesTranslationResolver implements TranslationResolver {

  private String resourceBundleName;
  private MvcContext mvcContext;

  public PropertiesTranslationResolver(final String resourceBundleName, final MvcContext mvcContext) {
    this.resourceBundleName = resourceBundleName;
    this.mvcContext = mvcContext;
  }

  @Override
  public String resolve(final String key) {
    return resolveTranslation(key, mvcContext.getLocale());
  }

  @Override
  public String resolve(final String key, final Object... args) {
    Objects.requireNonNull(args, "Translation args mustn't be null");

    final Locale requestLocale = mvcContext.getLocale();
    final String template = resolveTranslation(key, requestLocale);

    return template.equals(formatPlaceholder(key)) ? template : formatTranslationTemplate(requestLocale, template, args);
  }

  private String resolveTranslation(final String key, final Locale locale) {
    Objects.requireNonNull(key, "Translation key mustn't be null");
    Objects.requireNonNull(locale, "Translation locale mustn't be null");

    final ResourceBundle.Control control =
      ResourceBundle.Control.getNoFallbackControl( ResourceBundle.Control.FORMAT_PROPERTIES );

    final ResourceBundle resourceBundle = ResourceBundle.getBundle( resourceBundleName, locale, control );

    return resourceBundle.containsKey(key) ? resourceBundle.getString(key) : formatPlaceholder(key);
  }

  private static String formatTranslationTemplate(final Locale locale, final String template, final Object[] args) {
    final MessageFormat formatter = new MessageFormat(template, locale);

    return formatter.format(args);
  }

  private static String formatPlaceholder(final String key) {
    return String.format(PLACEHOLDER_TEMPLATE, key);
  }
}
