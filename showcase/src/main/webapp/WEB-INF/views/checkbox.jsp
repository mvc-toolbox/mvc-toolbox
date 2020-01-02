<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="http://chkal.de/mvc/toolbox" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>MVC Toolbox - HTML Checkbox</title>
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/dist/css/bootstrap.css" rel="stylesheet">
  </head>
  <body>
    <div class="container">

      <h1>HTML Checkbox</h1>

      <t:messages class="list-unstyled alert alert-success"/>

      <t:form action="/checkbox" method="POST">

        <div class="checkbox">
          <label>
            <t:checkbox name="checkbox1" model="${form.checkbox1}"/>
            <code>&lt;tb:checkbox name="checkbox1" model="\${form.checkbox1}"/&gt; &lt;!-- Value: null --&gt;</code>
          </label>
        </div>
        <div class="checkbox">
          <label>
            <t:checkbox name="checkbox2" model="${form.checkbox2}"/>
            <code>&lt;tb:checkbox name="checkbox2" model="\${form.checkbox2}"/&gt; &lt;!-- Value: true --&gt;</code>
          </label>
        </div>
        <div class="checkbox">
          <label>
            <t:checkbox name="checkbox3" model="${form.checkbox3}"/>
            <code>&lt;tb:checkbox name="checkbox3" model="\${form.checkbox3}"/&gt; &lt;!-- Value: false --&gt;</code>
          </label>
        </div>

        <button type="submit" class="btn btn-success">Submit</button>

      </t:form>

      <p style="margin-top: 10px">
        <a href="${pageContext.request.contextPath}/">Back to index</a>
      </p>

    </div>
  </body>
</html>