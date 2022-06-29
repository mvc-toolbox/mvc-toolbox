package de.chkal.mvctoolbox.showcase.redirect;

import jakarta.ws.rs.FormParam;

public class RedirectForm {

  @FormParam("param1")
  private String param1;

  @FormParam("param2")
  private String param2;

  public String getParam1() {
    return param1;
  }

  public void setParam1(String param1) {
    this.param1 = param1;
  }

  public String getParam2() {
    return param2;
  }

  public void setParam2(String param2) {
    this.param2 = param2;
  }
}
