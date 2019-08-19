package de.chkal.mvctoolbox.showcase.translation;

import javax.mvc.Controller;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/translations")
@Controller
public class TranslationController {

  @GET
  public String getTranslationIndex() {
    return "translation.jsp";
  }
}
