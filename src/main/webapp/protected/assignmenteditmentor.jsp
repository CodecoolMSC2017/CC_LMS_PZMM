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
        </tr>
    </table>
    <input type="submit" value="Log out" action="logout">
</div>
<br>

<div class="wrapper">



    <div class="pageContent">
        <h3>Edit Assignment</h3>
        <form class="assignmentEditor" action="assignmentEditorServlet" method="post">
            Assignment Title:<input type="text" name="title" value="${selectedAssignment.title}"><br>
            Assignment MaxScore:<input type="number" value="${selectedAssignment.maxScore}">
            Assignment IsPublished: <select name="isPublished">
            <option value="true">Public</option>
            <option value="false">Hidden</option></select>
            <input class="button" type="submit" value="Edit">
        </form>
    </div>

</div>

<div class="pageFooter">
    footer
</div>
</div>
</body>
</html>
