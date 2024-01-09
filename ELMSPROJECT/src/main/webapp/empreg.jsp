<%@ page contentType="text/html; charset=ISO-8859-1" isELIgnored="false"%>

<html>
<head>


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
	width: 420px;
	height: 680px;s
	background: #1c1c1c;
	border-radius: 8px;
	overflow: hidden;
	margin-top:30px;
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
	padding: 5px 40px;
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

.inputBox 
{
	position: relative;
	width: 300px;
	margin-top: 20px;
}
.inputBox input 
{
	position: relative;
	width: 100%;
	padding: 10px 10px 10px;
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
	transform: translateX(0px) translateY(-20px);
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
.gender-container {
    display: flex;
    align-items: center;
    margin-top: 18px; /* Add spacing as needed */
}

.gender-container input[type="radio"] {
    margin-right: 10px; /* Add some spacing between radio buttons */
}

.gender-container span {
margin-top: 30px;
    color: #8f8f8f;
    letter-spacing: 0.05em;
    font-size: 1em;
    margin-right: 10px;
    flex: 1; /* This will make the "Gender" label take up the remaining space */
}
.silver-label {
    color: silver;
}

</style>
</head>
<body>

<%@ include file="adminnavbar.jsp" %>

<div class="box">
        <form  action="insertemp" method="post" enctype="multipart/form-data">
         
            <div class="inputBox">
                <input type="text" name="name" required="required"/>
                <span>Name</span>
                <i></i>
            </div>
            <div class="inputBox gender-container">
            <span>Gender</span>
            <input type="radio" name="gender" value="MALE" required />
            <label for="male-radio" class="silver-label">MALE</label>
            <input type="radio" name="gender" value="FEMALE" required  />
            <label for="female-radio" class="silver-label">FEMALE</label>
            <input type="radio" name="gender" value="OTHERS" required />
            <label for="others-radio" class="silver-label">OTHERS</label>
            
            
        </div>
            <div class="inputBox">
                <input type="date" name="dob" required="required"/>
                <span>Date Of Birth</span>
                <i></i>
            </div>
           
            <div class="inputBox">
                <td>
				<select name="dept" required>
				<option value="">---Select---</option>
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
                <input type="number" name="salary" step="0.01" required/>
                <span>Salary</span>
                <i></i>
            </div>
            
             <div class="inputBox">
                <input type="email" name="email" pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}" placeholder="Enter a valid email address"  required/>
                <span>Email</span>
                <i></i>
            </div>
           
            

            <div class="inputBox">
                <input type="password" name="pwd" 	 required/>
                <span>Password</span>
                <i></i>
            </div>
            <div class="inputBox">
                <input type="text" name="location" required/>
                <span>Location</span>
                <i></i>
            </div>
            
            <div class="inputBox">
                <input type="text" name="contact" pattern="[789][0-9]{9}"  placeholder="Must be 10 digits" required/>
                <span>Contact</span>
                <i></i>
            </div>
            <div class="inputBox"> 
                <input type="file" name="profilepic" required="required"/> 
                <span>Profile Pic</span> 
                <i></i> 
            </div> 
            <center><input type="submit" value="Register" class="button"> </center>
        </form> 
    </div>


</body>
</html>

 
