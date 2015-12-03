<%--
  ~ Alx Shcherbak
  --%>

<%--
  Created by IntelliJ IDEA.
  User: AlxEx
  Date: 03.12.2015
  Time: 12:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index Guest</title>
</head>
<body>
<a class="button" href="/indexEnter">Task 4 - E-pam var1 Shcherbak Alex </a>
<table align="center">
    <form method="post" action="/login">
        <tr>
            <td>login</td>
            <td><input type="text" name="login" id="006"></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" id="007"></td>
        </tr>
        <tr>
            <td><a href="pages/registration.jsp" class="button">Registration</a> </td>
            <td><input type="submit"></td>
        </tr>
        <tr>
            <td>
                ${LoginFould}
            </td>
        </tr>
    </form>
</table>

</body>
</html>
