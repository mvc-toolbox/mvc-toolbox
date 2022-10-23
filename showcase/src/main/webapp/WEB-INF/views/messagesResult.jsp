<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="http://chkal.de/mvc/toolbox" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>MVC Toolbox - Message support</title>
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/5.1.3/dist/css/bootstrap.css"
          rel="stylesheet">
</head>
<body>
<div class="container">

    <h1>Message support</h1>

    <h2>JSP tag &lt;t:messages /&gt;</h2>
    <t:messages grouping="true" class="list-unstyled alert"
                successClass="alert-success"
                infoClass="alert-info"
                warningClass="alert-warning"
                errorClass="alert-danger"/>
    <p>
        This JSP tag supports you by adding your messages either grouped by their severity or
        just one after another. You can style the different severities by specifying their
        CSS classes.
    </p>

    <h2>Methods Toolbox#msg</h2>

    <h3>All success messages by Toolbox#msgS</h3>
    <c:forEach items="#{toolbox.msgS()}" var="msg">
        <div class="row">
            <span class="alert alert-success">${msg}</span>
        </div>
    </c:forEach>

    <h3>All info messages by Toolbox#msgI</h3>
    <c:forEach items="#{toolbox.msgI()}" var="msg">
        <div class="row">
            <span class="alert alert-info">${msg}</span>
        </div>
    </c:forEach>

    <h3>All warning messages by Toolbox#msgW</h3>
    <c:forEach items="#{toolbox.msgW()}" var="msg">
        <div class="row">
            <span class="alert alert-warning">${msg}</span>
        </div>
    </c:forEach>

    <h3>All error messages by Toolbox#msgE</h3>
    <c:forEach items="#{toolbox.msgE()}" var="msg">
        <div class="row">
            <span class="alert alert-danger">${msg}</span>
        </div>
    </c:forEach>

    <h3>All messages bound to a param</h3>
    <c:forEach items="#{toolbox.msg('testParam')}" var="msg">
        <div class="row">
            <c:if test="${msg.severity == 'ERROR'}">
                <span class="alert alert-danger">${msg.param}: ${msg}</span>
            </c:if>
            <c:if test="${msg.severity == 'INFO'}">
                <span class="alert alert-info">${msg.param}: ${msg}</span>
            </c:if>
        </div>
    </c:forEach>

    <p style="margin-top: 10px">
        <a href="${pageContext.request.contextPath}/mvc/messages">Back to messages</a> |
        <a href="${pageContext.request.contextPath}/">Back to index</a>
    </p>
</div>
</body>
</html>