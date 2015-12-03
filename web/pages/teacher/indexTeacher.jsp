<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index Teacher</title>
</head>
<body>
<a class="button" href="/indexEnter">Task 4 - E-pam var1 Shcherbak Alex </a>

<p/> Hi ${login}
<a class="button" href="/logout" align="right">LogOut</a>

<p/>

<div>
    <table id="tablet">
        <form action="/viewTeacher" method="post">
            <thead>
            <tr align="center"><h2>Faculty list</h2></tr>
            <tr>
                <th>ID</th>
                <th>Course title</th>
                <th>Status</th>
                <th></th>
            </tr>
            </thead>

            <tfoot>
            <tr>
                <th>ID</th>
                <th>Course title</th>
                <th>Status</th>
                <th></th>
            </tr>
            </tfoot>

            <tbody>

            <c:forEach var="item" items="${facultyes}">
                <tr>
                    <td><c:out value="${item.getId()}"></c:out></td>
                    <td>${item.getCourse().getTitle()}</td>
                    <td>${item.getStatus().toString()}</td>
                    <td>
                        <button type="submit" name="sub" value="${item.getId()}">GO</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </form>
    </table>
</div>
</body>
</html>
