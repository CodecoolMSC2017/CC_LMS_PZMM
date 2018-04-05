<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <title>${selectedAssignment.title}</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="../layout.css">
    <script src="../html5shiv.js"></script>
</head>
<body>
<div class="wrapper row1">
    <header id="header" class="clear">
        <div id="hgroup">
            <h1><a href="index">CunFuss</a></h1>
            <h2>Learning Management System</h2>
        </div>
        <nav>
            <ul>
                <li><a href="index">Home</a></li>
                <li><a href="profile">Profile</a></li>
                <li><a href="userlist">Users</a></li>
                <li><a href="../protected/logout">Log out</a></li>
            </ul>
        </nav>
        <div class="clear"></div>
    </header>
</div>
<!-- content -->
<div class="wrapper row2">
    <div id="container">
        <section class="last clear">
            <div class="three_third">
            <table>
                <td>
                <tr><h2>Title: ${selectedAssignment.title}</h2></tr>
                <tr><p>Maximum score: ${selectedAssignment.maxScore}</p></tr>
                <tr><p>Question: ${selectedAssignment.question}</p></tr>
                <tr><p>Answer: </p></tr>>
                    <form action="SubmitAssignmentServlet" method="post">
                        <textarea rows="4" cols="50" name = "answer" <c:out escapeXml="true" value="${selectedAssignment.done ? 'readonly' : ''}"/> > ${selectedAssignment.answer}</textarea>
                        <c:choose>
                            <c:when test = "${not selectedAssignment.done}"><br>
                                <input class="button" type="submit" value="Submit">
                            </c:when>
                            <c:otherwise>
                                <p>You already published this assignment</p>
                            </c:otherwise>
                        </c:choose>
                    </form>
                </td>
            </table>

            </div>
        </section>
    </div>
</div>
<!-- footer -->
<div class="wrapper row3">
    <footer id="footer">
        <p class="fl_left">Copyright &copy; 2018 - All Rights Reserved - </p>
        <p class="fl_right">Template by <a href="http://www.os-templates.com/" title="Free Website Templates">OS Templates</a></p>
        <div class="clear"></div>
    </footer>
</div>
</body>
</html>
