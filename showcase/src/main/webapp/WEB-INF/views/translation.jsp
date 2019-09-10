<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MVC Toolbox - Translations</title>
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/dist/css/bootstrap.css" rel="stylesheet">
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
            <b>Key from EmbeddedTranslationResolver:</b> embedded.key -> ${toolbox.t('embedded.key')}
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
            <b>Key:</b> hello.withPlaceholder -> ${toolbox.t('hello.withPlaceholder', ['John', 'Doe'])}
        </div>
    </div>
</div>
</body>
</html>
