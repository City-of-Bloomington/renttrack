package in.bloomington.rental.service;
import java.util.List;
import in.bloomington.rental.model.EmailDetailLog;
import java.util.List;

public interface EmailDetailLogService {

		public EmailDetailLog get(int id);
		public void save(EmailDetailLog val);

		public List<EmailDetailLog> getAll();
}
