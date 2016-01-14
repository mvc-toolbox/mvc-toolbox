package de.chkal.mvctoolbox.showcase.select;

import de.chkal.mvctoolbox.core.message.MvcMessages;
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
import java.util.ArrayList;
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
  private MvcMessages messages;

  @Inject
  private BindingResult bindingResult;

  @GET
  public String get() {

    List<SelectOption> countries = Arrays.stream(Locale.getISOCountries())
        .map(iso -> new SelectOption(iso, getCountryName(iso)))
        .sorted((a, b) -> a.getLabel().compareTo(b.getLabel()))
        .collect(Collectors.toList());
    models.put("countries", countries);

    List<SelectOption> pageSizes = Arrays.asList(10, 15, 25, 50, 100, 200, 500).stream()
        .map(i -> new SelectOption(i, String.valueOf(i), i > 100))
        .collect(Collectors.toList());
    models.put("pageSizes", pageSizes);

    List<SelectOption> intensities = new ArrayList<>();
    intensities.add(new SelectOption(null, "-"));
    intensities.add(new SelectOption(Intensity.LOW, "low"));
    intensities.add(new SelectOption(Intensity.MEDIUM, "medium"));
    intensities.add(new SelectOption(Intensity.HIGH, "high"));
    models.put("intensities", intensities);

    List<SelectOption> tags = Arrays.asList("java", "php", "mvc", "web", "javafx").stream()
        .map(tag -> new SelectOption(tag, tag))
        .collect(Collectors.toList());
    models.put("tags", tags);

    SelectForm form = new SelectForm();
    form.setCountry("DE");
    form.setPageSize(25);
    form.setIntensity(Intensity.MEDIUM);
    form.setTags(Arrays.asList("java", "web", "mvc"));
    models.put("form", form);

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
    messages.add("Page size: " + form.getPageSize());
    messages.add("Intensity: " + form.getIntensity());
    messages.add("Tags: " + form.getTags().stream().collect(Collectors.joining(", ")));
    return "redirect:/select";

  }

  private String getCountryName(String iso) {
    return new Locale(iso, iso).getDisplayCountry(Locale.ENGLISH);
  }

}
