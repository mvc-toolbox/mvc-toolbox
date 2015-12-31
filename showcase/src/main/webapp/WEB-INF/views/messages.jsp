<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>MVC Toolbox - Messages</title>
  </head>
  <body>

    <c:if test="${not empty toolbox.messages.all}">
      <ul>
        <c:forEach var="message" items="${toolbox.messages.all}">
          <li>${mvc.encoders.html(message.text)}</li>
        </c:forEach>
      </ul>
    </c:if>

    <form action="${mvc.basePath}/messages" method="POST">
      <button type="submit">Show me a message!</button>
    </form>

  </body>
</html>