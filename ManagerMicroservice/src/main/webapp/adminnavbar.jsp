

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
      height:70;
      top: 0;
      width: 100%;
      background-color: black; /* Change the background color as desired */
      overflow: hidden;
      z-index: 2; /* Ensure the top navigation bar is displayed on top */
    }

    .topnav a {
      float: left;
      display: block;
      color: white;
      text-align: center;
      padding: 14px 16px;
      text-decoration: none;
    }

    /* Style for the navigation links (you can customize this further) */
    .topnav a:hover {
      background-color: #ddd;
      color: black;
    }

    .topnav a.active {
      background-color: #4CAF50; /* Change the background color for the active link */
      color: white;
    }

    .sidenav {
      height: 100%;
      width: 0; /* Initially set to 0 to hide the sidebar */
      position: fixed;
      z-index: 1;
      top: 35;
      left: 0;
      background-color: #142586;
      padding-top: 20px;
      overflow-x: hidden; /* Add this to hide the sidebar content when closed */
      transition: 0.5s; /* Add a smooth transition effect */
    }

    .sidenav a {
      display: none; /* Initially hide the sidebar content */
      padding: 16px 8px;
      text-decoration: none;
      font-size: 18px;
      color: #f2f2f2;
      display: block;
    }

    .sidenav a:hover {
      background-color: #444;
    }

    .content {
      margin-left: 0; /* Adjusted to 0 when sidebar is closed */
      padding: 20px;
      transition: 0.5s; /* Add a smooth transition effect */
    }

    .sidenav-closed {
      width: 0;
    }
   
  </style>
</head>
<body>
  <div class="topnav">
    <a href="adminlogin" style="float: right;">Logout</a>
    <button onclick="toggleSidebar()" style="float: left; margin: 14px 5px;"><i class="fa fa-bars"></i></button>
    <p style="color: white; float: right; margin: 14px 5px;">Welcome ${mname}</p>
  </div>
  <div class="sidenav">
  <a href="adminhome">Home</a>
  <a href="viewallemps">View All Employees</a>
  <a href="viewallmngs">View All Managers</a>
  <a href="updateempstatus">Update Employee Status</a>
  <a href="updatemngstatus">Update Manager Status</a>
  <a href="empreg">Add Employee</a>
  <a href="mngreg">Add Manager</a>
</div>
  <div class="content">
    <!-- Your page content goes here -->
  </div>
  <script>
    // JavaScript function to toggle the sidebar
    function toggleSidebar() {
      var sidebar = document.querySelector(".sidenav");
      var content = document.querySelector(".content");
      var sidebarLinks = document.querySelectorAll(".sidenav a");

      if (sidebar.style.width === "250px") {
        sidebar.style.width = "0";
        content.style.marginLeft = "0";
        sidebarLinks.forEach(function(link) {
          link.style.display = "none";
        });
      } else {
        sidebar.style.width = "250px";
        content.style.marginLeft = "260px";
        sidebarLinks.forEach(function(link) {
          link.style.display = "block";
        });
      }
    }
  </script>
</body>
</html>

 
