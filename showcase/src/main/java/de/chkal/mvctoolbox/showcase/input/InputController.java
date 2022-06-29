package de.chkal.mvctoolbox.showcase.input;

import de.chkal.mvctoolbox.core.message.MvcMessages;

import jakarta.inject.Inject;
import jakarta.mvc.Models;
import jakarta.mvc.Controller;
import jakarta.mvc.binding.BindingResult;
import jakarta.validation.Valid;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Controller
@Path("/input")
public class InputController {

  @Inject
  private Models models;

  @Inject
  private MvcMessages messages;

  @Inject
  private BindingResult bindingResult;

  @GET
  public String get() {

    InputForm form = new InputForm();
    form.setText1(null);
    form.setText2("Some initial value");

    models.put("form", form);
    return "input.jsp";

  }

  @POST
  public String post(@BeanParam @Valid InputForm form) {

    if (bindingResult.isFailed()) {
      messages.add(bindingResult);
      models.put("form", form);
      return "input.jsp";
    }

    messages.add("Text #1: " + form.getText1());
    messages.add("Text #2: " + form.getText2());
    return "redirect:/input";

  }

}
