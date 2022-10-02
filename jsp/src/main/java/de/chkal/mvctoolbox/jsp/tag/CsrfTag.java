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

	@Override
	public void doTag() throws JspException, IOException {
		final HtmlWriter writer = new HtmlWriter(getJspContext());

		writer.beginStartTag("input")
				.attribute("type", "hidden")
				.attribute("name", getBean(MvcContext.class).getCsrf().getName())
				.attribute("value", getBean(MvcContext.class).getCsrf().getToken())
				.selfClose();
	}
}
