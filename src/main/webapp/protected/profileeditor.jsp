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
        <form class="profileEditor" action="profileEditorServlet" method="post">
            <p>Name:<input type="text" name="name" value="${user.name}"></p>
            <p>Email:<input type="text" name="email" value="${user.email}" readonly></p>
            <select name="role" id="role">
                    <option value="student">Student</option>
                    <option value="mentor">Mentor</option>
                  </select><br>
            <input class="button" type="submit" value="Save">
        </form>
    </div>

</div>

<div class="pageFooter">
    footer
</div>
</div>
</body>
</html>

