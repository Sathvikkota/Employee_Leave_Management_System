<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>  
<!DOCTYPE html>  
<html>  
<head>  
    <title>ELMS</title>  
    <style>  
        /* Apply CSS styles to the body element for background and text color */  
        body {
        background-image: url('image/dd.jpg');
        background-size: 100%;
        background-repeat: no-repeat;
        background-attachment: fixed;
    }
        body {  
            background-color: #f3f3f3;  
            color: #333;  
            font-family: Arial, sans-serif;  
            text-align: center;  
            margin: 0;  
            padding: 0;  
        }  
  
        /* Style the container of the form */  
        .form-container {  
            background-color: #fff;  
            border-radius: 10px;  
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);  
            max-width: 400px;  
            margin: 0 auto;  
            padding: 20px;  
            text-align: left;  
        }  
  
        /* Style the form elements */  
        .form-container label {  
            display: block;  
            font-weight: bold;  
            margin: 10px 0;  
        }  
  
        .form-container input, .form-container select, .form-container textarea {  
            width: 100%;  
            padding: 10px;  
            margin: 5px 0;  
            border: 1px solid #ccc;  
            border-radius: 4px;  
        }  
  
        .form-container button {  
            background-color: #007BFF;  
            color: #fff;  
            border: none;  
            border-radius: 4px;  
            padding: 10px 20px;  
            cursor: pointer;  
        }  
  
        .form-container button:hover {  
            background-color: #0056b3;  
        }  
    </style>  
</head>  
<br>  
<body>  
  
    <%@ include file="empnavbar.jsp" %>  
    <br>  
    <div class="form-container">  
        <h1>Leave Request Form</h1>  
    <form id="leaveForm" class="needs-validation" method="post"  action="submitform" > 
              <div class="card-header"> 
                <h4>Leave Request</h4> 
              </div> 
              <div class="card-body"> 
                <div class="mb-3"> 
                  <label for="name">Subject</label> 
                  <input type="text" class="form-control" id="name" name="subject" required="required"> 
                  <div class="invalid-feedback"> 
                    Please provide your name. 
                  </div> 
                </div> 
                <div class="mb-3"> 
                  <label for="email">Employee ID</label> 
                  <input type="number" class="form-control" id="email" name="number" value="${eid}" required="required" readonly> 
                  <div class="invalid-feedback">  
                  </div> 
                </div> 
                
                <div class="mb-3"> 
                  <label for="startDate">Start Date</label> 
                  <input type="date" class="form-control" id="startDate" name="startDate" required="required"> 
                  <div class="invalid-feedback"> 
                    Please select a start date. 
                  </div> 
                </div> 
                <div class="mb-3"> 
                  <label for="endDate">End Date</label> 
                  <input type="date" class="form-control" id="endDate" name="endDate" required="required"> 
                  <div class="invalid-feedback"> 
                    Please select an end date. 
                  </div> 
                </div> 
                <div class="mb-3"> 
                  <label for="reason">Reason for Leave</label> 
                  <textarea class="form-control" id="reason" name="reason" rows="3" required="required"></textarea> 
                  <div class="invalid-feedback"> 
                    Please provide a reason for your leave. 
                  </div> 
                </div> 
               
                
              </div> 
              <div class="card-footer text-right"> 
                <button type="submit" class="btn btn-primary">Submit</button> 
              </div> 
            </form>  
    </div>  
</body>  
</html>