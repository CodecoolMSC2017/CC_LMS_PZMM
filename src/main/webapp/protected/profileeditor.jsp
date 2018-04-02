<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="login.css">
    <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">
    <title>Profile Editor</title>
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

    <div class="editProfile">
        <h3>Edit your profile</h3>
        <form class="profileEditor" action="profileeditor" method="post">
            <p>Name:<input type="text" name="name" value="${user.name}"></p>
            <p>Email:<input type="text" name="email" value="${user.email}" readonly></p>
            <p>Role:
            <select name="role" id="role">
                <option selected disabled>${user.role}</option>
                <option value="student">Student</option>
                <option value="mentor">Mentor</option>
            </select><br></p>
            <input class="button" type="submit" value="Save">
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

