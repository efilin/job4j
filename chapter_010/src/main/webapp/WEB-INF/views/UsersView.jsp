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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        function checkInput() {
            if (validate()) {
                $('#formAdd').submit();
            }
        }

        function validate() {
            var result = false;
            if (!$('#name').val()) {
                alert($('#name').attr('title'));
            } else if (!$('#password').val()) {
                alert($('#password').attr('title'));
            } else {
                result = true;
            }
            return result;
        }

        $(document).ready(function () {
            var data = $('#country').val();
            $.ajax({
                method: "post",
                url: "city",
                data: JSON.stringify(data),
                contentType: "application/json",
                complete: function () {
                    readData();
                }
            });
        });
        $(document).ready(function () {
            $('#country').change(function () {
                var data = $('#country').val();
                $.ajax({
                    method: "post",
                    url: "city",
                    data: JSON.stringify(data),
                    contentType: "application/json",
                    complete: function () {
                        readData();
                    }
                });
            });
        });

        function readData() {
            $.ajax({
                method: "get",
                url: "city",
                contentType: "text/json",
                complete: function (data) {
                    var selectC = $('#country').val();
                    var json = JSON.parse(data.responseText);
                    var receivedCities = json[selectC].cities;
                    var $jsontwo = $("#city");
                    $jsontwo.empty();
                    $.each(receivedCities, function (index, value) {
                        $jsontwo.append("<option value=" + value + ">" + value + "</option>");
                    });

                }
            })
        }
    </script>
</head>
<body>
<c:if test="${error !=''}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>
<div class="container">
    <h2> List of all users </h2>
    <form action="${pageContext.servletContext.contextPath}/logout" method="get">
        <input type="submit" class="btn btn-primary" value="Logout"/>
    </form>
    <form class="form form-horizontal" action="${pageContext.servletContext.contextPath}/users" method="post"
          id="formAdd">
        <input type="hidden" name="action" value="add"/>
        <div class="form-group">
            <div class="col-md-3">
                <label for="name">Name:</label>
                <input type="text" class="form-control" name="name" title="Enter user name." id="name">
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-3">
                <label for="password">Password:</label>
                <input type="password" class="form-control" name="password" title="Enter user password" id="password">
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-3">
                <label for="role">Choose user role:</label>
                <select class="form-control" name="role" id="role">
                    <option value="user">user</option>
                    <option value="administrator">administrator</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-3">
                <label for="country">Choose user country:</label>
                <select class="form-control" name="country" id="country">
                    <option value="Russia">Russia</option>
                    <option value="USA">USA</option>
                    <option value="Germany">Germany</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-3">
                <label for="city">Choose user city:</label>
                <select class="form-control" name="city" id="city">
                </select>
            </div>
        </div>
        <button type="button" class="btn btn-primary" onclick="checkInput()">Create new user</button>
    </form>
    <table class="table table-bordered" id='table'>
        <thead>
        <tr>
            <th>Name</th>
            <th>Role</th>
            <th>Country</th>
            <th>City</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="name">
            <tr>
                <td><c:out value="${name.name}"><</c:out></td>
                <td><c:out value="${name.role}"><</c:out></td>
                <td><c:out value="${name.country}"><</c:out></td>
                <td><c:out value="${name.city}"><</c:out></td>
                <td>
                    <form action="${pageContext.servletContext.contextPath}/update" method="get">
                        <input type="hidden" name="action" value="update"/>
                        <input type="hidden" name="id" value="${name.id}">
                        <input type="submit" value="update">
                    </form>
                </td>
                <td>
                    <form action="${pageContext.servletContext.contextPath}/users" method="post">
                        <input type="hidden" name="action" value="delete"/>
                        <input type="hidden" name="id" value="${name.id}">
                        <input type="submit" value="delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>