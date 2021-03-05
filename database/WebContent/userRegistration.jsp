<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sunset</title>
</head>
<body>
	<caption><h2>Sign In</h2></caption>
	<div class="errorMsg" style="color:red;font-size:24px;" >
		<%String msg =(String)request.getAttribute("message");	if(msg!=null){out.print(msg);}%>
	</div>
				
	<form action="signUp" method="post">
	<table border="1" cellpadding="5">
		<tr>
          <th>Username</th>
          <td><input  type="email" name="email" size="50" value="" onkeyup="this.setAttribute('value', this.value);" required></td>
   		</tr>
   		<tr>
          <th>Password</th>
          <td><input  type="password" name="pw" size="50" value="" onkeyup="this.setAttribute('value', this.value);" required></td>
    	</tr>
    	<tr>
          <th>Confirm Password</th>
          <td><input  type="password" name="pw2" size="50" value="" onkeyup="this.setAttribute('value', this.value);" required></td>
    	</tr>
    	<tr>
          <th>First Name</th>
          <td><input  type="text" name="fname" size="50" value="" onkeyup="this.setAttribute('value', this.value);" required></td>
    	</tr>
    	<tr>
          <th>Last Name</th>
          <td><input  type="text" name="lname" size="50" value="" onkeyup="this.setAttribute('value', this.value);" required></td>
    	</tr>
    	<tr>
          <th>Date of Birth</th>
          <td><input  type="date" name="dob" value="" onkeyup="this.setAttribute('value', this.value);" required></td>
    	</tr>
    	<tr>
          <th>Gender</th>
           <td><input type ="text"list="genders" name="gender" id ="gender">
           		<datalist id="genders">
				    <option value="Male">Male</option>
				    <option value="Female">Female</option>
				    <option value="Non-Binary">Non-Binary</option>
				    <option value="Gender Fluid">Gender Fluid</option>
				    <option value="I do not want to share"selected="selected">I do not want to share</option>
			  	</datalist>
			</td>
  
    	</tr>
    	<tr>
          <td colspan="2" align="center">
       		<input type="submit" value="Sign Up" />
       		<a href="toLogin">Already Have an Account</a>
       		
          </td>
        </tr>
    </table>
	</form>
</body>
</html>