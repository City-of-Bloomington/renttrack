package in.bloomington.rental.service;

import java.util.List;

import in.bloomington.rental.model.RentalLog;

public interface RentalLogService {

		public RentalLog get(int id);
		public void save(RentalLog rentalLog);

		public List<RentalLog> findByRentalId(Integer val);
				
}
