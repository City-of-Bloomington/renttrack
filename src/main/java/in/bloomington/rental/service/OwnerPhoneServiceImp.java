package in.bloomington.rental.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.bloomington.rental.dao.OwnerPhoneDao;
import in.bloomington.rental.model.OwnerPhone;

@Service
public class OwnerPhoneServiceImp implements OwnerPhoneService {

		@Autowired
		private OwnerPhoneDao ownerPhoneDao;
		

		@Transactional
		public void save(OwnerPhone val) {
				ownerPhoneDao.save(val);
		}

		@Transactional
		public void update(int id, OwnerPhone val) {
			 ownerPhoneDao.update(id, val);
   }

		@Transactional
		public void delete(int id) {
				ownerPhoneDao.delete(id);
		}

		@Transactional(readOnly = true)
		public OwnerPhone get(int id) {
				return ownerPhoneDao.get(id);
		}		

		@Transactional(readOnly = true)
		public List<OwnerPhone> list() {
				return ownerPhoneDao.list();
		}
		
		@Transactional(readOnly = true)
		public List<OwnerPhone> findByOwnerId(int owner_id) {
				return ownerPhoneDao.findByOwnerId(owner_id);
   }
		

}
