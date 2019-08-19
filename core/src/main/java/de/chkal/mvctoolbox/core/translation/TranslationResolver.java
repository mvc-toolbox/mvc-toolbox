package de.chkal.mvctoolbox.core.translation;

import java.util.Locale;

/**
 * An interface for resolving translations based on a key and a {@link java.util.Locale}.
 * <br>
 * <br>
 * Through implementations of the {@link TranslationResolver} MVC views shall be able
 * to receive translations from different, prioritized sources, e.g. properties files or
 * a database.
 *
 * @author Tobias Erdle
 */
public interface TranslationResolver {

  /**
   * Resolve the translation for a string key and the provided {@link Locale}.
   *
   * @param key the string key for which translations shall be resolved
   * @param locale the {@link Locale} for which a translation is queried
   * @return the found translation or null, when the key doesn't exist
   */
  String resolve(final String key, final Locale locale);

}
