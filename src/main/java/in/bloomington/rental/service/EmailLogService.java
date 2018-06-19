package in.bloomington.rental.service;
import java.util.List;
import in.bloomington.rental.model.EmailLog;


public interface EmailLogService {

		public EmailLog get(int id);
		public void save(EmailLog val);

		public List<EmailLog> getAll();
}
