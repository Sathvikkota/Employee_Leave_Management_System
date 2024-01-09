<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style>
        body {
            background-image: url('image/dd.jpg');
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }
        table {
            border-collapse: collapse;
            width: 10%;
            margin: 0 auto;
            background-color: #fff;
            border: 2px double #000; /* Add a double black border to the table */
        }
        th, td {
            padding: 3px;
            text-align: left;
            border-bottom: 1px solid #ddd;
            border: 2px double #000; /* Add a double black border to table cells */
        }
        th {
            background-color: #00b4d8;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        h3 {
            text-align: center;
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <%@ include file="adminnavbar.jsp" %>
    <br>
    <span class="blink">
        <h3 style="color: red; text-align: center;">${message}</h3>
    </span>
    <h3 style="text-align: center;"><u>Update Employee Status</u></h3>
    <table>
        <tr style="background-color: #00b4d8; color: white;">
            <th>ID</th>
            <th>NAME</th>
            <th>DEPARTMENT</th>
            <th>LOCATION</th>
            <th>EMAIL ID</th>
            <th>CONTACT NO</th>
            <th>STATUS</th>
            <th>ACTION</th>
        </tr>
        <c:forEach items="${empdata}" var="emp">
            <tr>
                <td><c:out value="${emp.id}" /></td>
                <td><c:out value="${emp.name}" /></td>
                <td><c:out value="${emp.department}" /></td>
                <td><c:out value="${emp.location}" /></td>
                <td><c:out value="${emp.email}" /></td>
                <td><c:out value="${emp.contact}" /></td>
                <c:choose>
                    <c:when test="${emp.active}">
                        <td style="background-color: green;">ACTIVE</td>
                    </c:when>
                    <c:otherwise>
                        <td style="background-color: red;">INACTIVE</td>
                    </c:otherwise>
                </c:choose>
                <td>
                    <a href='<c:url value="setempstatus?id=${emp.id}&status=true"></c:url>'>Active</a>
                    <a href='<c:url value="setempstatus?id=${emp.id}&status=false"></c:url>'>Inactive</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
