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
<form name='loginForm' action="<c:url value='/' />" method='POST'>
    <table>
        <tr>
            <td>User:</td>
            <td><input class= type='text' name='login'></td>

            <td>Password:</td>
            <td><input type='password' name='password' /></td>

            <td colspan='2'><input  name="submit" type="submit" value="submit" /></td>
        </tr>
    </table>
</form>

<button class="register" onclick="location.href='/register'">Register</button>
<button class="addMeme" onclick="location.href='/formMeme'">Add meme</button><br>


<c:forEach var="m" items="${memes}">

    ${m.nameMeme}<br>
    ${m.dateUpload}<br>

    <img src="${m.sourceAdress}"><br>

</c:forEach>




</body>
</html>