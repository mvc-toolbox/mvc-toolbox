package de.chkal.mvctoolbox.showcase.linkto;

import de.chkal.mvctoolbox.core.linkto.api.LinkTarget;

import javax.mvc.annotation.Controller;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

/**
 * @author Florian Hirsch
 */
@Controller
@Path("list-params")
public class ListParameterController {

    @GET
    @Path("path/{p1}/{p2}")
    @LinkTarget("list-params")
    public String pathParams(@PathParam("p1") String p1, @PathParam("p2") long p2) {
        return "linkto.jsp";
    }

}
