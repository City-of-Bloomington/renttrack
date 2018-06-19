package in.bloomington.rental.service;

import java.util.List;

import in.bloomington.rental.model.OwnerPhone;

public interface OwnerPhoneService {
		void save(OwnerPhone val);
		void update(int id, OwnerPhone val);

		void delete(int id);
		
		OwnerPhone get(int id);

		List<OwnerPhone> list();
		List<OwnerPhone> findByOwnerId(int owner_id);

		
}
