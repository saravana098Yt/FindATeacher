package pack;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
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
import classes.main;

/**
 * Servlet implementation class SignupServlet
 */

@WebServlet(urlPatterns={"/SignupServlet"},loadOnStartup=0)
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init() {

		 try {
   		  String dbURL="jdbc:mysql://localhost:3306/FindTeacher";
   	      Class.forName("com.mysql.cj.jdbc.Driver");
   	      Common.application.dbConnection = DriverManager.getConnection(dbURL,"Saran","Saran1234");
   	  }
   	  catch(Exception ex) {
   		  System.out.println(ex.getMessage());
   	  }
	 
		 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings({ "unchecked" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session=request.getSession();
	String res=(String)session.getAttribute("response");
	if(res == null) {
		response.sendRedirect("extra.html");
	}
	else {
		session.setAttribute("pass", "Failed");
    	session.setAttribute("statusCode",500);
    	session.setAttribute("Message",res);
    	session.setAttribute("page","signup");
  	    response.sendRedirect("successfullySignup.jsp");
	}
	  
	}

}
