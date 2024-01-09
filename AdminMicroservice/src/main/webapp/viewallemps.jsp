<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <title>View All Employees</title>
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
            width: 80%;
            margin: 0 auto;
            background-color: #fff;
            border: 2px double #000; /* Add a double black border to the table */
        }
        th, td {
            padding: 10px;
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
        
        form {
        text-align: center;
        margin: 10px;
    }

    label {
        font-weight: bold;
    }

    select {
        padding: 5px;
        border: 1px solid #ccc;
        border-radius: 4px;
        margin: 5px;
    }

    input[type="submit"] {
        background-color: #00b4d8;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    /* Additional styles for better alignment */
    label, select {
        display: inline-block;
        vertical-align: middle;
    }
     table {
        margin: 0 auto; /* Center the table horizontally */
        margin-top: 20px; /* Add a top margin to move the table down */
        margin-right: 20px; /* Add a right margin to move the table to the right */
    }
    </style>
</head>
<body>
    <%@ include file="adminnavbar.jsp" %>
    <br>
    <h3>View All Employees</h3>
    <form action="viewallemps" method="post">
    <label for="departmentFilter">Filter by Department:</label>
    <select name="departmentFilter" id="departmentFilter">
        <option value="">All Departments</option>
        <option value="Sales">Sales</option>
        <option value="Technical">Technical</option>
        <option value="Marketing">Marketing</option>
    </select>
    <input type="submit" value="Apply Filter">
</form>

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
            <c:forEach items="${empdata}" var="emp">
                <tr>
                    <td><c:out value="${emp.id}" /></td>
                    <td><c:out value="${emp.name}" /></td>
                    <td><c:out value="${emp.department}" /></td>
                    <td><c:out value="${emp.location}" /></td>
                    <td><c:out value="${emp.email}" /></td>
                    <td><c:out value="${emp.contact}" /></td>
                    <td>
                        <a href='<c:url value="viewemp?id=${emp.id}"></c:url>'>View</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>