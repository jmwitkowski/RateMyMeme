<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--Expression language jest włączony--%>
<%@ page isELIgnored="false" %>
<%--JSTL jest włączony--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>RateMyMeme</title>
    <link rel="stylesheet" type="text/css" href="css/mystyle.css">
</head>
<body>

    ${message}<br>

<button class="homepage" onclick="location.href='/'">Return to home page</button><br>
    <img src="success.jpg">
</body>
</html>