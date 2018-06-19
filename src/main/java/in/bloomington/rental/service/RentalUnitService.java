package in.bloomington.rental.service;
import java.util.List;
import in.bloomington.rental.model.RentalUnit;
import java.util.List;

public interface RentalUnitService {

		public RentalUnit get(int id);
		public void save(RentalUnit rentalUnit);
		public void update(int id, RentalUnit rentalUnit);
		public void delete(int id);

		public List<RentalUnit> getAll();
		public List<RentalUnit> findByStructureId(Integer val);
				
}
