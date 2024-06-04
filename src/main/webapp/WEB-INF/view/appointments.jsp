<%--
  Created by IntelliJ IDEA.
  User: mihailden
  Date: 04.06.2024
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <link
            rel="stylesheet"
            href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
            integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
            crossorigin="anonymous">
    <link rel="stylesheet" href="../../styles/styles.css">
    <link rel="stylesheet" href="../../styles/header.css">
    <link rel="stylesheet" href="../../styles/appointments.css">
</head>
<body>
<header class="header__container">
    <nav class="header__nav">
        <div class="header__nav-item">
            <a href="<c:url value='/home'/>" class="header__link">Home</a>
            <c:if test="${role == 'USER'}">
                <a href="<c:url value='/my-appointments'/>" class="header__link">My appointments</a>
            </c:if>
        </div>
        <div class="header__nav-item">
            <span class="header__link">${login}</span>
            <a href="<c:url value='/logout'/>" class="header__link">Logout</a>
        </div>
    </nav>
</header>
<div class="appointment-container">
    <h1 class="title">
        ${login} Appointments
    </h1>
    <c:if test="${userTimes != null}">
        <form method="post">
            <table class="table">
                <thead>
                <tr>
                    <th style="border-bottom: 0;" scope="col">#</th>
                    <th style="border-bottom: 0;" scope="col">Date</th>
                    <th style="border-bottom: 0;" scope="col">Doctor</th>
                    <th style="border-bottom: 0;" scope="col">Action</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="time" items="${userTimes}">
                    <tr>
                        <th scope="row">${time.id}</th>
                        <td>${time.hour}:${time.minutes}</td>
                        <c:if test="${time.doctor != null}">
                            <td>${time.doctor.name}</td>
                        </c:if>
                        <c:if test="${time.doctor == null}">
                            <td>-</td>
                        </c:if>
                        <td>
                            <input class="btn btn-outline-danger" type="submit" value="Delete" name="Delete">
                            <input type="hidden" value="${time.id}" name="timeId">
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>
    </c:if>
    <c:if test="${userTimes == null}">
        <span class="subtitle">No data</span>
    </c:if>
</div>


</body>
</html>
