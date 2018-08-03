<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Delete Members By Name</title>

	<style>
		table, th, td {
    		border: 1px solid black;
    		border-collapse: collapse;
		}
	</style>
</head>

<body>

	<div>
		<a href="index.jsp">Back to Login Page</a>
	</div><br>

	<div>
	<form name="delMembersNameForm" action="MemberControllerServlet" method="POST">
	<input type="hidden" name="command" value="DEL_NAME" />
	<table>
		<caption style="white-space: nowrap; overflow: hidden;">Member Names To Delete</caption>
		<tbody>
			<tr>
				<td><label>1:</label></td>
				<td><input type="text" name="del_members_names" class="del_members_names"/></td>
			</tr>

			<tr>
				<td><label>2:</label></td>
				<td><input type="text" name="del_members_names" class="del_members_names"/></td>
			</tr>

			<tr>
				<td><label>3:</label></td>
				<td><input type="text" name="del_members_names" class="del_members_names"/></td>
			</tr>
					
			<tr>
				<td><label>4:</label></td>
				<td><input type="text" name="del_members_names" class="del_members_names"/></td>
			</tr>
					
			<tr>
				<td><label>5:</label></td>
				<td><input type="text" name="del_members_names" class="del_members_names"/></td>
			</tr>
					
			<tr>
				<td><label>6:</label></td>
				<td><input type="text" name="del_members_names" class="del_members_names"/></td>
			</tr>
					
			<tr>
				<td><label>7:</label></td>
				<td><input type="text" name="del_members_names" class="del_members_names"/></td>
			</tr>
					
			<tr>
				<td><label>8:</label></td>
				<td><input type="text" name="del_members_names" class="del_members_names"/></td>
			</tr>
					
			<tr>
				<td><label>9:</label></td>
				<td><input type="text" name="del_members_names" class="del_members_names"/></td>
			</tr>
					
			<tr>
				<td><label>10:</label></td>
				<td><input type="text" name="del_members_names" class="del_members_names"/></td>
			</tr>
					
			<tr>
				<td><label>11:</label></td>
				<td><input type="text" name="del_members_names" class="del_members_names"/></td>
			</tr>
					
			<tr>
				<td><label>12:</label></td>
				<td><input type="text" name="del_members_names" class="del_members_names"/></td>
			</tr>
					
			<tr>
				<td><label>13:</label></td>
				<td><input type="text" name="del_members_names" class="del_members_names"/></td>
			</tr>
					
			<tr>
				<td><label>14:</label></td>
				<td><input type="text" name="del_members_names" class="del_members_names"/></td>
			</tr>
					
			<tr>
				<td><label>15:</label></td>
				<td><input type="text" name="del_members_names" class="del_members_names"/></td>
			</tr>
					
			</tbody>
		</table>
			
		<br>
		<input type="submit" value="Submit"/>

			
	</form>
	</div>

</body>
</html>