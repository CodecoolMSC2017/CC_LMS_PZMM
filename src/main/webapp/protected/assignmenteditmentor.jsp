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
    <div class="pageContent">
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
</div>

<div class="pageFooter">
    footer
</div>
</div>
</body>
</html>
