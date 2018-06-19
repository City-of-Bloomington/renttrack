package in.bloomington.rental.config;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionEvent;


public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        // System.out.println("session created");
        event.getSession().setMaxInactiveInterval(1200); 
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
				// System.out.println("session destroyed");
    }
}
