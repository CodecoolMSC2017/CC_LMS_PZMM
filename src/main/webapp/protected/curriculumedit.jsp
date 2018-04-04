<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="login.css">
    <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">
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
            <td><a href="protected/index.jsp">Home</a></td>
        </tr>
    </table>
    <input type="submit" value="Log out" action="logout">
</div>
<br>

<div class="wrapper">



    <div class="pageContent">
        <h3>Edit Curriculum</h3>
        <form class="curriculumEditor" action="curriculumEditorServlet" method="post">
            Curriculum Title:<br>
            <input type="text" name="title" value="${selectedCurriculum.title}"><br>
            Curriculum Content:<br>
            <textarea rows="4" cols="50" name ="content">${selectedCurriculum.content}</textarea><br>
            <input type="checkbox" name="isPublished"  <c:out escapeXml="true" value="${selectedCurriculum.published ? 'checked' : ''}"/>>Published?<br>
            <input class="button" type="submit" value="Edit">

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

