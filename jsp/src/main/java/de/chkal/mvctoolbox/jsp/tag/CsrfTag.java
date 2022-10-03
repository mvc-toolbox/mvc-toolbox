package de.chkal.mvctoolbox.jsp.tag;

import de.chkal.mvctoolbox.jsp.DynamicBaseTag;
import de.chkal.mvctoolbox.jsp.HtmlWriter;
import jakarta.mvc.MvcContext;
import jakarta.servlet.jsp.JspException;

import java.io.IOException;

/**
 * Tag for adding the CSRF token as hidden form field into a form. The resulting HTML is
 * <code>&lt;input type="hidden" name="[value of ${mvc.csrf.name}]" value="[value of ${mvc.csrf.token}]/&gt;"</code>.
 * To change the name of the parameter please check the documentation of {@link jakarta.mvc.security.Csrf}.
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
	public void doTag() throws JspException, IOException {
		final HtmlWriter writer = new HtmlWriter(getJspContext());
		final MvcContext mvcContext = getBean(MvcContext.class);

		new Delegate(mvcContext.getCsrf().getName(), mvcContext.getCsrf().getToken(), writer).run();
	}
}
