package de.chkal.mvctoolbox.showcase.typesafe;

import javax.mvc.Controller;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Controller
@Path("/typesafe")
public class TypesafeController {

  @GET
  public Views get() {
    return Views.VIEW2;
  }

}
