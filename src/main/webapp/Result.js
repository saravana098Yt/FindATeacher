/**
 * 
 */
 var json={};
 
 onloadd();
 function onloadd(){
	 json=localStorage.getItem("json");
     console.log(json);
     json=JSON.parse(json);
			document.getElementById("user").innerHTML +="                   " +String(json.username);
			document.getElementById("total").innerHTML += "                          "+String(json.total);
			document.getElementById("marks").innerHTML += "                              "+json.result;
			document.getElementById("dat").innerHTML +=  "                  "+json.current;
			document.getElementById("name").innerHTML +="                       "+ json.AssignmentName;
	}
