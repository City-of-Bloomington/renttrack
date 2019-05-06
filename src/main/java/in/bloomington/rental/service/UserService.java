package in.bloomington.rental.service;

import java.util.List;

import in.bloomington.rental.model.RentUser;

public interface UserService {
		void save(RentUser user);
		void update(int id, RentUser user);

		void delete(int id);
		
		RentUser get(int id);
		RentUser findByUsername(String username);
		List<RentUser> getInspectors();
		List<RentUser> getAllInspectors();
	  List<RentUser> getActiveInspectors();
		List<RentUser> list();
		
}
