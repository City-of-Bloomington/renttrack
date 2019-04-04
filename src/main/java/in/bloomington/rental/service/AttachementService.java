package in.bloomington.rental.service;
import java.util.List;

import in.bloomington.rental.model.Attachement;

public interface AttachementService {

		public Attachement get(int id);
		public void save(Attachement attachement);

		public void delete(int id);
		public void delete(Attachement val);

		public List<Attachement> findByRentalId(Integer val);
		public List<Attachement> findByInspectionId(Integer val);
				
}
