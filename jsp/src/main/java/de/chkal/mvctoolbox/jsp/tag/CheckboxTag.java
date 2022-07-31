package de.chkal.mvctoolbox.jsp.tag;

import de.chkal.mvctoolbox.jsp.ClassList;
import de.chkal.mvctoolbox.jsp.DynamicBaseTag;
import de.chkal.mvctoolbox.jsp.HtmlWriter;

import jakarta.servlet.jsp.JspException;
import java.io.IOException;

public class CheckboxTag extends DynamicBaseTag {

  private String name;

  private Boolean model;

  private String errorClass;

  @Override
  public void doTag() throws JspException, IOException {

    HtmlWriter writer = new HtmlWriter(getJspContext());

    writer.beginStartTag("input");
    writer.attribute("type", "checkbox");
    writer.attribute("name", name);

    ClassList classList = ClassList.of(getClassAttribute());
    if (hasError(name)) {
      classList.add(errorClass);
    }
    classList.write(writer);

    writeDynamicAttributes(writer, "class");

    if (model != null && model) {
      writer.attribute("checked");
    }

    Object value = getDynamicAttribute("value");
    if (value == null) {
      value = "true";
    }
    writer.attribute("value", value);

    writer.selfClose();

  }

  public void setName(String name) {
    this.name = name;
  }

  public void setModel(Boolean model) {
    this.model = model;
  }

  public void setErrorClass(String errorClass) {
    this.errorClass = errorClass;
  }
}
