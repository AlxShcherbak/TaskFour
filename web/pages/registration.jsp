<%--
  ~ Alx Shcherbak
  --%>

<%--
  Created by IntelliJ IDEA.
  User: AlxEx
  Date: 03.12.2015
  Time: 1:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<a class="button" href="/indexEnter">Task 4 - E-pam var1 Shcherbak Alex </a>
<table align="center">
    <form action="/regist" method="post">
        <tr>
            <td>Registration</td>
        </tr>
        <tr>
            <td>login*</td>
            <td><input type="text" name="login" id="011"></td>
        </tr>
        <tr>
            <td>password*</td>
            <td><input type="password" name="password" id="012"></td>
        </tr>
        <tr>
            <td>password confirm*</td>
            <td><input type="password" name="password2" id="013"></td>
        </tr>
        <tr>
            <td>Name*</td>
            <td><input type="text" name="name" id="014"></td>
        </tr>
        <tr>
            <td/>
            <td>
                <button type="submit">Registration</button>
            </td>
        </tr>
        <tr>
            <td>
                ${RegMistake}
            </td>
        </tr>
    </form>
</table>

</body>
</html>
