<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--Expression language jest włączony--%>
<%@ page isELIgnored="false" %>
<%--JSTL jest włączony--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>RateMyMeme</title>
    <link rel="stylesheet" type="text/css" href="../css/mystyle.css">
</head>
<body>
<div class="sidebar"><a href="/"> <img src="../LOGO.jpg" height="100" align="right"> </a></div>
<div class="errorcontainer">
    ${errormessage}<br>

    <button class="homepage" onclick="location.href='/'">Return to home page</button>
    <br>
    <img src="../somethingWentWrong.jpg">
</div>
</body>
</html>