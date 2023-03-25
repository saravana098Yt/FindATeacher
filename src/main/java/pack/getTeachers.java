package pack;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import classes.ProfileImage;
import classes.Teacher;

/**
 * Servlet implementation class getTeachers
 */
@WebServlet("/getTeachers")
public class getTeachers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getTeachers() {
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
		Teacher ty=new Teacher();
		ProfileImage oo=new ProfileImage();
		ArrayList<Teacher> list=ty.getValues();
		ArrayList<ProfileImage> profile=oo.getAllImages();
		JSONArray array=new JSONArray();
		for(int i=0;i<list.size();i++) {
			JSONObject obj=new JSONObject();
			for(int j=0;j<profile.size();i++) {
				if(list.get(i).getUsername().equals(profile.get(j).getUsername())) {
					File f=new File(profile.get(j).getPath());
					String url=f.toURL().toString();
					
					obj.put("img",url);
				}
			}
			obj.put("name", list.get(i).getName());
			obj.put("username", list.get(i).getUsername());
			obj.put("age",list.get(i).getAge());
			obj.put("gender", list.get(i).getGender());
			obj.put("lang", String.join(", ",list.get(i).getLang()));
			array.add(obj);
		}
		
		response.getWriter().append(array.toString());
	}

}
