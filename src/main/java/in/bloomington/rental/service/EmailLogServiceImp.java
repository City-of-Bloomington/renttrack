package in.bloomington.rental.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import in.bloomington.rental.model.EmailLog;
import in.bloomington.rental.dao.EmailLogDao;

@Service
public class EmailLogServiceImp implements EmailLogService{
		
		@Autowired
		private EmailLogDao emailLogDao;

		
		@Transactional
		public void save(EmailLog val){
				emailLogDao.save(val);
		}
		@Transactional(readOnly = true)
		public EmailLog get(int id){
				EmailLog emailLog = emailLogDao.get(id);
        return  emailLog;
		}

		@Transactional(readOnly = true)		
		public List<EmailLog> getAll(){
        return emailLogDao.getAll();
    }
				
}
