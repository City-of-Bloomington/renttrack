package in.bloomington.rental.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import in.bloomington.rental.model.StandardFees;
import in.bloomington.rental.dao.StandardFeesDao;

@Service
public class StandardFeesServiceImp implements StandardFeesService{
		
		@Autowired
		private StandardFeesDao standardFeesDao;

		@Transactional
		public void save(StandardFees standardFees){
				standardFeesDao.save(standardFees);
		}
		@Transactional(readOnly = true)
		public StandardFees get(int id){
				StandardFees standardFees = standardFeesDao.get(id);
        return  standardFees;
		}

		@Transactional
    public void update(int id, StandardFees standardFees) {
        standardFeesDao.update(id, standardFees);
    }
 		
		@Transactional
    public void delete(int id) {
				standardFeesDao.delete(id);
    }

		@Transactional(readOnly = true)		
		public List<StandardFees> getAll(){
        return standardFeesDao.getAll();
    }

		@Transactional(readOnly = true)		
		public StandardFees getLatest(){
        return standardFeesDao.getLatest();
    }		
		


}
