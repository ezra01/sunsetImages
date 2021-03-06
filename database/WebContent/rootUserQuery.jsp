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
<h2 style= "text-align: center;">Query Result</h2>
<h3 style= "text-align: center;">${queryDesc}</h3>
<br><br>

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

<!-- Users -->
<table border="1" width="70%" align="center">
<tr>
	<th>User</th>
</tr>
<c:forEach items="${userList}" var="x" varStatus="indexNum">
<tr>
	
	<td>
			<div style="textalign:center;max-width: 800px">
				 <!--A. idk why these values are swapped -->
				<a href="${pageContext.request.contextPath}/profile?username=${x.email}">${x.email}</a>
				
			</div>
	</td>
	<td>
		
	</td>

	
</tr>
</c:forEach>
</table>
<!-- end of posts -->

</body>
</html>