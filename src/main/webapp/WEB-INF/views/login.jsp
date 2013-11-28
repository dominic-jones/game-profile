<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="dv" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="_csrf" scope="request" type="org.springframework.security.web.csrf.CsrfToken"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>

<body>
<div id="login-form">
    <h2>Login</h2>
    <form:form method="POST" cssClass="login-form">
        <dv:textbox path="username" text="Username"/>
        <dv:password path="password" text="Password"/>
        <button type="submit">Login</button>
    </form:form>
</div>
</body>
</html>
