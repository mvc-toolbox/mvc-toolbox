package de.chkal.mvctoolbox.showcase.checkbox;

import de.chkal.mvctoolbox.core.message.MvcMessages;

import jakarta.inject.Inject;
import jakarta.mvc.Models;
import jakarta.mvc.Controller;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Controller
@Path("/checkbox")
public class CheckboxController {

  @Inject
  private Models models;

  @Inject
  private MvcMessages messages;

  @GET
  public String get() {

    CheckboxForm form = new CheckboxForm();
    form.setCheckbox1(null);
    form.setCheckbox2(false);
    form.setCheckbox3(true);

    models.put("form", form);
    return "checkbox.jsp";

  }

  @POST
  public String post(@BeanParam CheckboxForm form) {
    messages.add("Checkbox #1: " + Boolean.TRUE.equals(form.getCheckbox1()));
    messages.add("Checkbox #2: " + Boolean.TRUE.equals(form.getCheckbox2()));
    messages.add("Checkbox #3: " + Boolean.TRUE.equals(form.getCheckbox3()));
    return "redirect:/checkbox";
  }

}
