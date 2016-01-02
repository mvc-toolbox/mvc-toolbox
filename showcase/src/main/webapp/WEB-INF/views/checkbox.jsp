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

      <tb:messages styleClass="list-unstyled alert alert-success"/>

      <tb:form action="/checkbox" method="POST">

        <div class="checkbox">
          <label>
            <tb:checkbox name="checkbox1" model="${form.checkbox1}" value="true"/>
            <code>&lt;tb:checkbox name="checkbox1" model="\${form.checkbox1}" value="true"/&gt; &lt;!-- Value: null --&gt;</code>
          </label>
        </div>
        <div class="checkbox">
          <label>
            <tb:checkbox name="checkbox2" model="${form.checkbox2}" value="true"/>
            <code>&lt;tb:checkbox name="checkbox2" model="\${form.checkbox2}" value="true"/&gt; &lt;!-- Value: true --&gt;</code>
          </label>
        </div>
        <div class="checkbox">
          <label>
            <tb:checkbox name="checkbox3" model="${form.checkbox3}" value="true"/>
            <code>&lt;tb:checkbox name="checkbox3" model="\${form.checkbox3}" value="true"/&gt; &lt;!-- Value: false --&gt;</code>
          </label>
        </div>

        <button type="submit" class="btn btn-success">Submit</button>

      </tb:form>

      <p style="margin-top: 10px">
        <a href="${mvc.contextPath}/">Back to index</a>
      </p>

    </div>
  </body>
</html>