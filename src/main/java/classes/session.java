package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import Common.application;

@SuppressWarnings("unused")
public class session {
  private String sessionId;
  private String username;
  public session() {
	  
  }
  public session(String username,String sessionId) {
	  this.sessionId=sessionId;
	  this.username=username;
  }
public String getSessionId() {
	return sessionId;
}
public void setSessionId(String sessionId) {
	this.sessionId = sessionId;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
  
public synchronized session createASession(String username,String id) {
	session ses=new session(username,id);
	return ses;
}
public synchronized void storeASession(session ses) {
	try {
		PreparedStatement stmt=application.dbConnection.prepareStatement("insert into session values(?,?)");
		stmt.setString(1, ses.getUsername());
		stmt.setString(2, ses.getSessionId());
		stmt.execute();
	}
	catch(Exception ex) {
		System.out.println(ex.getMessage());
	}
}

public synchronized String getstoreSessionIdIn(String username) {
	try {
		PreparedStatement stmt=application.dbConnection.prepareStatement("select sessionId from session where username = ?");
	    stmt.setString(1, username);
	    ResultSet re=stmt.executeQuery();
		if(re.next()) {
	    	return re.getString(1);
	    }
	}
	catch(Exception ex) {
		System.out.println(ex.getMessage());
	}
	return null;
}

public synchronized String getstoreUsernameIn(String id) {
	try {
		PreparedStatement stmt=application.dbConnection.prepareStatement("select username from session where sessionId = ?");
	    stmt.setString(1, id);
	    ResultSet re=stmt.executeQuery();
		if(re.next()) {
	    	return re.getString(1);
	    }
	}
	catch(Exception ex) {
		System.out.println(ex.getMessage());
	}
	return null;
}

public synchronized ArrayList<session> getAllSession() {
	  ArrayList<session> sessionList=new ArrayList<>();
	  try {
		  PreparedStatement stmt=application.dbConnection.prepareStatement("select * from session");
		  ResultSet re=stmt.executeQuery();
		  while(re.next()) {
			  String username=re.getString(1);
			  String sessionId=re.getString(2);
			  session ses=new session(username,sessionId);
			  sessionList.add(ses);
		  }
	  }
	  catch(Exception ex) {
		  System.out.println(ex.getMessage());
	  }
	  return sessionList;
}
}
