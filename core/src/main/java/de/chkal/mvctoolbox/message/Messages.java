package de.chkal.mvctoolbox.message;

import javax.mvc.annotation.RedirectScoped;
import javax.mvc.binding.BindingResult;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RedirectScoped
public class Messages implements Serializable {

  private static final long serialVersionUID = 1L;

  private final List<Message> messages = new ArrayList<>();

  public Messages add(String text) {
    return add(new Message(text));
  }

  public Messages add(Message.Severity severity, String text) {
    return add(new Message(severity, text));
  }

  public Messages add(BindingResult bindingResult) {

    bindingResult.getAllBindingErrors().stream()
        .map(e -> new Message(Message.Severity.ERROR, e.getParamName(), e.getMessage()))
        .forEach(this::add);

    bindingResult.getAllViolations().stream()
        .map(v -> new Message(Message.Severity.ERROR, null, v.getMessage()))
        .forEach(this::add);

    return this;
  }

  public Messages add(Message message) {
    Objects.requireNonNull(message, "Message must not be null");
    messages.add(message);
    return this;
  }

  public List<Message> getAll() {
    return Collections.unmodifiableList(messages);
  }

  public List<Message> getInfos() {
    return messages.stream()
        .filter(m -> m.getSeverity() == Message.Severity.INFO)
        .collect(Collectors.toList());
  }

  public List<Message> getWarnings() {
    return messages.stream()
        .filter(m -> m.getSeverity() == Message.Severity.WARNING)
        .collect(Collectors.toList());
  }

  public List<Message> getErrors() {
    return messages.stream()
        .filter(m -> m.getSeverity() == Message.Severity.ERROR)
        .collect(Collectors.toList());
  }

}
