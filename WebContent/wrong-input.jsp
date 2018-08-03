<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Wrong Input</title>
</head>
<body>

	 <h3> Your Training Plan does not match your Training Time Allocation. You did not allocate 
     enough time for your Training Plan. Please reschedule your form! </h3>
     
     <p style="color:red"> 
     	<Strong> Cause: <span id="whatFault"></span> </Strong> 
     </p>
     
     <div>
		<a href="user-input.jsp">back to user input </a>
	 </div>
  
  
  	<script>
  	function getFault(wrongInputNum) {
  		
  		var given = <%= (int)session.getAttribute("wrongInputGiven") %>;
		var required = <%= (double)session.getAttribute("wrongInputRequired") %>;
  		
		
		if(wrongInputNum == 1) {
			var ret = "Not enough time allocated for training level 1 agents!";
			ret += "Your input: " + given + ";    Required: " + required;
			return ret;
		}
		if(wrongInputNum == 2) {
			var ret = "Not enough time allocated for training level 2 agents!";
			ret += "Your input: " + given + ";    Required: " + required;
			return ret;
		}
		if(wrongInputNum == 36) {
			var ret = "Not enough time allocated for training level 3-6 agents!";
			ret += "Your input: " + given + ";    Required: " + required;
			return ret;
		}
		if(wrongInputNum == 712) {
			var ret = "Not enough time allocated for training level 7-12 agents! \n";
			ret += "Your input: " + given + ";    Required: " + required;
			return ret;
		}
		else {
			return "?"
		}
	}
  	</script>
  
  
  	<script>
			document.getElementById("whatFault").innerHTML = 
			getFault(<%= (int)session.getAttribute("wrongInputNum")%>);
	</script>

</body>
</html>