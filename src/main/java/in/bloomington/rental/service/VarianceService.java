package in.bloomington.rental.service;
import java.util.List;
import in.bloomington.rental.model.Variance;
import java.util.List;

public interface VarianceService {

		public Variance get(int id);
		public void save(Variance variance);
		public void update(int id, Variance variance);
		public void delete(int id);

		// needed for search
		public List<Variance> getAll();
		public List<Variance> findByText(String name);
		public List<Variance> find(String rental_id, String text, String dateFrom, String dateTo);
				
}
