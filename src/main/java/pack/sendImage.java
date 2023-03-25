package pack;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.ProfileImage;

/**
 * Servlet implementation class sendImage
 */
@SuppressWarnings("unused")
@WebServlet("/sendImage")
public class sendImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sendImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	@SuppressWarnings("resource")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession ses=request.getSession();
	    String username=(String) ses.getAttribute("username");
	    ProfileImage im=new ProfileImage();
	    String path=im.profileImgPath(username);
	  try{  
        FileInputStream file=new FileInputStream(new File(path));
        ServletOutputStream out = response.getOutputStream();
        byte[] by = new byte[1024];
        int byteRead;
        while((byteRead = file.read(by))!=-1)
        	out.write(by, 0, byteRead);
        file.close();
        out.close();
        
	    }  
	catch(Exception ex) {
	}
	}
	}

