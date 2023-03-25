package Student;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import classes.Quizz;

/**
 * Servlet implementation class ResultServlet
 */
@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResultServlet() {
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
	    Quizz q=new Quizz();
		HttpSession ses=request.getSession();
	String username=(String) ses.getAttribute("username");
	BufferedReader buff=request.getReader();
	String str="";
	String jsoninput="";
	while((str=buff.readLine()) != null) {
		jsoninput=str+jsoninput;
	}
	JSONParser parser=new JSONParser();
	JSONObject obj=null;
	try {
		obj=(JSONObject) parser.parse(jsoninput);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if(obj.get("QuizHigh") != null) {
	q.updateHighestRank(Integer.parseInt((String)obj.get("QuizHigh")), username);
	}
	obj.put("username", username);
	System.out.println(obj);
	response.getWriter().append(obj.toString());
	}

}
