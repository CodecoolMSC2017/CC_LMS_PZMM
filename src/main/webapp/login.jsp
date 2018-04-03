<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="login.css">
    <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">
    <title>Login</title>
</head>
<body>
<br>

<div class="wrapper">
    <div class="pageContent">
        <h3>Login</h3>
        <form class="login" action="login" method="post">
            Email:<input type="text" name="email"><br>
            Password:<input type="password" name="password"><br><br>
            <c:if test="${not empty error}">
                <p style="color: red;"><c:out value="${error}"/></p>
            </c:if>
            <input class="button" type="submit" value="Login"><br>
            <a href="register.jsp">Sign up!</a>
        </form>
    </div>
</div>

<div class="pageFooter">
    footer
</div>
</div>
</body>
</html>

