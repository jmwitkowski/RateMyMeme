<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--Expression language jest włączony--%>
<%@ page isELIgnored="false" %>
<%--JSTL jest włączony--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<html>
<head>
    <title>HomePage</title>
    <link rel="stylesheet" type="text/css" href="css/mystyle.css">
</head>
<body>

<security:authorize access="!hasAnyAuthority('USER', 'ADMIN')">
    <form name='loginForm' action="<c:url value='/' />" method='POST'>
        <table>
            <tr>
                <td>User:</td>
                <td><input class="" type='text' name='login' required></td>

                <td>Password:</td>
                <td><input type='password' name='password' required></td>

                <td colspan='2'><input class="submit" name="submit" type="submit" value="Sign In"/></td>
            </tr>
        </table>
    </form>
    Only logged users can add memes and vote!

    <button class="register" onclick="location.href='/register'">Register</button>
</security:authorize><br>

<security:authorize access="hasAnyAuthority('USER', 'ADMIN')">
    <button class="logout" onclick="location.href='/logout'">Logout</button>
    <br>
    Hi ${activeUserName}<br>
    <button class="addMeme" onclick="location.href='/formMeme'">Add meme</button>
    <br>
</security:authorize><br>


<c:forEach var="m" items="${memes}">
    <div align="center">
        <a href="/meme/${m.meme.id}">${m.meme.nameMeme}</a><br>
            ${m.meme.dateUpload.toString().replace("T", " ")}<br>
        <p>
            <a href="/meme/${m.meme.id}">
                <img src="../${m.meme.sourceAdress}" width="600" height="">
            </a>
            <security:authorize access="hasAnyAuthority('ADMIN')">
                <button class="dislike" type="button" name="" onclick="location.href='/delete/${m.meme.id}'">Del</button>
            </security:authorize><br>
                Likes: ${m.meme.receivedPluses}
            <security:authorize access="hasAnyAuthority('USER', 'ADMIN')">
                <c:if test="${!m.votingUsers.contains(activeUserName)}">
                    <button type="button" name="like" onclick="location.href='/votePlus/${m.meme.id}'">Like!</button>
                </c:if>
            </security:authorize>
                Dislikes: ${m.meme.receivedMinuses}
            <security:authorize access="hasAnyAuthority('USER', 'ADMIN')">
                <c:if test="${!m.votingUsers.contains(activeUserName)}">
                    <button class="dislike" type="button" name="dislike"
                            onclick="location.href='/voteMinus/${m.meme.id}'">Dislike!
                    </button>
                </c:if>
            </security:authorize><br>
        </p>
    </div>
</c:forEach>
</body>
</html>