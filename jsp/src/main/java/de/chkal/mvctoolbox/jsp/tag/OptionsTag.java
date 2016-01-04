package de.chkal.mvctoolbox.jsp.tag;

import de.chkal.mvctoolbox.core.model.SelectOption;
import de.chkal.mvctoolbox.jsp.BaseTag;
import de.chkal.mvctoolbox.jsp.HtmlWriter;

import javax.servlet.jsp.JspException;
import java.io.IOException;
import java.util.List;

public class OptionsTag extends BaseTag {

  private List items;

  @Override
  public void doTag() throws JspException, IOException {

    HtmlWriter writer = new HtmlWriter(getJspContext());

    for (Object obj : items) {
      if (obj instanceof SelectOption) {
        renderOption(writer, (SelectOption) obj);
      }
    }

  }

  private void renderOption(HtmlWriter writer, SelectOption option) throws IOException {

    writer.beginStartTag("option");
    writer.attribute("value", option.getValue());
    if (isSelected(option)) {
      writer.attribute("selected");
    }
    if (option.isDisabled()) {
      writer.attribute("disabled");
    }
    writer.endStartTag();

    writer.write(option.getLabel());

    writer.endTag("option");

  }

  private boolean isSelected(SelectOption option) {

    SelectTag selectTag = (SelectTag) findAncestorWithClass(this, SelectTag.class);

    return selectTag != null && selectTag.isSelected(option.getValue());

  }

  public void setItems(List items) {
    this.items = items;
  }

}
