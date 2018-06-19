package in.bloomington.rental.dao;
import java.util.List;
import in.bloomington.rental.model.LegalItEmailLog;

public interface LegalItEmailLogDao {

		LegalItEmailLog get(int id);
		void save(LegalItEmailLog val);
		List<LegalItEmailLog> findByRentalId(Integer val);		
}
