package de.chkal.mvctoolbox.showcase.checkbox;

import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Controller
@Path("/checkbox")
public class CheckboxController {

  @Inject
  private Models models;

  @GET
  public String get() {

    CheckboxForm form = new CheckboxForm();
    form.setCheckbox1(null);
    form.setCheckbox2(false);
    form.setCheckbox3(true);

    models.put("form", form);
    return "checkbox.jsp";

  }

}
