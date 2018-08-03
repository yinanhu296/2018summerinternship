<!DOCTYPE html>
<html>
<head>
	<title>Welcome</title>
	
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
			<h2>PICC training Login</h2>
		</div>
	</div>

     <form name="loginForm" action="login.jsp" method="POST">
            <div style="text-align: center;">
            <table border="1">
                <thead>
                    <tr>
                        <th colspan="2">Login Here</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Name:</td>
                        <td><input type="text" name="uname" value="" /></td>
                    </tr>
                    
                    <tr>
                        <td><input type="submit" value="Login" /></td>
                        <td><input type="reset" value="Reset" /></td>
                    </tr>
                    
                    <tr>
                        <td colspan="2"><a href="reg.jsp">Register Here</a></td>
                    </tr>
                </tbody>
            </table>
            </div>
        </form>
     
        <br><br>


		<form name="rankMembersForm" action=MemberControllerServlet method="GET">
			<input type="hidden" name="command" value="LIST" />
          	<input type="submit" value="see ranking of members"/>
    	</form>        
    	
        <br>
        <a href="del-members-name.jsp">Delete Members By Name</a><br>
        <a href="del-members-date.jsp">Delete Members By Date</a><br>
        <a href="del-members-all.jsp">Delete All Members</a><br>
        

	<!-- <div style="text-align: center;">
	<input type="button" value="Start" 
		onclick="window.location.href='user-input.jsp'; return false;"/> 
	</div> -->


</body>
</html>