package Student;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import classes.Question;
import classes.main;

/**
 * Servlet implementation class QuestionServlet
 */
@WebServlet("/Student/QuestionServlet")
public class QuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionServlet() {
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
		main m=new main();
		Question q1=new Question();
		ArrayList<Question> Questions=q1.getAllQuestions();
		String Type=request.getParameter("type");
		JSONArray array=new JSONArray();
		for(Question q:Questions) {
			if(q.getType().equals(Type)) {
				String question=q.getQuestion();
				String[]option=q.getOption();
				String correct=q.getCorrect();
				JSONObject obj=new JSONObject();
				obj.put("question", question);
				obj.put("a", option[0]);
				obj.put("b", option[1]);
				obj.put("c", option[2]);
				obj.put("d", option[3]);
				obj.put("correct",correct);
				obj.put("type", q.getType());
				array.add(obj);
			}
		}
		response.getWriter().append(array.toString());
	}

}
