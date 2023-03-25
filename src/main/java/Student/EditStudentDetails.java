package Student;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class EditStudentDetails
 */
@WebServlet("/Student/EditStudentDetails")
public class EditStudentDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditStudentDetails() {
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
		// TODO Auto-generated method stub
		JSONObject jsonobj=new JSONObject();
		String username="";
		Cookie[]cookies=request.getCookies();
		for(Cookie cookie:cookies) {
			if(cookie.getName().equals("username")) {
				username=cookie.getValue();
				break;
			}
		}
		if(username.isEmpty()) {
			jsonobj.put("statusCode",500);
			jsonobj.put("Message", "you is not Properly login !!");
		}
		else {
			RequestDispatcher disp=request.getRequestDispatcher("edit.html");
			disp.forward(request, response);
	}
}
}
