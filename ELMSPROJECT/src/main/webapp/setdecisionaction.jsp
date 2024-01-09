<%@ page contentType="text/html; charset=ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<html>
<head>
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

<%@ include file="mngnavbar.jsp" %>

<br>

<span class="blink">
    <h3 align="center" style="color:red">${message}</h3>
</span>

<h3 align="center"><u>Update Status</u></h3>

<table align="center" border="2">
    <tr bgcolor="black" style="color:white">
        <td>LEAVE ID</td>
        <td>EMP ID</td>
        <td>SUBJECT</td>
        <td>START DATE</td>
        <td>END DATE</td>
        <td>REASON</td>
        <td>Status</td>
        <td>ACTION</td>
    </tr>

    <c:forEach items="${reqdata}" var="lev">
        <tr>
            <td><c:out value="${lev.id}" /></td>
            <td><c:out value="${lev.empid}" /></td>
            <td><c:out value="${lev.subject}" /></td>
            <td><c:out value="${lev.startdate}" /></td>
            <td><c:out value="${lev.enddate}" /></td>
            <td><c:out value="${lev.reason}" /></td>

            <c:choose>
                <c:when test="${lev.status == 'TRUE'}">
                    <td bgcolor="green">ACCEPTED</td>
                    <td></td>
                </c:when>
                <c:when test="${lev.status == 'FALSE'}">
                    <td bgcolor="red">NOT ACCEPTED</td>
                    <td></td> 
                </c:when>
                <c:otherwise>
                    <td bgcolor="orange">PENDING</td>
                    <td>
                        <a href='<c:url value="setstatus?id=${lev.id}&status=TRUE"></c:url>'>ACCEPT</a>
                        <a href='<c:url value="setstatus?id=${lev.id}&status=FALSE"></c:url>'>REJECT</a>
                    </td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
</table>

</body>
</html>
