<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Weather Service</title>
    <style>
        .form-item { margin: 20px 0; }
        .form-label { font-weight: bold; }
        .form-error-field { background-color: #FFC; }
        .form-error-message { font-weight: bold; color: #900; }
    </style>
</head>
<body>

<form:form commandName="zipcode">
    <div class="form-item">
        <div class="form-label">Zipcode</div>
        <form:input path="zipCode" size="5" cssErrorClass="form-error-field"/>
        <div class="form-error-message"><form:errors path="zipCode"/></div>
    </div>
    <div class="form-item">
        <input type="submit" value="Submit" />
    </div>
</form:form>

</body>
</html>