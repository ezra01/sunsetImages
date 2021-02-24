<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>People Application</title>
</head>
<body>
	<caption><h2>Sign In</h2></caption>
	
				
	<form action="login" method="post">
	<table border="1" cellpadding="5">
		<tr>
          <th>Username</th>
          <td><input type="text" name="email" size="50" required></td>
   		</tr>
   		<tr>
          <th>Password</th>
          <td><input  type="password" name="pw" size="50" required></td>
    	</tr>
    	<tr>
          <td colspan="2" align="center">
       		<input type="submit" value="Login" />
       		<a href="toRegistration">Sign Up</a>
          </td>
        </tr>
    </table>
	</form>
</body>
</html>