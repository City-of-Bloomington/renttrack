package in.bloomington.rental.dao;
import java.util.List;
import in.bloomington.rental.model.Item;
import in.bloomington.rental.model.Address;

public interface AddressDao {

		Address get(int id);
		void save(Address address);
		void update(int id, Address address);
		void delete(int id);
		void delete(Address address);
		List<Address> find(String address, String inValid);
		List<Address> findByRentalId(Integer val);
		List<Address> getAll();
		List<Item> getList(String address);		

}
