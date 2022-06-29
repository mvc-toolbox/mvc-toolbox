package de.chkal.mvctoolbox.jsp;

import jakarta.servlet.jsp.JspContext;
import jakarta.servlet.jsp.JspWriter;
import java.io.IOException;

public class HtmlWriter {

  private final JspWriter writer;

  public HtmlWriter(JspContext context) {
    this(context.getOut());
  }

  public HtmlWriter(JspWriter writer) {
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
}
