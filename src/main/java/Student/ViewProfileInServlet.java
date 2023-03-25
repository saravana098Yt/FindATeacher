package Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Common.application;
import classes.Gender;
import classes.ProfileImage;
import classes.Student;
import classes.Teacher;
import classes.main;
import classes.session;

/**
 * Servlet implementation class ViewProfileInServlet
 */
@SuppressWarnings("unused")
@WebServlet("/Student/ViewProfileInServlet")
public class ViewProfileInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewProfileInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SimpleDateFormat sm=new SimpleDateFormat("MM-dd-yyyy");
		main m=new main();
		ProfileImage im=new ProfileImage();
		session ses=new session();
		String value="";
		String username="";
		String name="";
		int age=0;
		String dob="";
		String gender="";
		String role="";
		int exp=0;
		String lang="";
	HttpSession session=request.getSession();
	String UserName=(String)session.getAttribute("username");
	String path=im.profileImgPath(UserName);
	String Role="";
	try {
		PreparedStatement stmt=application.dbConnection.prepareStatement("select role from users where username =?");
		stmt.setString(1, UserName);
		ResultSet re=stmt.executeQuery();
		if(re.next()) {
			Role=re.getString(1);
		}
		else {
			value="Username is Empty !!";
		}
	}
	catch(Exception ex) {
		value="Error !!";
		ex.printStackTrace();
	}
	
	
	if(Role.equals("Student")) {
		Student s=new Student();
		ArrayList<Student> StudentList=s.getAllStudent();
		Student student=null;
		for(Student stu:StudentList) {
			if(UserName.equals(stu.getUsername())) {
				student=stu;
				break;
			}
		}
		name=student.getName();
		age=student.getAge();
		username=student.getUsername();
		
		if((student.getGender()).equals(Gender.Male)) {
			gender="Male";
		}
		else if((student.getGender()).equals(Gender.Female)) {
			gender="Female";
		}
		else {
			gender="Other";
		}
		
		role=Role;
		dob=(sm.format(student.getDob())).toString();
	}
	else if(Role.equals("Teacher")){
		Teacher t=new Teacher();
		ArrayList<Teacher> teacherList=t.getValues();
		Teacher teacher=null;
		for(Teacher tea :teacherList) {
			if(UserName.equals(tea.getUsername())) {
				teacher=tea;
				break;
			}
		}
		
		name=teacher.getName();
		age=teacher.getAge();
		username=teacher.getUsername();
		if((teacher.getGender()).equals(Gender.Male)) {
			gender="Male";
		}
		else if((teacher.getGender()).equals(Gender.Female)) {
			gender="Female";
		}
		else {
			gender="Other";
		}
		
		role=Role;
		dob=(sm.format(teacher.getDob())).toString();
		
	    exp=teacher.getExp();
	    lang=String.join(",", teacher.getLang());
	}
	JSONObject res=new JSONObject();
	if(role.equals("Student")) {
		res.put("name", name);
		res.put("age", Integer.toString(age));
		res.put("username", username);
		res.put("gender", gender);
		res.put("role", role);
		res.put("dob", dob);
		res.put("path", path);
		response.getWriter().append(res.toString());
	}
	else if(role.equals("Teacher")) {
		res.put("name", name);
		res.put("age", Integer.toString(age));
		res.put("username", username);
		res.put("gender", gender);
		res.put("role", role);
		res.put("dob", dob);
		res.put("exp",Integer.toString(exp));
		res.put("lang", lang);
		res.put("path", path);
		response.getWriter().append(res.toString());
	}
	else {
		res.put("Message", value);
		response.getWriter().append(res.toString());
	}
	}
}
