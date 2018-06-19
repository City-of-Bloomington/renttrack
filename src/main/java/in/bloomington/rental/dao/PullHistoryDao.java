package in.bloomington.rental.dao;
import java.util.List;
import org.hibernate.SessionFactory;
import in.bloomington.rental.model.PullHistory;

public interface PullHistoryDao {

		PullHistory get(int id);
		void save(PullHistory pullHistory);
		void update(int id, PullHistory pullHistory);
		void delete(int id);

		List<PullHistory> getPullHistoryForRental(int rental_id);
		// List<PullHistory> getAll();
		
				
}
