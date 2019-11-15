
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<html>
<head>
    <title>RateMyMeme</title>
    <link rel="stylesheet" type="text/css" href="../css/mystyle.css">
</head>
<body>
<% out.print("Adding meme form"); %>

<form method="post" action="/uploadmeme" enctype="multipart/form-data">
    Name of Meme: <input type="text" name="memeName" required><br />
    <input type="file"  class=" submit" name="file" accept="image/x-png,image/gif,image/jpeg" required><br />
    <input type="submit" class="submit" value = "Add!">
</form>
<button class="homepage" onclick="location.href='/'">Return to home page</button>

</body>
</html>