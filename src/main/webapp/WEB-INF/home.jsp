<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--Expression language jest włączony--%>
<%@ page isELIgnored="false" %>
<%--JSTL jest włączony--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>




<html>
<head>
    <title>Formularz</title>
</head>
<body>
<% out.print("Formularz dodawania mema"); %>

<form method="post" action="/tu podac" enctype="multipart/form-data">
    <input type="text"  name="login" placeholder="Nazwa" title="Please, enter valid email adress"/>
    <input type="password" name="password"  placeholder="Haslo" title="Please, enter password"/>
    <input type="submit"  value="Zaloguj" title="Login"/>
</form>

<table>

    ${uzytkownik.login}

</body>
</html>