<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="styling.css">
    <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">
    <title>Home</title>
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
            <tr>
                <td><h2>${selectedCurriculum.title}</h2></td>
            </tr>
            <tr>
                <td><p>${selectedCurriculum.content}</p></td>
            </tr>
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
