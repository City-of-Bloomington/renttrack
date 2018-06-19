package in.bloomington.rental.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 import in.bloomington.rental.model.Item;
import in.bloomington.rental.model.Address;
import in.bloomington.rental.dao.AddressDao;

@Service
public class AddressServiceImp implements AddressService{
		
		@Autowired
		private AddressDao addressDao;

		@Transactional
		public void save(Address address){
				addressDao.save(address);
		}
		@Transactional(readOnly = true)
		public Address get(int id){
        return  addressDao.get(id);
		}

		@Transactional
    public void update(int id, Address address) {
        addressDao.update(id, address);
    }
 		
		@Transactional
    public void delete(int id) {
				addressDao.delete(id);
    }
		@Transactional
    public void delete(Address val) {
				addressDao.delete(val);
    }
		
		@Transactional(readOnly = true)
		public List<Address> findByRentalId(Integer val){
				return addressDao.findByRentalId(val);
		}

		@Transactional(readOnly = true)
		public List<Address> find(String address, String inValid){
				return addressDao.find(address, inValid);
		}
		
		@Transactional(readOnly = true)
		public List<Address> getAll(){
				return addressDao.getAll();
		}
		
		@Transactional(readOnly = true)		
		public List<Item> getList(String address){
				return addressDao.getList(address);
		}
}
