package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.cj.protocol.Resultset;

import Common.application;

public class Quizz {
   private String name;
   private int numberOfQuestion;
   private Date uploadDate;
   private int highestRank;
   private Teacher teacher;
   
   public Quizz(String name,int numberOfQuestion,Date uploadDate,int highestRank,Teacher teacher){
	   this.name=name;
	   this.numberOfQuestion=numberOfQuestion;
	   this.uploadDate=uploadDate;
	   this.highestRank=highestRank;
	   this.teacher=teacher;
   }

   
public Teacher getTeacher() {
	return teacher;
}


public void setTeacher(Teacher teacher) {
	this.teacher = teacher;
}


public Quizz() {
	// TODO Auto-generated constructor stub
}



public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getNumberOfQuestion() {
	return numberOfQuestion;
}

public void setNumberOfQuestion(int numberOfQuestion) {
	this.numberOfQuestion = numberOfQuestion;
}

public Date getUploadDate() {
	return uploadDate;
}

public void setUploadDate(Date uploadDate) {
	this.uploadDate = uploadDate;
}

public int getHighestRank() {
	return highestRank;
}

public void setHighestRank(int highestRank) {
	this.highestRank = highestRank;
}
   

@SuppressWarnings("deprecation")
public synchronized ArrayList<Quizz> getAllQuizzes() {
	Teacher ty=new Teacher();
	  ArrayList<Quizz> Quizzes=new ArrayList<>();
	  SimpleDateFormat sm=new SimpleDateFormat("MM-dd-yyyy");
	  try {
		  PreparedStatement stmt=application.dbConnection.prepareStatement("select * from Quizz");
		  ResultSet re=stmt.executeQuery();
		  while(re.next()) {
			  String name=re.getString(1);
			  int numberOfQuestion=re.getInt(2);
			  String date=re.getString(3);
			  System.out.println("date : "+date);
			  int highestRank=re.getInt(4);
			  String teacherName=re.getString(5);
			  Teacher teacher=ty.getUserTeacherObj(teacherName);
			 Quizz im=new Quizz(name,numberOfQuestion,sm.parse(date),highestRank,teacher);
			 
			  Quizzes.add(im);
		  }
	  }
	  catch(Exception ex) {
		  System.out.println(ex.getMessage());
	  }
	  return Quizzes;
}

public synchronized Quizz createObjQuizz(String name,int numberOfQuestion,int highestRank,String date,String username)  {
	Teacher ty=new Teacher();
	SimpleDateFormat sm=new SimpleDateFormat("MM-dd-yyyy");
	Teacher teacher=ty.getUserTeacherObj(username);
	Quizz q=null;
	try {
		q = new Quizz(name,numberOfQuestion,sm.parse(date),highestRank,teacher);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return q;
	}

public synchronized void storetheQuizz(Quizz q) {
	SimpleDateFormat sm=new SimpleDateFormat("MM-dd-yyyy");
	try {
		PreparedStatement stmt=application.dbConnection.prepareStatement("insert into Quizz values(?,?,?,?,?)");
		stmt.setString(1, q.getName());
		stmt.setInt(2,q.getNumberOfQuestion());
		stmt.setString(3, sm.format(q.getUploadDate()).toString());
		stmt.setInt(4, q.highestRank);
		stmt.setString(5, q.getTeacher().getUsername());
		stmt.execute();
	}
	catch(Exception ex) {
		System.out.println(ex.getMessage());
	}
}

public synchronized void updateHighestRank(int highestRank,String name) {
	try {
		PreparedStatement stmt=application.dbConnection.prepareStatement("update Quizz set highestRank = ? where name = ?");
		stmt.setInt(1,highestRank);
		stmt.setString(2, name);
		stmt.executeUpdate();
	}
	catch(Exception ex) {
		System.out.println(ex.getMessage());
	}
}
public synchronized boolean isQuizzName(String QuizzName) {
	try {
		PreparedStatement stmt=application.dbConnection.prepareStatement("select name from Quizz where name = ?");
	    stmt.setString(1, QuizzName);
	   ResultSet re = stmt.executeQuery();
		if(re.next()) {
		  return true;
	  }
		else {
			return false;
		}
	}
	catch(Exception ex) {
		return false;
	}
}

public synchronized boolean updateNumberOfQuestion(String QuizzName) {
	int numOfQuestion=0;
	try {
		PreparedStatement smt=application.dbConnection.prepareStatement("select numberOfQuestion from Quizz where name =?");
		smt.setString(1,QuizzName);
		ResultSet re=smt.executeQuery();
		if(re.next()) {
			numOfQuestion=re.getInt(1);
		}
	}
	catch(Exception ex) {
		return false;
	}
	numOfQuestion++;
	try {
		PreparedStatement smt=application.dbConnection.prepareStatement("update Quizz set numberOfquestion = ? where name =?");
		smt.setInt(1, numOfQuestion);
		smt.setString(2,QuizzName);
		smt.executeUpdate();
		return true;
	}
	catch(Exception ex) {
		return false;
	}
			
}
}
