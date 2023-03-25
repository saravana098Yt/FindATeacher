/**
 * 
 */
 
 window.onload=()=>{
	document.getElementBy("username").value="";
	document.getElementBy("password").value="";
	if(document.cookie !== null){
		if(localStorage.getItem("role") === "Student"){
			window.location.href="http://"+window.location.hostName+":8080/FindATeacher/userPage2.html";
		}
		else{
			window.location.href="http://"+window.location.hostName+":8080/FindATeacher/userPage3.html";
		}
	}
	else{
		
	}
	
};
 function restrictAlpabet(x){
      var z=x.which || x.keycode;
      if((z >= 48 && z <= 57))
        return true;
      else
        return false;
    }
    
const show=document.querySelector("#show-password");
const password=document.querySelector("#password");

show.addEventListener("click",function(){
	if(password.getAttribute("type") == "password"){
		this.classList.toggle("fa-eye-slash");
		this.classList.remove("fa-eye");
	}
	else{
		this.classList.remove("fa-eye-slash");
		this.classList.toggle("fa-eye");
	}
	
	const type=password.getAttribute("type") === "password" ? "text" : "password";
	password.setAttribute("type",type);
});

function clickFunction(){
	var xhr=new XMLHttpRequest();
	xhr.onreadystatechange = ()=> {
		if(xhr.readyState == 4 && xhr.status == 200){
			if(xhr.responseText.indexOf("success") != -1){
				 document.cookie="username="
				window.location.href="http://"+window.location.hostname+":8080/FindATeacher/changePassword.html";
			}
			else{
			document.getElementById("message").innerHTML=xhr.responseText;
			}
		}
	}
	var username=document.getElementById("username").value;
	xhr.open("POST","http://"+window.location.hostname+":8080/FindATeacher/changePassword");
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	
	xhr.send("username="+username);
}

