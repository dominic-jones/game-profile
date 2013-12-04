<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:useBean id="model" scope="request" type="com.dv.game.test.ProfileViewModel"/>
<%//TODO 2013-12-04 Dom - Rename view to better fit intent%>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<p>You are now logged in ${model.username}.</p>
<p>Your characters are ${model.characterNames}.</p>
<p></p>
</body>
</html>
