package in.bloomington.rental.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import in.bloomington.rental.model.EmailDetailLog;
import in.bloomington.rental.dao.EmailDetailLogDao;

@Service
public class EmailDetailLogServiceImp implements EmailDetailLogService{
		
		@Autowired
		private EmailDetailLogDao emailDetailLogDao;
		
		@Transactional
		public void save(EmailDetailLog val){
				emailDetailLogDao.save(val);
		}
		@Transactional(readOnly = true)
		public EmailDetailLog get(int id){
				EmailDetailLog emailDetailLog = emailDetailLogDao.get(id);
        return  emailDetailLog;
		}

		@Transactional(readOnly = true)		
		public List<EmailDetailLog> getAll(){
        return emailDetailLogDao.getAll();
    }
				
}
