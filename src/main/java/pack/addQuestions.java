package pack;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import classes.Question;
import classes.Quizz;

/**
 * Servlet implementation class addQuestions
 */
@WebServlet("/addQuestions")
public class addQuestions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addQuestions() {
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
		// TODO Auto-generated method stub
		String value="";
		Quizz q=new Quizz();
		boolean validate= false;
		BufferedReader buff=request.getReader();
		
		String json="";
		String str="";
		while((str=buff.readLine()) != null) {
			json=str+json;
		}
		JSONParser parser=new JSONParser();
		JSONObject obj=null;
		
		try {
			obj=(JSONObject) parser.parse(json);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		validate=q.isQuizzName((String)obj.get("QuizzName"));
		if(validate) {
			Question a=new Question();
			String Question=(String) obj.get("Question");
			String optionA=(String) obj.get("option_a");
			String optionB=(String) obj.get("option_b");
			String optionC=(String) obj.get("option_c");
			String optionD=(String) obj.get("option_d");
			String correct=(String) obj.get("correct");
			String type=(String) obj.get("QuizzName");
			
		   boolean valid=a.storeTheQuestion(a.createObjQuestion(Question, optionA, optionB, optionC, optionD, correct, type));
			if(valid) {
				boolean vali=q.updateNumberOfQuestion(type);
				if(vali) {
					value="success !!";
					System.out.println(value);
				}
				else {
					value="Update Failed !!";
					System.out.println(value);
				}
				
			}
			else {
				value="Failed !!";
				System.out.println(value);
			}
		}
		else {
			value="QuizzName invalid !!";
			System.out.println(value);
		}
		response.getWriter().append(value);
		
	}

}
