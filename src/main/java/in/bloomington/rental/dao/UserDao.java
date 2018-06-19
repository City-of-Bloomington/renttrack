package in.bloomington.rental.dao;
import java.util.List;
import in.bloomington.rental.model.RentUser;

public interface UserDao {

		RentUser get(int id);
		RentUser findByUsername(String name);
		void save(RentUser user);
		void update(int id, RentUser user);
		void delete(int id);
		List<RentUser> list();
		List<RentUser> getInspectors();
		List<RentUser> getAllInspectors();		


}
