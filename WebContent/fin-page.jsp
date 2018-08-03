<!DOCTYPE html>
<html>
<head>
	<title>Finish Page</title>
	<style type="text/css">
 		table.center {
    		margin-left: auto;
    		margin-right: auto;
		}
	</style>
</head>
<body>

	<div id="container" style="text-align: center">
		<h3>Thank you</h3>
		<h3><%=session.getAttribute("uname")%>'s Final Score is: </h3>
		<h2 style="color: red"><%=session.getAttribute("final_score")%></h2>
	</div>
	
	<form name="returnForm" action=WorkerControllerServlet method="POST">
	
		<input type="hidden" name="command" value="FIN" />
	
        <div>
        	<table>
				<caption style="white-space: nowrap; overflow: hidden;">Save <%=session.getAttribute("uname")%>'s user data in database?</caption>
				<tbody>
					<tr>
						<td><label>Yes:</label></td>
						<td><input type="radio" name="saveData" value="1" required></td>
					</tr>

					<tr>
						<td><label>No:</label></td>
						<td><input type="radio" name="saveData" value="0"></td>
					</tr>
				</tbody>
			</table><br>
          
          <input type="submit" value="Return to Login Page"/>
          
        </div>
    </form>
	
</body>
</html>