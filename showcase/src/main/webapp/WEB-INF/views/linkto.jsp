<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>MVC Toolbox - LinkTo Examples</title>
  <link href="${mvc.contextPath}/webjars/bootstrap/3.3.6/dist/css/bootstrap.css" rel="stylesheet">
  <style type="text/css">
    h3 { margin-top: 30px; }
    h4 { margin-top: 25px; }
  </style>
</head>
<body>
<div class="container">

  <h1>LinkTo Support</h1>

  <h3>Given following controller:</h3>

  <pre><code>
package de.chkal.mvctoolbox.showcase.linkto;

@Controller
@Path("linkto")
public class LinkToController {

    @GET
    @Path("target")
    @LinkTarget("some-target")
    public String target() {
        return "linkto.jsp";
    }

    @GET
    public String root() {
        return "linkto.jsp";
    }

    @GET
    @Path("path/{p1}/{p2}")
    public String pathParams(@PathParam("p1") String p1, @PathParam("p2") long p2) {
        return "linkto.jsp";
    }

    @GET
    @Path("query")
    public String queryParams(@QueryParam("q1") String q1, @QueryParam("q2") long q2) {
        return "linkto.jsp";
    }

    @GET
    @Path("matrix")
    public String matrixParams(@MatrixParam("m1") String m1, @MatrixParam("m2") long m2) {
        return "linkto.jsp";
    }

    @GET
    @Path("bean/{p}")
    public String beanParams(@BeanParam BeanParams params) {
        return "linkto.jsp";
    }

    static class BeanParams {

        @PathParam("p")
        private String p;

        @MatrixParam("m")
        private long m;

        @QueryParam("q")
        private String q;

    }

}
  </code></pre>

  <h3>Usage from a Template</h3>

  <h4>Using the @LinkTo annotation</h4>
  <pre>\${toolbox.linkTo.target('some-target')}</pre>
  <span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span>
  <samp>${toolbox.linkTo.target('some-target')}</samp>

  <h4>Using controller and method name</h4>
  <pre>\${toolbox.linkTo.method('LinkToController', 'root')}</pre>
  <span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span>
  <samp>${toolbox.linkTo.method('LinkToController', 'root')}</samp>
  
  <h4>Using the fully qualified controller and method name</h4>
  <pre>\${toolbox.linkTo.method('de.chkal.mvctoolbox.showcase.linkto.LinkToController', 'root')}</pre>
  <span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span>
  <samp>${toolbox.linkTo.method('de.chkal.mvctoolbox.showcase.linkto.LinkToController', 'root')}</samp>

  <h4>Using path parameters with the List syntax</h4>
  <pre>\${toolbox.linkTo.method('LinkToController', 'pathParams', [42, 'baz'])}</pre>
  <span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span>
  <samp>${toolbox.linkTo.method('LinkToController', 'pathParams', [42, 'baz'])}</samp>

  <h4>Using path parameters with the Map syntax</h4>
  <pre>\${toolbox.linkTo.method('LinkToController', 'pathParams', {'p1': 'baz', 'p2': 4711})}</pre>
  <span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span>
  <samp>${toolbox.linkTo.method('LinkToController', 'pathParams', {'p1': 'baz', 'p2': 4711})}</samp>

  <h4>Using query parameters</h4>
  <pre>\${toolbox.linkTo.method('LinkToController', 'queryParams', {'q1': 'foo', 'q2': 42})}</pre>
  <span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span>
  <samp>${toolbox.linkTo.method('LinkToController', 'queryParams', {'q1': 'foo', 'q2': 42})}</samp>

  <h4>Using matrix parameters</h4>
  <pre>\${toolbox.linkTo.method('LinkToController', 'matrixParams', {'m1': 'foo', 'm2': 42})}</pre>
  <span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span>
  <samp>${toolbox.linkTo.method('LinkToController', 'matrixParams', {'m1': 'foo', 'm2': 42})}</samp>

  <h4>Using bean parameters</h4>
  <pre>\${toolbox.linkTo.method('LinkToController', 'beanParams', {'p': 'foo', 'm': 42, 'q': 'bar'})}</pre>
  <span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span>
  <samp>${toolbox.linkTo.method('LinkToController', 'beanParams', {'p': 'foo', 'm': 42, 'q': 'bar'})}</samp>

  <h3>Usage from a controller</h3>

  <pre><code>
@Controller
@Path("some-path")
public class SomeOtherController {

    @Inject
    private LinkTo linkTo;

    @Inject
    private Models models;

    public String root() {
        models.put("link-1", linkTo.builderFor("some-target").param("foo", "bar").param("baz", 42).build());
        models.put("link-2", linkTo.builderFor("LinkToController", "root").param("foo", "bar").param("baz", 42).build());
        models.put("link-3", linkTo.builderFor(LinkToController.class, "root").param("foo", "bar").param("baz", 42).build());
        return "some-view.jsp";
    }

}
  </code></pre>

  <p style="margin-top: 10px">
    <a href="${mvc.contextPath}/">Back to index</a>
  </p>

</div>

</body>
</html>
