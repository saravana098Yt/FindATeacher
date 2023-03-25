package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.simple.JSONObject;

import Common.application;

public class Student{
  private String name;
  private int age;
  private String username;
  private Gender gender;
  private Date dob;
  private Teacher assignTeacher;
  
  public Student(String name,int age,String username,Gender gender,Date dob) {
	  this.name=name;
	  this.age=age;
	  this.username=username;
	  this.gender=gender;
	  this.dob=dob;
  }
  public Student() {
	  
  }
public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getAge() {
	return age;
}

public void setAge(int age) {
	this.age = age;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public Gender getGender() {
	return gender;
}

public void setGender(Gender gender) {
	this.gender = gender;
}

public Date getDob() {
	return dob;
}

public void setDob(Date dob) {
	this.dob = dob;
}

public Teacher getAssignTeacher() {
	return assignTeacher;
}

public void setAssignTeacher(Teacher assignTeacher) {
	this.assignTeacher = assignTeacher;
}
  

@SuppressWarnings("deprecation")
public synchronized Student createAStudentObj(JSONObject jsonObj) {
	main m=new main();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	String name=(String) jsonObj.get("name");
	int age=Integer.parseInt((String)jsonObj.get("age"));
	String username=(String) jsonObj.get("username");
	Gender gender=chooseGender((String)jsonObj.get("gender"));
	Date dob=null;
	  try {
		  dob = sdf.parse((String)jsonObj.get("date"));
	  }
	  catch(ParseException ex) {
		  System.out.println(ex.getMessage());
	  }
	Student student=new Student(name,age,username,gender,dob);
	return student;
}
public synchronized Gender chooseGender(String gender) {
	Gender gend=null;
	if(gender.equals("Male")) {
		gend=Gender.Male;
	}
	else if(gender.equals("Female")) {
		gend=Gender.Female;
	}
	else if(gender.equals("Other")) {
		gend=Gender.Other;
	}
	return gend;
}

public synchronized void storeTheObj(Student student) {
	main m=new main();
	String role="Student";
	String gender="";
	Gender gend=student.getGender();
	if(gend.equals(Gender.Male)) {
		gender="Male";
	}
	else if(gend.equals(Gender.Female)) {
		gender="Female";
	}
	else {
		gender="Other";
	}
	try {
		PreparedStatement stmt=application.dbConnection.prepareStatement("insert into Student(name,age,username,gender,role,dob) values(?,?,?,?,?,?)");
	    stmt.setString(1, student.getName());
	    stmt.setInt(2,student.getAge());
	    stmt.setString(3, student.getUsername());
	    stmt.setString(4, gender);
	    stmt.setString(5, role);
	    stmt.setString(6, m.format.format(student.getDob()).toString());
	    stmt.execute();
	}
	catch(Exception ex) {
		System.out.println(ex.getMessage());
	}
}

@SuppressWarnings({ "hiding", "unused" })
public synchronized Student getUserStudentObj(String username) {
	Student student=new Student();
	ArrayList<Student> StudentList=student.getAllStudent();
	for(Student stu:StudentList) {
		if(stu.getUsername().equals(username)) {
			student=stu;
			break;
		}
	}
	
	if(student == null) {
		Teacher tea=new Teacher();
		ArrayList<Teacher> teacherList=tea.getValues();
		for(Teacher teacher:teacherList) {
			if(teacher.getUsername().equals(username)) {
				student=teacher;
				break;
			}
		}
	}
	else {
		return student;
	}
	return student;
	
}

public String updateStudentDetails(String name,String username) {
	String value="";
	try {
		PreparedStatement stmt=application.dbConnection.prepareStatement("update Student set name = ? where username = ?");
	    stmt.setString(1, name);
	    
	    stmt.setString(2, username);
	    int result=stmt.executeUpdate();
	    if(result != 0) {
	    	value= "Student Updated !!";
	    }
	    else {
	    	value="Student not updated !!";
	    }
	}
	catch(Exception ex) {
		System.out.println(ex.getMessage());
	}
	return value;
}

public synchronized ArrayList<Student> getAllStudent(){
	   ArrayList<Student> studentArray=new ArrayList<Student>();
	   
	   try {
		   PreparedStatement stmt=application.dbConnection.prepareStatement("select * from Student");
		   ResultSet re=stmt.executeQuery();
		   while(re.next()) {
			   String name = re.getString(1);
			   int age=re.getInt(2);
			   String username=re.getString(3);
			   String gend=re.getString(4);
			   Gender gender=null;
			   if(gend.equals("Male")) {
				   gender=Gender.Male;
			   }
			   else if(gend.equals("Female")) {
				   gender=Gender.Female;
			   }
			   else {
				   gender=Gender.Other;
			   }
			   Date dob=new Date(re.getDate(6).getTime());
			   Student student=new Student(name,age,username,gender,dob);
			   String teacher=re.getString(7);
			   if(teacher != null) {
				   Teacher teach = null;
				   Teacher teacherr=new Teacher();
				   ArrayList<Teacher> teacherList=teacherr.getValues();
				   for(Teacher tea:teacherList) {
					   if(tea.getUsername().equals(teacher)) {
						   teach=tea;
					   }
				   }
				   student.setAssignTeacher(teach);
			   }
			   
			   studentArray.add(student);
		   }
	   }
	   catch(Exception ex) {
		   System.out.println("stu : "+ex.getMessage());
	   }
	   return studentArray;
}
}
