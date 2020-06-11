<%--
  Created by IntelliJ IDEA.
  User: Serg
  Date: 07.06.2020
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html lang="ru">
<head>

    <title>Meals</title>

</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>

<table>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th>Action</th>
    </tr>

    <c:forEach var="meal" items="${meals}">
        <tr>
        <tr style="color:${meal.excess ? '#FF4500' : '#228B22'}">
            <td>${meal.dateTime}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td>
                <a href="./meals?action=edit&id=${meal.id}">edit</a>
                <a href="./meals?action=delete&id=${meal.id}">delete</a>
            </td>
        </tr>

    </c:forEach>
</table>
<br>
<a href="./meals?action=add">Add</a>

</body>
</html>
