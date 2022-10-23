package de.chkal.mvctoolbox.jsp.tag;

import de.chkal.mvctoolbox.core.translation.DefaultTranslationResolverLiteral;
import de.chkal.mvctoolbox.core.translation.TranslationResolver;
import de.chkal.mvctoolbox.jsp.BaseTag;
import de.chkal.mvctoolbox.jsp.HtmlWriter;
import java.io.IOException;
import java.util.List;

public class TranslationTag extends BaseTag {

  private static final String MISSING_RESOLVER_PLACEHOLDER = "!!! MISSING RESOLVER !!!";

  private String key;
  private List<String> vars;
  private TranslationResolver specializedResolver;

  @Override
  public void doTag() throws IOException {
    final HtmlWriter writer = new HtmlWriter(getJspContext());
    final TranslationResolver defaultTranslationResolver = getBean(
        new DefaultTranslationResolverLiteral());

    if (defaultTranslationResolver == null && specializedResolver == null) {
      writer.write(MISSING_RESOLVER_PLACEHOLDER);
      return;
    }

    final String translation = specializedResolver != null
        ? resolveTranslationWithResolver(specializedResolver, key, vars)
        : resolveTranslationWithResolver(defaultTranslationResolver, key, vars);
    writer.write(translation);
  }

  private String resolveTranslationWithResolver(final TranslationResolver resolver,
      final String key, final List<String> vars) {
    return vars == null || vars.isEmpty() ? resolver.resolve(key)
        : resolver.resolve(key, vars.toArray());
  }

  public void setKey(String key) {
    this.key = key;
  }

  public void setVars(List<String> vars) {
    this.vars = vars;
  }

  public void setSpecializedResolver(final TranslationResolver specializedResolver) {
    this.specializedResolver = specializedResolver;
  }
}
