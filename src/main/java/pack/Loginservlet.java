package pack;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import Common.application;
import classes.User;
import classes.session;

/**
 * Servlet implementation class Loginservlet
 */
@WebServlet("/Loginservlet")
public class Loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String ids;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Loginservlet() {
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
	@SuppressWarnings({ "unused", "unchecked" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String uniqueID=request.getParameter("ID");
		session ses=new session();
		User user=new User();
		HttpSession session=request.getSession();
		JSONObject obj=new JSONObject();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String username1="";
		String password1="";
		String role="";
		String value="";
		if((username != null)&&(password != null)) {
		try {
			PreparedStatement stmt=application.dbConnection.prepareStatement("select * from users where username = ?");
			stmt.setString(1,username);
			ResultSet re=stmt.executeQuery();
			if(re.next()) {
				username1 =re.getString(1);
				password1 =re.getString(2);
				role=re.getString(3);
				session.setAttribute("role", role);
			}
			else {
				session.setAttribute("pass", "Failed");
				session.setAttribute("StatusCode", 500);
				session.setAttribute("Message", "It is not username !!");
			}
		}
		catch(Exception ex) {
			session.setAttribute("pass", "Failed");
			session.setAttribute("StatusCode", 500);
			session.setAttribute("Message", "SQLException Error !!");
		}
		
		if(username.equals(username1)) {
			if(password.equals(password1)) {
				String ids="";
				
			ids= ses.getstoreSessionIdIn(username);
			if(ids == null) {
				session.setAttribute("pass", "Failed");
				session.setAttribute("StatusCode", 404);
				session.setAttribute("Message", "login falied !!");
			}
			else {
			    session.setAttribute("username", username);
				Cookie cookie=new Cookie("SESSIONID",ids);
				cookie.setMaxAge(7);
				response.addCookie(cookie);
				session.setAttribute("pass", "Congrats");
				session.setAttribute("statusCode", 200);
				session.setAttribute("Message", "Successfully Logged In");
			}
			}
			else {
				session.setAttribute("pass", "Failed");
				session.setAttribute("statusCode", 500);
				session.setAttribute("Message", "Password mismatch !!");
				
			}
		}
		else {
			session.setAttribute("pass", "Failed");
			session.setAttribute("StatusCode", 500);
			session.setAttribute("Message", "Username Incorrect !!");
		}
	}
	else {
		session.setAttribute("pass", "Failed");
		session.setAttribute("StatusCode", 404);
		session.setAttribute("Message", "No value Found !!");
	}
	
		session.setAttribute("page", "login");
		response.sendRedirect("successfullySignup.jsp");
	}
}
