/**
 * 
 */
 
 function getApassword(){
	var a=document.getElementById("password").value;
	var b=document.getElementById("password1").value;
	if(a==null){
		document.getElementById("message").innerHTML="Password is null !!";
	}
	else if(b == null){
		document.getElementById("message").innerHTML="Password is null !!"
	}
	else if(a == b){
		var xhr=new XMLHttpRequest();
	   xhr.onreadystatechange=()=>{
		if(xhr.readyState == 4 && xhr.status == 200){
			document.getElementById("message").innerHTML=xhr.responseText;
		}
	}
	var reqParams={};
	reqParams.password = a;
	xhr.open("POST","http://"+window.location.hostname+":8080/FindATeacher/changePasswordServlet");
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	xhr.send("password="+reqParams.password);
	}
	else{
		document.getElementById("message").innerHTML="Password Mismatch !!";
		p
	}
	
}