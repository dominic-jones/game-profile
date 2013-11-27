<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:useBean id="username" scope="request" type="java.lang.String"/>
<html>
<head>
    <title>Test</title>
</head>
<body>
<p>You are now logged in ${username}</p>
</body>
</html>
