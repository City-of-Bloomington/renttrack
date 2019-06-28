package in.bloomington.rental.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.Filter;

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
				// new CorsFilter()};
		}		

		@Override
		public void onStartup(ServletContext servletContext) throws ServletException {
				super.onStartup(servletContext);
				servletContext.addListener(new SessionListener());
		}		
}
