<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<%-- <%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %>  --%>
<html>

<head>
	<title>Input</title>
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
			<h2>PICC training</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Welcome <%=session.getAttribute("uname")%></h3>
	</div>
	
	<div id="header">
		<h3><%=session.getAttribute("uname")%>'s Agents and Unit Managers</h3>
	</div>

	<div class="container">
				
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
	
	</div>
		
		
		<form name="overviewForm" action="WorkerControllerServlet" method="POST">
  			<input type="hidden" name="uname" value=<%=session.getAttribute("uname")%> />
			<input type="hidden" name="command" value="LIST" />
  			<button>See Your List of agents and UMs</button>
		</form>
		
		
		<button onclick="myFunction()">Debug Info</button>
		<script>
			function myFunction() {
				 
   				 alert("umNum: "+<%=session.getAttribute("umNum")%> + "\n" +
   					   "recruitingContinueScore: "+<%=session.getAttribute("recruitingContinueScore")%> + "\n" +
   					   "recruitingAccumulativePoint: "+<%=session.getAttribute("recruitingAccumulativePoint")%> + "\n" + 
   					   "recruitingUMContinueScore: "+<%=session.getAttribute("recruitingUMContinueScore")%> + "\n" +
 					   "recruitingUMAccumulativePoint: "+<%=session.getAttribute("recruitingUMAccumulativePoint")%> + "\n" + 
   					   "ANameIndex: "+<%=session.getAttribute("ANameIndex")%> + "\n" +
   					   "UMNameIndex: "+<%=session.getAttribute("UMNameIndex")%> + "\n" + 
   					   "UMaskContinueScore: "+<%=session.getAttribute("UMaskContinueScore")%>);
   					
			}
	 		
	 		
		</script>
		
		<h3>
			Your Quarterly Decision for Season <span id="seasonNote"></span>
			
			<script>
				function transform(seasonNum) {
					var ret;
			
					switch(seasonNum){
						case 1:
							ret = "1/1";
							break;
						case 2:
							ret = "1/2";
							break;
						case 3:
							ret = "1/3";
							break;
						case 4:
							ret = "1/4";
							break;
						case 5:
							ret = "2/1";
							break;
						case 6:
							ret = "2/2";
							break;
						case 7:
							ret = "2/3";
							break;
						case 8:
							ret = "2/4";
							break;
						case 9:
							ret = "3/1";
							break;
						case 10:
							ret = "3/2";
							break;
						case 11:
							ret = "3/3";
							break;
						case 12:
							ret = "3/4";
							break;
					}
					return ret;
				}
		</script>
			
			
		
		<script>
		function validateForm() {
			<%-- var max_UM_sum = 4 * <%=session.getAttribute("umNum")%>; --%>
			<%
				String newUMName = (String)session.getAttribute("newUMName");
				int newUMTime;
				int otherUMTime;
				int umNum = (int)session.getAttribute("umNum");
				if(newUMName == "None") {
					newUMTime = 0;
					otherUMTime = 4*umNum;
				}
				else {
					newUMTime = 4;
					int numOtherUM = (int)session.getAttribute("umNum") - 1;
					otherUMTime = 4 * numOtherUM;
					if(otherUMTime < 0){
						otherUMTime = 0;
					}
				}
			%>
			/* alert("Hello!"+ max_UM_sum); */
				
			var M_time = document.getElementsByClassName('M_time');
			var UM_time = document.getElementsByClassName('UM_time');
			var new_UM_time = document.getElementsByClassName('new_UM_time');
			// var campaign = document.getElementsByName('campaign');
				
			if (M_time) {
				var M_sum = 0;
				for(var i = 0; i < M_time.length; i++)
				{
			      if(M_time[i].value != null){
				    if(M_time[i].value < 0) {
				      document.getElementById("error").innerHTML = "Only positive inputs in M_time!";
				      return false;
					}
					else {
					  var to_add = Number(M_time[i].value);
					  M_sum+=to_add;
					}
			      }
			    }
				if(M_sum > 5) {
			    	document.getElementById("error").innerHTML = "Manager's time is greater then 5! Your input time is " + M_sum;
			    	return false;
				}
			} 
			if (new_UM_time) {
				var new_UM_sum = 0;
				var newUMTime = <%=newUMTime%>;
				for(var i = 0; i < new_UM_time.length; i++)
				{
			      if(new_UM_time[i].value != null){
				    if(new_UM_time[i].value < 0) {
				      document.getElementById("error").innerHTML = "Only positive inputs in M_time!";
				      return false;
					}
					else {
					  var to_add = Number(new_UM_time[i].value);
					  new_UM_sum+=to_add;
					}
			      }
			    }
				
				if(new_UM_sum > newUMTime) {
					document.getElementById("error").innerHTML = "New unit manager's time is greater then " + newUMTime + "! Your input time is " + new_UM_sum;
					return false;
				}
			  }
		
			
			if (UM_time){
				var UM_sum = 0;
				var otherUMTime = <%=otherUMTime%>;
				for(var i = 0; i < UM_time.length; i++)
				{
					if(UM_time[i].value != null){
						if(UM_time[i].value < 0) {
				    		document.getElementById("error").innerHTML = "Only positive inputs in UM_time!";
							return false;
						}
						else {
							var to_add = Number(UM_time[i].value);
					   		UM_sum+=to_add;
						}
				 	}
				}
				// if(UM_sum > max_UM_sum) { 
				if(UM_sum > otherUMTime) { 
					document.getElementById("error").innerHTML = "Other unit manager's total time is greater then your maximum time("+otherUMTime+")! Your input time is " + UM_sum;
			   		return false;
				}
		    }
			
			// return true
			/////////////////////////////
			var seasonNum = <%=session.getAttribute("seasonNum")%>;
			// first season of a year
			if(seasonNum % 4 == 1) {
				var x = document.inputForm.YEM.value;
	    		if (x == null || x == "") {
	    			document.getElementById("error").innerHTML = "Year End Manpower must be predicted at season 1!";
			   		return false;
	    		}
	    		
	    		var y = document.inputForm.YEP.value;
	    		if (y == null || y == "") {
	    			document.getElementById("error").innerHTML = "Year End Production must be predicted at season 1!";
			   		return false;
	    		}
			}
	    		
			return true;
		
		}
		</script>
			
		<script>
			document.getElementById("seasonNote").innerHTML = 
			transform(<%=session.getAttribute("seasonNum")%>);
		</script>
		</h3>
		
		<form name="inputForm" action="WorkerControllerServlet" onsubmit="return validateForm()" method="POST">
		
			<input type="hidden" name="command" value="ADD" />
			
			<table>
				<caption>Agents to Discharge</caption>
				<tbody>
					<tr>
						<td><label>1:</label></td>
						<td><input type="text" name="A_discharge_names" class="A_discharge"/></td>
					</tr>

					<tr>
						<td><label>2:</label></td>
						<td><input type="text" name="A_discharge_names" class="A_discharge"/></td>
					</tr>

					<tr>
						<td><label>3:</label></td>
						<td><input type="text" name="A_discharge_names" class="A_discharge"/></td>
					</tr>
					
					<tr>
						<td><label>4:</label></td>
						<td><input type="text" name="A_discharge_names" class="A_discharge"/></td>
					</tr>
					
					<tr>
						<td><label>5:</label></td>
						<td><input type="text" name="A_discharge_names" class="A_discharge"/></td>
					</tr>
					
					<tr>
						<td><label>6:</label></td>
						<td><input type="text" name="A_discharge_names" class="A_discharge"/></td>
					</tr>
					
					<tr>
						<td><label>7:</label></td>
						<td><input type="text" name="A_discharge_names" class="A_discharge"/></td>
					</tr>
				</tbody>
			</table><br>
			
			
			<table>
				<caption style="white-space: nowrap; overflow: hidden;">Unit Manager to Discharge</caption>
				<tbody>
					<tr>
						<td><label>1:</label></td>
						<td><input type="text" name="UM_discharge_names" class="UM_discharge"/></td>
					</tr>

					<tr>
						<td><label>2:</label></td>
						<td><input type="text" name="UM_discharge_names" class="UM_discharge"/></td>
					</tr>

					<tr>
						<td><label>3:</label></td>
						<td><input type="text" name="UM_discharge_names" class="UM_discharge"/></td>
					</tr>
					
					<tr>
						<td><label>4:</label></td>
						<td><input type="text" name="UM_discharge_names" class="UM_discharge"/></td>
					</tr>
					
					<tr>
						<td><label>5:</label></td>
						<td><input type="text" name="UM_discharge_names" class="UM_discharge"/></td>
					</tr>
					
					<tr>
						<td><label>6:</label></td>
						<td><input type="text" name="UM_discharge_names" class="UM_discharge"/></td>
					</tr>
					
					<tr>
						<td><label>7:</label></td>
						<td><input type="text" name="UM_discharge_names" class="UM_discharge"/></td>
					</tr>
				</tbody>
			</table><br>
			
			<table>
				<caption style="white-space: nowrap; overflow: hidden;">
					Ask an Established Agent to become a Unit Manager
				</caption>
				<tbody>
					<tr>
						<td><label>No</label></td>
						<td><input type="radio" name="UMask" value="0" required></td>
					</tr>

					<tr>
						<td><label>Yes</label></td>
						<td><input type="radio" name="UMask" value="1"></td>
					</tr>
				</tbody>
			</table><br>
			
			<table>
				<caption style="white-space: nowrap; overflow: hidden;">Which Agents will be trained</caption>
				<tbody>
					<tr>
						<th>No.</th>
						<th>Name</th>
						<th>Training Points</th>
					</tr>
				
					<tr>
						<td><label>1:</label></td>
						<td><input type="text" id="training_names1" name="training_names" class="A_traning"/></td>
						<td><input type="number" id="training_points1" name="training_points"/></td>
					</tr>

					<tr>
						<td><label>2:</label></td>
						<td><input type="text" id="training_names2" name="training_names" class="A_traning"/></td>
						<td><input type="number" id="training_points2" name="training_points"/></td>
					</tr>

					<tr>
						<td><label>3:</label></td>
						<td><input type="text" id="training_names3" name="training_names" class="A_traning"/></td>
						<td><input type="number" id="training_points3" name="training_points"/></td>
					</tr>
					
					<tr>
						<td><label>4:</label></td>
						<td><input type="text" id="training_names4" name="training_names" class="A_traning"/></td>
						<td><input type="number" id="training_points4" name="training_points"/></td>
					</tr>
					
					<tr>
						<td><label>5:</label></td>
						<td><input type="text" id="training_names5" name="training_names" class="A_traning"/></td>
						<td><input type="number" id="training_points5" name="training_points"/></td>
					</tr>
					
					<tr>
						<td><label>6:</label></td>
						<td><input type="text" id="training_names6" name="training_names" class="A_traning"/></td>
						<td><input type="number" id="training_points6" name="training_points"/></td>
					</tr>
					
					<tr>
						<td><label>7:</label></td>
						<td><input type="text" id="training_names7" name="training_names" class="A_traning"/></td>
						<td><input type="number" id="training_points7" name="training_points"/></td>
					</tr>
					
					<tr>
						<td><label>8:</label></td>
						<td><input type="text" id="training_names8" name="training_names" class="A_traning"/></td>
						<td><input type="number" id="training_points8" name="training_points"/></td>
					</tr>
					
					<tr>
						<td><label>9:</label></td>
						<td><input type="text" id="training_names9" name="training_names" class="A_traning"/></td>
						<td><input type="number" id="training_points9" name="training_points"/></td>
					</tr>
					
					<tr>
						<td><label>10:</label></td>
						<td><input type="text" id="training_names10" name="training_names" class="A_traning"/></td>
						<td><input type="number" id="training_points10" name="training_points"/></td>
					</tr>
					
					<tr>
						<td><label>11:</label></td>
						<td><input type="text" id="training_names11" name="training_names" class="A_traning"/></td>
						<td><input type="number" id="training_points11" name="training_points"/></td>
					</tr>
					
					<tr>
						<td><label>12:</label></td>
						<td><input type="text" id="training_names12" name="training_names" class="A_traning"/></td>
						<td><input type="number" id="training_points12" name="training_points"/></td>
					</tr>
					
					<tr>
						<td><label>13:</label></td>
						<td><input type="text" id="training_names13" name="training_names" class="A_traning"/></td>
						<td><input type="number" id="training_points13" name="training_points"/></td>
					</tr>
					
					<tr>
						<td><label>14:</label></td>
						<td><input type="text" id="training_names14" name="training_names" class="A_traning"/></td>
						<td><input type="number" id="training_points14" name="training_points"/></td>
					</tr>
					
					<tr>
						<td><label>15:</label></td>
						<td><input type="text" id="training_names15" name="training_names" class="A_traning"/></td>
						<td><input type="number" id="training_points15" name="training_points"/></td>
					</tr>
					
				</tbody>
			</table><br>
			
			
			<table>
				<caption>Allocation of Manager's Time(5)</caption>
				<tbody>
					<tr>
						<th>Action</th>
						<th>Manager's Time(5)</th>
					</tr>
					
					<tr>
						<td><label>Training Quarter 1:</label></td>
						<td><input type="number" id="M_training1" name="M_training1" class="M_time" min=0/></td>
					</tr>

					<tr>
						<td><label>Training Quarter 2:</label></td>
						<td><input type="number" id="M_training2" name="M_training2" class="M_time" min=0/></td>
					</tr>

					<tr>
						<td><label>Training Quarter 3-6:</label></td>
						<td><input type="number" id="M_training36" name="M_training36" class="M_time" min=0/></td>
					</tr>
					
					<tr>
						<td><label>Training Quarter 7-12:</label></td>
						<td><input type="number" id="M_training712" name="M_training712" class="M_time" min=0/></td>
					</tr>
					
					<tr>
						<td><label>Training Established:</label></td>
						<td><input type="number" id="M_trainingE" name="M_trainingE" class="M_time" min=0/></td>
					</tr>
					
					<tr>
						<td><label>Recruiting Agents:</label></td>
						<td><input type="number" id="M_recruitingA" name="M_recruitingA" class="M_time" min=0/></td>
					</tr>
					
					
					<tr>
						<td><label>Recruiting a Unit Manager:</label></td>
						<td><input type="number" id="M_recruitingUM" name="M_recruitingUM" class="M_time" min=0/></td>
					</tr>
					
					<tr>
						<td><label>Training a Unit Manager:</label></td>
						<td><input type="number" id="M_trainingUM" name="M_trainingUM" class="M_time" min=0/></td>
					</tr>
					
					<tr>
						<td><label>General Promotion:</label></td>
						<td><input type="number" name="M_promotion" class="M_time" min=0/></td>
					</tr>
					
					<tr>
						<td><label>Personal Production:</label></td>
						<td><input type="number" name="M_production" class="M_time" min=0/></td>
					</tr>
					
					<tr>
						<td><label>Office Administration:</label></td>
						<td><input type="number" name="M_admin" class="M_time" min=0/></td>
					</tr>
				</tbody>
			</table><br>
			
			
			<table>
				<caption>Allocation of Unit Manager's Time(4 per UM)</caption>
				<caption>New Unit Manager: <%=session.getAttribute("newUMName")%> </caption>
				<tbody>
					<tr>
						<th>Action</th>
						
						<th>Newly Recruited UM's Time(<%=newUMTime%>)</th>
						
						<th>Other UMs' Time Combined(<%=otherUMTime%>)</th>
					</tr>
				
					<tr>
						<td><label>Training Quarter 1:</label></td>
						<td><input type="number" id="new_UM_training1" name="new_UM_training1" class="new_UM_time" min=0/></td>
						<td><input type="number" id="UM_training1" name="UM_training1" class="UM_time" min=0/></td>
					</tr>

					<tr>
						<td><label>Training Quarter 2:</label></td>
						<td><input type="number" id="new_UM_training2" name="new_UM_training2" class="new_UM_time" min=0/></td>
						<td><input type="number" id="UM_training2" name="UM_training2" class="UM_time" min=0/></td>
					</tr>

					<tr>
						<td><label>Training Quarter 3-6:</label></td>
						<td><input type="number" id="new_UM_training36" name="new_UM_training36" class="new_UM_time" min=0/></td>
						<td><input type="number" id="UM_training36" name="UM_training36" class="UM_time" min=0/></td>
					</tr>
					
					<tr>
						<td><label>Training Quarter 7-12:</label></td>
						<td><input type="number" id="new_UM_training712" name="new_UM_training712" class="new_UM_time" min=0/></td>
						<td><input type="number" id="UM_training712" name="UM_training712" class="UM_time" min=0/></td>
					</tr>
					
					<tr>
						<td><label>Training Established:</label></td>
						<td><input type="number" id="new_UM_trainingE" name="new_UM_trainingE" class="new_UM_time" min=0/></td>
						<td><input type="number" id="UM_trainingE" name="UM_trainingE" class="UM_time" min=0/></td>
					</tr>
					
					<tr>
						<td><label>Recruiting Agents:</label></td>
						<td><input type="number" id="new_UM_recruitingA" name="new_UM_recruitingA" class="new_UM_time" min=0/></td>
						<td><input type="number" id="UM_recruitingA" name="UM_recruitingA" class="UM_time" min=0/></td>
					</tr>
					
					<tr>
						<td><label>Training a Unit Manager:</label></td>
						<td><input type="number" id="new_UM_trainingUM" name="new_UM_trainingUM" class="new_UM_time" min=0/></td>
						<td><input type="number" id="UM_trainingUM" name="UM_trainingUM" class="UM_time" min=0/></td>
					</tr>
					
					<tr>
						<td><label>General Promotion:</label></td>
						<td><input type="number" name="new_UM_promotion" class="new_UM_time" min=0/></td>
						<td><input type="number" name="UM_promotion" class="UM_time" min=0/></td>
					</tr>
					
					<tr>
						<td><label>Personal Production:</label></td>
						<td><input type="number" name="new_UM_production" class="new_UM_time" min=0/></td>
						<td><input type="number" name="UM_production" class="UM_time" min=0/></td>
					</tr>
					
				</tbody>
			</table><br>
			
			<table>
				<caption style="white-space: nowrap; overflow: hidden;">Run Sales Campaign</caption>
				<tbody>
					<tr>
						<td><label>None:</label></td>
						<td><input type="radio" name="campaign" value="0" required></td>
					</tr>

					<tr>
						<td><label>Whoopie:</label></td>
						<td><input type="radio" name="campaign" value="0.1"></td>
					</tr>

					<tr>
						<td><label>Whooray:</label></td>
						<td><input type="radio" name="campaign" value="0.2"></td>
					</tr>
					
					<tr>
						<td><label>Whingdang:</label></td>
						<td><input type="radio" name="campaign" value="0.3"></td>
					</tr>
				</tbody>
			</table><br>
			
			<label>Agents to recruit this quarter:</label> <input type="number" name="ATR" min=0> <br>
			<label>Year End Manpower:</label> <input type="number" name="YEM" min=0> <br>
			<label>Year End Production:</label> <input type="number" name="YEP" min=0> <br>
			
			<br>
			<input type="submit" value="Submit"/>
			
			<br>
			<span id="error"></span> 
			
		</form>
		
</body>

</html>
