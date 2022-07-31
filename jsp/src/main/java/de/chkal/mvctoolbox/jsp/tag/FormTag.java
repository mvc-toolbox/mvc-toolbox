package de.chkal.mvctoolbox.jsp.tag;

import de.chkal.mvctoolbox.jsp.DynamicBaseTag;
import de.chkal.mvctoolbox.jsp.HtmlWriter;

import jakarta.mvc.MvcContext;
import jakarta.servlet.jsp.JspException;
import java.io.IOException;

public class FormTag extends DynamicBaseTag {

  private String action;

  @Override
  public void doTag() throws JspException, IOException {

    HtmlWriter writer = new HtmlWriter(getJspContext());

    MvcContext mvcContext = getBean(MvcContext.class);

    writer.beginStartTag("form");
    writer.attribute("action", mvcContext.getBasePath() + action);
    writeDynamicAttributes(writer);
    writer.endStartTag();

    getJspBody().invoke(getJspContext().getOut());

    writer.endTag("form");

  }

  public void setAction(String action) {
    this.action = action;
  }

}
