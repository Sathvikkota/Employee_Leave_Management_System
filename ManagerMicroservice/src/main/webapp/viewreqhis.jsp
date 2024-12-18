<%@ page contentType="text/html; charset=ISO-8859-1" isELIgnored="false"%>  
<%@ taglib uri="jakarta.tags.core" prefix="c"%>  
  
<html>  
<head>  
  
  
<style>  
  body {
        background-image: url('image/dd.jpg');
        background-size: 100%;
        background-repeat: no-repeat;
        background-attachment: fixed;
    }
  table {  
  border-collapse: collapse;  
  width: 100%;  
}  
  
th, td {  
  padding: 8px;  
  text-align: left;  
  border-bottom: 1px solid #ddd;  
}  
  
tr:nth-child(even) {  
  background-color: #f2f2f2;  
}  
</style>  
</head>  
<body>  
  
 <%@ include file="empnavbar.jsp" %> 
  
<br>  
  
<h3 align="center"><u>View All Requests</u></h3>  
  
<table align=center  border=2>   
<tr bgcolor="black" style="color:white">  
<td>ID</td>  
<td>SUBJECT</td>  
<td>START DATE</td>  
<td>END DATE</td>  
<td>REASON</td>  
<td>STATUS</td> 
</tr>  
  
<c:forEach items="${reqdata}"  var="lev">  
<tr>  
<td><c:out value="${lev.empid}" /></td>  
<td><c:out value="${lev.subject}" /></td>  
<td><c:out value="${lev.startdate}" /></td>  
<td><c:out value="${lev.enddate}" /></td>  
<td><c:out value="${lev.reason}" /></td>  
<c:choose>
                <c:when test="${lev.status == 'TRUE'}">
                    <td bgcolor="green">ACCEPTED</td>
                </c:when>
                <c:when test="${lev.status == 'FALSE'}">
                    <td bgcolor="red">NOT ACCEPTED</td>
                </c:when>
                <c:otherwise>
                    <td bgcolor="orange">PENDING</td>
                </c:otherwise>
            </c:choose>
 
  
</tr>  
</c:forEach>  
  
</table>  
  
</body>  
</html>