<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--Expression language jest włączony--%>
<%@ page isELIgnored="false" %>
<%--JSTL jest włączony--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<html>
<head>
    <title>HomePage</title>
    <link rel="stylesheet" type="text/css" href="../css/mystyle.css">
</head>
<body>


<div class="sidebar"><a href="/"> <img src="../LOGO.jpg" height="100" align="right"> </a>


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
    <a>Hi ${activeUserName}<br></a>
    <button class="logout" onclick="location.href='/logout'">Logout</button>

    <button class="addMeme" onclick="location.href='/formMeme'">Add meme</button>
    <br>
    </security:authorize><br>


    <c:forEach var="m" items="${memes}">

    <div class="container">
        <div class="memebar">
            <div class="leftdiv"><a href="/meme/${m.meme.id}">${m.meme.nameMeme}</a><br/>
                    ${m.meme.user.getLogin()}
            </div>
            <br/>
            <div class="rightdiv"> ${m.meme.dateUpload.toString().replace("T", " ")}</div>
        </div>
        <div>
            <a href="/meme/${m.meme.id}">
                <img src="../${m.meme.sourceAdress}" width="600" height="">
            </a>
            <security:authorize access="hasAnyAuthority('ADMIN')">
                <button class="dislike" type="button" name="" onclick="location.href='/delete/${m.meme.id}'">Del
                </button>
            </security:authorize><br>
            <b>Likes: ${m.meme.receivedPluses}</b>
            <security:authorize access="hasAnyAuthority('USER', 'ADMIN')">
                <c:if test="${!m.votingUsers.contains(activeUserName)}">
                    <button type="button" name="like" onclick="location.href='/votePlus/${m.meme.id}'">Like!
                    </button>
                </c:if>
            </security:authorize>
            <b>Dislikes: ${m.meme.receivedMinuses}</b>
            <security:authorize access="hasAnyAuthority('USER', 'ADMIN')">
                <c:if test="${!m.votingUsers.contains(activeUserName)}">
                    <button class="dislike" type="button" name="dislike"
                            onclick="location.href='/voteMinus/${m.meme.id}'">Dislike!
                    </button>
                </c:if>
            </security:authorize><br>
        </div>
    </div>
    </c:forEach>
    <div class="containrepagin" align="center">
            <c:if test="${pageindex != 1}">
                <button class="pagin" type="button" name="dislike"
                        onclick="location.href='/page/${pageindex-1}'">PREV
                </button>
            </c:if>

            <c:if test="${!islastpage}">
                <button class="pagin" type="button" name="dislike"
                        onclick="location.href='/page/${pageindex+1}'">NEXT
                </button>
            </c:if>
    </div>
</body>
</html>