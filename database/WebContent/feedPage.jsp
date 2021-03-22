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
<h2 style= "text-align: center;">Feed Page</h2>
<nav>
	<a href="profile">Profile</a>
	<a href="feed">Feed</a>
	<a href="community">Community</a>
</nav>

<br><a href="postPage.jsp">Post Image</a>
<br><br>
<form action="resetDatabase" method="post">
    <input type="submit" value="Initialize Database" />
</form>

<!-- Posts -->
<table border="1" width="70%" align="center">
<tr>
	<th>Feed</th>
</tr>
<c:forEach items="${imageList}" var="img">
<tr>
	<td><div style="textalign:center">
			${img.created} <!--A. idk why these values are swapped -->
			${img.poster}<!--B. idk why these values are swapped -->
			<br>
			<img style="height:200px;" src="${img.url}"alt="${img.details}"></img>
		</div>
	</td>
</tr>
</c:forEach>
</table>
<!-- end of posts -->

</body>
</html>