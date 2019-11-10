<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--Expression language jest włączony--%>
<%@ page isELIgnored="false" %>
<%--JSTL jest włączony--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<html>
<head>
    <title>HomePage</title>
</head>
<body>

<security:authorize access="!hasAnyAuthority('USER', 'ADMIN')">
<form name='loginForm' action="<c:url value='/' />" method='POST'>
    <table>
        <tr>
            <td>User:</td>
            <td><input class= type='text' name='login' required></td>

            <td>Password:</td>
            <td><input type='password' name='password' required></td>

            <td colspan='2'><input  name="submit" type="submit" value="submit" /></td>
        </tr>
    </table>
</form>
Only logged users can add memes!

<button class="register" onclick="location.href='/register'">Register</button>
</security:authorize><br>

<security:authorize access="hasAnyAuthority('USER', 'ADMIN')">
    <button class="logout" onclick="location.href='/logout'">Logout</button><br>
    Hi ${activeUserName}<br>
    <button class="addMeme" onclick="location.href='/formMeme'">Add meme</button><br>
</security:authorize><br>



<c:forEach var="m" items="${memes}">
<div align="center">
    ${m.nameMeme} <br />
    ${m.dateUpload.toString().replace("T", " ")}<br>

<%--    <img src="${m.sourceAdress}"><br>--%>

    <p>
        <a href="/meme${m.id}">
            <img border="0" alt="${m.nameMeme}" src="${m.sourceAdress}" width="600" height="">
        </a>
    </p>
</div>
</c:forEach>




</body>
</html>