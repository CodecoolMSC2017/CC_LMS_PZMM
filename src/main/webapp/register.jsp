<%@ page import="java.util.List,com.codecool.web.service.UserDaoImpl,com.codecool.web.model.User"%>
<html>
<form action="register" method="POST">
  Name:<br>
  <input type="text" name="name" placeholder="Leslie Nielsen"><br>
  Email:<br>
  <input type="text" name="email" placeholder="example@ex.com"><br>
  Role:<br>
  <select name="role">
    <option value="" disabled selected>Select your role</option>
    <option value="student">Student</option>
    <option value="mentor">Mentor</option>
  </select><br>
  Password:<br>
  <input type="password" name="password"><br>
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
