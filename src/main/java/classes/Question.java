package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Common.application;

public class Question {
  private String question;
  private String[]option;
  private String correct;
  private String Type;
  public Question(String question,String[]option,String correct,String Type) {
	  this.question=question;
	  this.option=option;
	  this.correct=correct;
	  this.Type=Type;
  }
public Question() {
	// TODO Auto-generated constructor stub
}
public String getQuestion() {
	return question;
}
public void setQuestion(String question) {
	this.question = question;
}
public String[] getOption() {
	return option;
}
public void setOption(String[] option) {
	this.option = option;
}
public String getCorrect() {
	return correct;
}
public void setCorrect(String correct) {
	this.correct = correct;
}
public String getType() {
	return Type;
}
public void setType(String type) {
	Type = type;
}


public synchronized ArrayList<Question> getAllQuestions() {
	  ArrayList<Question> Questions=new ArrayList<>();
	  try {
		  PreparedStatement stmt=application.dbConnection.prepareStatement("select * from Question");
		  ResultSet re=stmt.executeQuery();
		  while(re.next()) {
			  String Question=re.getString(1);
			  String a=re.getString(2);
			  String b=re.getString(3);
			  String c=re.getString(4);
			  String d=re.getString(5);
			  String correct=re.getString(6);
			  String Type=re.getString(7);
			  String[]option=new String[4];
			  option[0]=a;
			  option[1]=b;
			  option[2]=c;
			  option[3]=d;
			  Question im=new Question(Question,option,correct,Type);
			  Questions.add(im);
		  }
	  }
	  catch(Exception ex) {
		  System.out.println(ex.getMessage());
	  }
	  return Questions;
}


public Question createObjQuestion(String QuizzName,String a,String b,String c,String d,String correct,String type) {
	String []option=new String[]{a,b,c,d};
	Question q=new Question(QuizzName,option,correct,type);
	return q;
}


public boolean storeTheQuestion(Question q) {
	try {
		PreparedStatement smt=application.dbConnection.prepareStatement("insert into Question values(?,?,?,?,?,?,?)");
	    smt.setString(1, q.getQuestion());
	    String[]option=q.getOption();
	    smt.setString(2,option[0]);
	    smt.setString(3, option[1]);
	    smt.setString(4, option[2]);
	    smt.setString(5, option[3]);
	    smt.setString(6, q.getCorrect());
	    smt.setString(7, q.getType());
	    smt.execute();
	    return true;
	}
	catch(Exception ex) {
		return false;
	}
}
}
