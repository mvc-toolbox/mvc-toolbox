package de.chkal.mvctoolbox.core.message;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a single message which is meant to be displayed to the user. Every message consists
 * of a human readable text and a severity. Optionally a message can be associated with a parameter.
 * This is typically useful for validation errors which always relate to a specific parameter. If
 * the message is not associated with a parameter, the message is called a global message.
 *
 * @author Christian Kaltepoth
 */
public class MvcMessage implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * The severity of the message
   */
  public enum Severity {
    INFO,
    WARNING,
    ERROR,
    SUCCESS;
  }

  private final Severity severity;
  private final String param;
  private final String text;

  /**
   * Creates a new global info message.
   *
   * @param text The text of the message, must not be null
   * @return a new {@link MvcMessage} instance
   */
  public static MvcMessage info(String text) {
    return info(null, text);
  }

  /**
   * Creates a new info message referring to the given parameter.
   *
   * @param param The name of parameter this message refers to or null
   * @param text  The text of the message, must not be null
   * @return a new {@link MvcMessage} instance
   */
  public static MvcMessage info(String param, String text) {
    return new MvcMessage(Severity.INFO, param, text);
  }

  /**
   * Creates a new global warning message.
   *
   * @param text The text of the message, must not be null
   * @return a new {@link MvcMessage} instance
   */
  public static MvcMessage warning(String text) {
    return warning(null, text);
  }

  /**
   * Creates a new warning message referring to the given parameter.
   *
   * @param param The name of parameter this message refers to or null
   * @param text  The text of the message, must not be null
   * @return a new {@link MvcMessage} instance
   */
  public static MvcMessage warning(String param, String text) {
    return new MvcMessage(Severity.WARNING, param, text);
  }

  /**
   * Creates a new global error message.
   *
   * @param text The text of the message, must not be null
   * @return a new {@link MvcMessage} instance
   */
  public static MvcMessage error(String text) {
    return error(null, text);
  }

  /**
   * Creates a new error message referring to the given parameter.
   *
   * @param param The name of parameter this message refers to or null
   * @param text  The text of the message, must not be null
   * @return a new {@link MvcMessage} instance
   */
  public static MvcMessage error(String param, String text) {
    return new MvcMessage(Severity.ERROR, param, text);
  }

  /**
   * Creates a new global success message.
   *
   * @param text the text to be displayed
   * @return a new {@link MvcMessage} instance
   */
  public static MvcMessage success(final String text) {
    return new MvcMessage(Severity.SUCCESS, text);
  }

  /**
   * Creates a new success message referring to the given parameter.
   *
   * @param param The name of parameter this message refers to or null
   * @param text  The text of the message, must not be null
   * @return a new {@link MvcMessage} instance
   */
  public static MvcMessage success(String param, String text) {
    return new MvcMessage(Severity.SUCCESS, param, text);
  }

  /**
   * Creates a new global message with a severity of INFO.
   *
   * @param text The text of the message, must not be null
   */
  public MvcMessage(String text) {
    this(Severity.INFO, text);
  }

  /**
   * Creates a new global message with the given severity.
   *
   * @param severity The severity of the message, must not be null
   * @param text     The text of the message, must not be null
   */
  public MvcMessage(Severity severity, String text) {
    this(severity, null, text);
  }

  /**
   * Creates a new message referring to the given parameter.
   *
   * @param severity The severity of the message, must not be null
   * @param param    The name of parameter this message refers to or null
   * @param text     The text of the message, must not be null
   */
  public MvcMessage(Severity severity, String param, String text) {
    this.severity = Objects.requireNonNull(severity, "Severity must not be null");
    this.text = Objects.requireNonNull(text, "Text must not be null");
    this.param = param; // optional
  }

  /**
   * Returns the severity of of the message
   *
   * @return the severity of the message
   */
  public Severity getSeverity() {
    return severity;
  }

  /**
   * Returns the parameter the message is associated with or null for global messages
   *
   * @return the parameter or null
   */
  public String getParam() {
    return param;
  }

  /**
   * Returns the text of the message
   *
   * @return the text of the message
   */
  public String getText() {
    return text;
  }

  /**
   * Returns true for global messages which is equivalent to param being null.
   *
   * @return whether or not the message is global
   */
  public boolean isGlobal() {
    return param == null;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final MvcMessage that = (MvcMessage) o;
    return severity == that.severity && Objects.equals(param, that.param) && Objects.equals(text, that.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(severity, param, text);
  }

  @Override
  public String toString() {
    return getText();
  }
}
