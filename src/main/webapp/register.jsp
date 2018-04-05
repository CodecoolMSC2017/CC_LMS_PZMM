<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
  <link rel="stylesheet" type="text/css" href="login.css">
  <script src="login.js"></script>
</head>
<body>
<div class="login-page">
  <div class="form">
    <form class="login-form" action="register" method="post">
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
      <c:if test="${not empty info}">
        <p><c:out value="${info}"/></p>
      </c:if>
      <p class="message">Already registered?<a href="login.jsp">Log in!</a></p>
    </form>
  </div>
</div>
</body>
</html>
