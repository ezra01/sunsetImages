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

<script type = "text/javascript">
function getTimeForURL(){
	  var dt = new Date();
	  var strOutput = "";
	  strOutput = dt.getHours() + "_" + dt.getMinutes() + "_" + dt.getSeconds() + "_" + dt.getMilliseconds();
	  return strOutput;
	 }
	function Like(){
		
		var num = arguments[0];
		var likeStr = document.getElementById(num).innerHTML.trim();
		var isUnlike;
		if(likeStr=="Like"){isUnlike = false;}
		else{isUnlike = true;}
		
		var xml = new XMLHttpRequest();
		xml.onreadystatechange = function(){
			if (this.readyState ==4 && this.status == 200){
				document.getElementById(("counter"+num)).innerHTML=this.responseText+" Likes"; 
				if(isUnlike){document.getElementById(num).innerHTML= "Like";}
				else{document.getElementById(num).innerHTML= "Unlike";}
			}	
		};
		var url = ('<%=request.getContextPath()%>'+'/AjaxServlet?action='+likeStr+'&id='+num +'&time='+ getTimeForURL());
		
		xml.open('GET',url,false);
		xml.send();
		xml
	}
	</script>

<!-- Posts -->
<table border="1" width="70%" align="center">
<tr>
	<th>Feed</th>
	<th>Like</th>
</tr>
<c:forEach items="${imageList}" var="x" varStatus="indexNum">
<tr>
	
	<td>
			<div style="textalign:center;max-width: 800px">
				${x.created} <!--A. idk why these values are swapped -->
				${x.poster}<!--B. idk why these values are swapped -->
			<br>
				<img style="height:200px;" src="${x.url}"alt="${x.details}"></img>
				${x.details} ${x.imgId}
			</div>
	</td>
	<td>
		<div class="likeContainer" >
			<div id="counter${x.imgId}">
				<c:choose>
					<c:when test="${empty likeList[indexNum.index].likecount }">0</c:when> <%-- IDK WHY likecount works but likeCount does not.... --%>
					<c:otherwise>${likeList[indexNum.index].likecount}</c:otherwise>
				</c:choose>
				Likes
			</div>
			<br>
			<button id="${x.imgId}" onclick="Like(this.id)">
				<c:choose>
					<c:when test="${likeList[indexNum.index].boolResult}">Unlike</c:when>
					<c:otherwise>Like</c:otherwise>
				</c:choose>
			</button>
		</div>
	</td>

	
</tr>
</c:forEach>
</table>
<!-- end of posts -->

</body>
</html>