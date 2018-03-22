function registerValidate() {
  var name = document.getElementById("name").value;
  var email = document.getElementById("email").value;
  var role = document.getElementById("role").value;
  var password = document.getElementById("password").value;
  console.log(name,email,role,password);
  if(name == "" || email == "" || role == null || password == "")
  {
    alert("Fill every field please!");
    //window.location.replace("register.jsp");
    return false;
  }
  alert("Registration is successful!");
  return true;
}
