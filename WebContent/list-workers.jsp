<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
	<title>List Agents and UMs</title>
	<!-- <link type="text/css" rel="stylesheet" href="css/style.css"> -->
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

	<div id="header">
		<h3><%=session.getAttribute("uname")%>'s Agents and Unit Managers</h3>
	</div>
	
	<div>
		<a href="user-input.jsp">back to user input </a>
	</div>

	<div class="container">
	
		<div id="content">
				
			<table>
				<caption>
					Agents
				</caption>
				<tr>
					<th>Name</th>
					<th>Level</th>
					<th>Training</th>
					<th>No Training</th>
				</tr>
				<c:forEach var="tempWorker" items="${WORKERS_LIST}">
					<tr>
						<td> ${tempWorker.name} </td>
						<td> ${tempWorker.level} </td>
						<td> ${tempWorker.training} </td>
						<td> ${tempWorker.no_training} </td> 
					</tr>
				</c:forEach>
			</table><br>
			
			<table>
				<caption style="white-space: nowrap; overflow: hidden;">
					Unit Managers
				</caption>
				<tr>
					<th>Name</th>
				</tr>
				<c:forEach var="tempUM" items="${UMS_LIST}">
					<tr>
						<td> ${tempUM.name} </td>
					</tr>
				</c:forEach>
			</table><br>
			
			
			<section>
			  <h4>Help</h4>
			  <ul>
  				<li>Name: name of your agent / unit manager</li>
  				<li>Level: level of your agent, level 13 = established agent</li>
 			    <li>Training: for all agents equal or above level 2 (except level 13 agents)
 			    	<ul>
		  				<li>0 = this agent need 2 more training points to level up</li>
		  				<li>1 = this agent need 1 more training point to level up</li>
					</ul>
 			    </li>
 			    <li>
 			    	<p>No Training: this agent has not received any training for this amount of seasons</p>
 			    	<p>The higher this value, the more likely the agent is going to fall off</p>
 			    </li>
			  </ul>
			</section>
		
		</div>
	
	</div>
</body>


</html>








