package de.chkal.mvctoolbox.jsp.tag;

import de.chkal.mvctoolbox.jsp.DynamicAttributesTag;
import de.chkal.mvctoolbox.jsp.HtmlWriter;

import javax.enterprise.inject.spi.CDI;
import javax.mvc.MvcContext;
import javax.servlet.jsp.JspException;
import java.io.IOException;

public class FormTag extends DynamicAttributesTag {

  private String action;

  @Override
  public void doTag() throws JspException, IOException {

    HtmlWriter writer = new HtmlWriter(getJspContext());

    MvcContext mvcContext = getMvcContext();

    writer.beginStartTag("form");
    writer.attribute("action", mvcContext.getBasePath() + action);
    writeDynamicAttributes(writer);
    writer.endStartTag();

    getJspBody().invoke(getJspContext().getOut());

    writer.endTag("form");

  }

  public MvcContext getMvcContext() {
    return CDI.current().select(MvcContext.class).get();
  }

  public void setAction(String action) {
    this.action = action;
  }

}
