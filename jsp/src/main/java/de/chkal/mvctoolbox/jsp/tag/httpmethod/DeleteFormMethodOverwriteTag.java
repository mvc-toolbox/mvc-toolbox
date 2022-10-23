package de.chkal.mvctoolbox.jsp.tag.httpmethod;

/**
 * Generates an hidden input field which can be used to overwrite the form HTTP method to <code>DELETE</code>.
 * <br><br>
 * <b>OUTCOME (for MVC default):</b> <code>&lt;input type="hidden" name="_method" value="DELETE"/&gt;</code>
 */
public class DeleteFormMethodOverwriteTag extends BaseFormMethodOverwriteTag{

  public DeleteFormMethodOverwriteTag() {
    super("DELETE");
  }
}
