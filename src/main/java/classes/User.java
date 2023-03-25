package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Common.application;



public class User {
  private String username;
  private String password;
  private String role;
  
  public User(String username,String password,String role) {
	  this.username=username;
	  this.password=password;
	  this.role=role;
  }
  public User() {
	  
  }

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getRole() {
	return role;
}

public void setGender(String role) {
	this.role = role;
}
 
public synchronized User createAObj(String username,String password,String role) {
	  User user=new User(username,password,role);
	  return user;
  }
  
  public synchronized void storeObjUser(User user) {
	  
	  try {
		  PreparedStatement stmt=application.dbConnection.prepareStatement("insert into users values(?,?,?)");
	      stmt.setString(1, user.getUsername());
	      stmt.setString(2, user.getPassword());
	      stmt.setString(3, user.getRole());
	      stmt.execute();
	  }
	  catch(Exception ex) {
		  System.out.println(ex.getMessage());
	  }
  }
  
  public synchronized boolean isObjectUser(String username) {
	  try {
		  PreparedStatement stmt=application.dbConnection.prepareStatement("select * from users where username = ?");
		  stmt.setString(1, username);
		  ResultSet re=stmt.executeQuery();
		  if(re.next()) {
			  return false;
		  }
	  }
	  catch(Exception ex) {
		  System.out.println(ex.getMessage());
		  return false;
	  }
	        return true;
  }
  
  public synchronized String updatePasswordUser(String username,String password){
	  try {
		  PreparedStatement stmt=application.dbConnection.prepareStatement("update users set password = ? where username = ?");
	      stmt.setString(1, password);
	      stmt.setString(2, username);
	      int result=stmt.executeUpdate();
	      if(result == 0) {
	    	  return "password is not updated !!";
	      }
	      else {
	    	  return "password is updated !!";
	      }
	  }
	  catch(Exception ex) {
		  System.out.println(ex.getMessage());
	  }
	  return null;
  }
  
  public synchronized ArrayList<User> getAllUsers() {
	  ArrayList<User> userList=new ArrayList<>();
	  try {
		  PreparedStatement smtt=application.dbConnection.prepareStatement("select * from users");
		  ResultSet re=smtt.executeQuery();
		  while(re.next()) {
			  String username=re.getString(1);
			  String password=re.getString(2);
			  String role=re.getString(3);
			  User user=new User(username,password,role);
			  userList.add(user);
		  }
	  }
	  catch(Exception ex) {
		  System.out.println(ex.getMessage());
	  }
	  return userList;
  }
  
  public synchronized String getUserRole(String username) {
	  try {
		  PreparedStatement stmt=application.dbConnection.prepareStatement("select role from users where username = ?");
	      stmt.setString(1, username);
	      ResultSet re=stmt.executeQuery();
	      if(re.next()) {
	    	 return re.getString(1);
	      }
	      
	    	  
	      
	  }
	  catch(Exception ex ) {
		  System.out.println(ex.getMessage());
	  }
	  return "";
  }
}
