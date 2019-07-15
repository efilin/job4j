<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--Created by IntelliJ IDEA.
User: Eugene
Date: 18.05.2019
Time: 18:34
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update user</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
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
    <h2> Update user </h2>
    <form class="form form-horizontal" action="${pageContext.servletContext.contextPath}/update" method="post">
        <div class="form-group">
            <div class="col-md-3">
                <label for="name">Name:</label>
                <input type="text" class="form-control" title="User name:" name="name" id="name" value="${user.name}">
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-3">
                <label for="pwd">Password:</label>
                <input type="text" class="form-control" title="User password:" name="password" id="pwd"
                       value="${user.password}">
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-3">
                <label for="role">User role:</label>
                <select class="form-control" name="role" id="role">
                    <option value="administrator">administrator</option>
                    <option value="user">user</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-3">
                <label for="country">User country:</label>
                <select class="form-control" name="country" id="country">
                    <option selected>Choose country</option>
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
                    <option selected>Choose city</option>
                </select>
            </div>
        </div>
        <input type="hidden" name="id" value="${user.id}">
        <button type="submit" class="btn btn-primary">Update</button>
    </form>
</div>
</body>
</html>
