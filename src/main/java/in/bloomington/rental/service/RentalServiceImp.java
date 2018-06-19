package in.bloomington.rental.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import in.bloomington.rental.model.Rental;
import in.bloomington.rental.util.Search;
import in.bloomington.rental.dao.RentalDao;
import in.bloomington.rental.model.Address;
import in.bloomington.rental.dao.AddressDao;

@Service
public class RentalServiceImp implements RentalService{
		
		@Autowired
		private RentalDao rentalDao;

		@Autowired
		private AddressDao addressDao;
		
		@Transactional
		public void save(Rental rental){
				rentalDao.save(rental);
		}
		@Transactional(readOnly = true)
		public Rental get(int id){
				Rental rental = rentalDao.get(id);
				if(rental != null){
						List<Address> addresses = addressDao.findByRentalId(id);
						if(addresses  != null && addresses.size() > 0){
								rental.setAddresses(addresses);
						}
				}
        return  rental;
		}

		@Transactional
    public void update(int id, Rental rental) {
        rentalDao.update(id, rental);
    }
 		
		@Transactional
    public void delete(int id) {
				rentalDao.delete(id);
    }

		@Transactional(readOnly = true)		
		public List<Rental> getAll(){
        return rentalDao.getAll();
    }

		@Transactional(readOnly = true)		
		public List<Rental> search(Search search){
				return rentalDao.search(search);
		}
		
		@Transactional(readOnly = true)				
		public List<Rental> findExpireDate(String dateFrom, String dateTo){
				return rentalDao.findExpireDate(dateFrom, dateTo);
		}


}
