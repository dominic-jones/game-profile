<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="dv" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="_csrf" scope="request" type="org.springframework.security.web.csrf.CsrfToken"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/global.css">
</head>

<body>
<form:form method="POST">
    <h2>Login</h2>
    <form:errors/>
    <dv:textbox path="username" text="Username" autocomplete="off" autofocus="autofocus"/>
    <dv:password path="password" text="Password"/>
    <button type="submit">Login</button>
</form:form>
</body>
</html>
