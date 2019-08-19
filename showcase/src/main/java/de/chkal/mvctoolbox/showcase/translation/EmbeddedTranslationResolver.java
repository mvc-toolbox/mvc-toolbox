package de.chkal.mvctoolbox.showcase.translation;

import de.chkal.mvctoolbox.core.translation.TranslationResolver;
import java.util.Locale;
import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;

/**
 * Simple custom {@link TranslationResolver} to receive a translation from an embedded
 * 'storage'. Only for demonstrating the extension mechanism of the translation resolution.
 *
 * @author Tobias Erdle
 */
@ApplicationScoped
@Priority(TranslationResolver.DEFAULT_PRIORITY + 1)
public class EmbeddedTranslationResolver implements TranslationResolver {

  @Override
  public String resolve(final String key, final Locale locale) {
    return "embedded.key".equals(key) ? "From embedded resolver" : null;
  }
}
