package in.bloomington.rental.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import in.bloomington.rental.model.Can;
import in.bloomington.rental.dao.CanDao;

@Service
public class CanServiceImp implements CanService{
		
		@Autowired
		private CanDao canDao;

		
		@Transactional
		public void save(Can val){
				canDao.save(val);
		}
		@Transactional(readOnly = true)
		public Can get(int id){
				Can can = canDao.get(id);
        return  can;
		}

		@Transactional
    public void update(int id, Can can) {
        canDao.update(id, can);
    }
 		
		@Transactional
    public void delete(int id) {
				canDao.delete(id);
    }

		@Transactional(readOnly = true)		
		public List<Can> getAll(){
        return canDao.getAll();
    }
		
		@Transactional(readOnly = true)		
		public List<Can> findByName(String val){
        return canDao.findByName(val);
		}
		
}
