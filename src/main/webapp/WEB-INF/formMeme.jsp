
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<html>
<head>
    <title>Formularz</title>
</head>
<body>
<% out.print("Formularz dodawania mema"); %>

<form method="post" action="/uploadmeme" enctype="multipart/form-data">
    Nazwa Mema: <input type="text" name="memeName"><br />
    <input type="file" name="file"><br />
    <input type="submit" value = "Dodaj!">
</form>

</body>
</html>