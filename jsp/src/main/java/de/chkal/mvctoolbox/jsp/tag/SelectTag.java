package de.chkal.mvctoolbox.jsp.tag;

import de.chkal.mvctoolbox.jsp.ClassList;
import de.chkal.mvctoolbox.jsp.DynamicBaseTag;
import de.chkal.mvctoolbox.jsp.HtmlWriter;

import javax.servlet.jsp.JspException;
import java.io.IOException;
import java.util.Objects;

public class SelectTag extends DynamicBaseTag {

  private String name;

  private Object model;

  private String errorClass;

  @Override
  public void doTag() throws JspException, IOException {

    HtmlWriter writer = new HtmlWriter(getJspContext());

    writer.beginStartTag("select");
    writer.attribute("name", name);

    ClassList classList = ClassList.of(getClassAttribute());
    if (hasError(name)) {
      classList.add(errorClass);
    }
    classList.write(writer);

    writeDynamicAttributes(writer, "class");

    writer.endStartTag();

    getJspBody().invoke(getJspContext().getOut());

    writer.endTag("select");

  }

  protected boolean isSelected(Object value) {
    return Objects.equals(value, model);
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
