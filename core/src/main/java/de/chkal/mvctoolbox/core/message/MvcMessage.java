package de.chkal.mvctoolbox.core.message;

import java.io.Serializable;
import java.util.Objects;

public class MvcMessage implements Serializable {

  private static final long serialVersionUID = 1L;

  public enum Severity {
    INFO,
    WARNING,
    ERROR
  }

  private final Severity severity;
  private final String param;
  private final String text;

  public MvcMessage(String text) {
    this(Severity.INFO, text);
  }

  public MvcMessage(Severity severity, String text) {
    this(severity, null, text);
  }

  public MvcMessage(Severity severity, String param, String text) {
    this.severity = Objects.requireNonNull(severity, "Severity must not be null");
    this.text = Objects.requireNonNull(text, "Text must not be null");
    this.param = param; // optional
  }

  public Severity getSeverity() {
    return severity;
  }

  public String getParam() {
    return param;
  }

  public String getText() {
    return text;
  }
}
