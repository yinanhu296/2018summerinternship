<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete All Members</title>
</head>
<body>

	<div>
		<a href="index.jsp">Back to Login Page</a>
	</div><br>

	<div>
	<form name="delMembersAllForm" action="MemberControllerServlet" method="POST">
	<input type="hidden" name="command" value="DEL_ALL" />
	
		<h4 style="color: darkred"> 
			If you are sure you'd like to delete ALL members data, click <span style="color: orangered">Submit</span> button below. 
			All data about members(players) will lost, including their name and their score.
		</h4>
		
			
		<br>
		<input type="submit" value="Submit"/>
	
	</form>
	</div>



</body>
</html>