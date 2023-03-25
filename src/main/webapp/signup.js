/**
 * 
 */
 var a=0;

 
 function CreateElement(){
	
	var d=document.querySelector("#sub");
	if( a!= 1){
		a++;
	   var type=document.querySelector("form");
		var b=document.createElement("div");
		type.appendChild(b);
		b.classList.add("input-group");
		b.id="a2";
		var c=document.createElement("input");
		b.appendChild(c);
		c.setAttribute("type","text");
		c.setAttribute("onkeypress","restrictAlpabet(event)");
		c.setAttribute("name","Exp");
		c.setAttribute("required","");
		var e=document.createElement("label");
		b.appendChild(e);
		e.setAttribute("for","");
		e.innerHTML="Experience";
		type.insertBefore(b,d);
		
		
		b=document.createElement("div");
		type.appendChild(b);
		b.classList.add("input-group");
		b.id="a3";
		c=document.createElement("input");
		b.appendChild(c);
		c.setAttribute("type","text");
		c.setAttribute("name","lang");
		c.setAttribute("required","");
		e=document.createElement("label");
		b.appendChild(e);
		e.setAttribute("for","");
		e.innerHTML="Languages in csv";
		type.insertBefore(b,d);
		
		//d=document.queryselector("#a2");
		//b=document.createElement("div");
		//type.appendChild(b);
		//b.classList.add("input.group");
		//b.id="a3";
		//a=document.createElement("input");
		//b.appendChild(a);
		//a.setAttribute("type","text");
		//a.setAttribute("onkeypress","restrictAlpabet(event)");
		//a.setAttribute("name","lang");
		//a.setAttribute("required");
		//c=document.createElement("label");
		//b.appendChild("c");
		//c.setAttribute("for","");	
		//type.insertBefore(b,d);
	}
	else{
		document.getElementById("a2").style="display:block";
		document.getElementById("a3").style="display:block";
		
	}
	document.getElementById("cent").style="height:1000px";
}

function hide(){
	if(a==1){
	document.getElementById("a2").style="display:none";
	document.getElementById("a3").style="display:none";
	document.getElementById("cent").style="height:900px";
	}
}
function restrictAlpabet(x){
      var z=x.which || x.keycode;
      if((z >= 48 && z <= 57))
        return true;
      else
        return false;
  }
  
 // function signup(){
//	var a=document.getElementById("#Message");
	//var xhr=new XMLHttpRequest();
//	xhr.onreadystatechange=() =>{
	//	var resp = JSON.parse(this.responseText);
		//if(this.readyState == 4 && this.status== 200){
			//if(resp.statusCode == 200){
				//a.innerHTML=resp.Message;
		//	}
//			else{
	//			a.innerHTML=resp.Message;
//			}
//		}
//		}
//	var reqParams={};
	
//	reqParams.name=document.getElementById("Name").value;
//	reqParams.age=document.getElementById("age").value;
//	reqParams.role=document.getElementById("role").value;
//	reqParams.gender=document.getElementById("gender").value;
//	reqParams.dob=document.getElementById("dob").value;
//	if(reqParams.role === "Teacher"){
//		reqParams.Exp=document.getElementById("Exp").value;
//		reqParams.lang=document.getElementById("lang").value;
//	}
	
//	    xhr.open("POST","SignupServlet");
//	    xhr.setRequestHeader("Content-Type","application/json");
//	    xhr.send("Details="+JSON.stringify(reqParams));	
//}//
  //  
  
  
 