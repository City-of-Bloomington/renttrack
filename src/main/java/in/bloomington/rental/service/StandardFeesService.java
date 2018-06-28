package in.bloomington.rental.service;
import java.util.List;
import in.bloomington.rental.model.StandardFees;
import java.util.List;

public interface StandardFeesService {

		public StandardFees get(int id);
		public void save(StandardFees val);
		public void update(int id, StandardFees val);
		public void delete(int id);

		public List<StandardFees> getAll();

}
