<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
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
<h2 align="center" style= "text-align: center;">Community Page</h2>

<!-- People -->
<div style="border:0;width:70%;display:flex;flex-direction: column;">
<c:forEach items="${personList}" var="x" varStatus="indexNum">
			<div style="float:left;width:230px;max-width:350px;margin:15px;padding:10px;background-color:SkyBlue;display:grid;">
				<div style="textalign:center;font-size:14px;">
					
					${x.fName} 
					<br>
					${x.lName}
					<br>
					${x.email}
					 
				</div>
				<div style="float:right;">
				<c:choose>
				<c:when test=""><button style="height:30px;">UnFollow</button></c:when>
				<c:otherwise><button style="height:30px;">Follow</button></c:otherwise>
				</c:choose>
				</div>
			</div>
</c:forEach>
</div>
<!-- end of people -->

</body>
</html>