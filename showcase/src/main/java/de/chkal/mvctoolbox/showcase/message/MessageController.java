package de.chkal.mvctoolbox.showcase.message;

import de.chkal.mvctoolbox.core.message.MvcMessage;
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

  @POST
  public String post() {
    messages.add(MvcMessage.Severity.INFO, "This message is preserved across a redirect!");
    messages.add(MvcMessage.Severity.WARNING, "This message is preserved across a redirect!");
    messages.add(MvcMessage.Severity.ERROR, "This message is preserved across a redirect!");
    return "redirect:/messages";
  }

}
