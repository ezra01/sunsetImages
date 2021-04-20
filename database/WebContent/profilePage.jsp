<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%Cookie ck[] = request.getCookies(); %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sunset</title>
</head>
<body>
<nav>
	<a href="profile?username=<%=ck[0].getValue()%>">Profile</a>
	<a href="feed">Feed</a>
	<a href="community">Community</a>
</nav>
<h2 style= "text-align: center;">${person.fName} ${person.lName}'s Profile</h2>
<h4 class="sectionTitle">My Info</h4>
<div class="userInformation">
${person.email}<br>${person.gender}<br>${person.birthday}
</div>

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
			if(isUnlike){document.getElementById(num).innerHTML= "Like";}
			else{document.getElementById(num).innerHTML= "Unlike";}
			document.getElementById(("counter"+num)).innerHTML=this.responseText+" Likes";
		}	
	};
	var url = ('<%=request.getContextPath()%>'+'/AjaxServlet?action='+likeStr+'&id='+num +'&time='+ getTimeForURL());
	
	xml.open('GET',url,false);
	xml.send();
}
	</script>
<!-- Posts -->
<table border="1" width="60%" align="right">
<tr>
	<th>My Posts</th>
	<th>Likes</th>
	<th>Edit</th>
</tr>
<c:forEach items="${imageList}" var="img" varStatus="indexNum">
<tr>
	<td><img style="height:200px;;max-width: 800px" src="${img.url}"alt="${img.details}"> ${img.details} </img></td>
	<td>
		<div id="counter${x.imgId}">
		<c:choose>
			<c:when test="${empty likeList[indexNum.index].likecount }">0</c:when> <%-- IDK WHY likecount works but likeCount does not.... --%>
			<c:otherwise>${likeList[indexNum.index].likecount}</c:otherwise>
		</c:choose>
		Likes
		</div>
		<br>
		<button id="${img.imgId}" onclick="Like(this.id)">
				<c:choose>
					<c:when test="${likeList[indexNum.index].boolResult}">Unlike</c:when>
					<c:otherwise>Like</c:otherwise>
				</c:choose>
			</button>
	</td>
	<td>
		
	</td>
</tr>
</c:forEach>
</table>
<!-- end of posts -->
<!-- Followers / Fans -->
<h4 class="sectionTitle">Fans: ${fanCount}</h4>
<div style="border:0;width:25%;display:flex;flex-direction: column;">
<c:forEach items="${fanList}" var="x" varStatus="indexNum">
			<a class ="personBtn"
				href="${pageContext.request.contextPath}/profile?username=${x.email}"
				style="float:left;text-decoration:none;width:200px;max-width:350px;margin:15px;padding:10px;background-color:SkyBlue;display:grid;"
			>
				<div style="textalign:center;font-size:14px;">
					
					${x.fName} 
					<br>
					${x.lName}
					<br>
					${x.email}
					 
				</div>
			</a>
</c:forEach>
</div>
<!-- end of Followers / Fans -->
<!-- Followings / Idols -->
<h4 class="sectionTitle">Idols:  ${idolCount}</h4>
<div style="border:0;width:25%;display:flex;flex-direction: column;">
<c:forEach items="${idolList}" var="x" varStatus="indexNum">
			<a class ="personBtn"
				href="${pageContext.request.contextPath}/profile?username=${x.email}"
				style="float:left;text-decoration:none;width:200px;max-width:350px;margin:15px;padding:10px;background-color:SkyBlue;display:grid;"
			>
				<div style="textalign:center;font-size:14px;">
					
					${x.fName} 
					<br>
					${x.lName}
					<br>
				</div>
			</a>
</c:forEach>
</div>
<!-- end of Followers / Idols -->

</body>
</html>