package in.bloomington.rental.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import in.bloomington.rental.model.RentalUnit;
import in.bloomington.rental.dao.RentalUnitDao;

@Service
public class RentalUnitServiceImp implements RentalUnitService{
		
		@Autowired
		private RentalUnitDao rentalUnitDao;

		@Transactional
		public void save(RentalUnit rentalUnit){
				rentalUnitDao.save(rentalUnit);
		}
		@Transactional(readOnly = true)
		public RentalUnit get(int id){
        return  rentalUnitDao.get(id);
		}

		@Transactional
    public void update(int id, RentalUnit rentalUnit) {
        rentalUnitDao.update(id, rentalUnit);
    }
 		
		@Transactional
    public void delete(int id) {
				rentalUnitDao.delete(id);
    }

		@Transactional(readOnly = true)		
		public List<RentalUnit> getAll(){
        return rentalUnitDao.getAll();
    }

		@Transactional(readOnly = true)
		public List<RentalUnit> findByStructureId(Integer val){
				return rentalUnitDao.findByStructureId(val);
		}

		
}
