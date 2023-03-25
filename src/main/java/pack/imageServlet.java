package pack;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import Common.application;
import classes.ProfileImage;
import classes.main;

/**
 * Servlet implementation class imageServlet
 */
@MultipartConfig(
		maxFileSize = 1024*1024*1024
		)
@WebServlet("/imageServlet")
public class imageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public imageServlet() {
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
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		main m=new main();
		HttpSession session=request.getSession();
		String username=(String) session.getAttribute("username");
		ProfileImage im=new ProfileImage();
		String imgfilename=null;
		String value="";
		boolean validate=im.isUsernamecheck(username);
			
		Part part=request.getPart("file");
		
		String fileName=ExtractFileName(part);
		String savePath="/home/saravan-zstk298/FindATeacher/images/"+fileName;
		File saveDirFile=new File(savePath);
		part.write(savePath);
		
		ArrayList<ProfileImage> imglist=im.getAllImages();
		
		if(validate) {
			ProfileImage pm=null;
			for(ProfileImage p1:imglist){
				if(p1.getUsername().equals(username)) {
					pm=p1;
					break;
				}
			}
			pm.setPath(savePath);
		 im.UpdateImg(username,savePath);
		}
		else {
		im.storeTheImg(im.createAProfileImg(username, fileName, savePath));
		}
		
			response.getWriter().append(value);

	}
	
	public String ExtractFileName(Part part) {
		String contentDisp=part.getHeader("content-disposition");
		String []items=contentDisp.split(";");
		
		for(String s:items) {
			if(s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=")+2,s.length()-1);
			}
		}
		return "";
		
	}

}
