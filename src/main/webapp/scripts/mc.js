function validate(){
	var ok = true;
	var f_name = document.getElementById("f_name").value;
	if (f_name.length==0) {
		alert("First name invalid!");
		document.getElementById("f_name>-error").style.display ="inline";		
		ok = false;
	} else {
		document.getElementById("f_name>-error").style.display = "none";
	}
	
	var l_name = document.getElementById("l_name").value;
	if (l_name.length==0) {
		alert("First name invalid!");
		document.getElementById("l_name-error").style.display ="inline";		
		ok = false;
	} else {
		document.getElementById("l_name-error").style.display = "none";
	}
	
	var l_name = document.getElementById("l_name").value;
	if (l_name.length==0) {
		alert("First name invalid!");
		document.getElementById("l_name-error").style.display ="inline";		
		ok = false;
	} else {
		document.getElementById("l_name-error").style.display = "none";
	}
	
	var relation = document.getElementById("relation").value;
	if (relation.length==0) {
		alert("First name invalid!");
		document.getElementById("relation-error").style.display ="inline";		
		ok = false;
	} else {
		document.getElementById("relation-error").style.display = "none";
	}
	
	var url = document.getElementById("url").value;
	if (url.length==0) {
		alert("First name invalid!");
		document.getElementById("url-error").style.display ="inline";		
		ok = false;
	} else {
		document.getElementById("url-error").style.display = "none";
	}
	
	if (!ok)
		return false;
		
}



function doSimpleAjax(address){
	if(validate()){
		console.log("I've been called");
 		var request = new XMLHttpRequest();
 		var data='';
 		//retrieve corresponding parameters
		data += "principal=" + document.getElementById("principal").value
		+ "&";
		data += "interest=" + document.getElementById("interest").value +
		"&";
		data += "period=" + document.getElementById("period").value +
		"&";
		data += "calculate=true";
		//forward the url
 		request.open("GET", (address + "?" + data), true);
 		//handle the request
 		request.onreadystatechange = function() {
 		handler(request);
 		};
		request.send(null);
	}
	
}

/**
	* if the request is valid, handle the request and target the "result" attribute in document
	* @param  request
*/
function handler(request){
	if ((request.readyState == 4) && (request.status == 200)){
 	var target = document.getElementById("result");
 	target.innerHTML = request.responseText;
 	}
}