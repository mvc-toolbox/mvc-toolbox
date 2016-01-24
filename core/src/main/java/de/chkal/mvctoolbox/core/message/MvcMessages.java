package de.chkal.mvctoolbox.core.message;

import javax.mvc.binding.BindingResult;
import java.util.List;

public interface MvcMessages {

  MvcMessages add(String text);

  MvcMessages add(MvcMessage.Severity severity, String text);

  MvcMessages add(BindingResult bindingResult);

  MvcMessages add(MvcMessage message);

  List<MvcMessage> getAll();

  List<MvcMessage> getInfos();

  List<MvcMessage> getWarnings();

  List<MvcMessage> getErrors();

  MvcMessage getMessage(String param);

  List<MvcMessage> getMessages(String param);

}
