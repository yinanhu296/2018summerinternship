<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Page Redirection</title>
    </head>
    <body onload="document.myForm.submit()">
        If you are not redirected automatically, click the redirect button below.
       	<form name="myForm" action="WorkerControllerServlet" method="GET">
       		<input type="submit" value="Redirect"/>
       	</form>
    </body>
</html>