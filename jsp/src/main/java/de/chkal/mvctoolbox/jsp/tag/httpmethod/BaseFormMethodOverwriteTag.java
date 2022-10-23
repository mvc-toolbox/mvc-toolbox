package de.chkal.mvctoolbox.jsp.tag.httpmethod;

import de.chkal.mvctoolbox.jsp.BaseTag;
import de.chkal.mvctoolbox.jsp.HtmlWriter;
import jakarta.mvc.MvcContext;
import jakarta.mvc.form.FormMethodOverwriter;
import java.io.IOException;

public abstract class BaseFormMethodOverwriteTag extends BaseTag {

  static class Delegate {

    private final String fieldName;
    private final String method;
    private final HtmlWriter writer;

    public Delegate(String fieldName, String method, HtmlWriter writer) {
      this.fieldName = fieldName;
      this.method = method;
      this.writer = writer;
    }

    public void run() throws IOException {
      writer.beginStartTag("input")
          .attribute("type", "hidden")
          .attribute("name", fieldName)
          .attribute("value", method)
          .selfClose();
    }
  }

  private final String method;

  protected BaseFormMethodOverwriteTag(final String method) {
    this.method = method;
  }

  @Override
  public void doTag() throws IOException {
    final HtmlWriter writer = new HtmlWriter(getJspContext());
    final MvcContext mvcContext = getBean(MvcContext.class);

    new Delegate(getFieldName(mvcContext), method, writer).run();
  }

  private String getFieldName(final MvcContext mvcContext) {
    final Object value = mvcContext.getConfig().getProperty(FormMethodOverwriter.HIDDEN_FIELD_NAME);
    if (value instanceof String) {
      return String.valueOf(value);
    }

    return FormMethodOverwriter.DEFAULT_HIDDEN_FIELD_NAME;
  }
}
