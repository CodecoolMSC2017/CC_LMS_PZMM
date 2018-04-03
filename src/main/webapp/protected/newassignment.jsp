<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html"%>
<!DOCTYPE html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="styling.css">
    <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">
    <jsp:include page="/curriculumList" />
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
            <td><a href="profile">Profile</a></td>
            <td><a href="userlist">Userlist</a></td>
        </tr>
    </table>
    <form action="logout" method="post"><input type="submit" value="Log out" ></form>

</div>
<br>

<div class="wrapper">

    <div class="pageLeftMenu">
        <h3>LeftMenu</h3>
    </div>

    <div class="pageContent">
        <h3>Create new assignment:</h3>
        <form method="post" action="newassignment">
            Title:<br>
            <input type="text" name="title" placeholder="JAVA OOP Basics"><br>
            Question:<br>
            <input type="text" name="question"><br>
            Max score:<br>
            <input type="number" name="maxscore" min="1" value="1">
            Visibility:<br>
            <select name="visibility">
                <option value="published">Published</option>
                <option value="unpublished">Unpublished</option>
            </select><br>
            <input type="submit" name="save" value="Save">

            <c:if test="${not empty error}">
                <p style="color: red;"><c:out value="${error}"/></p>
            </c:if>
            <c:if test="${not empty info}">
                <p><c:out value="${info}"/></p>
            </c:if>
        </form>
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
