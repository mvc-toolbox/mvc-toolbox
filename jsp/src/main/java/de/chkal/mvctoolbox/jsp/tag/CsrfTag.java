package de.chkal.mvctoolbox.jsp.tag;

import de.chkal.mvctoolbox.jsp.DynamicBaseTag;
import de.chkal.mvctoolbox.jsp.HtmlWriter;
import jakarta.mvc.MvcContext;
import java.io.IOException;

/**
 * Tag for adding the CSRF token as hidden form field into a form. The resulting HTML is
 * <code>&lt;input type="hidden" name="[value of ${mvc.csrf.name}]" value="[value of
 * ${mvc.csrf.token}]/&gt;"</code>. To change the name of the parameter please check the
 * documentation of {@link jakarta.mvc.security.Csrf}.
 */
public class CsrfTag extends DynamicBaseTag {

  public static class Delegate {

    private final String name;
    private final String token;
    private HtmlWriter writer;

    public Delegate(final String name, final String token, final HtmlWriter writer) {
      this.name = name;
      this.token = token;
      this.writer = writer;
    }

    public void run() throws IOException {
      writer.beginStartTag("input")
          .attribute("type", "hidden")
          .attribute("name", name)
          .attribute("value", token)
          .selfClose();
    }
  }

  @Override
  public void doTag() throws IOException {
    final HtmlWriter writer = new HtmlWriter(getJspContext());
    doTagWithWriter(writer);
  }

  /**
   * Performs the tag rendering with the specified writer. This method is package-private to enable
   * embedding it inside other tags.
   *
   * @param writer the {@link HtmlWriter} to use
   * @return <code>null</code> in case of a {@link HtmlWriter} based on @link
   * jakarta.servlet.jsp.JspContext} or the rendered tag
   * @throws IOException in case of IO problems during tag writing
   */
  String doTagWithWriter(final HtmlWriter writer) throws IOException {
    final MvcContext mvcContext = getBean(MvcContext.class);

    new Delegate(mvcContext.getCsrf().getName(), mvcContext.getCsrf().getToken(), writer).run();

    return writer.getOutput();
  }
}
