package de.chkal.mvctoolbox.core.message;

import javax.mvc.annotation.RedirectScoped;
import javax.mvc.binding.BindingResult;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.ws.rs.FormParam;

@RedirectScoped
public class MvcMessagesImpl implements Serializable, MvcMessages {

  private static final long serialVersionUID = 1L;

  private final List<MvcMessage> messages = new ArrayList<>();

  @Override
  public MvcMessages add(String text) {
    return add(new MvcMessage(text));
  }

  @Override
  public MvcMessages add(MvcMessage.Severity severity, String text) {
    return add(new MvcMessage(severity, text));
  }

  @Override
  public MvcMessages add(BindingResult bindingResult) {

    bindingResult.getAllBindingErrors().stream()
        .map(e -> new MvcMessage(MvcMessage.Severity.ERROR, e.getParamName(), e.getMessage()))
        .forEach(this::add);

    bindingResult.getAllViolations().stream()
        .forEach(v -> {

          final String path = v.getPropertyPath().toString();
          String param = path.substring(path.lastIndexOf('.') + 1);

          try {
            String annotatedParam = v.getLeafBean().getClass().getDeclaredField(param).getAnnotation(FormParam.class).value();
            if (annotatedParam != null && !annotatedParam.isEmpty()) {
              param = annotatedParam;
            }
          } catch (NoSuchFieldException e) {
            // What to do here?
            // Ignore?
          }

          this.add(new MvcMessage(MvcMessage.Severity.ERROR, param, v.getMessage()));
        });

    return this;
  }

  @Override
  public MvcMessages add(MvcMessage message) {
    Objects.requireNonNull(message, "Message must not be null");
    messages.add(message);
    return this;
  }

  @Override
  public List<MvcMessage> getAll() {
    return Collections.unmodifiableList(messages);
  }

  @Override
  public List<MvcMessage> getInfos() {
    return messages.stream()
        .filter(m -> m.getSeverity() == MvcMessage.Severity.INFO)
        .collect(Collectors.toList());
  }

  @Override
  public List<MvcMessage> getWarnings() {
    return messages.stream()
        .filter(m -> m.getSeverity() == MvcMessage.Severity.WARNING)
        .collect(Collectors.toList());
  }

  @Override
  public List<MvcMessage> getErrors() {
    return messages.stream()
        .filter(m -> m.getSeverity() == MvcMessage.Severity.ERROR)
        .collect(Collectors.toList());
  }

  @Override
  public MvcMessage getMessage(String param) {
    return messages.stream()
        .filter(m -> Objects.equals(param, m.getParam()))
        .findFirst().orElse(null);
  }

  @Override
  public List<MvcMessage> getMessages(String param) {
    return messages.stream()
        .filter(m -> Objects.equals(param, m.getParam()))
        .collect(Collectors.toList());
  }

}
