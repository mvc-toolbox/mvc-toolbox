package de.chkal.mvctoolbox.core;

import de.chkal.mvctoolbox.core.message.MvcMessage;
import de.chkal.mvctoolbox.core.message.MvcMessages;
import de.chkal.mvctoolbox.core.translation.DefaultTranslationResolver;
import de.chkal.mvctoolbox.core.translation.TranslationResolver;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is the only one annotated with {@link Named}. This way the library won't pollute the
 * global EL namespace. Users will have to use this class to access other MVC Toolbox objects (i.e.
 * <code>${toolbox.messages}</code>)
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

  /**
   * Return all global messages stored in {@link #messages}.
   *
   * @return all global messages or an empty list
   */
  public List<MvcMessage> msg() {
    return getMessages().getAll().stream()
        .filter(MvcMessage::isGlobal)
        .collect(Collectors.toList());
  }

  /**
   * Return all messages for a specified param.
   *
   * @param param the name of the param. Must not be null or blank.
   * @return list of all messages for a param or an empty list
   */
  public List<MvcMessage> msg(final String param) {
    if (param == null || param.isBlank()) {
      throw new IllegalArgumentException("param must not be blank");
    }

    return messages.getMessages(param);
  }

  /**
   * Returns all {@link de.chkal.mvctoolbox.core.message.MvcMessage.Severity#ERROR} messages.
   *
   * @return list of error messages or an empty list
   */
  public List<MvcMessage> msgE() {
    return getMessages().getErrors();
  }

  /**
   * Returns all {@link de.chkal.mvctoolbox.core.message.MvcMessage.Severity#INFO} messages.
   *
   * @return list of info messages or an empty list
   */
  public List<MvcMessage> msgI() {
    return getMessages().getInfos();
  }

  /**
   * Returns all {@link de.chkal.mvctoolbox.core.message.MvcMessage.Severity#WARNING} messages.
   *
   * @return list of warning messages or an empty list
   */
  public List<MvcMessage> msgW() {
    return getMessages().getWarnings();
  }

  /**
   * Returns all {@link de.chkal.mvctoolbox.core.message.MvcMessage.Severity#SUCCESS} messages.
   *
   * @return list of all success messages or an empty list
   */
  public List<MvcMessage> msgS() {
    return getMessages().getSuccesses();
  }

  public String t(final String key) {
    return translations.resolve(key);
  }

  public String t(final String key, final List<Object> args) {
    return translations.resolve(key, args.toArray());
  }

  /**
   * Resolve translation for a key which contains placeholders going to be replaced with the array
   * parameter.
   *
   * @param key  the key of the translation. Must not be <code>null</code>
   * @param args the arguments passed to the template resolved by the key
   * @return the resolved translation or a placeholder
   * @see TranslationResolver#resolve(String, Object...)
   */
  public String t(final String key, final Object... args) {
    return translations.resolve(key, args);
  }
}
