<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
Created by IntelliJ IDEA.
User: Eugene
Date: 18.05.2019
Time: 18:34
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update user</title>
    <h3> Update user </h3>
    <meta charset='UTF-8'>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/update" method="post">
    Name:<br>
    <input type="text" name="name" value="${user.name}">
    <br>
    <input type="hidden" name="id" value="${user.id}">
    <input type="submit" value="Update">
</form>
</body>
</html>
