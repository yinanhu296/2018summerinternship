
function transform(seasonNum) {
			var ret;
			if(seasonNum == 1){
				return "1/1";
			}
			if(seasonNum == 2){
				return "1/2";
			}
			
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

function validateForm123() {
	return false;
}


function validateForm() {
	
	var M_time = document.getElementsByClassName('M_time');
	var UM_time = document.getElementsByClassName('UM_time');
	var campaign = document.getElementsByName('campaign');
	//console.log(campaign);
	//if(campaign === null){
	//	document.getElementById("error").innerHTML = "You must select a campaign option!";
	//	return false;
	//}
	
	
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
	if (UM_time){
		var UM_sum = 0;
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
		var max_UM_sum = umNumJS * 4; 
		if(UM_sum > max_UM_sum) { 
    		document.getElementById("error").innerHTML = "Unit Manager's total time is greater then your maximum time! Your input time is " + UM_sum;
    		return false;
		}
	}    	
	//document.inputForm.submit();
	return true;
}