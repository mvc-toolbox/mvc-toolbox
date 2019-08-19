package de.chkal.mvctoolbox.showcase.input;

import javax.validation.constraints.Size;

import javax.ws.rs.FormParam;

public class InputForm {

  @FormParam("text1")
  @Size(min = 5, message = "Text #1: Please enter at least 5 characters")
  private String text1;

  @FormParam("text2")
  @Size(min = 5, message = "Text #2: Please enter at least 5 characters")
  private String text2;

  public String getText1() {
    return text1;
  }

  public void setText1(String text1) {
    this.text1 = text1;
  }

  public String getText2() {
    return text2;
  }

  public void setText2(String text2) {
    this.text2 = text2;
  }

}
