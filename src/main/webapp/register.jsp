<%@ page import="java.util.List,com.codecool.web.service.UserDaoImpl,com.codecool.web.model.User"%>
<!DOCTYPE html>
<head>
  <script src="RegisterError.js"></script>
  <meta http-equiv="content-type" content="text/html; charset=utf-8">
  <link rel="stylesheet" type="text/css" href="login.css">
  <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">
  <title>Home</title>
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



  <div class="pageContentReg">
    <h3>Register</h3>
    <form class="register" action="register" method="POST">
      Name:<br>
      <input type="text" name="name" id="name" placeholder="Leslie Nielsen"><br>
      Email:<br>
      <input type="text" name="email" id="email" placeholder="example@ex.com"><br>
      Role:<br>
      <select name="role" id="role">
        <option value="" disabled selected>Select your role</option>
        <option value="student">Student</option>
        <option value="mentor">Mentor</option>
      </select><br>
      Password:<br>
      <input type="password" name="password" id="password"><br><br>
      <input type="submit" name="register" value="Register">
      <c:if test="${not empty error}">
      <p style="color: red;"><c:out value="${error}"/></p>
      </c:if>
      <c:if test="${empty error}">
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
