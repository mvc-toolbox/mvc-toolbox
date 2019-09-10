package de.chkal.mvctoolbox.core;

import de.chkal.mvctoolbox.core.message.MvcMessages;
import de.chkal.mvctoolbox.core.translation.TranslationResolverChain;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
  private TranslationResolverChain translations;

  public MvcMessages getMessages() {
    return messages;
  }

  public String t(final String key) {
    return translations.resolve(key, null);
  }

  public String t(final String key, final List<Object> args) {
    return translations.resolve(key, args.toArray());
  }
}
