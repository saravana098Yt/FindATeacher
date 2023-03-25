package pack;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import classes.Student;
import classes.Teacher;
import classes.User;

/**
 * Servlet implementation class StudentDetails
 */
@WebServlet("/StudentDetails")
public class StudentDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentDetails() {
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
		SimpleDateFormat formatter=new SimpleDateFormat("MM-dd-yyyy");
		HttpSession ses=request.getSession();
		String username=(String) ses.getAttribute("username");
		Student s=new Student();
		User u=new User();
		String role=u.getUserRole(username);
		if(role.equals("Teacher")) {
			Teacher teacher=new Teacher();
			Teacher stu=teacher.getUserTeacherObj(username);
			JSONObject json=new JSONObject();
			json.put("name", stu.getName());
			json.put("username", username);
			response.getWriter().append(json.toString());
		}
		else if(role.equals("Student")) {
				Student stu=s.getUserStudentObj(username);
				JSONObject json=new JSONObject();
				json.put("name", stu.getName());
				json.put("username", username);
				response.getWriter().append(json.toString());
		}
	   else {
		JSONObject json=new JSONObject();
		  json.put("message", "username is null !!");
			response.getWriter().append(json.toString());
	}
	}

}
