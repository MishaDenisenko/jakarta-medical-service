<%--
  Created by IntelliJ IDEA.
  User: mihailden
  Date: 03.06.2024
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>${doctor.name}</title>
    <link
            rel="stylesheet"
            href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
            integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
            crossorigin="anonymous">
    <link rel="stylesheet" href="../../styles/styles.css">
    <link rel="stylesheet" href="../../styles/header.css">
    <link rel="stylesheet" href="../../styles/doctor_info.css">
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
            <a href="<c:url value='/logout'/>" class="header__link">Logout</a>
        </div>
    </nav>
</header>
<div class="doctor-container">
    <h1 class="title">
        ${doctor.name}
    </h1>
    <c:choose>
        <c:when test="${role == 'USER'}">
            <c:if test="${doctorTimes != null}">
                <form method="post">
                    <table class="table">
                        <thead>
                        <tr>
                            <th style="border-bottom: 0;" scope="col">#</th>
                            <th style="border-bottom: 0;" scope="col">Date</th>
                            <th style="border-bottom: 0;" scope="col">Doctor</th>
                            <th style="border-bottom: 0;" scope="col">Status</th>
                            <th style="border-bottom: 0;" scope="col">Action</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach var="time" items="${doctorTimes}">
                            <tr>
                                <th scope="row">${time.id}</th>
                                <td>
                                    <input readonly type="text" name="hour" value="${time.hour}">
                                    :
                                    <input readonly type="text" name="minutes" value="${time.minutes}">
                                <c:if test="${time.doctor != null}">
                                    <td>${time.doctor.name}</td>
                                </c:if>
                                <c:if test="${time.doctor == null}">
                                    <td>-</td>
                                </c:if>
                                <td>${time.status}</td>
                                <td>
                                    <c:if test="${time.status == 'FREE'}">
                                        <input class="btn btn-outline-success" type="submit" value="Add_${time.id}" name="Add">
                                    </c:if>
                                    <c:if test="${time.status == 'BUSY'}">
                                        <c:if test="${time.user.login == login}">
                                            <input class="btn btn-outline-danger" type="submit" value="Delete_${time.id}" name="Delete">
                                        </c:if>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </form>
            </c:if>
            <c:if test="${doctorTimes == null}">
                <span class="subtitle">No data</span>
            </c:if>
        </c:when>
        <c:when test="${role == 'ADMIN'}">
            <c:if test="${doctorTimes != null}">
                <form method="post">
                    <table class="table" >
                        <thead>
                        <tr>
                            <th style="border-bottom: 0;" scope="col">#</th>
                            <th style="border-bottom: 0;" scope="col">Date</th>
                            <th style="border-bottom: 0;" scope="col">User</th>
                            <th style="border-bottom: 0;" scope="col">Status</th>
                            <th style="border-bottom: 0;" scope="col">Action</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach var="time" items="${doctorTimes}">
                            <tr>
                                <th scope="row">${time.id}</th>
                                <td>
                                    <input type="number" placeholder="hh" name="hour_${time.id}" value="${time.hour}">
                                    :
                                    <input type="number" placeholder="mm" name="minutes_${time.id}" value="${time.minutes}">
                                </td>
                                <c:if test="${time.user != null}">
                                    <td>${time.user.login}</td>
                                </c:if>
                                <c:if test="${time.user == null}">
                                    <td>-</td>
                                </c:if>
                                <td>${time.status}</td>
                                <td>
                                    <input href="/doctor?timeId=${time.id}" class="btn btn-outline-primary" type="submit" value="Change_${time.id}" name="Change">
                                    <input href="/doctor?timeId=${time.id}" class="btn btn-outline-danger" type="submit" value="Delete_${time.id}" name="Delete">
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <th scope="row"></th>
                            <td>
                                <input type="number" placeholder="hh" name="hour">
                                :
                                <input type="number" placeholder="mm" name="minutes">
                            </td>
                            <td></td>
                            <td></td>
                            <td>
                                <input class="btn btn-outline-success" type="submit" value="Add" name="Add">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>

            </c:if>
            <c:if test="${doctorTimes == null}">
                <form method="post">
                    <table class="table" >
                        <thead>
                        <tr>
                            <th style="border-bottom: 0;" scope="col">#</th>
                            <th style="border-bottom: 0;" scope="col">Date</th>
                            <th style="border-bottom: 0;" scope="col">User</th>
                            <th style="border-bottom: 0;" scope="col">Status</th>
                            <th style="border-bottom: 0;" scope="col">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th scope="row"></th>
                            <td>
                                <input type="number" placeholder="hh" name="hour">
                                :
                                <input type="number" placeholder="mm" name="minutes">
                            </td>
                            <td></td>
                            <td></td>
                            <td>
                                <input class="btn btn-outline-success" type="submit" value="Add" name="Add">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </c:if>
        </c:when>
    </c:choose>

</div>
</body>
</html>
