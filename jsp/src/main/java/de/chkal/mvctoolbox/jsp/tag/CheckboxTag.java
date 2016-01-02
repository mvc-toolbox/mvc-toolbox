package de.chkal.mvctoolbox.jsp.tag;

import de.chkal.mvctoolbox.jsp.HtmlWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class CheckboxTag extends SimpleTagSupport implements DynamicAttributes {

  private final Map<String, Object> attributes = new TreeMap<>();

  private Boolean model;

  @Override
  public void doTag() throws JspException, IOException {

    HtmlWriter writer = new HtmlWriter(getJspContext());

    writer.beginStartTag("input");
    writer.attribute("type", "checkbox");

    for (Map.Entry<String, Object> entry : attributes.entrySet()) {
      writer.attribute(entry.getKey(), entry.getValue());
    }

    if (model != null && model) {
      writer.attribute("checked");
    }

    writer.selfClose();

  }

  @Override
  public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {
    attributes.put(localName, value);
  }

  public void setModel(Boolean model) {
    this.model = model;
  }

}
