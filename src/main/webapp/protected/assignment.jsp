<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="login.css">
    <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">
    <title>Assignment</title>
</head>
<body>

<div class="pageHeader">
    <img class="logo" src="../lezli.jpg" >
    <a href="index.jsp">CunFuss</a>
    <img class="dwi" src="../dwi.png" >
</div>

<div class="nav">
    <table>
        <tr>
            <td><a href="index.jsp">Home</a></td>
            <td><a href="../register.jsp">Register</a></td>
            <td><a href="../login.jsp">Login</a></td>
            <td><a href="userlist">Userlist</a></td>
            <td><a href="profile">Profile</a></td>
        </tr>
    </table>
    <input type="submit" value="Log out" action="logout">
</div>
<br>

<div class="wrapper">

    <div class="pageLeftMenu">
        <h3>LeftMenu</h3>
    </div>

    <div class="pageContent">
        <h3>Content</h3>
        <table>
            <td>
                <tr><h2>Title:${selectedAssignment.title}</h2></tr>
                <tr><p>Maximum score:${selectedAssignment.maxScore}</p></tr>
                <tr><p>Question:${selectedAssignment.question}</p></tr>
                <tr><p>Answer:${selectedAssignment.answer}</p></tr>
            </td>
        </table>
    </div>

    <div class="pageRightMenu">
        <h3>RightMenu</h3>
    </div>
</div>

<div class="pageFooter">
    footer
</div>
</div>
</body>
</html>
