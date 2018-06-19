package in.bloomington.rental.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import in.bloomington.rental.model.InspectionFileLog;
import in.bloomington.rental.dao.InspectionFileLogDao;

@Service
public class InspectionFileLogServiceImp implements InspectionFileLogService{
		
		@Autowired
		private InspectionFileLogDao inspectionFileLogDao;

		
		@Transactional
		public void save(InspectionFileLog val){
				inspectionFileLogDao.save(val);
		}
		@Transactional(readOnly = true)
		public InspectionFileLog get(int id){
				return inspectionFileLogDao.get(id);
		}

		@Transactional(readOnly = true)		
		public List<InspectionFileLog> findByRentalId(Integer rentalId){
        return inspectionFileLogDao.findByRentalId(rentalId);
    }
		@Transactional(readOnly = true)				
		public int findCountByRentalId(int rentalId){
        return inspectionFileLogDao.findCountByRentalId(rentalId);
		}

		
}
