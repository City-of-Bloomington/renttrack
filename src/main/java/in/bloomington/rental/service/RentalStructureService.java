package in.bloomington.rental.service;
import java.util.List;
import in.bloomington.rental.model.RentalStructure;
import java.util.List;

public interface RentalStructureService {

		public RentalStructure get(int id);
		public void save(RentalStructure rentalStructure);
		public void update(int id, RentalStructure rentalStructure);
		public void delete(int id);
		public void delete(RentalStructure val);
		public List<RentalStructure> getAll();
		public List<RentalStructure> findByRentalId(Integer val);
				
}
