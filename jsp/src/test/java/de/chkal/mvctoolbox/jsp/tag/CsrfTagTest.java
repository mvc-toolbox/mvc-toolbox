package de.chkal.mvctoolbox.jsp.tag;

import de.chkal.mvctoolbox.jsp.HtmlWriter;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class CsrfTagTest {

	@Test
	public void doTagShouldReturnCorrectRenderedValue() throws Exception {
		final HtmlWriter writer = new HtmlWriter(new StringWriter());

		final CsrfTag.Delegate sut = new CsrfTag.Delegate("foo", "bar", writer);
		sut.run();

		assertEquals("<input type=\"hidden\" name=\"foo\" value=\"bar\"/>", writer.getOutput());
	}
}