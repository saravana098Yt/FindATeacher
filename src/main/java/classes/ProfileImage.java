package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Common.application;

public class ProfileImage {
  private String username;
  private String fileName;
  private String path;
  public ProfileImage(){
	  
  }
public ProfileImage(String username,String fileName,String path){
	  this.username=username;
	  this.fileName=fileName;
	  this.path=path;
  }
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPath() {
	return path;
}
public void setPath(String path) {
	this.path = path;
}

public ProfileImage createAProfileImg(String username,String fileName,String path) {
	ProfileImage img=new ProfileImage(username,fileName,path);
	return img;
	
}

public void storeTheImg(ProfileImage img) {
	try {
		PreparedStatement stmt=application.dbConnection.prepareStatement("insert into ProfileImage values(?,?,?)");
	    stmt.setString(1, img.getUsername());
	    stmt.setString(2, img.fileName());
	    stmt.setString(3, img.getPath());
	    stmt.execute();
	}
	catch(Exception ex) {
		ex.printStackTrace();
	}
}

private String fileName() {
	// TODO Auto-generated method stub
	return fileName;
}
public String profileImgPath(String username) {
	try {
		PreparedStatement stmt=application.dbConnection.prepareStatement("select path from ProfileImage where username = ?");
		stmt.setString(1, username);
		ResultSet re=stmt.executeQuery();
		if(re.next()) {
			return re.getString(1);
		}
		else {
			return "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_640.png";
		}
	}
	catch(Exception ex) {
	   System.out.println(ex.getMessage());
	   
	}
	return null;
}


public boolean isUsernamecheck(String username) {
	ProfileImage pm1=new ProfileImage();
	ArrayList<ProfileImage> imglist=pm1.getAllImages();
	for(ProfileImage pm:imglist) {
		if(pm.getUsername().equals(username)) {
			System.out.println(pm.getUsername()+" "+username);
			return true;
		}
	}
	return false;
}


public void UpdateImg(String username,String path) {
	try {	
		PreparedStatement stmt=application.dbConnection.prepareStatement("update ProfileImage set path = ? where username = ?");
	    stmt.setString(1, path);
	    stmt.setString(2, username);
	    stmt.executeUpdate();
	}
	catch(Exception ex) {
		
	}
}



public synchronized ArrayList<ProfileImage> getAllImages() {
	  ArrayList<ProfileImage> imgList=new ArrayList<>();
	  try {
		  PreparedStatement stmt=application.dbConnection.prepareStatement("select * from ProfileImage");
		  ResultSet re=stmt.executeQuery();
		  while(re.next()) {
			  String username=re.getString(1);
			  String fileName=re.getString(2);
			  String path=re.getString(3);
			  System.out.println(username);
			  ProfileImage im=new ProfileImage(username,fileName,path);
			  imgList.add(im);
		  }
	  }
	  catch(Exception ex) {
		  System.out.println(ex.getMessage());
	  }
	  return imgList;
}

public String getUrl(String path) {
	
}
}
