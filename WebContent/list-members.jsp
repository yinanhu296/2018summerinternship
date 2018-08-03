<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
	<title>Members Ranking</title>
	<style>
	table, th, td {
    	border: 1px solid black;
    	border-collapse: collapse;
	}
	.container {
  		display: inline-flex;
	}
	</style>
</head>

<body>

	<div class="container">
	
		<div id="content">
				
			<table>
				<caption>
					All Members Ranking
				</caption>
				<tr>
					<th>Name</th>
					<th>Score</th>
					<th>Rank</th>
				</tr>
				<c:forEach var="tempMember" items="${MEMBERS_LIST}" varStatus="counter">
					<tr>
						<td> ${tempMember.uname} </td>
						<td> ${tempMember.score} </td>
						<td> ${counter.count} </td>
					</tr>
				</c:forEach>
			</table><br>
		
		<br>
		<a href="index.jsp">back to login page </a>
		
		</div>
	
	</div>
</body>


</html>