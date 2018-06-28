package in.bloomington.rental.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import in.bloomington.rental.model.Egress;
import in.bloomington.rental.dao.EgressDao;

@Service
public class EgressServiceImp implements EgressService{
		
		@Autowired
		private EgressDao egressDao;
		
		@Transactional
		public void save(Egress val){
				egressDao.save(val);
		}
		@Transactional(readOnly = true)
		public Egress get(int id){
				Egress egress = egressDao.get(id);
        return  egress;
		}

		@Transactional
    public void update(int id, Egress egress) {
        egressDao.update(id, egress);
    }
 		
		@Transactional
    public void delete(int id) {
				egressDao.delete(id);
    }

		@Transactional(readOnly = true)		
		public List<Egress> getAll(){
        return egressDao.getAll();
    }
		
		@Transactional(readOnly = true)		
		public List<Egress> findByYear(int val){
        return egressDao.findByYear(val);
		}
		
}
