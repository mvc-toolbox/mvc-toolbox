<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="http://chkal.de/mvc/toolbox" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>MVC Toolbox - Form method overwrite</title>
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/5.1.3/dist/css/bootstrap.css"
          rel="stylesheet">
</head>
<body>
<div class="container">

    <h1>Form method overwrite</h1>

    <p>
        This page demonstrates how the HTTP method of a form can be overwritten by using
        the MVC <code>form method overwrite</code> feature and MVC Toolbox JSP tags.
    </p>

    <h2>Normal POST form</h2>
    <t:form action="/form-method-overwrite" method="POST" class="form-horizontal">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-success">Submit POST</button>
        </div>
    </t:form>

    <h2>PUT form</h2>
    <t:form action="/form-method-overwrite" method="POST" class="form-horizontal">
        <t:methodPut/>
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-success">Submit POST</button>
        </div>
    </t:form>

    <h2>PATCH form</h2>
    <t:form action="/form-method-overwrite" method="POST" class="form-horizontal">
        <t:methodPatch/>
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-success">Submit POST</button>
        </div>
    </t:form>

    <h2>DELETE form</h2>
    <t:form action="/form-method-overwrite" method="POST" class="form-horizontal">
        <t:methodDelete/>
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-success">Submit POST</button>
        </div>
    </t:form>

    <p style="margin-top: 10px">
        <a href="${pageContext.request.contextPath}/">Back to index</a>
    </p>

</div>
</body>
</html>