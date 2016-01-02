package de.chkal.mvctoolbox.jsp.tag;

import de.chkal.mvctoolbox.core.message.Message;
import de.chkal.mvctoolbox.core.message.Messages;
import de.chkal.mvctoolbox.jsp.BaseTag;
import de.chkal.mvctoolbox.jsp.HtmlWriter;

import javax.servlet.jsp.JspException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MessagesTag extends BaseTag {

  private boolean grouping;

  private String infoClass;

  private String styleClass;

  private String warningClass;

  private String errorClass;

  @Override
  public void doTag() throws JspException, IOException {

    HtmlWriter writer = new HtmlWriter(getJspContext());

    Messages messages = getBean(Messages.class);

    if (grouping) {
      renderList(writer, messages.getInfos(), infoClass);
      renderList(writer, messages.getWarnings(), warningClass);
      renderList(writer, messages.getErrors(), errorClass);
    } else {
      renderList(writer, messages.getAll(), null);
    }

  }

  private void renderList(HtmlWriter writer, List<Message> messages, String listClass)
      throws IOException {

    if (!messages.isEmpty()) {

      writer.beginStartTag("ul");

      List<String> classes = new ArrayList<>();
      if (styleClass != null && styleClass.trim().length() > 0) {
        classes.add(styleClass);
      }
      if (listClass != null && listClass.trim().length() > 0) {
        classes.add(listClass);
      }
      if (!classes.isEmpty()) {
        writer.attribute("class", classes.stream().collect(Collectors.joining(" ")));
      }


      writer.endStartTag();

      for (Message message : messages) {
        renderListEntry(writer, message);
      }

      writer.endTag("ul");

    }
  }

  private void renderListEntry(HtmlWriter writer, Message message) throws IOException {
    writer.beginStartTag("li").endStartTag();
    writer.write(message.getText());
    writer.endTag("li");
  }

  public void setGrouping(boolean grouping) {
    this.grouping = grouping;
  }

  public void setStyleClass(String styleClass) {
    this.styleClass = styleClass;
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
