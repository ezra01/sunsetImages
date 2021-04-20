<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
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
<h2 align="center" style= "text-align: center;">Community Page</h2>

<script type = "text/javascript">
function getTimeForURL(){
	  var dt = new Date();
	  var strOutput = "";
	  strOutput = dt.getHours() + "_" + dt.getMinutes() + "_" + dt.getSeconds() + "_" + dt.getMilliseconds();
	  return strOutput;
	 }
	function Follow(){
		
		var followee = arguments[0].substring(4);
		
		var followStr = document.getElementById(arguments[0]).innerHTML.trim();
		var isFollowing;
		if(followStr=="Follow"){isFollowing = true;}
		else{isFollowing = false;}
		
		var xml = new XMLHttpRequest();
		xml.onreadystatechange = function(){
			if (this.readyState ==4 && this.status == 200){
				if(isFollowing){document.getElementById(("fbtn"+followee)).innerHTML= "Unfollow";}
				else{document.getElementById(("fbtn"+followee)).innerHTML= "Follow";}
			}	
		};
		var url = ('<%=request.getContextPath()%>'+'/AjaxServlet?action=Follow&id='+followee +'&time='+ getTimeForURL());
		
		xml.open('GET',url,false);
		xml.send();
		
		
	}
	</script>

<!-- People -->
<div style="border:0;width:70%;display:flex;flex-direction: column;">
<c:forEach items="${personList}" var="x" varStatus="indexNum">
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
				<div style="float:right;">
				<button id="fbtn${x.email}" style="height:30px;" onclick="Follow(this.id)">
					<c:choose>
						<c:when test="${followList[indexNum.index].idol!=NULL}">Unfollow</c:when>
						<c:otherwise>Follow</c:otherwise>
					</c:choose>
				</button>
				</div>
			</a>
</c:forEach>
</div>
<!-- end of people -->

</body>
</html>