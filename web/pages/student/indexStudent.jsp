<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index Student</title>
</head>
<body>
<a class="button" href="/indexEnter">Task 4 - E-pam var1 Shcherbak Alex </a>

<p/> Hi ${login}
<a class="button" href="/logout" align="right">LogOut</a>

<p/>

<p/>

<div>
    <table>
        <form action="/gotoFaculty" method="post">
            <thead>
            <tr align="center"><h2>Available Faculty list</h2></tr>
            <tr>
                <th>ID</th>
                <th>Course title</th>
                <th>Status</th>
                <th></th>
            </tr>
            </thead>

            <tbody>

            <c:forEach var="item" items="${availableFacults}">
                <tr>
                    <td><c:out value="${item.getId()}"></c:out></td>
                    <td>${item.getCourse().getTitle()}</td>
                    <td>${item.getStatus().toString()}</td>
                    <td>
                        <input type="hidden" name="student" value="${student.getId()}">
                        <button type="submit" name="faculty" value="${item.getId()}">GO</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </form>
    </table>
</div>

<div>
    <table>
        <thead>
        <tr align="center"><h2>Faculty list</h2></tr>
        <tr>
            <th>ID</th>
            <th>Course title</th>
            <th>Status</th>
            <th>MARK</th>
        </tr>
        </thead>


        <tbody>

        <c:forEach var="item" items="${facultyes}">
            <tr>
                <td><c:out value="${item.getId()}"></c:out></td>
                <td>${item.getCourse().getTitle()}</td>
                <td>${item.getStatus().toString()}</td>
                <td>${student.getMarkByFaculty(item).getMark()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
