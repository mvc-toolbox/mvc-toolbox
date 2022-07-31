package de.chkal.mvctoolbox.showcase.typesafe;

import jakarta.mvc.Controller;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Controller
@Path("/typesafe")
public class TypesafeController {

  @GET
  public Views get() {
    return Views.VIEW2;
  }

}
