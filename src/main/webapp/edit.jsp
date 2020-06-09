<%--
  Created by IntelliJ IDEA.
  User: Serg
  Date: 08.06.2020
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html lang="ru">
<head>

    <title>${action} Meal</title>

</head>
<body>
<form action="meals" method="post">
    <input type="hidden" name="id" value="${meal.id}">
    <label><input type="number" name="calories" value="${meal.calories}"/>Calories</label>
    <label><input type="datetime-local" name="date" value="${meal.dateTime}"/>Date</label>
    <label><input type="text" name="description" value="${meal.description}"/>Description</label>
    <br>
    <input type="submit" value="Submit"/>
</form>

</body>
</html>
