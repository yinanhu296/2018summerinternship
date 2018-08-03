<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Delete Members By Date</title>
</head>

<body>

	<div>
		<a href="index.jsp">Back to Login Page</a>
	</div>

	<form name="delMembersDateForm" action="MemberControllerServlet" method="POST">
	<input type="hidden" name="command" value="DEL_DATE" />

		<h4>Delete All Members Registered on This Date: </h4>
		
		<div style="margin-bottom: 20px">
		
		<input type="number" name="del_members_date_year" class="del_members" min="2000" max="9999" style="width: 60px;" required/>
		-
		<input type="number" name="del_members_date_month" class="del_members" min="01" max="12" style="width: 30px;" required/>
		-
		<input type="number" name="del_members_date_day" class="del_members" min="01" max="31" style="width: 30px;" required/>
		
		</div>
		
		<p>Example: 2018-08-01</p>
		<p>Month or date having single digit must have a leading 0</p>
		
			
		<input type="submit" value="Submit"/>
			
	</form>

</body>
</html>