<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--Expression language jest włączony--%>
<%@ page isELIgnored="false" %>
<%--JSTL jest włączony--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<html>
<head>
    <title>RateMyMeme</title>
    <link rel="stylesheet" type="text/css" href="../css/mystyle.css">
</head>
<body>

<security:authorize access="!hasAnyAuthority('USER', 'ADMIN')">
    <form name='loginForm' action="<c:url value='/' />" method='POST'>
        <table>
            <tr>
                <td>User:</td>
                <td><input class=type='text' name='login' required></td>

                <td>Password:</td>
                <td><input type='password' name='password' required></td>

                <td colspan='2'><input class="submit" name="submit" type="submit" value="submit"/></td>
            </tr>
        </table>
    </form>
    Only logged users can comment memes!

    <button class="register" onclick="location.href='/register'">Register</button>

</security:authorize><br>
<button class="homepage" onclick="location.href='/'">Return to home page</button>
<br>
<security:authorize access="hasAnyAuthority('USER', 'ADMIN')">
    <button class="logout" onclick="location.href='/logout'">Logout</button>
    <br>
    Hi ${activeUserName}<br>
    <button class="submit" onclick="location.href='/formMeme'" >Add meme</button>
    <br>
</security:authorize><br>


<div align="center">

    <p>
        ${meme.nameMeme}
        ${meme.dateUpload.toString().replace("T", " ")}
        <br>
        <img src="../${meme.sourceAdress}" width="600" height=""><br>
        </a>
    </p>

    <form action="/addComment/${meme.id}" method="post" name='addComment'>
        <input class="comments" type="text" name="content" placeholder="Comment meme" maxlength="300" /> <br />
        <input class="submit" type="submit" value="Comment">
    </form>

</div>


</body>
</html>