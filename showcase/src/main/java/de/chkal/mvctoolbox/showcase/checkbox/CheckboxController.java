package de.chkal.mvctoolbox.showcase.checkbox;

import de.chkal.mvctoolbox.core.message.Messages;

import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Controller
@Path("/checkbox")
public class CheckboxController {

  @Inject
  private Models models;

  @Inject
  private Messages messages;

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
