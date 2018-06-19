package in.bloomington.rental.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.bloomington.rental.dao.ZoningDao;
import in.bloomington.rental.model.Zoning;

@Service
public class ZoningServiceImp implements ZoningService {

		@Autowired
		private ZoningDao zoningDao;

		@Transactional
		public void save(Zoning val) {
				zoningDao.save(val);
		}

		@Transactional
		public void update(int id, Zoning val) {
			 zoningDao.update(id, val);
   }

		@Transactional
		public void delete(int id) {
				zoningDao.delete(id);
		}

		@Transactional(readOnly = true)
		public Zoning get(int id) {
				return zoningDao.get(id);
		}		

		@Transactional(readOnly = true)
		public List<Zoning> list() {
				return zoningDao.list();
   }

}
