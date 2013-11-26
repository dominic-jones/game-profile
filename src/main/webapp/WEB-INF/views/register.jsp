<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:useBean id="_csrf" scope="request" type="org.springframework.security.web.csrf.CsrfToken"/>
<html>
<body>
<form:form method="POST">
    <label for="username">Username</label>
    <input id="username" name="username">
    <form:errors path="username"/>

    <br/>

    <label for="password">Password</label>
    <input id="password" name="password" type="password">
    <form:errors path="password"/>

    <br/>

    <button type="submit">Register</button>
</form:form>
</body>
</html>
