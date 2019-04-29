package in.bloomington.rental.service;

import java.util.List;

import in.bloomington.rental.model.Item;
import in.bloomington.rental.model.Owner;
import in.bloomington.rental.model.RentalOwner;

public interface OwnerService {

		public Owner get(int id);
		RentalOwner getRentalOwner(int id);		
		public void save(Owner owner);
		public void save(RentalOwner val);		
		public void update(int id, Owner owner);
		public void delete(int id);
		public void removeRentalOwner(int id);

		// needed for search
		public List<Owner> getAll();
		public List<Owner> findByName(String name);
		public List<Owner> findAgentByName(String name);
		public List<Owner> search(Owner owner);
		public List<Owner> find(String name, String address, String city, String state, String zip, String email);
		public List<RentalOwner> getAllForOwner(int owner_id);
		public List<RentalOwner> getAllForRental(int rental_id);		
		public List<Owner> findOwnersForExpireEmail(String startDate,
																								String endDate);
		public List<Item> getList(String name);
		public List<Item> getAgentList(String name);		
		
}
