<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="US-ASCII">
    <title>Login Page</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/login" method="post">

    username: <input type="text" name="user">
    <br>
    password: <input type="password" name="password">
    <br>
    <input type="submit" value="login">
</form>
</body>
</html>