package in.bloomington.rental.dao;
import java.util.List;
import in.bloomington.rental.model.Attachement;

public interface AttachementDao {

		Attachement get(int id);
		void save(Attachement attachement);
		void delete(int id);
		void delete(Attachement attachement);
		List<Attachement> findByRentalId(Integer val);
		List<Attachement> findByInspectionId(Integer val);		
		
}
