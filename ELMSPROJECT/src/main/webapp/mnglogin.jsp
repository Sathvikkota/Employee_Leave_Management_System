<%@ page contentType="text/html; charset=ISO-8859-1" isELIgnored="false"%>

<html>
<head>
<link type="text/css" rel="stylesheet" href="css/login.css">

<style>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800;900&display=swap');
*
{
	margin: 0;
	padding: 0;
	box-sizing: border-box;
  font-family: 'Poppins', sans-serif;
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
	height: 460px;
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
	padding: 50px 40px;
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

/* Style for the CAPTCHA */
.captcha-box {
  margin-top: 0.009px;
  text-align: left;
  font-size: 1.1em;
  color: #45f3ff;
}
</style>
</head>
<body>

<%@ include file="navbar.jsp" %>
<video autoplay loop muted class="bg-video">
  <source src="videos/vdoo.mp4" type="video/mp4">
  Your browser does not support the video tag.
</video>
<br>

<span class="blink">
<h3 align=center style="color:red">${message}</h3>
</span>

<h3 align=center><u>Manager Login</u></h3>

<br>

<div class="box">
        <form id="loginForm" action="checkmnglogin" method="post" onsubmit="return checkCaptcha();">
            <h2>Sign in</h2>
            <div class="inputBox">
                <input type="email" name="email" pattern="[a-zA-Z0-9._%+-]+@[a-zAZ0-9.-]+\.[a-zA-Z]{2,}" required="required">
                <span>Email</span>
                <i></i>
            </div>
            <div class="inputBox">
                <input type="password" name="pwd" required="required">
                <span>Password</span>
                <i></i>
            </div>
            <div class="captcha-box" id="captchaText">
                <!-- CAPTCHA text goes here -->
            </div>
            <input type="text" id="userInput" placeholder="Enter CAPTCHA" required="required">
            <div class="links">
                <a href="#">Forgot Password ?</a>
            </div>
            <input type="submit" value="Login">
        </form>
    </div>
<script type="text/javascript">
function generateCaptchaText(length) {
    const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    let captchaText = '';
    for (let i = 0; i < length; i++) {
        const randomIndex = Math.floor(Math.random() * characters.length);
        captchaText += characters[randomIndex];
    }
    return captchaText;
}

// Function to update the CAPTCHA text and clear user input
function updateCaptcha() {
    const captchaElement = document.getElementById('captchaText');
    const userInputElement = document.getElementById('userInput');

    // Generate a new CAPTCHA text
    const newCaptchaText = generateCaptchaText(5);

    // Update the CAPTCHA text
    captchaElement.textContent = newCaptchaText;

    // Clear user input
    userInputElement.value = '';
}

// Function to check if the user input matches the CAPTCHA
function checkCaptcha() {
    const captchaElement = document.getElementById('captchaText');
    const userInputElement = document.getElementById('userInput');

    if (userInputElement.value === captchaElement.textContent) {
        // Optionally, update the CAPTCHA text for the next verification
        updateCaptcha();
        return true; // Allow the form to submit
    } else {
        alert('CAPTCHA verification failed. Please try again.');
        // Update the CAPTCHA text to create a new CAPTCHA
        updateCaptcha();
        return false; // Prevent the form from submitting
    }
}

// Initialize the CAPTCHA
updateCaptcha();

</script>

</body>
</html>
