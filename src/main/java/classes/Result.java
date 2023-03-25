package classes;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Common.application;

public class Result {
  private String AssignmentName;
  private String username;
  private int totalMarks;
  private int takenMarks;
  private Date completeDate;
  public Result(String AssignmentName,String username,int totalMarks,int takenMarks,Date completeDate) {
	  this.AssignmentName=AssignmentName;
	  this.username=username;
	  this.totalMarks=totalMarks;
	  this.takenMarks=takenMarks;
	  this.completeDate=completeDate;
  }
public String getAssignmentName() {
	return AssignmentName;
}
public void setAssignmentName(String assignmentName) {
	AssignmentName = assignmentName;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public int getTotalMarks() {
	return totalMarks;
}
public void setTotalMarks(int totalMarks) {
	this.totalMarks = totalMarks;
}
public int getTakenMarks() {
	return takenMarks;
}
public void setTakenMarks(int takenMarks) {
	this.takenMarks = takenMarks;
}
public Date getCompleteDate() {
	return completeDate;
}
public void setCompleteDate(Date completeDate) {
	this.completeDate = completeDate;
}
  
public void storeTheResult(Result re) {
	SimpleDateFormat sdf=new SimpleDateFormat("MM-dd-yyyy");
	try {
		PreparedStatement stmt=application.dbConnection.prepareStatement("insert into Result values(?,?,?,?,?)");
	    stmt.setString(1, re.getUsername());
	    stmt.setInt(2, re.getTotalMarks());
	    stmt.setInt(3, re.getTakenMarks());
	    stmt.setString(4, re.getAssignmentName());
	    stmt.setString(5, sdf.format(re.getCompleteDate()));
	    stmt.execute();
	}
	catch(SQLException ex) {
		System.out.println("SQl exception in result !!");
	}
}
public Result createObjResult(String username,int totalMarks,int takenMarks,String AssignmentName,Date CompleteDate) {
	return new Result(AssignmentName,username,totalMarks,takenMarks,CompleteDate);
}
}
