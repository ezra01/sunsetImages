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
<h2 style= "text-align: center;">Feed Page</h2>

<br><a href="postPage.jsp">Post Image</a>
<br><br>
<form action="resetDatabase" method="post">
    <input type="submit" value="Initialize Database" />
</form>

<!-- Posts -->
<table border="1" width="70%" align="center">
<tr>
	<th>Feed</th>
	<th>Like</th>
	<th>Edit</th>
</tr>
<c:forEach items="${imageList}" var="x" varStatus="indexNum">
<tr>
	
	<td>
			<div style="textalign:center">
				${x.created} <!--A. idk why these values are swapped -->
				${x.poster}<!--B. idk why these values are swapped -->
			<br>
				<img style="height:200px;" src="${x.url}"alt="${x.details}"></img>
			</div>
	</td>
	<td>
		<c:choose>
			<c:when test="${empty likeList[indexNum.index].likecount }">0</c:when> <%-- IDK WHY likecount works but likeCount does not.... --%>
			<c:otherwise>${likeList[indexNum.index].likecount}</c:otherwise>
		</c:choose>
		Likes
		<br>
		<c:choose>
			<c:when test="${likeList[indexNum.index].boolResult}"><button>Unlike</button></c:when>
			<c:otherwise><button>Like</button></c:otherwise>
		</c:choose>
	</td>
	<td>
		
	</td>	
	
</tr>
</c:forEach>
</table>
<!-- end of posts -->

</body>
</html>