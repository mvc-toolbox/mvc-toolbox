package de.chkal.mvctoolbox.showcase.select;

import jakarta.ws.rs.FormParam;
import java.util.ArrayList;
import java.util.List;

public class SelectForm {

  @FormParam("country")
  private String country;

  @FormParam("pageSize")
  private Integer pageSize;

  @FormParam("intensity")
  private Intensity intensity;

  @FormParam("tags")
  private List<String> tags = new ArrayList<>();

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public Intensity getIntensity() {
    return intensity;
  }

  public void setIntensity(Intensity intensity) {
    this.intensity = intensity;
  }

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }
}
