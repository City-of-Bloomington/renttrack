package in.bloomington.rental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.bloomington.rental.dao.RentalLegalDao;
import in.bloomington.rental.model.RentalLegal;

@Service
public class RentalLegalServiceImp implements RentalLegalService{
		
		@Autowired
		private RentalLegalDao rentalLegalDao;

		
		@Transactional
		public void save(RentalLegal val){
				rentalLegalDao.save(val);
		}
		@Transactional(readOnly = true)
		public RentalLegal get(int id){
				RentalLegal rentalLegal = rentalLegalDao.get(id);
        return  rentalLegal;
		}
		
}
