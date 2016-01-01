package de.chkal.mvctoolbox.showcase.checkbox;

import javax.mvc.annotation.Controller;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Controller
@Path("/checkbox")
public class CheckboxController {

  @GET
  public String get() {
    return "checkbox.jsp";
  }

}
