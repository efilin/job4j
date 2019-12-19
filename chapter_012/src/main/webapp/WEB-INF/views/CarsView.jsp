<%--
  Created by IntelliJ IDEA.
  User: Eugene
  Date: 19.10.2019
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Площадка продажи машин</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function () {
            $("#filter").on("change", function () {
                var dop = document.getElementById("filter").value;
                if (dop === 'currentManufacturer') {
                    readData();
                }
            });
        });


        function readData() {
            $.ajax({
                method: "get",
                url: "manufacturer",
                contentType: "text/json",
                complete: function (data) {
                    var json = JSON.parse(data.responseText);
                    var $jsontwo = $("#manufacturers");
                    $jsontwo.empty();
                    $.each(json, function (index, value) {
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
<c:if test="${carUpdate !=''}">
    <div style="background-color: green">
        <c:out value="${carUpdate}"/>
    </div>
</c:if>
<div class="container-fluid">
</div>
<br>

<div class="container">
    <form class="form-inline" action="logout" method="post" id="logout">
        <button type="submit" class="btn btn-primary">Logout</button>
    </form>

    <div class="container">
        <form class="form-inline" action="carslist" method="get">
            <div class="form-group">

            </div>
            <div class="form-group">
                <select class="form-control" name="filter" id="filter">
                    <option value="allCars" selected>Показать все объявления</option>
                    <option value="lastDay">Объявления за последний день</option>
                    <option value="withPhoto">Объявления с фото</option>
                    <option value="currentManufacturer">Конкретного производителя</option>
                </select>
            </div>
            <select class="form-control" name="manufacturers" id="manufacturers">
            </select>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </form>
    </div>
    <h2> Список машин на продажу:</h2>
    <table class="table table-bordered" id='table'>
        <thead>
        <tr>
            <th>Фото</th>
            <th>Марка</th>
            <th>Модель</th>
            <th>Пробег</th>
            <th>Цена</th>
            <th>Год выпуска</th>
            <th>Характеристики</th>
            <th>Продается</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${allCars}" var="car">
            <tr>
                <td>
                    <img src="${pageContext.request.contextPath}/photo?carId=<c:out value="${car.id}"/>"
                         alt="Фото" width="100%"></td>
                <td><c:out value="${car.manufacturer}"><</c:out></td>
                <td><c:out value="${car.model}"><</c:out></td>
                <td><c:out value="${car.mileage}"><</c:out></td>
                <td><c:out value="${car.price}"><</c:out></td>
                <td><c:out value="${car.productionYear}"><</c:out></td>
                <td><c:out value="${car.carBody}"><</c:out>, объем двигателя: <c:out value="${car.engine}"><</c:out> л,
                    трансмиссия:<c:out
                            value="${car.transmission}"><</c:out>, <c:out value="${car.description}"><</c:out></td>
                <td><c:choose>
                    <c:when test="${car.onSale==true}">
                        Да
                    </c:when>
                    <c:otherwise>
                        Нет
                    </c:otherwise>
                </c:choose>
                </td>
                <td>

                    <form action="update" method="post">
                        <input type="hidden" name="carId" value="${car.id}">
                        <button type="submit" class="btn btn-primary">Изменить статус объявления</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form class="form-inline" action="add" method="get" title="Добавить новое объявление"
          id="formAdd">
        <button type="submit" class="btn btn-primary">Добавить объявление</button>
    </form>
</div>
</body>
</html>
