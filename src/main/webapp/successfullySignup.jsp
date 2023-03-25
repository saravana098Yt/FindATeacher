<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
    
    
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a id="im" href="#">
<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTG8cWmq4KaPIsJKEyuXo4mFrFH_96T7J7O3g&usqp=CAU" id="im1"/>
</a>
<div id="main">
<h1 id="h1">${pass}</h1>
<img src="" id="img1"/>
<p id="p1">${Message}</p>
<a href="#" id="link">
<input type="submit" id="sub" value="ok" />
</a>
</div>

<script>
if("${page}" === "signup"){
if("${pass}" === "Congrats"){
	if("${role}" === "Teacher"){
		document.cookie="username="+"${username}";
		localStorage.setItem("role","Teacher");
		document.getElementById("img1").setAttribute("src","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS-JIJT7FCyVMxFUVb5zTbmokWJ_n0GOWCTDw&usqp=CAU");
		document.getElementById("sub").style ="display:block";
		document.getElementById("link").setAttribute("href","userPage3.html");
		document.getElementById("im").setAttribute("href","signup.html");
	}
	else{
		document.cookie="username="+"${username}";
		localStorage.setItem("role","Student");
		document.getElementById("img1").setAttribute("src","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS-JIJT7FCyVMxFUVb5zTbmokWJ_n0GOWCTDw&usqp=CAU");
		document.getElementById("sub").style ="display:block";
		document.getElementById("link").setAttribute("href","userPage2.html");
		document.getElementById("im").setAttribute("href","signup.html");
	}
	
}
else{
	document.getElementById("img1").setAttribute("src","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwrjq8j-gd2gTlOe-VbexuYty-Vk0-vB7KJw&usqp=CAU");
    document.getElementById("sub").style ="display:none";
    document.getElementById("im").setAttribute("href","extra.html");
}
}
else if("${page}" === "login"){
	if("${pass}" === "Congrats"){
		if("${role}" === "Teacher"){
			document.cookie="username="+"${username}";
			localStorage.setItem("role","Teacher");
			document.getElementById("img1").setAttribute("src","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS-JIJT7FCyVMxFUVb5zTbmokWJ_n0GOWCTDw&usqp=CAU");
			document.getElementById("sub").style ="display:block";
			document.getElementById("link").setAttribute("href","userPage3.html");
			document.getElementById("im").setAttribute("href","login.html");
		}
		else{
			document.cookie="username="+"${username}";
			localStorage.setItem("role","Student");
			document.getElementById("img1").setAttribute("src","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS-JIJT7FCyVMxFUVb5zTbmokWJ_n0GOWCTDw&usqp=CAU");
			document.getElementById("sub").style ="display:block";
			document.getElementById("link").setAttribute("href","userPage2.html");
			document.getElementById("im").setAttribute("href","login.html");
		}
		
	}
	else{
		document.getElementById("img1").setAttribute("src","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwrjq8j-gd2gTlOe-VbexuYty-Vk0-vB7KJw&usqp=CAU");
	    document.getElementById("sub").style ="display:none";
	    document.getElementById("im").setAttribute("href","login.html");
	}
}
else{
	if("${pass}" === "Congrats"){
		if("${role}" === "Teacher"){
			document.cookie="username="+"${username}";
			localStorage.setItem("role","Teacher");
			document.getElementById("img1").setAttribute("src","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS-JIJT7FCyVMxFUVb5zTbmokWJ_n0GOWCTDw&usqp=CAU");
			document.getElementById("sub").style ="display:block";
			document.getElementById("link").setAttribute("href","userPage3.html");
			document.getElementById("im").setAttribute("href","login.html");
		}
		else{
			document.cookie="username="+"${username}";
			localStorage.setItem("role","Student");
			document.getElementById("img1").setAttribute("src","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS-JIJT7FCyVMxFUVb5zTbmokWJ_n0GOWCTDw&usqp=CAU");
			document.getElementById("sub").style ="display:block";
			document.getElementById("link").setAttribute("href","userPage3.html");
			document.getElementById("im").setAttribute("href","signup.html");
		}
		
	}
	else{
		document.getElementById("img1").setAttribute("src","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwrjq8j-gd2gTlOe-VbexuYty-Vk0-vB7KJw&usqp=CAU");
	    document.getElementById("sub").style ="display:none";
	    document.getElementById("im").setAttribute("href","extra.html");
	}
}


</script>
</body>


<style>

body{
width:100%;
height:100%;
display:flex;
align-items:center;
justify-content:center;
min-height:100vh;
margin:0%;
}
#main{
	width:500px;
	height:550px;
	border:1px solid black;
	margin-left:2%;
	margin-top:3%;
	background-color:white;
	overfolw:hidden;
}
#h1{
	font-size:53px;
	margin-top:3%;
	text-align:center;
	color:black;
}
#img1{
	width:206px;
	height:206px;
	margin-left:28%;
	margin-top:2%;
}
#p1{
	font-size:23px;
	text-align:center;
	margin-top:9%;
	font-weight:bold;
	color:black;
}
input{
width:93px;
height:35px;
background:#7CFC00;
font-size:24px;
margin-left:39%;
margin-top:10%;
color:grey;
border-radius:5px;
border:1px solid grey;
}
a{
text-decoration:none;
}
#im{
width:29px;
height:29px;
margin-left:508px;
margin-top:-468px;
position:absolute;
border:1px solid black;
}
#im1{
width:100%;
height:100%;
}
</style>


</html>