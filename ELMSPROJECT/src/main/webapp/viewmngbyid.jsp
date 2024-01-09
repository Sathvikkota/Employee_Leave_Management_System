<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Spring Boot Project</title>
    
    <style>
    	@import url("https://fonts.googleapis.com/css2?family=Nunito:wght@300&display=swap");

* {
  margin: 0;
  padding: 0;
  font-family: "Nunito", sans-serif;
}

:root {
  --green: #58a497;
  --yellow: #e09449;
  --lightgreen1: #a4bdb7;
  --brown: #8b4448;
  --gray: gray;
  --lightgreen2: rgb(164, 189, 183, 0.5);
  --box: #ededed;
}

body {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;

  background: #a4bdb7;
}

/* Content-1:Start */
.box {
  width: 350px;
  height: fit-content;
  border-radius: 20px;
  padding: 10px;
  text-align: center;
  background: #ededed;
}

.box1 {
  margin-top: 10px;
}

.content {
  margin: 15px 2px;
}

.image img {
  height: auto;
  width: 120px;
  border-radius: 50%;

  display: block;
  margin-left: auto;
  margin-right: auto;
  margin-bottom: 5px;
}

.level {
  font-size: 0.7em;
  background-color: rgb(164, 189, 183, 0.5);
  width: 70px;
  padding: 3px;
  border-radius: 5px;
  font-weight: bolder;
  letter-spacing: 1px;

  display: block;
  margin: 0px auto 10px;
}

.name {
  font-size: 1.25em;
  font-weight: bolder;
  letter-spacing: 1px;
}



.icons {
  margin: 0px 30px;
  font-size: 1.5em;
  display: flex;
  justify-content: space-around;
}

.icons button {
  width: fit-content;
  height: fit-content;
  border: none;
  font-size: 1em;
}

ion-icon:hover {
  color: #58a497;
  transition: 0.5s;
}

button {
  width: 40px;
  height: 40px;
  border-radius: 10px;
  font-weight: bolder;
}

.button {
  display: flex;
  justify-content: space-around;
  flex-direction: row;
  margin: 20px 30px 0px;
}

.button .message {
  background: #ededed;
  border: 2px solid #000;
}

.button .connect {
  background-color: #000;
  color: #ededed;
  border: none;
}
button.connect:hover {
  letter-spacing: 1px;
  transition: 0.5s;
}
button.message:hover {
  letter-spacing: 1px;
  transition: 0.5s;
  background: rgba(88, 164, 151, 0.5);
}
/* Content-1:End */



/* Responsiveness:Start */
@media screen and (max-width: 480px) {
    .box{
        width: 100vw;
        border-radius: 0px;
    }
    .button{
        display: flex;
        flex-direction: column;
    }
    .button button{
        width: 100px;
    }
    button.connect{
        margin-top: 10px;
    }
    .content2{
        padding: 0px 20px;
    }
    .content2 img{
        width: 60px;
        height: 60px;
    }
}
/* Responsiveness:End */
    </style>
    
</head>

<body>

<%@ include file="adminnavbar.jsp" %>
        
<section>

  <div class="box1 box">
    <div class="content">
      <div class="image">
        <img src="displayprofilepic?id=<c:out value='${mng.id}' ></c:out>" alt="Profile Image">
      </div>
      <div class="level">
        <p>MANAGER</p>
      </div>
      <div class="text">
        <p class="name">ID: ${mng.id}</p>
        <p class="name">Name: ${mng.name}</p>
        <p class="name">DOB: ${mng.dateofbirth}</p>
        <p class="name">Department: ${mng.department}</p>
        <p class="name">Salary: ${mng.salary}</p>
        <p class="name">Email: ${mng.email}</p>
        <p class="name">Location: ${mng.location}</p>
        <p class="name">Contact: ${mng.contact}</p>
      </div>
      
      
    </div>
  </div>

</section>

<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>






    
</body>

</html>
