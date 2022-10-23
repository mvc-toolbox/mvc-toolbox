package de.chkal.mvctoolbox.showcase.translation;

import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/translations")
@Controller
public class TranslationController {

  @Inject
  Models models;

  @GET
  public String getTranslationIndex() {
    models.put("surname", "Doe");
    return "translation.jsp";
  }
}
