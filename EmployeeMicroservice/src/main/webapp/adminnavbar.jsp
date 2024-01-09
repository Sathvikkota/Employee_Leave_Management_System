
 
<!DOCTYPE html>
<html>
<head>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        .navbar {
            height: 100vh;
            width: 250px;
            position: fixed;
            top: 0;
            left: 0;
            background-color: #333;
            overflow-x: hidden;
            transition: 0.3s;
            padding-top: 60px;
        }

        .navbar a {
            padding: 8px 8px 8px 32px;
            text-decoration: none;
            font-size: 18px;
            color: #ccc;
            display: block;
            transition: 0.3s;
        }

        .navbar a:hover {
            color: #fff;
        }

        .navbar h2 {
            color: #fff;
            text-align: center;
            padding: 10px 0;
        }

        .navbar .closebtn {
            position: absolute;
            top: 0;
            left: 220px;
            padding: 10px 15px;
            cursor: pointer;
            color: #fff;
        }

        #main {
            transition: margin-left .5s;
            padding: 16px;
        }

        #menu-button {
            font-size: 30px;
            cursor: pointer;
            position: absolute;
            top: 20px;
            left: 20px;
            color: #ccc;
            z-index: 1;
        }

        #menu-button:hover {
            color: #fff;
        }

        .menu-open #menu-button {
            left: 250px;
        }

        .menu-open .navbar {
            width: 250px;
        }

        @media screen and (max-height: 450px) {
            .navbar {padding-top: 15px;}
            .navbar a {font-size: 16px;}
        }
    </style>
</head>
<body>

<div id="menu-button">&#9776;</div>

<div class="navbar" id="mySidenav">
    <div class="closebtn" onclick="closeNav()">&times;</div>
    <h2>DASHBOARD</h2>
     <a href="adminhome">Home</a><br>
  <a href="viewallemps">View All Employees</a><br>
  <a href="viewallmngs">View All Managers</a><br>
  <a href="updateempstatus">Update Employee Status</a><br>
  <a href="updatemngstatus">Update Manager Status</a><br>
  <a href="empreg">Add Employee</a><br>
  <a href="mngreg">Add Manager</a><br>
</div>



<script>
    function openNav() {
        document.getElementById("mySidenav").style.width = "250px";
        document.getElementById("main").style.marginLeft = "250px";
        document.body.classList.add('menu-open');
    }

    function closeNav() {
        document.getElementById("mySidenav").style.width = "0";
        document.getElementById("main").style.marginLeft = "0";
        document.body.classList.remove('menu-open');
    }

    document.getElementById('menu-button').addEventListener('click', openNav);
</script>

</body>
</html>
 