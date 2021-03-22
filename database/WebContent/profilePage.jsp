<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sunset</title>
</head>
<body>
<nav>
	<a href="profile">Profile</a>
	<a href="feed">Feed</a>
	<a href="community">Community</a>
</nav>
<h2 style= "text-align: center;">${person.fName} ${person.lName}'s Profile</h2>
<div class="userInformation">
${person.email}<br>${person.gender}<br>${person.birthday}
<!-- Posts -->
<table border="1" width="70%" align="center">
<tr>
	<th>My Posts</th>
</tr>
<c:forEach items="${imageList}" var="img">
<tr>
	<td><img style="height:200px;" src="${img.url}"alt="${img.details}"></img></td>
</tr>
</c:forEach>
</table>
<!-- end of posts -->
</div>
</body>
</html>