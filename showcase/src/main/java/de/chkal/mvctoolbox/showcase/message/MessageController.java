package de.chkal.mvctoolbox.showcase.message;

import de.chkal.mvctoolbox.core.message.Message;
import de.chkal.mvctoolbox.core.message.Messages;

import javax.inject.Inject;
import javax.mvc.annotation.Controller;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Controller
@Path("/messages")
public class MessageController {

  @Inject
  private Messages messages;

  @GET
  public String get() {
    return "messages.jsp";
  }

  @POST
  public String post() {
    messages.add(Message.Severity.INFO, "This message is preserved across a redirect!");
    messages.add(Message.Severity.WARNING, "This message is preserved across a redirect!");
    messages.add(Message.Severity.ERROR, "This message is preserved across a redirect!");
    return "redirect:/messages";
  }

}
