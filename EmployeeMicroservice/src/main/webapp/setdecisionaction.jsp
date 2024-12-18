<%@ page contentType="text/html; charset=ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <style></style>
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
