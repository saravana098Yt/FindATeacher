package Teacher;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

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

/**
 * Servlet implementation class updateTeacherDetails
 */
@SuppressWarnings("unused")
@WebServlet("/updateTeacherDetails")
public class updateTeacherDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateTeacherDetails() {
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
		Teacher teacher=new Teacher();
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
		int age=Integer.parseInt((String) jsonObj.get("age"));
		String username=(String) jsonObj.get("username");
		String gender=(String) jsonObj.get("gender");
		String dob=(String) jsonObj.get("dob");
		String exp=(String)jsonObj.get("Exp");
		String lang=(String)jsonObj.get("languages");
		Teacher tea=teacher.getUserTeacherObj(username);
		tea.setName(name);
		tea.setAge(age);
		tea.setUsername(username);
		tea.setGender(Gender.toGender(gender));
		
		try {
			tea.setDob(formatter.parse(dob));
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String value=teacher.updateTeacherDetails(name, age, username, gender, dob,exp,lang);
		
		response.getWriter().append(value);
	}

}
