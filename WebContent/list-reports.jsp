<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
	<title>List Reports</title>
	<style>
	table, th, td {
    	border: 1px solid black;
    	border-collapse: collapse;
	}
	</style>
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>List Reports</h2>
		</div>
	</div>

	<div id="container">
	
		<div id="content">
		
		<!-- 	<input type="button" value="Add Student" 
				   onclick="window.location.href='add-student-form.jsp'; return false;"
				   class="add-student-button"
			/> -->
		
		<table>
				<caption>Agents' Score</caption>
				<tbody>
					<tr>
						<td><label>professional-agents:</label></td>
						<td>${RET_REPORT.agents13}</td>
					</tr>

					<tr>
						<td><label>level-12-agents:</label></td>
						<td>${RET_REPORT.agents12}</td>
					</tr>

					<tr>
						<td><label>level-11-agents:</label></td>
						<td>${RET_REPORT.agents11}</td>
					</tr>
					
					<tr>
						<td><label>level-10-agents:</label></td>
						<td>${RET_REPORT.agents10}</td>
					</tr>

					<tr>
						<td><label>level-9-agents:</label></td>
						<td>${RET_REPORT.agents9}</td>
					</tr>
					
					<tr>
						<td><label>level-8-agents:</label></td>
						<td>${RET_REPORT.agents8}</td>
					</tr>

					<tr>
						<td><label>level-7-agents:</label></td>
						<td>${RET_REPORT.agents7}</td>
					</tr>
					
					<tr>
						<td><label>level-6-agents:</label></td>
						<td>${RET_REPORT.agents6}</td>
					</tr>

					<tr>
						<td><label>level-5-agents:</label></td>
						<td>${RET_REPORT.agents5}</td>
					</tr>
					
					<tr>
						<td><label>level-4-agents:</label></td>
						<td>${RET_REPORT.agents4}</td>
					</tr>

					<tr>
						<td><label>level-3-agents:</label></td>
						<td>${RET_REPORT.agents3}</td>
					</tr>
					
					<tr>
						<td><label>level-2-agents:</label></td>
						<td>${RET_REPORT.agents2}</td>
					</tr>

					<tr>
						<td><label>level-1-agents:</label></td>
						<td>${RET_REPORT.agents1}</td>
					</tr>
				</tbody>
			</table><br>
		
			<p>Agents All: ${RET_REPORT.agentsAll}</p><br>
			<p>SubsidyA: ${RET_REPORT.subsidyA}</p><br>
			<p>SubsidyB: ${RET_REPORT.subsidyB}</p><br>
			<p>Subsidy: ${RET_REPORT.subsidy}</p><br>
			<p>Cost: ${RET_REPORT.cost}</p><br>
			<p>Budget: ${RET_REPORT.budget}</p><br>
			<p>mProduction: ${RET_REPORT.mProduction}</p><br>
			<p>umProduction: ${RET_REPORT.umProduction}</p><br>
			<p>combineAll: ${RET_REPORT.combineAll}</p><br>
				
				
			<div style="background-color:lightblue; width:20%; margin-bottom:10px;" >
			<table>
				<caption style="white-space: nowrap; overflow: hidden;">Agents Discharged</caption>
				<c:forEach var="ADname" items="${A_DISCHARGE_NAMES}">
					<tr> 
						<td> ${ADname} </td> 
					</tr>
				</c:forEach>
			</table><br>
			</div>
			
			
			<div style="background-color:lightblue; width:20%; margin-bottom:10px;" >
			<table>
				<caption style="white-space: nowrap; overflow: hidden;">UMs Discharged</caption>
				<c:forEach var="UMDname" items="${UM_DISCHARGE_NAMES}">
					<tr> 
						<td> ${UMDname} </td> 
					</tr>
				</c:forEach>
			</table><br>
			</div>
			
			
			<div style="background-color:lightblue; width:20%; margin-bottom:10px;" >
			<table>
				<caption style="white-space: nowrap; overflow: hidden;">Agents Hired</caption>
				
				<c:forEach var="AHname" items="${A_HIRE_NAMES}">
					<tr> 
						<td> ${AHname} </td>
					</tr>
				</c:forEach>
			</table><br>
			</div>
			
			<div style="background-color:lightblue; width:20%; margin-bottom:10px;" >
			<table>
				<caption style="white-space: nowrap; overflow: hidden;">UM Hired</caption>
					<tr> 
						<td> ${UM_HIRE_NAME} </td>
					</tr>
			</table><br>
			</div>
				
			
			<table>
				<caption>Department Simulation Score</caption>
				<tr>
					<th>season</th>
					<th>agentsAll</th>
					<th>mProduction</th>
					<th>combineAll</th>
					<th>subsidy</th>
					<th>budget</th>
					<th>cost</th>
				</tr>
				
				<c:forEach var="report" items="${RET_REPORT_GROUP}">
					<tr>
						<td> ${report.season} </td>
						<td> ${report.agentsAll} </td>
						<td> ${report.mProduction} </td>
						<td> ${report.combineAll} </td>
						<td> ${report.subsidy} </td>
						<td> ${report.budget} </td>
						<td> ${report.cost} </td>
					</tr>
				</c:forEach>
			</table><br>
			
			
			
			
			<table>
				<caption>Expected Year Score</caption>
				<tbody>
					<tr>
						<td><label>Year Subsidy:</label></td>
						<td>${RET_YEAR_REPORT.subsidy}</td>
					</tr>

					<tr>
						<td><label>Year Working Capital:</label></td>
						<td>${RET_YEAR_REPORT.working_capital}</td>
					</tr>

					<tr>
						<td><label>Year mProduction:</label></td>
						<td>${RET_YEAR_REPORT.mProduction}</td>
					</tr>
					
					<tr>
						<td><label>Year Renewal Commission:</label></td>
						<td>${RET_YEAR_REPORT.renewal_commision}</td>
					</tr>

					<tr>
						<td><label>Year Additional Subsidy:</label></td>
						<td>${RET_YEAR_REPORT.additional_subsidy}</td>
					</tr>
					
					<tr>
						<td><label>Total Score:</label></td>
						<td>${RET_YEAR_REPORT.total_score}</td>
					</tr>
					
				</tbody>
			</table>
			<p style="color: darkRed">Attention: Year Renewal Commission is only added to Total Score at the end of the year</p>
			<br>	
			
			<p>Agents plan to hire: ${RET_YEAR_REPORT.expected_ATR}</p><br>
			<p>Expected YEM: ${RET_YEAR_REPORT.expected_YEM}</p><br>
			<p>Actual YEM: ${RET_YEAR_REPORT.actual_YEM}</p><br>
		
		</div>
	
		<form name="listReportForm" action="WorkerControllerServlet" method="POST">
  			<input type="hidden" name="uname" value=<%=session.getAttribute("uname")%> />
			<input type="hidden" name="command" value="NEXT" />
  			<button>Continue</button>
		</form>
		
	</div>
</body>


</html>