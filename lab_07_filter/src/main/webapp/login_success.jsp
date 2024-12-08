<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Login Success Page</title>
</head>
<body>
<%
    String userUUID = null;
    Cookie[] cookies = request.getCookies();
    System.out.println(session.getAttribute("uuid"));
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getValue().equals(session.getAttribute("uuid"))) userUUID = cookie.getValue();
        }
    }
%>
<h3>Hello? <%=userUUID %>!!!</h3>
<br>
<form action="${pageContext.request.contextPath}/logout" method="post">
    <input type="submit" value="Logout">
</form>
</body>
</html>