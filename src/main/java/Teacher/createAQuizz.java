package Teacher;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Common.application;
import classes.Quizz;
import classes.Teacher;

/**
 * Servlet implementation class createAQuizz
 */
@WebServlet("/createAQuizz")
public class createAQuizz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createAQuizz() {
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
		HttpSession ses=request.getSession();
		String username=(String) ses.getAttribute("username");
		Teacher t=new Teacher();
		Teacher t1=t.getUserTeacherObj(username);
		String res="";
		Quizz q=new Quizz();
		boolean validate=false;
		SimpleDateFormat sm=new SimpleDateFormat("MM-dd-yyyy");
		String name=request.getParameter("QuizzName");
		int numberOfQuestions=0;
		String uploadDate=sm.format(new Date()).toString();
		int highestRank=0;
		validate =q.isQuizzName(name);
		if(!validate) {
		try {
			PreparedStatement stmt=application.dbConnection.prepareStatement("insert into Quizz values(?,?,?,?,?)");
			stmt.setString(1, name);
			stmt.setInt(2, numberOfQuestions);
			stmt.setString(3, uploadDate);
			stmt.setInt(4, highestRank);
			stmt.setString(5, t1.getUsername());
			stmt.execute();
			res="success !!";
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			res="failed !!";
		}
		}
		else {
			res="taken !!";
		}
		response.getWriter().append(res);
	}

}
