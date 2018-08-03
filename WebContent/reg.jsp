<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
        <style>
			table, th, td {
    			border: 1px solid black;
    			border-collapse: collapse;
			}
		</style>
    </head>
    <body>
        <form method="post" action="registration.jsp">
            <div style="text-align: center;">
            <table>
                <thead>
                    <tr>
                        <th colspan="2">Enter Information Here:</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Name</td>
                        <td><input type="text" name="uname"/></td>
                    </tr>
                   
                    <tr>
                        <td><input type="submit" value="Submit" /></td>
                        <td><input type="reset" value="Reset" /></td>
                    </tr>
                    <tr>
                        <td colspan="2"><a href="index.jsp">Login Here</a></td>
                    </tr>
                </tbody>
            </table>
            </div>
        </form>
    </body>
</html>