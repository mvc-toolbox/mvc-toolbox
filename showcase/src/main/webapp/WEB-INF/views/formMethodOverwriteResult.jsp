<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="http://chkal.de/mvc/toolbox" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>MVC Toolbox - Form method overwrite result</title>
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/5.1.3/dist/css/bootstrap.css"
          rel="stylesheet">
</head>
<body>
<div class="container">

    <h1>Form method overwrite - Result</h1>

    <div class="row">
        <div class="alert alert-info">You performed a ${method} request!</div>
    </div>

    <div class="row">
        <p style="margin-top: 10px">
            <a href="${pageContext.request.contextPath}/mvc/form-method-overwrite">Back to Form
                method overwrite</a> |
            <a href="${pageContext.request.contextPath}/">Back to index</a>
        </p>
    </div>

</div>
</body>
</html>