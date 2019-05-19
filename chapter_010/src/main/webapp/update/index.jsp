<%@ page import="ru.job4j.crud.User" %>
<%@ page import="ru.job4j.crud.ValidateService" %><%--
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
<%User user = ValidateService.getInstance().findById(Integer.parseInt(request.getParameter("id")));%>
<form action="<%=request.getContextPath()%>/update" method="post">
    Name:<br>
    <input type="text" name="name" value="<%=user.getName()%>">
    <br>
    <input type="hidden" name="id" value="<%=Integer.toString(user.getId())%>">
    <input type="submit" value="Update">
</form>
</body>
</html>
