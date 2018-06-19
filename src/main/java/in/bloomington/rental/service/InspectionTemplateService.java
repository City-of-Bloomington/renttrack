package in.bloomington.rental.service;
import java.util.List;
import in.bloomington.rental.model.InspectionTemplate;
import java.util.List;

public interface InspectionTemplateService {

		public InspectionTemplate get(int id);
		public void save(InspectionTemplate val);
		public void update(int id, InspectionTemplate val);
		public void delete(int id);
		public List<InspectionTemplate> findByRentalId(int id);

}
