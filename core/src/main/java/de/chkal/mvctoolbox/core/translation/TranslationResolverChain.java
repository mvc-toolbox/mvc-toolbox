package de.chkal.mvctoolbox.core.translation;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.annotation.Priority;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.mvc.MvcContext;

/**
 * Chain to resolve translations from different {@link TranslationResolver}.
 *
 * @author Tobias Erdle
 */
@RequestScoped
public class TranslationResolverChain {

  /**
   * Template which is used when a key is unknown or couldn't be resolved. Results in something like '???my.key???'.
   */
  private static final String UNKNOWN_KEY_TEMPLATE = "???%s???";

  @Inject
  private MvcContext mvcContext;

  @Inject
  private Instance<TranslationResolver> translationResolver;

  /**
   * Resolve the translation for a specific key.
   *
   * @param key the key for which a translation shall be resolved. Mustn't be null!
   * @return the found translation or a placeholder with the form of {@link #UNKNOWN_KEY_TEMPLATE}
   */
  public String resolve(final String key) {
    final String resolvedTranslation = resolveTranslation(key);

    return resolvedTranslation != null ? resolvedTranslation : formatUnknownKey(key);
  }

  /**
   * Setter for a {@link MvcContext} instance. Shall only be used for testing in an CDI environment
   * to add mocks.
   *
   * @param mvcContext the {@link MvcContext} to set
   */
  void setMvcContext(final MvcContext mvcContext) {
    this.mvcContext = mvcContext;
  }

  private String resolveTranslation(final String key) {
    final Locale requestLocale = mvcContext.getLocale();

    final List<TranslationResolver> sortedTranslationResolver = sortTranslationResolversByPriority(translationResolver);
    for (final TranslationResolver resolver : sortedTranslationResolver) {
      final String translation = resolver.resolve(key, requestLocale);

      if (translation != null) {
        return translation;
      }
    }

    return null;
  }

  private static List<TranslationResolver> sortTranslationResolversByPriority(final Instance<TranslationResolver> translationResolvers) {
    return translationResolvers.stream()
        .sorted(new PriorityComparator())
        .collect(Collectors.toList());
  }

  private static String formatUnknownKey(final String key) {
    return String.format(UNKNOWN_KEY_TEMPLATE, key);
  }

  private static final class PriorityComparator implements Comparator<TranslationResolver> {

    @Override
    public int compare(final TranslationResolver c1, final TranslationResolver c2) {
      final Priority priorityAnn1 = c1.getClass()
          .getAnnotation(Priority.class);
      final Priority priorityAnn2 = c2.getClass()
          .getAnnotation(Priority.class);

      final int priority1 = priorityAnn1 != null ? priorityAnn1.value() : TranslationResolver.DEFAULT_PRIORITY;
      final int priority2 = priorityAnn2 != null ? priorityAnn2.value() : TranslationResolver.DEFAULT_PRIORITY;

      return priority2 - priority1;
    }
  }
}
