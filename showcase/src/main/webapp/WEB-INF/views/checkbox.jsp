<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tb" uri="http://chkal.de/mvc/toolbox" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>MVC Toolbox - HTML Checkbox</title>
    <link href="${mvc.contextPath}/webjars/bootstrap/3.3.6/dist/css/bootstrap.css" rel="stylesheet">
  </head>
  <body>
    <div class="container">

      <h1>HTML Checkbox</h1>

      <form>
        <div class="checkbox">
          <label>
            <tb:checkbox/>
            <code>&lt;tb:checkbox/&gt;</code>
          </label>
        </div>
        <div class="checkbox">
          <label>
            <tb:checkbox model="${null}"/>
            <code>&lt;tb:checkbox model="\${null}"/&gt;</code>
          </label>
        </div>
        <div class="checkbox">
          <label>
            <tb:checkbox model="${false}"/>
            <code>&lt;tb:checkbox model="\${false}"/&gt;</code>
          </label>
        </div>
        <div class="checkbox">
          <label>
            <tb:checkbox model="${true}"/>
            <code>&lt;tb:checkbox model="\${true}"/&gt;</code>
          </label>
        </div>
      </form>

      <p style="margin-top: 10px">
        <a href="${mvc.contextPath}/">Back to index</a>
      </p>

    </div>
  </body>
</html>