<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="dv" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="_csrf" scope="request" type="org.springframework.security.web.csrf.CsrfToken"/>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form:form method="POST">
    <dv:textbox path="username" text="Username"/>
    <dv:password path="password" text="Password"/>
    <button type="submit">Login</button>
</form:form>
</body>
</html>
