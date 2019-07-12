package in.bloomington.rental.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import in.bloomington.rental.model.Owner;
import in.bloomington.rental.dao.ReportDao;

@Service
public class ReportServiceImp implements ReportService{
		
		@Autowired
		private ReportDao reportDao;

		@Transactional(readOnly = true)		
		public List<Owner> getAll(){
				System.err.println(" service impl called ");
        return reportDao.getAll();
    }

}
