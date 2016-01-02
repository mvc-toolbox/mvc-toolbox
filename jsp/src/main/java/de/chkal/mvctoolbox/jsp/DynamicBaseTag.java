package de.chkal.mvctoolbox.jsp;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.DynamicAttributes;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class DynamicBaseTag extends BaseTag implements DynamicAttributes {

  private final Map<String, Object> attributes = new HashMap<>();

  protected void writeDynamicAttributes(HtmlWriter writer) throws IOException {
    for (Map.Entry<String, Object> entry : attributes.entrySet()) {
      writer.attribute(entry.getKey(), entry.getValue());
    }
  }

  @Override
  public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {
    attributes.put(localName, value);
  }

}
