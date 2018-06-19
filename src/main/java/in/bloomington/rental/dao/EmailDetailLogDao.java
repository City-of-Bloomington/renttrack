package in.bloomington.rental.dao;
import java.util.List;
import org.hibernate.SessionFactory;
import in.bloomington.rental.model.EmailDetailLog;

public interface EmailDetailLogDao {

		EmailDetailLog get(int id);
		void save(EmailDetailLog val);
		List<EmailDetailLog> getAll();
}
