<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--
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
<c:if test="${error !=''}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>
<form action="${pageContext.servletContext.contextPath}/logout" method="get">
    <input type="submit" value="Logout"/>
</form>
<form action="${pageContext.servletContext.contextPath}/" method="post">
    <input type="hidden" name="action" value="add"/>
    Name : <input type="text" name="name"/><br>
    Password : <input type="password" name="password"/><br>
    <select name="role">
        <option value="user">user</option>
        <option value="administrator">administrator</option>
    </select>
    <input type="submit" value="Create"/>
</form>
<table>
    <c:forEach items="${users}" var="name">
        <tr>
            <td>
                <c:out value="${name.name}"><</c:out>
            </td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/update" method="get">
                    <input type="hidden" name="action" value="update"/>
                    <input type="hidden" name="id" value="${name.id}">
                    <input type="submit" value="update">
                </form>
            </td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/" method="post">
                    <input type="hidden" name="action" value="delete"/>
                    <input type="hidden" name="id" value="${name.id}">
                    <input type="submit" value="delete">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>