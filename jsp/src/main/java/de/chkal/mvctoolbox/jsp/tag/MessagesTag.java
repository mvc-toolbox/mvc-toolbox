package de.chkal.mvctoolbox.jsp.tag;

import de.chkal.mvctoolbox.core.message.Message;
import de.chkal.mvctoolbox.core.message.Messages;
import de.chkal.mvctoolbox.jsp.ClassList;
import de.chkal.mvctoolbox.jsp.DynamicBaseTag;
import de.chkal.mvctoolbox.jsp.HtmlWriter;

import javax.servlet.jsp.JspException;
import java.io.IOException;
import java.util.List;

public class MessagesTag extends DynamicBaseTag {

  private boolean grouping;

  private String infoClass;

  private String warningClass;

  private String errorClass;

  @Override
  public void doTag() throws JspException, IOException {

    HtmlWriter writer = new HtmlWriter(getJspContext());

    Messages messages = getBean(Messages.class);

    if (grouping) {
      renderList(writer, messages.getInfos(), infoClass, false);
      renderList(writer, messages.getWarnings(), warningClass, false);
      renderList(writer, messages.getErrors(), errorClass, false);
    } else {
      renderList(writer, messages.getAll(), null, true);
    }

  }

  private void renderList(HtmlWriter writer, List<Message> messages, String listClass,
                          boolean styleListEntries) throws IOException {

    if (!messages.isEmpty()) {

      writer.beginStartTag("ul");

      ClassList.of(getClassAttribute(), listClass).write(writer);

      writer.endStartTag();

      for (Message message : messages) {
        renderListEntry(writer, message, styleListEntries);
      }

      writer.endTag("ul");

    }
  }

  private void renderListEntry(HtmlWriter writer, Message message, boolean addSeverityClass)
      throws IOException {

    writer.beginStartTag("li");
    if (addSeverityClass) {
      ClassList.of(getSeverityClass(message.getSeverity())).write(writer);
    }
    writer.endStartTag();

    writer.write(message.getText());

    writer.endTag("li");

  }

  private String getSeverityClass(Message.Severity severity) {
    switch (severity) {
      case INFO:
        return infoClass;
      case WARNING:
        return warningClass;
      case ERROR:
        return errorClass;
    }
    return null;
  }

  public void setGrouping(boolean grouping) {
    this.grouping = grouping;
  }

  public void setInfoClass(String infoClass) {
    this.infoClass = infoClass;
  }

  public void setWarningClass(String warningClass) {
    this.warningClass = warningClass;
  }

  public void setErrorClass(String errorClass) {
    this.errorClass = errorClass;
  }

}
