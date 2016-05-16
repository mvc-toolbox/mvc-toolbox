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
    p.note { margin-top: 10px; }
  </style>
</head>
<body>
<div class="container">

  <h1>LinkTo Support</h1>

  <p>
    Hardcoding URIs in a template can be awkward.
    The MVC Toolbox provides the LinkTo functionality to generate URIs from <code>@Path</code>, <code>@PathParam</code>, <code>@QueryParam</code> and <code>@MatrixParam</code> annotations.
    <code>LinkTo</code> can be used from a template but also provides a builder which can be used from Java code.
  </p>

  <h2>Simple URLs without parameters</h2>

  <p>Given following controller:</p>

<pre><code>@Controller
@Path("linkto")
public class LinkToController {

    @GET
    @LinkTarget("some-target")
    public String root() {
        return "linkto.jsp";
    }

}</code></pre>

  <p>A controller method to build the URI for can be referenced in three different ways.</p>

  <p>
    First you can use the <a href="https://github.com/chkal/mvc-toolbox/blob/master/core/src/main/java/de/chkal/mvctoolbox/core/linkto/api/LinkTarget.java">@LinkTarget</a>
    annotation to define a symbolic name for a controller method.
    To do so just place the annotation on the controller method and specify the name in the value attribute as shown in the controller above.
  </p>

  <p>Now you can generate the corresponding URI using a simple EL expression like this:</p>

  <pre>\${toolbox.linkTo.target('some-target')}<br />&lt!-- ${toolbox.linkTo.target('some-target')} --&gt</pre>

  <p class="note"><strong>Note:</strong> You should use <code>@LinkTarget</code> only on one method if you have the same URI-template but multiple controller methods for different HTTP verbs.</p>

  <p>
    But you don't have to use <code>@LinkTarget</code> to use the LinkTo feature.
    You can also reference a controller method with the simple class name of your controller and the method name:
  </p>

  <pre>\${toolbox.linkTo.method('LinkToController', 'root')}<br />&lt!-- ${toolbox.linkTo.method('LinkToController', 'root')} --&gt;</pre>

  <p>Please note that this only works if the simple class name is not ambiguous. Otherwise you have to use the FQCN of your controller instead:</p>

  <pre>\${toolbox.linkTo.method('de.chkal.mvctoolbox.showcase.linkto.LinkToController', 'root')}<br />&lt!-- ${toolbox.linkTo.method('de.chkal.mvctoolbox.showcase.linkto.LinkToController', 'root')} --&gt;</pre>

  <h2>Specifying parameters</h2>

  <p>
    The first section described how to link to controller methods without any parameters.
    But in real world application most controller methods will use parameters which you need to specify when creating the URI.
  </p>

  <p>See the following controller as an example:</p>

<pre><code>@Controller
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

}</code></pre>

  <p>
    To set path parameters when building an URI, you have to provide a map containing the values for each parameter.
    Please note that you will have to set all path parameters because path parameters are required for the URI:
  </p>

  <pre>\${toolbox.linkTo.target('path-params', {'p1': 'baz', 'p2': 4711})}<br />&lt!-- ${toolbox.linkTo.target('path-params', {'p1': 'baz', 'p2': 4711})} --&gt;</pre>

  <p>You can also set query parameter values using the same map:</p>

  <pre>\${toolbox.linkTo.target('query-params', {'q1': 'foo', 'q2': 42})}<br />&lt!-- ${toolbox.linkTo.target('query-params', {'q1': 'foo', 'q2': 42})} --&gt;</pre>

  <p>And also matrix parameters:</p>
  <pre>\${toolbox.linkTo.target('matrix-params', {'m1': 'foo', 'm2': 42})}<br />&lt!-- ${toolbox.linkTo.target('matrix-params', {'m1': 'foo', 'm2': 42})} --&gt;</pre>

  <p>
    The type of the parameter is automatically infered from the corresponding annotations on your controller.
    This means you can just specify the values by name and you don't have to care about the type of the parameter.
    This also works with <code>@BeanParam</code> parameters.
  </p>

  <pre>\${toolbox.linkTo.target('bean-params', {'p': 'foo', 'm': 42, 'q': 'bar'})}<br />&lt!-- ${toolbox.linkTo.target('bean-params', {'p': 'foo', 'm': 42, 'q': 'bar'})} --&gt;</pre>

  <h2>Specifying parameters as lists</h2>

  <p>Sometimes you only need path-parameters with a given order. For convenience you can also use the following list syntax.</p>

  <p>Given following controller:</p>

<pre><code>@Controller
@Path("list-params")
public class PathParameterController {

    @GET
    @Path("path/{p1}/{p2}")
    @LinkTarget("list-params")
    public String pathParams(@PathParam("p1") String p1, @PathParam("p2") long p2) {
        return "linkto.jsp";
    }

}</code></pre>

  <p>Instead of using the verbose map syntax, you can simply specify the path parameter values as a list:</p>

  <pre>\${toolbox.linkTo.target('list-params', [42, 'baz'])}<br />&lt!-- ${toolbox.linkTo.target('list-params', [42, 'baz'])} --&gt;</pre>

  <p>Please note that this syntax can only be used if there are only path parameters in the URI-template.</p>

  <h2>Building URIs programmatically</h2>

  <p>
    Sometimes it might be necessary to build a URI programmatically.
    The MVC Toolbox provides a builder with a very similar API:
  </p>

<pre><code>@Controller
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

}</code></pre>

  <p style="margin-top: 10px">
    <a href="${mvc.contextPath}/">Back to index</a>
  </p>

</div>

</body>
</html>
