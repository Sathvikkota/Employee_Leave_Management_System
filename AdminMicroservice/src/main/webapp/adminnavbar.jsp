<!DOCTYPE html>
	<html>
	<head>
	  <link type="text/css" rel="stylesheet" href="css/style.css">
	    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	  <title>Spring Boot MVC</title>
	  <style>
	    body {
	      margin: 0;
	      padding: 0;
	      font-family: Arial, sans-serif;
	      background-color: #f2f2f2;
	    }
	    .topnav {
	      position: fixed;
	      height: 70px;
	      top: 0;
	      width: 100%;
	      background-color: #023E8A;
	      overflow: hidden;
	      z-index: 2;
	    }
	
	    .topnav a {
	      float: left;
	      display: block;
	      color: white;
	      text-align: center;
	      padding: 14px 16px;
	      text-decoration: none;
	    }
	
	    .topnav a:hover {
	      background-color: #ddd;
	      color: black;
	    }
	
	    .topnav a.active {
	      background-color: #4CAF50;
	      color: white;
	    }
	
	    .sidenav {
	      height: 100%;
	      width: 0;
	      position: fixed;
	      z-index: 1;
	      top: 70px;
	      left: 0;
	      background: linear-gradient(to bottom, #023E8A, #0096C7, #90E0EF);
	      padding-top: 20px;
	      overflow-x: hidden;
	      transition: 0.5s;
	    }
	
	    .sidenav a {
  padding: 2px 2px; /* Reduce the top and bottom padding */
  text-decoration: none;
  font-size: 15px;
  color: black;
  display: block;
  text-align: left;
  margin-bottom: 5px; /* Add margin to separate links */
}
	
	    .sidenav a:hover {
	      background-color: white;
	    }
	
	    .content {
	      margin-left: 0;
	      padding: 20px;
	      transition: 0.5s;
	    }
	
	    .sidenav-closed {
	      width: 0;
	    }
	    .level {
	      font-size: 0.7em;
	      background-color: rgb(164, 189, 183, 0.5);
	      width: 220px;
	      height: 45px;
	      padding: 3px;
	      border-radius: 5px;
	      font-weight: bolder;
	      letter-spacing: 1px;
	      display: block;
	      margin: 0px auto 10px;
	      text-align: center;
	    }
	    .level p {
	      font-size: 20px;
	      font-weight: bold;
	      font-family: 'Your Desired Font', sans-serif;
	      color: #023E8A;
	      margin: 0;
	    }
	    .welcome-text {
	      font-size: 22px;
	      font-family: 'Your Desired Font', sans-serif;
	      color: white;
	      margin: 14px 16px;
	      float: left;
	    }
	  </style>
	</head>
	<body>
	  <div class="topnav">
	    <a href="adminlogin" style="float: right;"><p class="welcome-text">Logout</p></a>
	    <p class="welcome-text">InfinitumConnect</p>
	    <button onclick="toggleSidebar()" style="float: left; margin: 14px 5px;"><i class="fa fa-bars"></i></button>
	  </div>
	  <div class="sidenav">
	    <div class="level">
	      <p>ADMIN DASHBOARD</p>
	    </div>
	    
	     <a href="adminhome"><h3><i class="fa fa-home" style="font-size:36px"></i>Home</h3></a>
  <a href="viewallemps"><h3><i class="fa fa-home" style="font-size:36px"></i>View All Employees</h3></a>
  <a href="viewallmngs"><h3><i class="fa fa-home" style="font-size:36px"></i>View All Managers</h3></a>
  <a href="updateempstatus"><h3><i class="fa fa-home" style="font-size:36px"></i>Update Employee Status</h3></a>
  <a href="updatemngstatus"><h3><i class="fa fa-home" style="font-size:36px"></i>Update Manager Status</h3></a>
  <a href="empreg"><h3><i class="fa fa-home" style="font-size:36px"></i>Add Employee</h3></a>
  <a href="mngreg"><h3><i class="fa fa-home" style="font-size:36px"></i>Add Manager</h3></a>
	  </div>
	  <div class="content">
	    <!-- Your page content goes here -->
	  </div>
	  <script>
	    // JavaScript function to open the sidebar
	    function openSidebar() {
	      var sidebar = document.querySelector(".sidenav");
	      var content = document.querySelector(".content");
	      var sidebarLinks = document.querySelectorAll(".sidenav a");
	
	      sidebar.style.width = "250px";
	      content.style.marginLeft = "260px";
	      sidebarLinks.forEach(function(link) {
	        link.style.display = "block";
	      });
	    }
	
	    // Call the openSidebar() function when the page loads to keep the sidebar open
	    window.addEventListener("load", openSidebar);
	  </script>
	</body>
	</html>