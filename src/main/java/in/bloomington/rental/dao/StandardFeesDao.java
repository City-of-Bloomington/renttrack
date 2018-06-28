package in.bloomington.rental.dao;
import java.util.List;
import org.hibernate.SessionFactory;
import in.bloomington.rental.model.StandardFees;

public interface StandardFeesDao {

		StandardFees get(int id);
		void save(StandardFees standardFees);
		void update(int id, StandardFees standardFees);
		void delete(int id);
		List<StandardFees> getAll();
						
}
