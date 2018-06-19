package in.bloomington.rental.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import in.bloomington.rental.model.RentalStructure;
import in.bloomington.rental.dao.RentalStructureDao;

@Service
public class RentalStructureServiceImp implements RentalStructureService{
		
		@Autowired
		private RentalStructureDao rentalStructureDao;

		@Transactional
		public void save(RentalStructure rentalStructure){
				rentalStructureDao.save(rentalStructure);
		}
		@Transactional(readOnly = true)
		public RentalStructure get(int id){
        return  rentalStructureDao.get(id);
		}

		@Transactional
    public void update(int id, RentalStructure rentalStructure) {
        rentalStructureDao.update(id, rentalStructure);
    }
 		
		@Transactional
    public void delete(int id) {
				rentalStructureDao.delete(id);
    }

		@Transactional
    public void delete(RentalStructure val) {
				rentalStructureDao.delete(val);
    }
		

		@Transactional(readOnly = true)		
		public List<RentalStructure> getAll(){
        return rentalStructureDao.getAll();
    }

		@Transactional(readOnly = true)
		public List<RentalStructure> findByRentalId(Integer val){
				return rentalStructureDao.findByRentalId(val);
		}

		
}
