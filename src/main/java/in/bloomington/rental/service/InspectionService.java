package in.bloomington.rental.service;
import java.util.List;
import in.bloomington.rental.model.Inspection;
import java.util.List;

public interface InspectionService {

		public Inspection get(int id);
		public void save(Inspection inspection);
		public void update(int id, Inspection inspection);
		public void delete(int id);

		public List<Inspection> getAll();
		public List<Inspection> findByRentalId(int id);
		public int findCountByRentalId(int id);
}
