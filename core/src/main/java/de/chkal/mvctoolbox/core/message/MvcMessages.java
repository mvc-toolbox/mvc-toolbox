package de.chkal.mvctoolbox.core.message;

import javax.mvc.annotation.RedirectScoped;
import javax.mvc.binding.BindingResult;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RedirectScoped
public class MvcMessages implements Serializable {

  private static final long serialVersionUID = 1L;

  private final List<MvcMessage> messages = new ArrayList<>();

  public MvcMessages add(String text) {
    return add(new MvcMessage(text));
  }

  public MvcMessages add(MvcMessage.Severity severity, String text) {
    return add(new MvcMessage(severity, text));
  }

  public MvcMessages add(BindingResult bindingResult) {

    bindingResult.getAllBindingErrors().stream()
        .map(e -> new MvcMessage(MvcMessage.Severity.ERROR, e.getParamName(), e.getMessage()))
        .forEach(this::add);

    bindingResult.getAllViolations().stream()
        .map(v -> new MvcMessage(MvcMessage.Severity.ERROR, null, v.getMessage()))
        .forEach(this::add);

    return this;
  }

  public MvcMessages add(MvcMessage message) {
    Objects.requireNonNull(message, "Message must not be null");
    messages.add(message);
    return this;
  }

  public List<MvcMessage> getAll() {
    return Collections.unmodifiableList(messages);
  }

  public List<MvcMessage> getInfos() {
    return messages.stream()
        .filter(m -> m.getSeverity() == MvcMessage.Severity.INFO)
        .collect(Collectors.toList());
  }

  public List<MvcMessage> getWarnings() {
    return messages.stream()
        .filter(m -> m.getSeverity() == MvcMessage.Severity.WARNING)
        .collect(Collectors.toList());
  }

  public List<MvcMessage> getErrors() {
    return messages.stream()
        .filter(m -> m.getSeverity() == MvcMessage.Severity.ERROR)
        .collect(Collectors.toList());
  }

  public MvcMessage getMessage(String param) {
    return messages.stream()
        .filter(m -> Objects.equals(param, m.getParam()))
        .findFirst().orElse(null);
  }

  public List<MvcMessage> getMessages(String param) {
    return messages.stream()
        .filter(m -> Objects.equals(param, m.getParam()))
        .collect(Collectors.toList());
  }

}
