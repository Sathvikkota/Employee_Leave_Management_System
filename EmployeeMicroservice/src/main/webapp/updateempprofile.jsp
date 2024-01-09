<%@ page contentType="text/html; charset=ISO-8859-1" isELIgnored="false"%> 

<html>
<head>

<link type="text/css" rel="stylesheet" href="css/style.css">

<style>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800;900&display=swap');
*
{
	margin: 0;
	padding: 0;
	box-sizing: border-box;
  font-family: 'Poppins', sans-serif;
}
body {
        background-image: url('image/dd.jpg');
        background-size: 100%;
        background-repeat: no-repeat;
        background-attachment: fixed;
    }
body 
{
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
	flex-direction: column;
	
}
.box 
{
	position: relative;
	width: 380px;
	height: 680px;
	background: #1c1c1c;
	border-radius: 8px;
	overflow: hidden;
}
.box::before 
{
	content: '';
	z-index: 1;
	position: absolute;
	top: -50%;
	left: -50%;
	width: 380px;
	height: 420px;
	transform-origin: bottom right;
	background: linear-gradient(0deg,transparent,#45f3ff,#45f3ff);
	animation: animate 6s linear infinite;
}
.box::after 
{
	content: '';
	z-index: 1;
	position: absolute;
	top: -50%;
	left: -50%;
	width: 380px;
	height: 420px;
	transform-origin: bottom right;
	background: linear-gradient(0deg,transparent,#45f3ff,#45f3ff);
	animation: animate 6s linear infinite;
	animation-delay: -3s;
}
@keyframes animate 
{
	0%
	{
		transform: rotate(0deg);
	}
	100%
	{
		transform: rotate(360deg);
	}
}
form 
{
	position: absolute;
	inset: 2px;
	background: #28292d;
	padding: 20px 40px;
	border-radius: 8px;
	z-index: 2;
	display: flex;
	flex-direction: column;
}
h2 
{
	color: #45f3ff;
	font-weight: 500;
	text-align: center;
	letter-spacing: 0.1em;
}
h3
{
	color: rgb(0, 0, 0);
	font-weight: 50;
	text-align: center;
	letter-spacing: 0.1em;
}
.inputBox 
{
	position: relative;
	width: 300px;
	margin-top: 35px;
}
.inputBox input 
{
	position: relative;
	width: 100%;
	padding: 20px 10px 10px;
	background: transparent;
	outline: none;
	box-shadow: none;
	border: none;
	color: #23242a;
	font-size: 1em;
	letter-spacing: 0.05em;
	transition: 0.5s;
	z-index: 10;
}
.inputBox span 
{
	position: absolute;
	left: 0;
	padding: 20px 0px 10px;
	pointer-events: none;
	font-size: 1em;
	color: #8f8f8f;
	letter-spacing: 0.05em;
	transition: 0.5s;
}
.inputBox input:valid ~ span,
.inputBox input:focus ~ span 
{
	color: #45f3ff;
	transform: translateX(0px) translateY(-34px);
	font-size: 0.75em;
}
.inputBox i 
{
	position: absolute;
	left: 0;
	bottom: 0;
	width: 100%;
	height: 2px;
	background: #45f3ff;
	border-radius: 4px;
	overflow: hidden;
	transition: 0.5s;
	pointer-events: none;
	z-index: 9;
}
.inputBox input:valid ~ i,
.inputBox input:focus ~ i 
{
	height: 44px;
}
.links 
{
	display: flex;
	justify-content: space-between;
}
.links a 
{
	margin: 10px 0;
	font-size: 0.75em;
	color: #8f8f8f;
	text-decoration: beige;
}
.links a:hover, 
.links a:nth-child(2)
{
	color: #45f3ff;
}
input[type="submit"]
{
	border: none;
	outline: none;
	padding: 11px 25px;
	background: #45f3ff;
	cursor: pointer;
	border-radius: 4px;
	font-weight: 600;
	width: 100px;
	margin-top: 10px;
}
input[type="submit"]:active 
{
	opacity: 0.8;
}
.bg-video {
  position: absolute;
  top: 0;
  left: 0;
  min-width: 100%;
  min-height: 100%;
  width: auto;
  height: auto;
  z-index: -1;
  object-fit: cover;
}


</style>
</head>
<body>

<%@ include file="empnavbar.jsp" %>



<h3 align=center><u>Update Profile</u></h3>

<div class="box">
        <form  action="updateemp" method="post">
         
            <div class="inputBox">
                <input type="text" name="name" required="required" value="${emp.name}"/>
                <span>Name</span>
                <i></i>
            </div>
            <div class="inputBox">
                <input type="date" name="dob" required="required"  value="${emp.dateofbirth}"   />
                <span>Date Of Birth</span>
                <i></i>
            </div>
            <div class="inputBox">
                <td>
				<select name="dept" required>
				<option value="${emp.department}" selected>${emp.department}</option>
				<option value="SALES">Sales</option>
				<option value="TECHNICAL">Technical</option>
				<option value="MARKETING">Marketing</option>
				<option value="OTHERS">Others</option>
				</select>
				</td>
                <span>Department</span>
                <i></i>
            </div>
             <div class="inputBox">
                <input type="email" name="email" required  value="${emp.email}" readonly/>
                <span>Email</span>
                <i></i>
            </div>

            <div class="inputBox">
                <input type="password" name="pwd" required  value="${emp.password}" />
                <span>Password</span>
                <i></i>
            </div>
            <div class="inputBox">
                <input type="text" name="location" required value="${emp.location}"/>
                <span>Location</span>
                <i></i>
            </div>
            
            <div class="inputBox">
                <input type="text" name="contact" required value="${emp.contact}" />
                <span>Contact</span>
                <i></i>
            </div>
            <input type="submit" value="Update" class="button">
        </form>
    </div>

</body>

</html>