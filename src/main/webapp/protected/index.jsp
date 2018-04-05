<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <title>CunFuss</title>
    <jsp:include page="/curriculumList" />
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="../layout.css">
    <script src="../html5shiv.js"></script>
</head>
<body>
<div class="wrapper row1">
    <header id="header" class="clear">
        <div id="hgroup">
            <h1><a href="index">CunFuss</a></h1>
            <h2>Learning Management System</h2>
        </div>
        <nav>
            <ul>
                <li><a href="index">Home</a></li>
                <li><a href="profile">Profile</a></li>
                <li><a href="userlist">Users</a></li>
                <li><a href="../protected/logout">Log out</a></li>
            </ul>
        </nav>
        <div class="clear"></div>
    </header>
</div>
<!-- content -->
<div class="wrapper row2">
    <div id="container">
        <section class="last clear">
            <div class="three_third">
                <h2>Curriculums:</h2>
                <c:forEach items="${curriculums}" var="curriculum">
                    <p><a href="../setCurriculum?curriculum=${curriculum.title}"><c:out value ="${curriculum.title}"/></a><br></p>
                </c:forEach>
                <c:if test="${user.role=='mentor'}">
                    <p><a href="newcurriculum.jsp">Create new curriculum</a></p>
                </c:if>
                <h2>Assignments:</h2>
                <c:forEach items="${assignments}" var="assignment">
                    <p><a href="../setAssignment?assignment=${assignment.title}"><c:out value ="${assignment.title}"/></a><br></p>
                </c:forEach>
                <c:if test="${user.role=='mentor'}">
                    <p><a href="newassignment.jsp">Create a new assignment</a></p>
                </c:if>
            </div>
        </section>
    </div>
</div>
<!-- footer -->
<div class="wrapper row3">
    <footer id="footer">
        <p class="fl_left">Copyright &copy; 2018 - All Rights Reserved - </p>
        <p class="fl_right">Template by <a href="http://www.os-templates.com/" title="Free Website Templates">OS Templates</a></p>
        <div class="clear"></div>
    </footer>
</div>
</body>
</html>
