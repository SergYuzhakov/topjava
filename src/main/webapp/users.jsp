<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="ru">
<head>
    <title>Users</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Users</h2>
    <form action="users" method="post">
<p>User: <%= request.getParameter("user") %></p>
        <input type="hidden" name="userId" value=<%= request.getParameter("user") %>>
    <button type="submit">Apply</button>
</form>
</body>
</html>