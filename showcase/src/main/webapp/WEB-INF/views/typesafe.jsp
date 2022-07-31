<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="http://chkal.de/mvc/toolbox" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>MVC Toolbox - Typesafe Views</title>
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/5.1.3/dist/css/bootstrap.css" rel="stylesheet">
  </head>
  <body>
    <div class="container">

      <h1>Typesafe Views</h1>

      <p>
        The controller selected this view by returning an enum.
        This is a typesafe way of referring to views.
      </p>

      <p style="margin-top: 10px">
        <a href="${pageContext.request.contextPath}/">Back to index</a>
      </p>

    </div>
  </body>
</html>