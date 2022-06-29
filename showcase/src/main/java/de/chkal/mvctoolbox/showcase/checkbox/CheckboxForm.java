package de.chkal.mvctoolbox.showcase.checkbox;

import jakarta.ws.rs.FormParam;

public class CheckboxForm {

  @FormParam("checkbox1")
  private Boolean checkbox1;

  @FormParam("checkbox2")
  private Boolean checkbox2;

  @FormParam("checkbox3")
  private Boolean checkbox3;

  public Boolean getCheckbox1() {
    return checkbox1;
  }

  public void setCheckbox1(Boolean checkbox1) {
    this.checkbox1 = checkbox1;
  }

  public Boolean getCheckbox2() {
    return checkbox2;
  }

  public void setCheckbox2(Boolean checkbox2) {
    this.checkbox2 = checkbox2;
  }

  public Boolean getCheckbox3() {
    return checkbox3;
  }

  public void setCheckbox3(Boolean checkbox3) {
    this.checkbox3 = checkbox3;
  }
}
