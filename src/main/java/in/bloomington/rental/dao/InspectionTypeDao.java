package in.bloomington.rental.dao;
import java.util.List;
import in.bloomington.rental.model.InspectionType;

public interface InspectionTypeDao {

		InspectionType get(int id);
		void save(InspectionType inspectionType);
		void update(int id, InspectionType inspectionType);
		void delete(int id);
		List<InspectionType> list();

}
