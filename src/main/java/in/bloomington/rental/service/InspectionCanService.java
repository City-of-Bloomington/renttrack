package in.bloomington.rental.service;
import java.util.List;
import in.bloomington.rental.model.InspectionCan;
import java.util.List;

public interface InspectionCanService {

		public InspectionCan get(int id);
		public void save(InspectionCan val);
		public void update(int id, InspectionCan val);
		public void delete(int id);

		public List<InspectionCan> getAll();
		public List<InspectionCan> findByName(String val);
		public List<InspectionCan> findByInspectionId(int id);		
}
