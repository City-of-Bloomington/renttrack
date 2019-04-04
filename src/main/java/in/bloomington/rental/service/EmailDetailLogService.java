package in.bloomington.rental.service;

import java.util.List;

import in.bloomington.rental.model.EmailDetailLog;

public interface EmailDetailLogService {

		public EmailDetailLog get(int id);
		public void save(EmailDetailLog val);

		public List<EmailDetailLog> getAll();
}
