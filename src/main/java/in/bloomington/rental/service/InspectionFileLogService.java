package in.bloomington.rental.service;
import java.util.List;
import in.bloomington.rental.model.InspectionFileLog;


public interface InspectionFileLogService {

		public InspectionFileLog get(int id);
		public void save(InspectionFileLog val);

		public List<InspectionFileLog> findByRentalId(Integer rentalId);
		public int findCountByRentalId(int id);

}
