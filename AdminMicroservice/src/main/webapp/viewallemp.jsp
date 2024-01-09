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
 
 <%@ include file="mngnavbar.jsp" %>
 
<br> 
 
<h3 align="center"><u>View All Employees</u></h3> 
 
<table align=center  border=2>  
<tr bgcolor="black" style="color:white"> 
<td>ID</td> 
<td>NAME</td> 
<td>DEPARTMENT</td> 
<td>LOCATION</td> 
<td>EMAIL ID</td> 
<td>CONTACT NO</td> 
<td>ACTION</td>
</tr> 
 
<c:forEach items="${empdata}"  var="emp"> 
<tr> 
<td><c:out value="${emp.id}" /></td> 
<td><c:out value="${emp.name}" /></td> 
<td><c:out value="${emp.department}" /></td> 
<td><c:out value="${emp.location}" /></td> 
<td><c:out value="${emp.email}" /></td> 
<td><c:out value="${emp.contact}" /></td> 
<td>
<a href='<c:url value="viewempm?id=${emp.id}"></c:url>'>View</a>
</td>
 
</tr> 
</c:forEach> 
 
</table> 
 
</body> 
</html>