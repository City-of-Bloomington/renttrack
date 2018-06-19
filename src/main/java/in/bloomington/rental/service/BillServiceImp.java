package in.bloomington.rental.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import in.bloomington.rental.model.Bill;
import in.bloomington.rental.dao.BillDao;

@Service
public class BillServiceImp implements BillService{
		
		@Autowired
		private BillDao billDao;

		@Transactional
		public void save(Bill bill){
				billDao.save(bill);
		}
		@Transactional(readOnly = true)
		public Bill get(int id){
				Bill bill = billDao.get(id);
        return  bill;
		}

		@Transactional
    public void update(int id, Bill bill) {
        billDao.update(id, bill);
    }
 		
		@Transactional
    public void delete(int id) {
				billDao.delete(id);
    }

		@Transactional(readOnly = true)		
		public List<Bill> getAll(){
        return billDao.getAll();
    }

		/*
		@Transactional(readOnly = true)
		public List<Rental> findByName(String name){
				return rentalDao.findByName(name);
		}

		@Transactional(readOnly = true)
		public List<Rental> find(String name, String address, String city, String state, String zip, String email){
				return rentalDao.find(name, address, city, state, zip, email);
		}
		*/		
}
