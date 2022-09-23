package de.chkal.mvctoolbox.core.translation;

import java.util.Locale;

/**
 * An interface for resolving translations based on a key and a {@link java.util.Locale}.
 * <br>
 * <br>
 * Through implementations of the {@link TranslationResolver} MVC views shall be able
 * to receive translations from different, prioritized sources, e.g. properties files or
 * a database.
 * <br>
 * <br>
 * Please note, that the {@link Locale} gets resolved by the implementations of the {@link TranslationResolver} interface.
 *
 * @author Tobias Erdle
 */
public interface TranslationResolver {

	/**
	 * The template for the placeholder in case a language key doesn't exist.
	 */
	String PLACEHOLDER_TEMPLATE = "??? %s ???";

	/**
	 * Resolve the translation for a string key.
	 * <p>
	 * The {@link Locale} must be resolved by the implementation of this interface.
	 *
	 * @param key the string key for which translations shall be resolved
	 * @return the found translation or a placeholder <code>??? keyname ???</code>, when the key doesn't exist
	 */
	String resolve(final String key);

	/**
	 * Resolve the translation for a string key. The translation may
	 * be a {@link java.text.MessageFormat} template which can be enriched with custom arguments.
	 * <p>
	 * The {@link Locale} must be resolved by the implementation of this interface.
	 *
	 * @param key  the string key for which translations shall be resolved
	 * @param args an array of variable length containing placeholder values
	 * @return the found translation with filled placeholders or a placeholder <code>??? keyname ???</code>, when the key doesn't exist
	 */
	String resolve(final String key, final Object... args);
}
