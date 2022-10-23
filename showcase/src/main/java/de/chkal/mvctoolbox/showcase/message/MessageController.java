package de.chkal.mvctoolbox.showcase.message;

import de.chkal.mvctoolbox.core.message.MvcMessage;
import de.chkal.mvctoolbox.core.message.MvcMessage.Severity;
import de.chkal.mvctoolbox.core.message.MvcMessages;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Controller
@Path("/messages")
public class MessageController {

  @Inject
  private MvcMessages messages;

  @GET
  public String get() {
    return "messages.jsp";
  }

  @GET
  @Path("/result")
  public String getResult() {
    return "messagesResult.jsp";
  }

  @POST
  public String post() {
    messages.add(MvcMessage.Severity.INFO, "This INFO message is preserved across a redirect!");
    messages.add(MvcMessage.Severity.WARNING, "This WARNING message is preserved across a redirect!");
    messages.add(MvcMessage.Severity.ERROR, "This ERROR message is preserved across a redirect!");
    messages.add(Severity.SUCCESS, "This SUCCESS message is preserved across a redirect!");

    messages.add(MvcMessage.info("testParam", "This INFO is bound to a param"));
    messages.add(MvcMessage.error("testParam", "This ERROR message is bound to the param too"));

    return "redirect:/messages/result";
  }

}
