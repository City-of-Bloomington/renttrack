package in.bloomington.rental.dao;
import java.util.List;
import org.hibernate.SessionFactory;
import in.bloomington.rental.model.RentalStructure;

public interface RentalStructureDao {

		RentalStructure get(int id);
		void save(RentalStructure rentalStructure);
		void update(int id, RentalStructure rentalStructure);
		void delete(int id);
		void delete(RentalStructure val);
		// needed for search
		List<RentalStructure> getAll();
		List<RentalStructure> findByRentalId(Integer rentalId);
				
}
