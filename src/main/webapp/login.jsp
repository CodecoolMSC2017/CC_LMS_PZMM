<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <link rel="stylesheet" type="text/css" href="login.css">
    <script src="login.js"></script>
</head>
<body class="authentication">
<div class="login-page">
    <div class="form">
        <form class="login-form" action="login" method="post">
            <input type="text" placeholder="email" name="email"/>
            <input type="password" placeholder="password" name="password"/>
            <input class="button" type="submit" value="Login"><br>
            <c:if test="${not empty error}">
                <p style="color: red;"><c:out value="${error}"/></p>
            </c:if>
            <p class="message">Not registered? <a href="register.jsp"> Create an account</a></p>
        </form>
    </div>
</div>
</body>
</html>
