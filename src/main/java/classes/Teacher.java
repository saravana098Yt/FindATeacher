package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;

import Common.application;

public class Teacher extends Student{
   private int Exp;
   private List<String> lang=new ArrayList<>();
   private List<Student> students=new ArrayList<>();
  
  public Teacher(String name,int age,String username,Gender gender,Date dob,int Exp,List<String>languages) {
	  super(name,age,username,gender,dob);
	  this.Exp=Exp;
	  this.lang=languages;
  }
  public Teacher() {
	  
  }
public int getExp() {
	return Exp;
}

public void setExp(int exp) {
	Exp = exp;
}

public List<String> getLang() {
	return lang;
}

public void setLang(List<String> lang) {
	this.lang = lang;
}

public List<Student> getStudents() {
	return students;
}

public void setStudents(List<Student> students) {
	this.students = students;
}
  
  @SuppressWarnings("deprecation")
public synchronized Teacher createObjTeacher(JSONObject json) {
	  SimpleDateFormat sdf=new SimpleDateFormat("MM-dd-yyyy");
	  String name=(String) json.get("name");
	  int age=Integer.parseInt((String)json.get("age"));
	  String username=(String)json.get("username");
	  Gender gender=chooseGender((String)json.get("gender"));
	  Date dob=null;
	  try {
		  dob = sdf.parse((String)json.get("date"));
	  }
	  catch(ParseException ex) {
		  
	  }
	  int exp = Integer.parseInt((String)json.get("exp"));
	  List<String> languages=Arrays.asList(((String)json.get("lang")).split(","));
	  Teacher teacher= new Teacher(name,age,username,gender,dob,exp,languages);
	  return teacher;
	  
  }
  
  public synchronized Gender chooseGender(String gend) {
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
	  return gender;
  }
  public synchronized void storeTheObj(Teacher teacher) {
	    main m=new main();
	    String role="Teacher";
		String gender="";
		Gender gend=teacher.getGender();
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
			PreparedStatement stmt=application.dbConnection.prepareStatement("insert into Teacher values(?,?,?,?,?,?,?,?)");
		    stmt.setString(1, teacher.getName());
		    stmt.setInt(2,teacher.getAge());
		    stmt.setString(3, teacher.getUsername());
		    stmt.setString(4, gender);
		    stmt.setString(5, role);
		    stmt.setString(6, m.format.format(teacher.getDob()).toString());
		    stmt.setInt(7, teacher.getExp());
		    stmt.setString(8,String.join(",",teacher.getLang()));
		    stmt.execute();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
  
  public synchronized Teacher getUserTeacherObj(String username) {
	  Teacher tm=new Teacher();
		ArrayList<Teacher> teacherList= tm.getValues();
		for(Teacher tea:teacherList) {
			if(tea.getUsername().equals(username)) {
				System.out.println(tea.getUsername());
				return tea;
			}
		}
		return null;
	}

	public String updateTeacherDetails(String name,int age,String username,String gender,String dob,String exp,String lang) {
		String value="";
		try {
			PreparedStatement stmt=application.dbConnection.prepareStatement("update Teacher set name = ? ,age = ? ,gender=? ,dob =? ,exp=?,lang=? where username = ?");
		    stmt.setString(1, name);
		    stmt.setInt(2, age);
		    stmt.setString(3, gender);
		    stmt.setString(4,dob);
		    stmt.setString(5, username);
		    stmt.setString(7, lang);
		    int result=stmt.executeUpdate();
		    if(result != 0) {
		    	value= "Teacher Updated !!";
		    }
		    else {
		    	value="Teacher not updated !!";
		    }
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return value;
	}
	
	@SuppressWarnings("deprecation")
	public synchronized ArrayList<Teacher> getValues() {
		  SimpleDateFormat sm=new SimpleDateFormat("MM-dd-yyyy");
		ArrayList<Teacher> teacherArray=new ArrayList<Teacher>();
		   try {
			   PreparedStatement stmt=application.dbConnection.prepareStatement("select * from Teacher");
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
				   Date dob=sm.parse(re.getString(6)); 
				   int exp=re.getInt(7);
				   String lang=re.getString(8);
				   List<String> languages= Arrays.asList(lang.split(","));
				   Teacher teacher=new Teacher(name,age,username,gender,dob,exp,languages);
				   teacherArray.add(teacher);
			   }
		   }
		   catch(Exception ex) {
			   System.out.println("teach : "+ex.getMessage());
		   }
		   return teacherArray;
	   }
	public String updateTeacherDetails(String name, String username) {
		String value="";
		try {
			PreparedStatement st=application.dbConnection.prepareStatement("update Teacher set name = ? where username =?");
			st.setString(1, name);
			st.setString(2, username);
			st.executeUpdate();
			value="Teacher Updated !!";
			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			value="Teacher is not Updated !!";
		}
		return value;
	}
}
