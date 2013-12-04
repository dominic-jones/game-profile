<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:useBean id="model" scope="request" type="com.dv.game.profile.ProfileViewModel"/>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<p>You are now logged in ${model.username}.</p>

<p>Your characters are ${model.characterNames}.</p>
</body>
</html>
