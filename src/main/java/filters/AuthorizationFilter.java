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
import javax.servlet.http.HttpFilter;

import org.json.simple.JSONObject;

import Common.application;

/**
 * Servlet Filter implementation class AuthorizationFilter
 */
public class AuthorizationFilter extends HttpFilter implements Filter {
       
    private static final long serialVersionUID = -545412871335003408L;

	/**
     * @see HttpFilter#HttpFilter()
     */
    public AuthorizationFilter() {
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
	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		JSONObject jsonObj=new JSONObject();
		boolean validated=false;
		String username=(String)request.getAttribute("LoggedInUser");
		try {
			PreparedStatement stmt=application.dbConnection.prepareStatement("select * from users where username = ? and role = ?");
		    stmt.setString(1, username);
		    stmt.setString(2, "Teacher");
		    ResultSet re=stmt.executeQuery();
		    if(re.next()) {
		    	chain.doFilter(request, response);
		    }
		    else {
		    	jsonObj.put("statusCode", 400);
				jsonObj.put("message", "You don't have enough permission");
		    }
		
		}
		catch(Exception ex) {
			jsonObj.put("statusCode", 500);
			jsonObj.put("Message", "Error occured Problem,Please contact Administrater !!");
		}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
