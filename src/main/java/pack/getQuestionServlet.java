package pack;

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
import classes.Quizz;

/**
 * Servlet implementation class getQuestionServlet
 */
@WebServlet("/getQuestionServlet")
public class getQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getQuestionServlet() {
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
		JSONArray array=new JSONArray();
		boolean validate = false;
		Quizz q=new Quizz();
		Question q2=new Question();
		String QuizzName = request.getParameter("QuizzName");
		ArrayList<Quizz> Quizzes=q.getAllQuizzes();
		System.out.println("Q"+Quizzes.size());
		for(Quizz q1 : Quizzes) {
			
			if(q1.getName().equals(QuizzName)) {
				validate = true;
				break;
			}
		}
		
		if(validate) {
			ArrayList<Question> Questions =q2.getAllQuestions();
			System.out.println(Questions.size());
			for(int i=0;i<Questions.size();i++) {
				if(Questions.get(i).getType().equals(String.valueOf(QuizzName))) {
					JSONObject json=new JSONObject();
					json.put("Question", Questions.get(i).getQuestion());
					String[]arr=Questions.get(i).getOption();
					json.put("a",arr[0]);
					json.put("b", arr[1]);
					json.put("c", arr[2]);
					json.put("d", arr[3]);
					json.put("correct", Questions.get(i).getCorrect());
					
					array.add(json);
				}

		}
		}
		else {
			JSONObject json=new JSONObject();
			json.put("Message", "Quizz not found !!");
			array.add(json);
		}
		System.out.println(array.toString());
		response.getWriter().append(array.toString());
	}

}
