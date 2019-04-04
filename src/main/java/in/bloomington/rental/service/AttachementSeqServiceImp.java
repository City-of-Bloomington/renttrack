package in.bloomington.rental.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.bloomington.rental.dao.AttachementSeqDao;
import in.bloomington.rental.model.AttachementSeq;

@Service
public class AttachementSeqServiceImp implements AttachementSeqService{
		
		@Autowired
		private AttachementSeqDao attachementSeqDao;

		@Transactional
		public void save(AttachementSeq attachementSeq){
				attachementSeqDao.save(attachementSeq);
		}
		@Transactional(readOnly = true)
		public AttachementSeq get(int id){
        return  attachementSeqDao.get(id);
		}
		
}
