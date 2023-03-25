/**
 * 
 */
 document.getElementById("back").style="display:block";
 var Quizz=localStorage.getItem("Quizz");
 var quizzz=Quizz[0];
 var quizHigh=quizzz.highestRank;
 var json={};
 json=localStorage.getItem("array");
 console.log(json);
 
 var quizGame={};
 quizGame=JSON.parse(json);
 
 const quiz=document.getElementById("quiz");
 const answerEle=document.querySelectorAll(".Answer");
  const questionE1=document.getElementById("Question");
 const a_text=document.getElementById("a_text");
 const b_text=document.getElementById("b_text");
 const c_text=document.getElementById("c_text");
 const d_text=document.getElementById("d_text");
 
 let currentQuiz =0;
 let score = 0;
 
 loadquiz();
 
 function loadquiz(){
	deselectAnswers();
	const currentQuizData=quizGame[currentQuiz];
	console.log(currentQuizData);
	questionE1.innerText=currentQuizData.Question;
	a_text.innerText=currentQuizData.a;
	b_text.innerText=currentQuizData.b;
	c_text.innerText=currentQuizData.c;
	d_text.innerText=currentQuizData.d;
	
}

function deselectAnswers(){
	answerEle.forEach(answerEle => answerEle.checked = false)
}


function getSelected(){
	let answer="";
	answerEle.forEach(answerE1 => {
		if(answerE1.checked){
			answer=answerE1.id;
		}
	});
	return answer;
}

const submitBtn=document.getElementById("sub");
submitBtn.addEventListener("click",()=>{
	const answer=getSelected();
	if(answer){
		if(answer === quizGame[currentQuiz].correct){
			score++;
		}
			
		currentQuiz++;
		
		if(currentQuiz < quizGame.length){
			loadquiz();
		}
		else{
			document.getElementById("back").style="display:none";
			save();
			quiz.style.height="307px";
			quiz.innerHTML = `<a id="mm" href="http://localhost:8080/FindATeacher/Assignment.html">Home</a>
			<h2>You answered ${score}/${quizGame.length} Questions correctly !!<h2>
		    
		    <button onclick="location.reload()" id="rel">Reload</button>
		    <a id="re" href="Result.html" >Result</a>
		    `;
		}
	}
});

function save(){
	var xhr=new XMLHttpRequest();
	xhr.onreadystatechange = () => {
		if(xhr.readyState == 4 && xhr.status == 200){
			console.log(" result success !!");
			var jsonArr=xhr.responseText;
			localStorage.setItem("json",jsonArr);
			if(quizHigh<score){
				quizHigh=score;
				
			}
		}
	}
	var req={};
	req.result = score;
	req.total = quizGame.length;
	req.AssignmentName=localStorage.getItem("QuizName");
	const date2=new Date();
	
	let yyyy=date2.getFullYear();
	let dd=date2.getDate();
	let MM=date2.getMonth()+1;
	if(dd<10){
		dd='0'+dd;
	}
	if(MM < 10){
		MM='0'+MM;
	}
	req.current=MM+'-'+dd+'-'+yyyy;
	req.QuizHigh=quizHigh;
	xhr.open("POST","http://"+window.location.hostname+":8080/FindAteacher/ResultServlet");
	xhr.setRequestHeader("Content-Type","application/json");
	xhr.send(JSON.stringify(req));
}

var distance = 300000;
  
var x = setInterval(function() {
  
  var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
  var seconds = Math.floor((distance % (1000 * 60)) / 1000);
  console.log(minutes+" "+seconds);
  if(0 <= minutes && minutes <= 9){
	document.getElementById("minutes").innerHTML ="0"+minutes;
  }
  else{
	document.getElementById("minutes").innerHTML =minutes;
 }
  if(0 <= seconds && seconds <= 9){
	document.getElementById("sec").innerHTML ="0"+seconds;
  }
  else{
	document.getElementById("sec").innerHTML =seconds;
 }
 
  
  distance=distance-1000;
}, 1000);
