<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="http://chkal.de/mvc/toolbox" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>MVC Toolbox - Redirect support</title>
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/5.1.3/dist/css/bootstrap.css" rel="stylesheet">
  </head>
  <body>
    <div class="container">

      <h1>Redirect support</h1>

      <t:form action="/redirect/${param1}" method="POST" class="form-horizontal">
        <div class="form-group">
          <label class="col-sm-4 control-label">Path parameter from URL</label>
          <div class="col-sm-8">
            <p class="form-control-static">${mvc.encoders.html(param1)}</p>
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-4 control-label">Query parameter from url</label>
          <div class="col-sm-8">
            <p class="form-control-static">${mvc.encoders.html(param2)}</p>
          </div>
        </div>
        <div class="form-group">
          <label for="param1" class="col-sm-4 control-label">New value for path paramter</label>
          <div class="col-sm-8">
            <t:input id="param1" name="param1" class="form-control"/>
          </div>
        </div>
        <div class="form-group">
          <label for="param1" class="col-sm-4 control-label">New value for query parameter</label>
          <div class="col-sm-8">
            <t:input id="param2" name="param2" class="form-control"/>
          </div>
        </div>
        <div class="form-group">
          <div class="col-sm-offset-4 col-sm-8">
            <button type="submit" class="btn btn-default">Submit</button>
          </div>
        </div>
      </t:form>

      <p style="margin-top: 10px">
        <a href="${pageContext.request.contextPath}/">Back to index</a>
      </p>

    </div>
  </body>
</html>