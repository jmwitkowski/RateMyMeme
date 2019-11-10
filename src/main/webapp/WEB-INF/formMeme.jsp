
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<html>
<head>
    <title>RateMyMeme</title>
</head>
<body>
<% out.print("Adding meme form"); %>

<form method="post" action="/uploadmeme" enctype="multipart/form-data">
    Nazwa Mema: <input type="text" name="memeName" required><br />
    <input type="file" name="file" required><br />
    <input type="submit" value = "Add!">
</form>

</body>
</html>