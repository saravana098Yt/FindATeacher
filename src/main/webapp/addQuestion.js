/**
 * 
 */
 
 function myFunction(){
	var xhr=new XMLHttpRequest();
	xhr.onreadystatechange =()=>{
		if(xhr.readyState == 4 && xhr.status == 200){
			if(xhr.responseText.indexOf("success") != -1){
				document.getElementById("aa").style="margin-top:-5%";
				document.getElementById("bb").style="margin-top:-6%";
				document.getElementById("messag").innerHTML="Question created !!";
			}
			else if(xhr.responseText.indexOf("invalid") != -1){
				document.getElementById("messag").innerHTML=("invalid Quizz Type !!");
			}
			else{
				document.getElementById("messag").innerHTML=("Question not created !!");
			}
			
			if(document.getElementById("messag").innerHTML === "Question created !!"){
				document.getElementById("QuizzName").value="";
				document.getElementById("Question").value="";
				document.getElementById("a").value="";
				document.getElementById("b").value="";
				document.getElementById("c").value="";
				document.getElementById("d").value="";
				document.getElementById("correct").value="";
				setTimeout('', 5000);
				document.getElementById("messag").innerHTML="";
				document.getElementById("aa").style="margin-top:5%";
				document.getElementById("bb").style="margin-top:5%";
			}
		}
	}
	var req={};
	req.QuizzName=document.getElementById("QuizzName").value;
	req.Question=document.getElementById("Question").value;
	req.option_a=document.getElementById("a").value;
	req.option_b=document.getElementById("b").value;
	req.option_c=document.getElementById("c").value;
	req.option_d=document.getElementById("d").value;
	req.correct=document.getElementById("correct").value;
	
	xhr.open("POST","http://"+window.location.hostname+":8080/FindATeacher/addQuestions");
	xhr.setRequestHeader("Content-Type","application/JSON");
	xhr.send(JSON.stringify(req));
}