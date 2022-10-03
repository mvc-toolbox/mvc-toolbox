package de.chkal.mvctoolbox.jsp;

import jakarta.servlet.jsp.JspContext;
import jakarta.servlet.jsp.JspWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class HtmlWriter {

  private final Writer writer;

  public HtmlWriter(JspContext context) {
    this(context.getOut());
  }

  public HtmlWriter(JspWriter writer) {
    this.writer = writer;
  }

  public HtmlWriter(final StringWriter writer) {
    this.writer = writer;
  }

  public HtmlWriter beginStartTag(String name) throws IOException {
    writer.write("<");
    writer.write(name);
    return this;
  }

  public HtmlWriter attribute(String name) throws IOException {
    return attribute(name, name);
  }

  public HtmlWriter attribute(String name, Object value) throws IOException {
    writer.write(" ");
    writer.write(name);
    writer.write("=\"");
    if (value != null) {
      writer.write(value.toString());
    }
    writer.write("\"");
    return this;
  }

  public HtmlWriter selfClose() throws IOException {
    writer.write("/>");
    return this;
  }

  public HtmlWriter endStartTag() throws IOException {
    writer.write(">");
    return this;
  }

  public HtmlWriter endTag(String name) throws IOException {
    writer.write("</");
    writer.write(name);
    writer.write(">");
    return this;
  }

  public HtmlWriter write(String text) throws IOException {
    writer.write(text);
    return this;
  }

  /**
   * Returns the output of the {@link #writer} if possible.
   *
   * @return the output as String when {@link #writer} is a {@link StringWriter} or <code>null</code> in any other case
   */
  public String getOutput() {
    if (writer instanceof StringWriter) {
      return writer.toString();
    } else {
      return null;
    }
  }
}
