function registerValidate() {
  var name = document.getElementById("name").value;
  var email = document.getElementById("email").value;
  var role = document.getElementById("role").value;
  var password = document.getElementById("password").value;
  if(name == null || email == null || role == null || password == null)
  {
    alert("Fill every field please!");
    window.location.replace("register.jsp");
    return false;
  }
  return true;
}
