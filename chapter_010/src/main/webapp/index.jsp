<%@ page import="ru.job4j.crud.User" %>
<%@ page import="ru.job4j.crud.ValidateService" %><%--
  Created by IntelliJ IDEA.
  User: Eugene
  Date: 16.05.2019
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Users</title>
    <h3> List of all users </h3>
    <meta charset='UTF-8'>
</head>
<body>
<form action="<%=request.getContextPath()%>/list" method="post">
    <input type="hidden" name="action" value="add"/>
    Name : <input type="text" name="name"/>
    <input type="submit" value="Create"/>
</form>
<table>
    <%for (User name : ValidateService.getInstance().findAll()) { %>
    <tr>
        <td>
            <%=name.getName()%>
        </td>
        <td>
            <form action="<%=request.getContextPath()%>/update/index.jsp" method="get">
                <input type="hidden" name="action" value="update"/>
                <input type="hidden" name="id" value="<%=name.getId()%>">
                <input type="submit" value="update">
            </form>
        </td>
        <td>
            <form action="<%=request.getContextPath()%>/list" method="post">
                <input type="hidden" name="action" value="delete"/>
                <input type="hidden" name="id" value="<%=name.getId()%>">
                <input type="submit" value="delete">
            </form>
        </td>
    </tr>
    <%}%>
</table>
</body>
</html>
