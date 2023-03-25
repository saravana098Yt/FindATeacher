package pack;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Common.application;
import classes.Student;
import classes.Teacher;
import classes.User;
import classes.session;

/**
 * Servlet implementation class passwordServlet
 */
@WebServlet("/passwordServlet")
public class passwordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public passwordServlet() {
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
		// TODO Auto-generated method stub
		String username=request.getParameter("username");

		
		session ses=new session();
		User user=new User();
		Teacher teacher=new Teacher();
		Student student=new Student();
		String password=request.getParameter("password");
		String password1=request.getParameter("password1");
		HttpSession session=request.getSession();
		JSONObject json=null;
		String jsonSignup=(String)session.getAttribute("Det");
		JSONParser parser=new JSONParser();
		try {
			json=(JSONObject)parser.parse(jsonSignup);
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
		
        if(username == null) {
        	session.setAttribute("pass", "Failed");
        	session.setAttribute("statusCode",500);
        	session.setAttribute("Message","username is null !!");
        }
        else {

        	
        	boolean validation=user.isObjectUser(username);
        	
        	if(!validation) {
        		session.setAttribute("pass", "Failed");
            	session.setAttribute("statusCode",500);
            	session.setAttribute("Message","username is already taken !!");
        	}
        	else {
        		String ro=(String) json.get("role");
        		session.setAttribute("role", ro);
        		user.storeObjUser(user.createAObj(username, password, ro));
        	if(password != null && password1 != null) {
        		if(password.equals(password1)) {
        			UUID uuid=UUID.randomUUID();
    				String ids=uuid.toString();
        			if(ro.equals("Student")) {
        				json.put("username", username);
        				student.storeTheObj(student.createAStudentObj(json));
        				ses.storeASession(ses.createASession(username,ids));
               	        session.setAttribute("username", username);
               	        Cookie cookie=new Cookie("SESSIONID",ids);
               	        cookie.setMaxAge(7);
               	        response.addCookie(cookie);
        			}
        			else {
        				json.put("username", username);
        				teacher.storeTheObj(teacher.createObjTeacher(json));
        				ses.storeASession(ses.createASession(username,ids));
               	        session.setAttribute("username", username);
               	        Cookie cookie=new Cookie("SESSIONID",ids);
               	        cookie.setMaxAge(7);
               	        response.addCookie(cookie);
        			}
        			session.setAttribute("pass", "Congrats");
                	session.setAttribute("statusCode",200);
                	session.setAttribute("Message","Successfully Signup !!");
        		}
        		else {
        			session.setAttribute("pass", "Failed");
                	session.setAttribute("statusCode",500);
                	session.setAttribute("Message","password is misMatch !!");
        		}
        			
        	}
        	else {
        		session.setAttribute("pass", "Failed");
            	session.setAttribute("statusCode",500);
            	session.setAttribute("Message","password is null !!");
        	}
        	//
        	}
        	
        	}
         session.setAttribute("page","signup");
		 response.sendRedirect("successfullySignup.jsp");
}
}
