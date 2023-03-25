package filters;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import Common.application;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
public class AuthenticationFilter extends HttpFilter implements Filter {
       
    private static final long serialVersionUID = -4387294348231416555L;

	/**
     * @see HttpFilter#HttpFilter()
     */
    public AuthenticationFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		boolean validated=false;
		JSONObject js=new JSONObject();
		HttpServletRequest httpRequest=(HttpServletRequest)request;
		HttpServletResponse httpResponse=(HttpServletResponse)response;
		Cookie[] cookies=httpRequest.getCookies();
		
		HttpSession session=httpRequest.getSession();
		String username=(String) session.getAttribute("username");
				try {
					PreparedStatement ptmt=application.dbConnection.prepareStatement("select * from session where username = ?");
				    ptmt.setString(1, username);
				    ResultSet re=ptmt.executeQuery();
				    if(re.next()) {
				    	validated=true;
				    	String username1=re.getString(1);
				    	String id=re.getString(2);
				    	Cookie cookie=new Cookie("SESSIONID",id);
				    	httpResponse.addCookie(cookie);
				    	request.setAttribute("LoggedInUser",username);
				    	
				    }
				    else {
				    	validated=false;
				    	js.put("statusCode", 400);
				    	js.put("Message", "unauthentication user !!!");
				    }
				}
				catch(Exception ex) {
					js.put("statusCode", 500);
					js.put("Message", "Error occured Problem ,Please administrater contact !!");
				}
		
		if(validated) {
			chain.doFilter(request, response);
		}
		else {
			response.getWriter().append(js.toString());
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
