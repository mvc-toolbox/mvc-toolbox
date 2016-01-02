package de.chkal.mvctoolbox.jsp.tag;

import de.chkal.mvctoolbox.jsp.DynamicAttributesTag;
import de.chkal.mvctoolbox.jsp.HtmlWriter;

import javax.servlet.jsp.JspException;
import java.io.IOException;

public class CheckboxTag extends DynamicAttributesTag {

  private Boolean model;

  @Override
  public void doTag() throws JspException, IOException {

    HtmlWriter writer = new HtmlWriter(getJspContext());

    writer.beginStartTag("input");
    writer.attribute("type", "checkbox");

    writeDynamicAttributes(writer);

    if (model != null && model) {
      writer.attribute("checked");
    }

    writer.selfClose();

  }

  public void setModel(Boolean model) {
    this.model = model;
  }

}
