<%@ page contentType="text/html; charset=ISO-8859-1" isELIgnored="false"%> 

<html>
<head>
<link type="text/css" rel="stylesheet" href="css/style.css">

<style>
body {
    background-image: url('image/dd.jpg');
    background-size: 100%;
    background-repeat: no-repeat;
    background-attachment: fixed;
}
.statistics-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: flex-end; /* Move containers to the right */
    align-items: center;
    height: 100vh;
    margin-top: 50px;
    animation: fallIn 1.5s ease-in-out;
    margin-right: 10%; /* Leave some gap on the right */
}
@keyframes fallIn {
    0% {
        transform: translateY(-200px);
    }
    100% {
        transform: translateY(0);
    }
}
.statistics-box:hover {
    transform: scale(1.1); /* Zoom out the card */
    background-color: #e7c798; /* Change the background color */
}
.statistics-box p {
    font-size: 48px;
    margin: 0;
    color: #008000; /* Green color for the number values */
}

.statistics-box {
    flex: 1;
    max-width: 300px;
    background-color: #cad9db;
    padding: 20px;
    margin: 10px;
    box-shadow: 0 5px 10px rgba(163, 157, 157, 0.2);
    text-align: center;
    color: #0a0808;
    border-radius: 10px;
}

.statistics-box h2 {
    font-size: 36px;
    margin-bottom: 20px;
}

.statistics-box p {
    font-size: 48px;
    margin: 0;
}
</style>
</head>
<body>
<%@ include file="empnavbar.jsp" %>

<h1>Welcome ${ename}</h1>

<div class="statistics-container">
    <div class="statistics-box">
        <h3>Total Predefined Leaves</h3>
        <h3>12</h3>
    </div>
    
    <div class="statistics-box">
        <h3>Leaves Used</h3>
        <h3>3</h3>
    </div>  
    <div class="statistics-box">
        <h3>Leaves Left</h3>
        <h3>9</h3>
    </div>  
</div>

</body>
</html>
