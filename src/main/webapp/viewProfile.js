/**
 * 
 */
 window.onload=()=>{
	var id=document.getElementById("mm");
	if(localStorage.getItem("role") === "Teacher"){
		id.href="userPage3.html";
	}
	else{
		id.href="userPage2.html";
	}
}
 
 
 
  
 function myfunction(){
	var json={};
	var xhr =new XMLHttpRequest();
	xhr.onreadystatechange= () => {
		if(xhr.readyState == 4 && xhr.status == 200){
			json=JSON.parse(xhr.responseText);
			document.getElementById("name").innerHTML=json.name;
			document.getElementById("age").innerHTML=Number(json.age);
			document.getElementById("username").innerHTML=json.username;
			document.getElementById("img").src='sendImage';
			document.getElementById("gender").innerHTML=json.gender;
			document.getElementById("role").innerHTML=json.role;
			document.getElementById("dob").innerHTML=json.dob;
			if(json.role === "Teacher"){
				document.getElementById("put").style="display:block";
				document.getElementById("put1").style="display:block";
				document.getElementById("profile").style="height:1000px";
				document.getElementById("exp").style="display:block";
				document.getElementById("lang").style="display:block";

				document.getElementById("exp").innerHTML=json.exp;
				document.getElementById("lang").innerHTML=json.lang;
			}
			else{
				document.getElementById("put").style="display:none";
				document.getElementById("put1").style="display:none";
				document.getElementById("exp").style="display:none";
				document.getElementById("lang").style="display:none";
				document.getElementById("profile").style="height:841px";
			}
			
		}
	}
	xhr.open("POST","http://"+window.location.hostname+":8080/FindATeacher/Student/ViewProfileInServlet");
	xhr.setRequestHeader("Content-Type","application/json");
	xhr.send();
}
 myfunction();
 
 const fileInput=document.getElementById("file_input");
 
 function getFiles(elem){
	var xhr=new XMLHttpRequest();
	
	let formdata=new FormData();
	formdata.append('file',elem.files[0]);
	xhr.onreadystatechange=()=>{
		if(xhr.readyState == 4 && xhr.status == 200){
			console.log(xhr.responseText);
		}
	}
	xhr.open("POST","http://"+window.location.hostname+":8080/FindATeacher/imageServlet");
	xhr.send(formdata);
	
}
 
 var url="";
 
 fileInput.onchange =() => {
	const file=fileInput.files[0];
	const reader=new FileReader();
	reader.addEventListener("load",() => {
		document.getElementById("img").src=reader.result;
	});
	reader.readAsDataURL(file);
	
	getFiles(fileInput);
	
}
function setImage(elem){
	const reader=new FileReader();
	reader.addEventListener("load",()=>{
		document.getElementById("img").src=reader.result;
	});
	var file=new File(elem);
	reader.readAsDataURL(file);
}

   
