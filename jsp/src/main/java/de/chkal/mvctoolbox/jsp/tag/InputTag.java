package de.chkal.mvctoolbox.jsp.tag;

import de.chkal.mvctoolbox.jsp.ClassList;
import de.chkal.mvctoolbox.jsp.DynamicBaseTag;
import de.chkal.mvctoolbox.jsp.HtmlWriter;

import jakarta.servlet.jsp.JspException;
import java.io.IOException;

public class InputTag extends DynamicBaseTag {

  private String name;

  private Object model;

  private String errorClass;

  @Override
  public void doTag() throws JspException, IOException {

    HtmlWriter writer = new HtmlWriter(getJspContext());

    writer.beginStartTag("input");
    writer.attribute("name", name);

    ClassList classList = ClassList.of(getClassAttribute());
    if (hasError(name)) {
      classList.add(errorClass);
    }
    classList.write(writer);

    writeDynamicAttributes(writer, "class");

    if (model != null) {
      writer.attribute("value", model.toString());
    }

    writer.selfClose();

  }

  public void setName(String name) {
    this.name = name;
  }

  public void setModel(Object model) {
    this.model = model;
  }

  public void setErrorClass(String errorClass) {
    this.errorClass = errorClass;
  }

}
