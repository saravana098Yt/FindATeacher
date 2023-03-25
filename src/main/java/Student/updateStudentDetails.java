package Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import classes.Gender;
import classes.Student;
import classes.Teacher;
import classes.User;

/**
 * Servlet implementation class updateStudentDetails
 */
@WebServlet("/Student/updateStudentDetails")
public class updateStudentDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateStudentDetails() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Teacher t=new Teacher();
		Student student=new Student();
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		BufferedReader buff=request.getReader();
		String json="";
		String str="";
		while((str=buff.readLine()) != null) {
			json=str+json;
		}
		JSONParser parser=new JSONParser();
		JSONObject jsonObj=null;
		try {
	      jsonObj=(JSONObject) parser.parse(json);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String name=(String) jsonObj.get("name");
		
		String username=(String) jsonObj.get("username");
		User u=new User();
		String role=u.getUserRole(username);
		if(role.equals("Student")) {
			Student stu=student.getUserStudentObj(username);
			stu.setName(name);
			stu.setUsername(username);
			String value=student.updateStudentDetails(name,username);
			response.getWriter().append(value);
		}
		else if(role.equals("Teacher")) {
			Teacher stu=t.getUserTeacherObj(username);
			stu.setName(name);
			stu.setUsername(username);
			String value=t.updateTeacherDetails(name,username);
			response.getWriter().append(value);
		}
		
	}

}
