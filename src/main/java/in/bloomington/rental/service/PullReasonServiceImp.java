package in.bloomington.rental.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.bloomington.rental.dao.PullReasonDao;
import in.bloomington.rental.model.PullReason;

@Service
public class PullReasonServiceImp implements PullReasonService {

		@Autowired
		private PullReasonDao pullReasonDao;

		@Transactional
		public void save(PullReason val) {
				pullReasonDao.save(val);
		}

		@Transactional
		public void update(int id, PullReason val) {
			 pullReasonDao.update(id, val);
   }

		@Transactional
		public void delete(int id) {
				pullReasonDao.delete(id);
		}

		@Transactional(readOnly = true)
		public PullReason get(int id) {
				return pullReasonDao.get(id);
		}		

		@Transactional(readOnly = true)
		public List<PullReason> list() {
				return pullReasonDao.list();
   }

}
