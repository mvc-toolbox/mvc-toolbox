<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="http://chkal.de/mvc/toolbox" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>MVC Toolbox - HTML Select</title>
    <link href="${mvc.contextPath}/webjars/bootstrap/3.3.6/dist/css/bootstrap.css" rel="stylesheet">
    <style>
      input.invalid {
        background-color: #f2dede !important;
      }
    </style>
  </head>
  <body>
    <div class="container">

      <h1>HTML Select</h1>

      <t:messages grouping="true" class="list-unstyled alert" infoClass="alert-success"
                  warningClass="alert-warning" errorClass="alert-danger"/>

      <t:form action="/select" method="POST" class="form-horizontal">

        <div class="form-group">
          <label for="country" class="col-sm-2 control-label">Country</label>
          <div class="col-sm-6">
            <t:select id="country" name="country" model="${form.country}"
                      class="form-control" errorClass="invalid">
              <t:options items="${countries}"/>
            </t:select>
            <span id="helpBlock" class="help-block">
              Select single string value, Germany is preselected.
            </span>
          </div>
        </div>

        <div class="form-group">
          <label for="pageSize" class="col-sm-2 control-label">Page Size</label>
          <div class="col-sm-6">
            <t:select id="pageSize" name="pageSize" model="${form.pageSize}"
                      class="form-control" errorClass="invalid">
              <t:options items="${pageSizes}"/>
            </t:select>
            <span id="helpBlock" class="help-block">
              Select single integer value, some items disabled, 25 preselected.
            </span>
          </div>
        </div>

        <div class="form-group">
          <label for="intensity" class="col-sm-2 control-label">Intensity</label>
          <div class="col-sm-6">
            <t:select id="intensity" name="intensity" model="${form.intensity}"
                      class="form-control" errorClass="invalid">
              <t:options items="${intensities}"/>
            </t:select>
            <span id="helpBlock" class="help-block">
              Select single enum value, with null value, medium preselected.
            </span>
          </div>
        </div>

        <div class="form-group">
          <label for="tags" class="col-sm-2 control-label">Tags</label>
          <div class="col-sm-6">
            <t:select id="tags" name="tags" model="${form.tags}"
                      multiple="multiple" size="5"
                      class="form-control" errorClass="invalid">
              <t:options items="${tags}"/>
            </t:select>
            <span id="helpBlock" class="help-block">
              Select multiple values, subset preselected.
            </span>
          </div>
        </div>

        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-success">Submit</button>
          </div>
        </div>

      </t:form>

      <p style="margin-top: 10px">
        <a href="${mvc.contextPath}/">Back to index</a>
      </p>

    </div>
  </body>
</html>