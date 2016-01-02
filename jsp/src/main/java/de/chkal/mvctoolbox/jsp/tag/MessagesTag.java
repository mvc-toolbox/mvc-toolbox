package de.chkal.mvctoolbox.jsp.tag;

import de.chkal.mvctoolbox.core.message.Message;
import de.chkal.mvctoolbox.core.message.Messages;
import de.chkal.mvctoolbox.jsp.DynamicAttributesTag;
import de.chkal.mvctoolbox.jsp.HtmlWriter;

import javax.enterprise.inject.spi.CDI;
import javax.servlet.jsp.JspException;
import java.io.IOException;
import java.util.List;

public class MessagesTag extends DynamicAttributesTag {

  @Override
  public void doTag() throws JspException, IOException {

    HtmlWriter writer = new HtmlWriter(getJspContext());

    Messages messages = getMessages();

    renderList(writer, messages.getInfos());
    renderList(writer, messages.getWarnings());
    renderList(writer, messages.getErrors());

  }

  private void renderList(HtmlWriter writer, List<Message> messages) throws IOException {
    if (!messages.isEmpty()) {
      writer.beginStartTag("ul").endStartTag();
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

  public Messages getMessages() {
    return CDI.current().select(Messages.class).get();
  }

}
