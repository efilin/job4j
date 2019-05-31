<%--
  Created by IntelliJ IDEA.
  User: Eugene
  Date: 30.05.2019
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Login</title>
</head>
<body>
<c:if test="${error !=''}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>
<form action="${pageContext.servletContext.contextPath}/signin" method="post">
    Name : <input type="text" name="name"/> <br>
    Password : <input type="password" name="password"/> <br>
    <input type="submit" value="login"/>
</form>
</body>
</html>
