package in.bloomington.rental.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import in.bloomington.rental.model.InspectionCan;
import in.bloomington.rental.dao.InspectionCanDao;

@Service
public class InspectionCanServiceImp implements InspectionCanService{
		
		@Autowired
		private InspectionCanDao inspectionCanDao;

		
		@Transactional
		public void save(InspectionCan val){
				inspectionCanDao.save(val);
		}
		@Transactional(readOnly = true)
		public InspectionCan get(int id){
				InspectionCan can = inspectionCanDao.get(id);
        return  can;
		}

		@Transactional
    public void update(int id, InspectionCan can) {
        inspectionCanDao.update(id, can);
    }
 		
		@Transactional
    public void delete(int id) {
				inspectionCanDao.delete(id);
    }

		@Transactional(readOnly = true)		
		public List<InspectionCan> getAll(){
        return inspectionCanDao.getAll();
    }
		
		@Transactional(readOnly = true)		
		public List<InspectionCan> findByName(String val){
        return inspectionCanDao.findByName(val);
		}
		@Transactional(readOnly = true)		
		public List<InspectionCan> findByInspectionId(int val){
        return inspectionCanDao.findByInspectionId(val);
		}		
}
