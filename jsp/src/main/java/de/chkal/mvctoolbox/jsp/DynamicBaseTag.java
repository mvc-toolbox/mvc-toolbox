package de.chkal.mvctoolbox.jsp;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.DynamicAttributes;
import java.io.IOException;
import java.util.*;

public abstract class DynamicBaseTag extends BaseTag implements DynamicAttributes {

  private final Map<String, Object> attributes = new HashMap<>();

  protected void writeDynamicAttributes(HtmlWriter writer) throws IOException {
    writeDynamicAttributes(writer, Collections.emptyList());
  }

  protected void writeDynamicAttributes(HtmlWriter writer, String... ignore) throws IOException {
    writeDynamicAttributes(writer, Arrays.asList(ignore));
  }

  protected void writeDynamicAttributes(HtmlWriter writer, List<String> ignore) throws IOException {
    for (Map.Entry<String, Object> entry : attributes.entrySet()) {
      if (!ignore.contains(entry.getKey())) {
        writer.attribute(entry.getKey(), entry.getValue());
      }
    }
  }

  @Override
  public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {
    attributes.put(localName, value);
  }

  protected Object getDynamicAttribute(String name) {
    return attributes.get(name);
  }

  protected String getDynamicStringAttribute(String name) {
    Object value = attributes.get(name);
    return value != null ? value.toString() : null;
  }

}
