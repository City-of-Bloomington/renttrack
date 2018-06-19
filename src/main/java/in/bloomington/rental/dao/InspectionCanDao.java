package in.bloomington.rental.dao;
import java.util.List;
import org.hibernate.SessionFactory;
import in.bloomington.rental.model.InspectionCan;

public interface InspectionCanDao {

		InspectionCan get(int id);
		void save(InspectionCan inspectionCan);
		void update(int id, InspectionCan inspectionCan);
		void delete(int id);

		List<InspectionCan> findByName(String str);
		List<InspectionCan> findByInspectionId(int id);
		List<InspectionCan> getAll();
				
}
