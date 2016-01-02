<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tb" uri="http://chkal.de/mvc/toolbox" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>MVC Toolbox - Message support</title>
    <link href="${mvc.contextPath}/webjars/bootstrap/3.3.6/dist/css/bootstrap.css" rel="stylesheet">
  </head>
  <body>
    <div class="container">

      <h1>Message support</h1>

      <tb:messages grouping="true" styleClass="list-unstyled alert" infoClass="alert-success"
                   warningClass="alert-warning" errorClass="alert-danger"/>

      <form action="${mvc.basePath}/messages" method="POST">
        <button type="submit" class="btn btn-primary">Show me some messages!</button>
      </form>

      <p style="margin-top: 10px">
        <a href="${mvc.contextPath}/">Back to index</a>
      </p>

    </div>
  </body>
</html>