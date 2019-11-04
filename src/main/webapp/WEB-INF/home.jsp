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

<c:forEach var="m" items="${memes}">

    ${m.nameMeme}<br>
    ${m.dateUpload}<br>

    <img src="${m.sourceAdress}"><br>

</c:forEach>




</body>
</html>