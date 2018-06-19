package in.bloomington.rental.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import in.bloomington.rental.model.Attachement;
import in.bloomington.rental.dao.AttachementDao;

@Service
public class AttachementServiceImp implements AttachementService{
		
		@Autowired
		private AttachementDao attachementDao;

		@Transactional
		public void save(Attachement attachement){
				attachementDao.save(attachement);
		}
		@Transactional(readOnly = true)
		public Attachement get(int id){
        return  attachementDao.get(id);
		}

		@Transactional
    public void delete(int id) {
				attachementDao.delete(id);
    }
		@Transactional
    public void delete(Attachement val) {
				attachementDao.delete(val);
    }
		
		@Transactional(readOnly = true)
		public List<Attachement> findByRentalId(Integer val){
				return attachementDao.findByRentalId(val);
		}
		
		@Transactional(readOnly = true)
		public List<Attachement> findByInspectionId(Integer val){
				return attachementDao.findByInspectionId(val);
		}
		
}
