var Teachers=[];
 
 /**
 
 function getTeachers(){
	var xhr=new XMLHttpRequest();
	xhr.onreadystatechange =()=>{
		if(xhr.readystate == 4 && xhr.status == 200){
			Teachers=JSON.parse(xhr.responseText);
		}
		xhr.open("POST","http://"+window.location.hostname+":8080/FindATeacher/getTeachers");
		xhr.setRequestHeader("Content-Type","application/JSON");
		xhr.send();
	}
}
**/
 window.onload=()=>{
	
	for(var i=0;i<Teachers.length;i++){
		var div=document.createElement("div");
		var span=document.createElement("span");
		var img=document.createelement("img");
		var button=document.createElement("button");
		div.appendChild(img);
		div.appendChild(span);
		div.appendChild(button);
		div.classList.add("cll");
		img.classList.add("imm");
		button.classList.add("sp");
		var Teacher=Teachers[i];
		
		span.innerHTML="Teacher Name : "+Teacher.get("name")+"\nusername : "+Teacher.get(username)+"\n age : "+Teacher.get("age")+"\ngender : "+Teacher.get("gender")+"\n languages : "+Teacher.get(lang);
		button.innerHTML="Add Teacher";
		if(Teacher.get("img") === null){
			img.src="https://t4.ftcdn.net/jpg/01/07/57/91/360_F_107579101_QVlTG43Fwg9Q6ggwF436MPIBTVpaKKtb.jpg";
		}
		else{
			img.src=Teacher.get("img");
		}
		
	}
}