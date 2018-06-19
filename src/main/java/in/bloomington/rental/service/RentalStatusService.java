package in.bloomington.rental.service;

import java.util.List;

import in.bloomington.rental.model.RentalStatus;

public interface RentalStatusService {
   void save(RentalStatus val);
		void update(int id, RentalStatus val);

		void delete(int id);
		
		RentalStatus get(int id);

   List<RentalStatus> list();
		
}
