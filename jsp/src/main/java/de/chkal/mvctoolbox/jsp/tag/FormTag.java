package de.chkal.mvctoolbox.jsp.tag;

import de.chkal.mvctoolbox.jsp.DynamicBaseTag;
import de.chkal.mvctoolbox.jsp.HtmlWriter;
import de.chkal.mvctoolbox.jsp.tag.httpmethod.BaseFormMethodOverwriteTag;
import de.chkal.mvctoolbox.jsp.tag.httpmethod.DeleteFormMethodOverwriteTag;
import de.chkal.mvctoolbox.jsp.tag.httpmethod.PatchFormMethodOverwriteTag;
import de.chkal.mvctoolbox.jsp.tag.httpmethod.PutFormMethodOverwriteTag;
import jakarta.mvc.MvcContext;
import jakarta.servlet.jsp.JspException;
import java.io.IOException;
import java.io.StringWriter;

public class FormTag extends DynamicBaseTag {

  private static final String GET_METHOD = "get";
  private static final String POST_METHOD = "post";
  private static final String PUT_METHOD = "put";
  private static final String PATCH_METHOD = "patch";
  private static final String DELETE_METHOD = "delete";

  private String action;
  private String method;

  @Override
  public void doTag() throws JspException, IOException {

    HtmlWriter writer = new HtmlWriter(getJspContext());

    MvcContext mvcContext = getBean(MvcContext.class);

    writer.beginStartTag("form");
    writer.attribute("action", mvcContext.getBasePath() + action);
    writer.attribute("method", determineStartTagMethod(method));
    writeDynamicAttributes(writer);
    writer.endStartTag();

    getJspBody().invoke(getJspContext().getOut());

    if (isExtendedMethodRange(method)) {
      writer.write(renderFormMethodOverwriteTag(method));
    }

    writer.write(renderCsrfTag());
    writer.endTag("form");

  }

  private String determineStartTagMethod(final String method) {
    if (isWritingMethod(method)) {
      return POST_METHOD;
    } else {
      return GET_METHOD;
    }
  }

  private boolean isWritingMethod(final String method) {
    return POST_METHOD.equalsIgnoreCase(method) || isExtendedMethodRange(method);
  }

  private boolean isExtendedMethodRange(final String method) {
    return PUT_METHOD.equalsIgnoreCase(method)
        || PATCH_METHOD.equalsIgnoreCase(method)
        || DELETE_METHOD.equalsIgnoreCase(method);
  }

  private String renderFormMethodOverwriteTag(final String method) throws IOException {
    return determineFormMethodOverwriteTag(method).doTagWithWriter(
        new HtmlWriter(new StringWriter()));
  }

  private BaseFormMethodOverwriteTag determineFormMethodOverwriteTag(final String method) {
    if (PUT_METHOD.equalsIgnoreCase(method)) {
      return new PutFormMethodOverwriteTag();
    } else if (PATCH_METHOD.equalsIgnoreCase(method)) {
      return new PatchFormMethodOverwriteTag();
    } else {
      return new DeleteFormMethodOverwriteTag();
    }
  }

  private String renderCsrfTag() throws IOException {
    final HtmlWriter writer = new HtmlWriter(new StringWriter());
    return new CsrfTag().doTagWithWriter(writer);
  }

  public void setAction(String action) {
    this.action = action;
  }

  public void setMethod(final String method) {
    this.method = method;
  }
}
