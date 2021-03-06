<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  ~ Alx Shcherbak
  --%>

<%--
  Created by IntelliJ IDEA.
  User: AlxEx
  Date: 03.12.2015
  Time: 1:28
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Set marks</title>
</head>
<body>
<a class="button" href="/indexEnter">Task 4 - E-pam var1 Shcherbak Alex </a>

<p/> Hi ${login}
<a class="button" href="/logout" align="right">LogOut</a>

<p/>

<div>
    <table id="tablet">
            <thead>
            <tr align="center"><h2>Faculty list</h2></tr>
            <tr>
                <th>Student ID</th>
                <th>Student Name</th>
                <th>MARK</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="item" items="${students}">
                <tr>
                    <td><c:out value="${item.getId()}"></c:out></td>
                    <td>${item.getName()}</td>
                    <td>${item.getMarkByFaculty(faculty).getMark()}</td>
                </tr>
            </c:forEach>
            </tbody>
    </table>
</div>

</body>
</html>
