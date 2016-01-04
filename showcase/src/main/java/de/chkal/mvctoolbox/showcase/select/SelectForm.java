package de.chkal.mvctoolbox.showcase.select;

import javax.ws.rs.FormParam;

public class SelectForm {

  @FormParam("country")
  private String country;

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

}
