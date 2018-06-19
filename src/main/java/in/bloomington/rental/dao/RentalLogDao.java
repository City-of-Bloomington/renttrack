package in.bloomington.rental.dao;
import java.util.List;
import in.bloomington.rental.model.RentalLog;

public interface RentalLogDao {

		RentalLog get(int id);
		void save(RentalLog val);
		List<RentalLog> findByRentalId(Integer val);
		
}
