package in.bloomington.rental.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import in.bloomington.rental.model.RentUser;

public class UserRequestFilter implements Filter {

	@Override
	public void destroy() {
		// ...
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//
	}

	@Override
	public void doFilter(ServletRequest request, 
											 ServletResponse response,
											 FilterChain chain)
			throws IOException, ServletException {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession(false);
			String uri = req.getRequestURI();
			try{
					if(uri.indexOf("login") > -1 ||
						 uri.indexOf("showErrors") > -1 ||
						 uri.indexOf(".js") > -1 ||
						 uri.indexOf(".jpg") > -1 ||						 
						 uri.indexOf(".css") > -1){
							chain.doFilter(request, response);
					}
					else if(session != null){
							RentUser user = (RentUser)session.getAttribute("user");
							if(user != null && user.isValid()){
									chain.doFilter(request, response);
							}
							else{
									request.getRequestDispatcher("/WEB-INF/views/welcome.jsp")
											.forward(request, response);
							}
					}
					else{
							request.getRequestDispatcher("/WEB-INF/views/welcome.jsp")
									.forward(request, response);
					}
			} catch (Exception ex) {
					request.setAttribute("message", ex);
					// System.err.println(" user filter "+ex);
					request.getRequestDispatcher("/WEB-INF/views/errors.jsp")
							.forward(request, response);
			}
			
	}

}
