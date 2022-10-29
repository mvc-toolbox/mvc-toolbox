package de.chkal.mvctoolbox.jsp.tag;

import de.chkal.mvctoolbox.jsp.HtmlWriter;
import jakarta.servlet.jsp.tagext.SimpleTag;
import java.io.IOException;

/**
 * Marks a class as supporting the possibility to perform the {@link SimpleTag#doTag()} method
 * functionality with specifying a custom {@link HtmlWriter}.
 */
public interface DoTagWithWriterSupporting {

  String doTagWithWriter(final HtmlWriter writer) throws IOException;
}
