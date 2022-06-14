<%--
  Created by IntelliJ IDEA.
  User: ramirosoto
  Date: 6/10/22
  Time: 8:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  if (session.getAttribute("name")==null){
    response.sendRedirect("login.jsp");
  }
%>

<!DOCTYPE html>
<html>

<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="css/reimStyle.css">
  <link rel="stylesheet" href="css/styles.css">
  <title>Reimbursement</title>

</head>
<body>
<input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
<%@include file="navbar.jsp"%>

<form method="post" action="reimbursement" enctype="multipart/form-data">

  <h1 style="margin-top: 40px; color: white;"><strong>Reimbursement form</strong></h1>

  <div class="form-group">
    <label for="title" style="color: white;"><span>Enter Amount</span></label>
    <input type="number" name="amount" id="title" class="form-controll" required="required"/>
  </div>
  <div class="form-group">
    <label for="caption" style="color: white;"><span>Enter Description</span></label>
    <input type="text" name="description" id="caption" class="form-controll" required="required"/>
  </div>

  <div class="form-group file-area">
    <label for="images" style="color: white;"><strong>Receipt Images</strong><span> Your images should be at least 400x300 wide</span></label>
    <input type="file" name="receipt" id="images" required="required" multiple="multiple" />
  </div>

  <div class="form-group">
    <button type="submit">submit</button>
  </div>

</form>

<link href='https://fonts.googleapis.com/css?family=Lato:100,200,300,400,500,600,700' rel='stylesheet' type='text/css'>

<%--<a href="http://scribblerockerz.com/drag-n-drop-file-input-without-javascript/" class="back-to-article" target="_blank">back to Article</a>--%>

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet" href="alert/dist/sweetalert.css">

<script type="text/javascript">
  var status = document.getElementById("status").value;
  if (status == "success"){
    swal("Congratulations", "Account created", "success");
  }
  // if (status == "invalidName"){
  //   swal("Sorry", "Invalid Name", "error");
  // }
  // if (status == "invalidLastName"){
  //   swal("Sorry", "Invalid Last Name", "error");
  // }
  // if (status == "invalidEmail"){
  //   swal("Sorry", "Invalid Email", "error");
  // }
  // if (status == "invalidUserName"){
  //   swal("Sorry", "Invalid Username", "error");
  // }
  // if (status == "invalidPassword"){
  //   swal("Sorry", "Invalid Password", "error");
  // }

</script>
</body>

</html>