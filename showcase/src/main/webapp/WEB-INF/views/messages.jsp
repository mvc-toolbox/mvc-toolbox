<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="http://chkal.de/mvc/toolbox" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>MVC Toolbox - Message support</title>
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/dist/css/bootstrap.css" rel="stylesheet">
  </head>
  <body>
    <div class="container">

      <h1>Message support</h1>

      <t:messages grouping="true" class="list-unstyled alert" infoClass="alert-success"
                   warningClass="alert-warning" errorClass="alert-danger"/>

      <t:form action="/messages" method="POST">
        <button type="submit" class="btn btn-primary">Show me some messages!</button>
      </t:form>

      <p style="margin-top: 10px">
        <a href="${pageContext.request.contextPath}/">Back to index</a>
      </p>

    </div>
  </body>
</html>