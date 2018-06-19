package in.bloomington.rental.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import in.bloomington.rental.model.Inspection;
import in.bloomington.rental.dao.InspectionDao;

@Service
public class InspectionServiceImp implements InspectionService{
		
		@Autowired
		private InspectionDao inspectionDao;

		
		@Transactional
		public void save(Inspection inspection){
				inspectionDao.save(inspection);
		}
		@Transactional(readOnly = true)
		public Inspection get(int id){
				Inspection inspection = inspectionDao.get(id);
        return  inspection;
		}

		@Transactional
    public void update(int id, Inspection inspection) {
        inspectionDao.update(id, inspection);
    }
 		
		@Transactional
    public void delete(int id) {
				inspectionDao.delete(id);
    }

		@Transactional(readOnly = true)		
		public List<Inspection> getAll(){
        return inspectionDao.getAll();
    }
		
		@Transactional(readOnly = true)		
		public List<Inspection> findByRentalId(int id){
        return inspectionDao.findByRentalId(id);
		}
		@Transactional(readOnly = true)
		public int findCountByRentalId(int id){
				return  inspectionDao.findCountByRentalId(id);
		}
		
}
