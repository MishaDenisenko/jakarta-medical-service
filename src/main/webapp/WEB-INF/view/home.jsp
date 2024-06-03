<%--
  Created by IntelliJ IDEA.
  User: mihailden
  Date: 03.06.2024
  Time: 00:52
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>ADMIN</title>
</head>
<body>
<h1>${U}</h1>
<c:choose>
    <c:when test="${role == 'ADMIN'}">
        <h1>ADMIN</h1>
    </c:when>
    <c:when test="${role == 'USER'}">
        <h1>USER</h1>
    </c:when>
</c:choose>
<a href="<c:url value='/logout' />">Logout</a>
<a href="<c:url value='/another' />">Another</a>
</body>
</html>
