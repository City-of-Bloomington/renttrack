package in.bloomington.rental.service;

import java.util.List;

import in.bloomington.rental.model.RentalNote;

public interface RentalNoteService {
		void save(RentalNote val);
		void update(int id, RentalNote val);

		void delete(int id);
		
		RentalNote get(int id);

		List<RentalNote> list();
		List<RentalNote> findByRentalId(int rental_id);
		
}
