/**
 * 
 */
 function myFunction(){
	var xhr=new XMLHttpRequest();
	xhr.onreadystatechange = () =>{
		console.log(xhr.responseText);
		if(xhr.responseText.indexOf("success") !== -1){
			document.getElementById("messag").innerHTML="Quizz created !!";
		}
		else if(xhr.responseText.indexOf("taken") !== -1){
			document.getElementById("messag").innerHTML=("Quizz already taken !!");
		}
		else {
			document.getElementById("messag").innerHTML=("Quizz not created !!");
		}
	}
	var req={};
	req.QuizzName=document.getElementById("QuizzName").value;
	xhr.open("POST","http://"+window.location.hostname+":8080/FindATeacher/createAQuizz");
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	xhr.send("QuizzName="+req.QuizzName);
}