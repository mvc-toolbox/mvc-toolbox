package de.chkal.mvctoolbox.jsp.tag.httpmethod;

/**
 * Generates an hidden input field which can be used to overwrite the form HTTP method to <code>PATCH</code>.
 * <br><br>
 * <b>OUTCOME (for MVC default):</b> <code>&lt;input type="hidden" name="_method" value="PATCH"/&gt;</code>
 */
public class PatchFormMethodOverwriteTag extends BaseFormMethodOverwriteTag{

  public PatchFormMethodOverwriteTag() {
    super("PATCH");
  }
}
