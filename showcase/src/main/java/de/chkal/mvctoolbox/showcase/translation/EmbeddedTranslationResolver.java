package de.chkal.mvctoolbox.showcase.translation;

import de.chkal.mvctoolbox.core.translation.TranslationResolver;
import javax.enterprise.context.ApplicationScoped;

/**
 * Simple custom {@link TranslationResolver} to receive a translation from an embedded
 * 'storage'. Only for demonstrating the extension mechanism of the translation resolution.
 *
 * @author Tobias Erdle
 */
@ApplicationScoped
public class EmbeddedTranslationResolver implements TranslationResolver {

  @Override
  public String resolve(final String key) {
    return "embedded.key".equals(key) ? "From embedded resolver" : null;
  }

  @Override
  public String resolve(final String key, final Object... args) {
    return resolve(key);
  }
}
