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

<div class="sidebar"><a href="/"> <img src="../LOGO.png" height="100" align="right"> </a>


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
        <button class="register" onclick="location.href='/register'">Register</button>
        </security:authorize>


        <button class="homepage" onclick="location.href='/'">Return to home page</button>

        <security:authorize access="hasAnyAuthority('USER', 'ADMIN')">
        <a>Hi ${activeUserName}<br></a>
        <button class="logout" onclick="location.href='/logout'">Logout</button>

        <button class="submit" onclick="location.href='/formMeme'">Add meme</button>
        <br>
        </security:authorize><br>


        <div class="container">
            <div class="memebar">
                <div class="leftdiv"><a>${memeWithVote.meme.nameMeme}</a><br/>
                    ${memeWithVote.meme.user.getLogin()}
                </div>
                <br/>
                <div class="rightdiv"> ${memeWithVote.meme.dateUpload.toString().replace("T", " ")}</div>
            </div>
            <img src="../${memeWithVote.meme.sourceAdress}" width="600" height=""><br>
            </a>
            <security:authorize access="hasAnyAuthority('ADMIN')">
                <button class="dislike" type="button" name="" onclick="location.href='/delete/${memeWithVote.meme.id}'">
                    Del
                </button>
            </security:authorize><br>
            <b>Likes: ${memeWithVote.meme.receivedPluses}</b>
            <security:authorize access="hasAnyAuthority('USER', 'ADMIN')">
                <c:if test="${!memeWithVote.votingUsers.contains(activeUserName)}">
                    <button type="button" name="like" onclick="location.href='/votePlus/${memeWithVote.meme.id}'">Like!
                    </button>
                </c:if>
            </security:authorize>
            <b>Dislikes: ${memeWithVote.meme.receivedMinuses}</b>
            <security:authorize access="hasAnyAuthority('USER', 'ADMIN')">
                <c:if test="${!memeWithVote.votingUsers.contains(activeUserName)}">
                    <button class="dislike" type="button" name="dislike"
                            onclick="location.href='/voteMinus/${memeWithVote.meme.id}'">Dislike!
                    </button>
                </c:if>
            </security:authorize><br>

            <security:authorize access="hasAnyAuthority('USER', 'ADMIN')">
                <form action="/addComment/${memeWithVote.meme.id}" method="post" name='addComment'>
                    <input class="comments" type="text" name="content" placeholder="Comment meme" maxlength="300"/>
                    <br/>
                    <input class="submit" type="submit" value="Comment">
                </form>
            </security:authorize><br>
            <security:authorize access="!hasAnyAuthority('USER', 'ADMIN')">
                Only logged users can comment memes!

                <button class="register" onclick="location.href='/register'">Register</button>
            </security:authorize><br>

            <c:forEach var="comment" items="${listOfComments}">
                <div class="memebar">
                    <div class="leftdiv"><a>${comment.user.getLogin()}</a>
                    <br/>
                    <i><a class="dateComment">${comment.uploadDate.toString().replace("T", " ")}</a></i><br/></div>
                    <div class="rightdiv"><a class="comment">${comment.content}</a><br/></div>
                </div>
                <br/>
            </c:forEach>
        </div>


</body>
</html>