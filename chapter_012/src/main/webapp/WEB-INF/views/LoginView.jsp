<%--
  Created by IntelliJ IDEA.
  User: Eugene
  Date: 19.10.2019
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<c:if test="${error !=''}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>
<c:if test="${userAdd !=''}">
    <div style="background-color: green">
        <c:out value="${userAdd}"/>
    </div>
</c:if>

<div class="container">
    <h2>Enter user login & password</h2>
    <form class="form form-horizontal" action="${pageContext.servletContext.contextPath}/signin" method="post">
        <div class="form-group">
            <div class="col-md-3">
                <label for="login">Login:</label>
                <input type="text" class="form-control" name="login" id="login" placeholder="Enter user login">
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-3">
                <label for="pwd">Password:</label>
                <input type="password" class="form-control" name="password" id="pwd" placeholder="Enter password">
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Login</button>
    </form>
    <form class="form-inline" action="${pageContext.servletContext.contextPath}/addseller" method="get"
          title="Добавить пользователя"
          id="formAdd">
        <button type="submit" class="btn btn-primary">Добавить пользователя</button>
    </form>
</div>
</body>
</html>