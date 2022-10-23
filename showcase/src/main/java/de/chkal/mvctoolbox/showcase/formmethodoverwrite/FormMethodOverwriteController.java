package de.chkal.mvctoolbox.showcase.formmethodoverwrite;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;

@Controller
@Path("/form-method-overwrite")
@RequestScoped
public class FormMethodOverwriteController {

  private static final String RESULT_PAGE = "formMethodOverwriteResult.jsp";

  @Inject
  Models models;

  @GET
  public String index() {
    return "formMethodOverwrite.jsp";
  }

  @POST
  public String post() {
    models.put("method", "POST");
    return RESULT_PAGE;
  }

  @PUT
  public String put() {
    models.put("method", "PUT");
    return RESULT_PAGE;
  }

  @PATCH
  public String patch() {
    models.put("method", "PATCH");
    return RESULT_PAGE;
  }

  @DELETE
  public String delete() {
    models.put("method", "DELETE");
    return RESULT_PAGE;
  }
}
