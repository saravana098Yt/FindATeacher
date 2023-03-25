package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;



/**
 * Servlet Filter implementation class ValidationFilter
 */
@SuppressWarnings("serial")
public class ValidationFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public ValidationFilter() {
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
		String responseObj=null;
		boolean validate=false;
		HttpServletRequest httpRequest=(HttpServletRequest)request;
		HttpServletResponse httpResponse=(HttpServletResponse)response;
		HttpSession session=httpRequest.getSession();
		String name=httpRequest.getParameter("name");
		String age1=httpRequest.getParameter("age");
		String gender=httpRequest.getParameter("gender");
		String date=httpRequest.getParameter("dob");
		String role=httpRequest.getParameter("role");
		String exp=null;
		String lang=null;
		if(name != null) {
			if(age1 != null) {
				try {
				int age=Integer.parseInt(age1);
				}
				catch(NumberFormatException ex) {
				   responseObj="number only !!";
				}
				
				if(gender != null) {
					
					if(date != null) {
						
						if(role != null) {
							if(role.equals("Student")) {
								
								JSONObject obj1=new JSONObject();
								obj1.put("name", name);
								obj1.put("age", age1);
								obj1.put("gender", gender);
								obj1.put("role", role);
								obj1.put("date", date);
								session.setAttribute("Det", obj1.toString());
								validate=true;
							}
							else {
								exp=request.getParameter("Exp");
       							lang=request.getParameter("lang");
								if(exp != null) {
									if(lang != null) {				
										
										JSONObject obj1=new JSONObject();
										obj1.put("name", name);
										obj1.put("age",age1);
										obj1.put("gender", gender);
										obj1.put("role", role);
										obj1.put("date", date);
										obj1.put("exp", exp);
										obj1.put("lang", lang);
										
										session.setAttribute("Det", obj1.toString());
										validate=true;
									}
									else {
										validate=false;
										responseObj="Language is empty !!";
									}
								}
								else {
									validate=false;
									responseObj="Experience is empty !!";
								}
							}
						}
						else {
							validate=false;
							responseObj="role is empty !!";
						}
					}
					
					else {
						validate=false;
						responseObj="Date is empty !!";
					}
				}
				else {
					validate=false;
					responseObj="gender is empty !!";
				}
			}
			else {
				validate=false;
				responseObj="Age is empty !!";
			}
		}
		else {
			validate=false;
			responseObj="Name is empty !!";
		}
       if(validate) {
    	   chain.doFilter(httpRequest, httpResponse);
       }
       else {
    	   session.setAttribute("response",responseObj);
       }
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
