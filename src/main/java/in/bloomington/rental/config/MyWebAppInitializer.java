package in.bloomington.rental.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.Filter;

/*
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import javax.servlet.ServletRegistration;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import javax.servlet.MultipartConfigElement;
*/
public class MyWebAppInitializer
      extends AbstractAnnotationConfigDispatcherServletInitializer {

		// for spring security config use the following
		/*
		@Override
		protected Class<?>[] getRootConfigClasses() {
				return new Class[] { AppConfig.class, WebSecurityConfig.class };
			
		}
		*/
		// for non security config use the following
		@Override
		protected Class<?>[] getRootConfigClasses() {
      return new Class[] { AppConfig.class };
   }

   @Override
   protected Class<?>[] getServletConfigClasses() {
      return new Class[] { WebConfig.class };
   }

   @Override
   protected String[] getServletMappings() {
      return new String[] { "/" };
   }
		@Override
		protected Filter[] getServletFilters() {
				return new Filter[]{new UserRequestFilter()};
		}		

		@Override
		public void onStartup(ServletContext servletContext) throws ServletException {
				super.onStartup(servletContext);
				servletContext.addListener(new SessionListener());
		}		
}
