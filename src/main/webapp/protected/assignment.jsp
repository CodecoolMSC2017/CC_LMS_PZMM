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
            <td><a href="/protected/index.jsp">Home</a></td>
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
                <tr><p>Answer:</p></tr>
                <form action="SubmitAssignmentServlet" method="post">
                    <textarea rows="4" cols="50" name = "answer" <c:out escapeXml="true" value="${selectedAssignment.done ? 'readonly' : ''}"/> > ${selectedAssignment.answer}</textarea>
                    <c:choose>
                        <c:when test = "${not selectedAssignment.done}">
                            <input class="button" type="submit" value="Submit">
                        </c:when>
                        <c:otherwise>
                            <p>You already published this Assignment</p>
                        </c:otherwise>
                    </c:choose>
                </form>
            </td>
        </table>
    </div>
</div>

<div class="pageFooter">
    footer
</div>
</div>
</body>
</html>
