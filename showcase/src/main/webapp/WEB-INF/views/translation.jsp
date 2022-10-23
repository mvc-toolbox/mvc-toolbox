<%@ taglib prefix="t" uri="http://chkal.de/mvc/toolbox" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MVC Toolbox - Translations</title>
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/5.1.3/dist/css/bootstrap.css"
          rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h1>Translations</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            On this page, the showcase provides translations for following locales:

            <ul>
                <li>Locale.GERMAN</li>
            </ul>

            and english as default.
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <h2>Used locale: ${mvc.locale}</h2>
        </div>
    </div>

    <div class="row">
        <div class="col-md-6">
            <b>Key:</b> greeting -> ${toolbox.t('greeting')}
        </div>
        <div class="col-md-6">
            <b>Key:</b> hello.world -> ${toolbox.t('hello.world')}
        </div>
        <div class="col-md-6">
            <b>Key from EmbeddedTranslationResolver:</b> embedded.key
            -> ${embedded.resolve('embedded.key')}
        </div>
        <div class="col-md-6">
            <b>Unknown key:</b> unknown.key -> ${toolbox.t('unknown.key')}
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <h3>Usage of MessageFormat templates</h3>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <p>
                This translation makes use of the java.text.MessageFormat templates, which
                leads to properties like <i>hello.withPlaceholder=Hello, {0} {1}!</i>
            </p>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <b>Key:</b> hello.withPlaceholder
            -> ${toolbox.t('hello.withPlaceholder', ['John', 'Doe'])}
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <h3>Usage of custom properties producer</h3>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <b>Key:</b> hello.withPlaceholder -> ${page1.resolve("hello")}
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <h2>Used locale: ${mvc.locale}</h2>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <h2>Using &lt;t:text/&gt; tag</h2>
        </div>
    </div>

    <div class="row">
        <div class="col-md-6">
            <b>Key: hello.world</b> <t:text key="hello.world"/>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <b>Key: hello.withPlaceholder</b> <t:text key="hello.withPlaceholder"
                                                      vars="${['John', surname]}"/>
        </div>
    </div>

    <div class="row">
        <div class="col-md-6">
            <b>Key (embedded resolver): embedded.key</b>
            <t:text key="embedded.key" specializedResolver="${embedded}"/>
        </div>
    </div>
</div>
</body>
</html>
