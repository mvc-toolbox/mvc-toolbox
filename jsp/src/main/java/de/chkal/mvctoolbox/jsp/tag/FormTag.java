package de.chkal.mvctoolbox.jsp.tag;

import de.chkal.mvctoolbox.jsp.DynamicBaseTag;
import de.chkal.mvctoolbox.jsp.HtmlWriter;
import jakarta.mvc.MvcContext;
import jakarta.servlet.jsp.JspException;
import java.io.IOException;
import java.io.StringWriter;

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

    writer.write(renderCsrfTag());
    writer.endTag("form");

  }

  private String renderCsrfTag() throws IOException {
    final HtmlWriter writer = new HtmlWriter(new StringWriter());
    return new CsrfTag().doTagWithWriter(writer);
  }

  public void setAction(String action) {
    this.action = action;
  }

}
