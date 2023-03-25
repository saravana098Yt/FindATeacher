/**
 * 
 */
 
 var i=0;
 document.getElementById("li").classList.add("line"+i);
 var array={};
 function getColumn(){
	var xhr=new XMLHttpRequest();
	xhr.onreadystatechange = () =>{
		if(xhr.readyState == 4 && xhr.status == 200){
			console.log("success !!")
			array=JSON.parse(xhr.responseText);
			console.log(array);
			array.map((elem) =>{
				document.getElementById("head").innerHTML += elem.name;
				document.getElementById("numberOfQuestion").innerHTML+=elem.numberOfQuestion;
				document.getElementById("highestRank").innerHTML+=elem.highestRank;
				document.getElementById("uploadDate").innerHTML+=String(elem.uploadDate);
				document.getElementById("te").innerHTML+=elem.CreatorName;
				localStorage.setItem("username",elem.username);
			});
			
		}
		else if(xhr.readyState == 4){
			console.log("falied !!")
		}
	}
	xhr.open("POST","http://"+window.location.hostname+":8080/FindATeacher/Student/QuizzServlet");
	xhr.setRequestHeader("Content-Type","application/JSON");
	xhr.send();
}
getColumn();
localStorage.setItem("Quizz",array);
var json={};
var value = "";
document.getElementById("bub").addEventListener("click",()=>{
	var text="";
	text=document.getElementById("head").innerHTML;
    console.log("text ="+text);
	var xhr=new XMLHttpRequest();
	xhr.onreadystatechange = () => {
		if(xhr.readyState == 4 && xhr.status == 200){
			json=JSON.parse(xhr.responseText);
			console.log(json);
			json.map((elem)=>{
				if(elem.Message != null){
					console.log("failed !")
				}
				else{
					console.log("success !!");
					localStorage.setItem("array",JSON.stringify(json));
					localStorage.setItem("QuizName",document.getElementById("head").innerHTML);
					window.location.href="http://"+window.location.hostname+":8080/FindATeacher/questionAndAnswer.html";
				}
			});
			
		}
		else if(xhr.readyState == 4){
			console.log("failed !!");
		}
	}
	
	var req={};
	req.QuizzName=text;
	console.log(req.QuizzName);
	xhr.open("POST","http://"+window.location.hostname+":8080/FindATeacher/getQuestionServlet");
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	xhr.send("QuizzName="+req.QuizzName);
});






