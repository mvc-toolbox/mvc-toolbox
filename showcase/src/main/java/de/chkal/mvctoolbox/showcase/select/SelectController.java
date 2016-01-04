package de.chkal.mvctoolbox.showcase.select;

import de.chkal.mvctoolbox.core.message.Messages;
import de.chkal.mvctoolbox.core.model.SelectOption;

import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.mvc.binding.BindingResult;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@Path("/select")
public class SelectController {

  @Inject
  private Models models;

  @Inject
  private Messages messages;

  @Inject
  private BindingResult bindingResult;

  @GET
  public String get() {

    SelectForm form = new SelectForm();
    form.setCountry("DE");
    models.put("form", form);

    List<SelectOption> countries = Arrays.stream(Locale.getISOCountries())
        .map(iso -> new SelectOption(iso, new Locale(iso, iso).getDisplayCountry(Locale.ENGLISH)))
        .sorted((a, b) -> a.getLabel().compareTo(b.getLabel()))
        .collect(Collectors.toList());
    models.put("countries", countries);

    return "select.jsp";

  }

  @POST
  public String post(@BeanParam @Valid SelectForm form) {

    if (bindingResult.isFailed()) {
      messages.add(bindingResult);
      models.put("form", form);
      return "select.jsp";
    }

    messages.add("Country: " + form.getCountry());
    return "redirect:/select";

  }

}
