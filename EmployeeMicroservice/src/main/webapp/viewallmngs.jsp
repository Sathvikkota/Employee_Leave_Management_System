<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <title>View All Managers</title>
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
            width: 50%;
            margin: 0 auto;
            background-color: #fff;
            border: 2px double #000; /* Add a double black border to the table */
        }
        th, td {
            padding: 8px;
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
    <h3>View All Managers</h3>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>NAME</th>
                <th>DEPARTMENT</th>
                <th>LOCATION</th>
                <th>EMAIL ID</th>
                <th>CONTACT NO</th>
                <th>ACTION</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${mngdata}" var="mng">
                <tr>
                    <td><c:out value="${mng.id}" /></td>
                    <td><c:out value="${mng.name}" /></td>
                    <td><c:out value="${mng.department}" /></td>
                    <td><c:out value="${mng.location}" /></td>
                    <td><c:out value="${mng.email}" /></td>
                    <td><c:out value="${mng.contact}" /></td>
                    <td>
                        <a href='<c:url value="viewmng?id=${mng.id}"></c:url>'>View</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
