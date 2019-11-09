<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--Expression language jest włączony--%>
<%@ page isELIgnored="false" %>
<%--JSTL jest włączony--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>HomePage</title>
</head>
<body>

    ${errormessage}<br>

<button class="homepage" onclick="location.href='/'">Return to home page</button><br>
<img src="somethingWentWrong.jpg">
</body>
</html>