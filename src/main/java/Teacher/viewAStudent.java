package Teacher;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import classes.Gender;
import classes.Student;
import classes.main;

/**
 * Servlet implementation class viewAStudent
 */
@WebServlet("/viewAStudent")
public class viewAStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewAStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Student st=new Student();
		ArrayList<Student> StudentList = st.getAllStudent();
		HttpSession ses=request.getSession();
		JSONArray array=new JSONArray();
		String username=(String)ses.getAttribute("username");
		for(Student stu:StudentList) {
			if(stu.getAssignTeacher().getUsername().equals(username)){
				JSONObject obj=new JSONObject();
				obj.put("name",stu.getName());
				obj.put("username",stu.getUsername());
				obj.put("dob",stu.getDob());
				obj.put("age",stu.getDob());
				obj.put("gender", stu.getGender().toString());
				array.add(obj);
			}
		}
		response.getWriter().append(array.toString());
	}

}
