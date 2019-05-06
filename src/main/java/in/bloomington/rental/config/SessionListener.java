package in.bloomington.rental.config;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionEvent;


public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        event.getSession().setMaxInactiveInterval(12000); 
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
    }
}
