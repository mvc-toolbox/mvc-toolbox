package de.chkal.mvctoolbox.showcase.translation;

import jakarta.mvc.Controller;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/translations")
@Controller
public class TranslationController {

  @GET
  public String getTranslationIndex() {
    return "translation.jsp";
  }
}
