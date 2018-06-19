package in.bloomington.rental.service;
import java.util.List;
import in.bloomington.rental.model.Address;
import in.bloomington.rental.model.Item;

public interface AddressService {

		public Address get(int id);
		public void save(Address address);
		public void update(int id, Address address);

		public void delete(int id);
		public void delete(Address val);

		public List<Address> findByRentalId(Integer val);
		public List<Address> find(String address, String inValid);
		public List<Address> getAll();
		public List<Item> getList(String address);
				
}
