<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="login.css">
    <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">
    <title>Curriculum creation</title>
</head>
<body>

<div class="pageHeader">
    <img class="logo" src="lezli.jpg" >
    <a href="index.jsp">CunFuss</a>
    <img class="dwi" src="dwi.png" >
</div>

<div class="nav">
    <table>
        <tr>
            <td><a href="index.jsp">Home</a></td>
            <td><a href="register.jsp">Register</a></td>
            <td><a href="login.jsp">Login</a></td>
            <td><a href="userlist">Userlist</a></td>
        </tr>
    </table>
</div>
<br>

<div class="wrapper">



    <div class="newCurriculumCreation">
        <h3>Create new curriculum</h3>
        <form class="register" action="curriculumcreation" method="POST">
            Title:<br>
            <input type="text" name="title" id="name" placeholder="Title"><br>
            Content:<br>
            <input type="text" name="content" id="email" placeholder="Content"><br>
            Visibility:<br>
            <select name="ispublished" id="ispublished">
                <option value="published">Published</option>
                <option value="notpublished">Not published</option>
            </select><br>
            <input type="submit" name="creation" value="Create">

            <c:if test="${not empty error}">
                <p style="color: red;"><c:out value="${error}"/></p>
            </c:if>
            <c:if test="${not empty info}">
                <p><c:out value="${info}"/></p>
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
