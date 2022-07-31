package de.chkal.mvctoolbox.core;

import de.chkal.mvctoolbox.core.message.MvcMessages;
import de.chkal.mvctoolbox.core.translation.DefaultTranslationResolver;
import de.chkal.mvctoolbox.core.translation.TranslationResolver;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

/**
 * This class is the only one annotated with {@link Named}. This way the library
 * won't pollute the global EL namespace. Users will have to use this class
 * to access other MVC Toolbox objects (i.e. <code>${toolbox.messages}</code>)
 */
@Named
@ApplicationScoped
public class Toolbox {

  @Inject
  private MvcMessages messages;

  @Inject
  @DefaultTranslationResolver
  private TranslationResolver translations;

  public MvcMessages getMessages() {
    return messages;
  }

  public String t(final String key) {
    return translations.resolve(key);
  }

  public String t(final String key, final List<Object> args) {
    return translations.resolve(key, args.toArray());
  }
}
