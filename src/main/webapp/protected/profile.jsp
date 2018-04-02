<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="login.css">
    <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">
    <title>Profile</title>
</head>
<body>
<br>

<div class="wrapper">
    <div class="pageContent">
        <h3>Profile</h3>
        <p>Name: <c:out value="${user.name}"/></p>
        <p>Email: <c:out value="${user.email}"/></p>
        <p>Role: <c:out value="${user.role}"/></p>
        <a href="profileeditor.jsp">Edit profile</a>
    </div>
</div>

<div class="pageFooter">
    footer
</div>
</div>
</body>
</html>
