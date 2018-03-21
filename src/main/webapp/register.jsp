<%@ page import="java.util.List,com.codecool.web.service.UserDaoImpl,com.codecool.web.model.User"%>
<html>
<head>
  <script src="RegisterError.js"></script>
</head>
<form action="register" onsubmit="return registerValidate();" method="POST">
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
  <input type="password" name="password" id="password"><br>
  <input type="submit" name="register" value="Register">
</form>
<%
UserDaoImpl userDao = new UserDaoImpl();
List<User> users = userDao.getAllUsers();
for (User user : users) {
    out.print("<p>" + user + "</p><br>");
}
%>
</html>
