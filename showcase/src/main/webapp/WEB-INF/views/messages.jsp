<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>MVC Toolbox - Messages</title>
    <link href="${mvc.contextPath}/webjars/bootstrap/3.3.6/dist/css/bootstrap.css" rel="stylesheet">
  </head>
  <body>
    <div class="container">

      <h1>Message support</h1>

      <c:if test="${not empty toolbox.messages.all}">
        <div class="alert alert-success" role="alert">
          <ul class="list-unstyled">
            <c:forEach var="message" items="${toolbox.messages.all}">
              <li>${mvc.encoders.html(message.text)}</li>
            </c:forEach>
          </ul>
        </div>
      </c:if>

      <form action="${mvc.basePath}/messages" method="POST">
        <button type="submit" class="btn btn-primary">Show me a message!</button>
      </form>

      <p style="margin-top: 10px">
        <a href="${mvc.contextPath}/">Back to index</a>
      </p>

    </div>
  </body>
</html>