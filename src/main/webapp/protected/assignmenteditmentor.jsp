<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="../layout.css">
    <script src="../html5shiv.js"></script>
    <title>Assignment</title>
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
                <h3>Edit Assignment</h3>
                <form class="assignmentEditor" action="assignmentEditorServlet" method="post">
                    Assignment Title:<br>
                    <input type="text" name="title" value="${selectedAssignment.title}"><br>
                    Assignment MaxScore:<br>
                    <input type="number" name="maxScore" value="${selectedAssignment.maxScore}"><br>
                    Assignment Question:<br>
                    <textarea rows="4" cols="50" name ="question">${selectedAssignment.question}</textarea><br>
                    Assignment IsPublished:<br>
                    <select name="isPublished">
                        <option value="true">Publish</option>
                        <option value="false">Unpublish</option>
                    </select>
                    <br><input class="button" type="submit" value="Edit">
                    <c:if test="${not empty error}">
                        <p style="color: red;"><c:out value="${error}"/></p>
                    </c:if>
                    <c:if test="${not empty info}">
                        <p style="color: blue;"><c:out value="${info}"/></p>
                    </c:if>
                </form>
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
