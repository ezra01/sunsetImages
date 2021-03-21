<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Post</title>
</head>
<body>
<caption><h2>Post</h2></caption>
	
				
	<form action="post" method="post">
	<table border="1" cellpadding="5">
		<tr>
          <th>URL</th>
          <td><input type="text" name="url" size="50" required></td>
   		</tr>
   		<tr>
          <th>Details</th>
          <td><input  type="text" name="details" size="50"></td>
    	</tr>
    	<tr>
          <td colspan="2" align="center">
       		<input type="submit" value="Post Image" />
       		<a href="feed">Back to Feed</a>
          </td>
        </tr>
    </table>
	</form>
</body>
</html>