package de.chkal.mvctoolbox.core.message;

import jakarta.mvc.binding.BindingResult;
import java.util.List;

/**
 * <p>Represents a list of messages to be displayed to the user.</p>
 *
 * <p>Instances implementing this interface must be injectable using
 * {@link jakarta.inject.Inject} and are {@link jakarta.mvc.RedirectScoped}.</p>
 */
public interface MvcMessages {

  /**
   * Adds a new global info message.
   *
   * @param text The text of the message, must not be <code>null</code>
   * @return self
   */
  MvcMessages add(String text);

  /**
   * Adds a new global message with the given severity.
   *
   * @param severity The severity of the message, must not be <code>null</code>
   * @param text     The text of the message, must not be <code>null</code>
   * @return self
   */
  MvcMessages add(MvcMessage.Severity severity, String text);

  /**
   * This method adds one error message for each binding error or
   * constraint violation in the given binding result.
   *
   * @param bindingResult The binding result to create messages from, ust not be <code>null</code>
   * @return self
   */
  MvcMessages add(BindingResult bindingResult);

  /**
   * Adds the given message.
   *
   * @param message the message
   * @return self
   */
  MvcMessages add(MvcMessage message);

  /**
   * Returns a list of all messages
   *
   * @return all messages
   */
  List<MvcMessage> getAll();

  /**
   * Returns a list of all messages with a severity of INFO.
   *
   * @return all info messages
   */
  List<MvcMessage> getInfos();

  /**
   * Returns a list of all messages with a severity of WARNING.
   *
   * @return all warning messages
   */
  List<MvcMessage> getWarnings();

  /**
   * Returns a list of all messages with a severity of ERROR.
   *
   * @return all error messages
   */
  List<MvcMessage> getErrors();

  /**
   * If there is at least one message queued for the given parameter,
   * the first one is returned. Will return <code>null</code> if no message for the parameter
   * exists.
   *
   * @param param The parameter name
   * @return The first message for the parameter or <code>null</code>
   * @see #getMessages(String)
   */
  MvcMessage getMessage(String param);

  /**
   * Returns a list of all messages queued for the given parameter.
   *
   * @param param The parameter name
   * @return All message for the given parameter
   * @see #getMessage(String)
   */
  List<MvcMessage> getMessages(String param);

}
