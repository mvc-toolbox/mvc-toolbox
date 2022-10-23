package de.chkal.mvctoolbox.jsp.tag.httpmethod;

import static org.junit.Assert.assertEquals;

import de.chkal.mvctoolbox.jsp.HtmlWriter;
import de.chkal.mvctoolbox.jsp.tag.httpmethod.BaseFormMethodOverwriteTag.Delegate;
import java.io.StringWriter;
import org.junit.Test;

public class BaseFormMethodOverwriteTagTest {

  @Test
  public void delegateShouldRenderCorrectTag() throws Exception {
    final HtmlWriter writer = new HtmlWriter(new StringWriter());

    final Delegate sut = new Delegate("_method", "DELETE", writer);
    sut.run();

    assertEquals("<input type=\"hidden\" name=\"_method\" value=\"DELETE\"/>", writer.getOutput());
  }
}