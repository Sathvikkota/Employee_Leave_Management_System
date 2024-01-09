<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link type="text/css" rel="stylesheet" href="css/style.css">
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
       
    </style>
</head>
<body>
    <%@ include file="adminnavbar.jsp" %>
    <br>
    <span class="blink">
        <h3 style="color: red; text-align: center;">${message}</h3>
    </span>
    <h3 style="text-align: center;"><u>Update Manager Status</u></h3>
    <table>
        <tr style="background-color: black; color: white;">
            <th>ID</th>
            <th>NAME</th>
            <th>DEPARTMENT</th>
            <th>LOCATION</th>
            <th>EMAIL ID</th>
            <th>CONTACT NO</th>
            <th>STATUS</th>
            <th>ACTION</th>
        </tr>
        <c:forEach items="${mngdata}" var="mng">
            <tr>
                <td><c:out value="${mng.id}" /></td>
                <td><c:out value="${mng.name}" /></td>
                <td><c:out value="${mng.department}" /></td>
                <td><c:out value="${mng.location}" /></td>
                <td><c:out value="${mng.email}" /></td>
                <td><c:out value="${mng.contact}" /></td>
                <c:choose>
                    <c:when test="${mng.active}">
                        <td style="background-color: green;">ACTIVE</td>
                    </c:when>
                    <c:otherwise>
                        <td style="background-color: red;">INACTIVE</td>
                    </c:otherwise>
                </c:choose>
                <td>
                    <a href='<c:url value="setmngstatus?id=${mng.id}&status=true"></c:url>'>Active</a>
                    <a href='<c:url value="setmngstatus?id=${mng.id}&status=false"></c:url>'>Inactive</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
