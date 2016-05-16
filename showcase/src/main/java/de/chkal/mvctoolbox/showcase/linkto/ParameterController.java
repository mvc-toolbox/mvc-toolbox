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
@Path("parameters")
public class ParameterController {

    @GET
    @Path("path/{p1}/{p2}")
    @LinkTarget("path-params")
    public String pathParams(@PathParam("p1") String p1, @PathParam("p2") long p2) {
        return "linkto.jsp";
    }

    @GET
    @Path("query")
    @LinkTarget("query-params")
    public String queryParams(@QueryParam("q1") String q1, @QueryParam("q2") long q2) {
        return "linkto.jsp";
    }

    @GET
    @Path("matrix")
    @LinkTarget("matrix-params")
    public String matrixParams(@MatrixParam("m1") String m1, @MatrixParam("m2") long m2) {
        return "linkto.jsp";
    }

    @GET
    @Path("bean/{p}")
    @LinkTarget("bean-params")
    public String beanParams(@BeanParam BeanParams params) {
        return "linkto.jsp";
    }

    static class BeanParams {

        @PathParam("p")
        private String p;

        @QueryParam("q")
        private String q;

        @MatrixParam("m")
        private long m;

    }

}
