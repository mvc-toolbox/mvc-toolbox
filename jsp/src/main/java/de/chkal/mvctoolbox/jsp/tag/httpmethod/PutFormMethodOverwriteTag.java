package de.chkal.mvctoolbox.jsp.tag.httpmethod;

/**
 * Generates an hidden input field which can be used to overwrite the form HTTP method to <code>PUT</code>.
 * <br><br>
 * <b>OUTCOME (for MVC default):</b> <code>&lt;input type="hidden" name="_method" value="PUT"/&gt;</code>
 */
public class PutFormMethodOverwriteTag extends BaseFormMethodOverwriteTag{

  public PutFormMethodOverwriteTag() {
    super("PUT");
  }
}
