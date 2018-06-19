package in.bloomington.rental.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import in.bloomington.rental.model.RentalLog;
import in.bloomington.rental.dao.RentalLogDao;

@Service
public class RentalLogServiceImp implements RentalLogService{
		
		@Autowired
		private RentalLogDao rentalLogDao;

		@Transactional
		public void save(RentalLog rentalLog){
				rentalLogDao.save(rentalLog);
		}
		@Transactional(readOnly = true)
		public RentalLog get(int id){
        return  rentalLogDao.get(id);
		}
		
		@Transactional(readOnly = true)
		public List<RentalLog> findByRentalId(Integer val){
				return rentalLogDao.findByRentalId(val);
		}
				
}
