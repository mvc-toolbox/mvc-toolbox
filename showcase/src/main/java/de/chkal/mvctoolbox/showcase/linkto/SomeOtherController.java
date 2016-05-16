package de.chkal.mvctoolbox.showcase.linkto;

import de.chkal.mvctoolbox.core.linkto.api.LinkTo;

import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.net.URI;

/**
 * @author Florian Hirsch
 */
@Controller
@Path("some-path")
public class SomeOtherController {

	@Inject
	private LinkTo linkTo;

	@GET
	public String root() {

		URI link1 = linkTo.builderFor("some-target")
				.param("foo", "bar")
				.param("baz", 42)
				.build();

		URI link2 = linkTo.builderFor("LinkToController", "root")
				.param("foo", "bar")
				.param("baz", 42)
				.build();

		URI link3 = linkTo.builderFor(LinkToController.class, "root")
				.param("foo", "bar")
				.param("baz", 42)
				.build();

		return "some-view.jsp";
	}

}