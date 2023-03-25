package Student;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import classes.Quizz;
import classes.main;

/**
 * Servlet implementation class QuizzServlet
 */
@WebServlet("/Student/QuizzServlet")
public class QuizzServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizzServlet() {
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
		HttpSession ses=request.getSession();
		main m=new main();
		SimpleDateFormat sm=new SimpleDateFormat("MM-dd-yyyy");
		JSONArray array=new JSONArray();
		Quizz m1=new Quizz();
		ArrayList<Quizz> Quizzes=m1.getAllQuizzes();
		for(Quizz q : Quizzes) {
			JSONObject obj=new JSONObject();
			if(q.getName().equals("Demo")) {
			obj.put("username",(String) ses.getAttribute("username"));
			obj.put("name", q.getName());
			obj.put("numberOfQuestion", Integer.toString(q.getNumberOfQuestion()));
			System.out.println(q.getUploadDate());
			obj.put("uploadDate",sm.format(q.getUploadDate()).toString());
			obj.put("highestRank", Integer.toString(q.getHighestRank()));
			obj.put("CreatorName", q.getTeacher().getUsername());
			array.add(obj);
			}
		}
		response.getWriter().append(array.toString());
	}

}
