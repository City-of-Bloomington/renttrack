package in.bloomington.rental.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.bloomington.rental.dao.InspectionTypeDao;
import in.bloomington.rental.model.InspectionType;

@Service
public class InspectionTypeServiceImp implements InspectionTypeService {

		@Autowired
		private InspectionTypeDao inspectionTypeDao;

		@Transactional
		public void save(InspectionType val) {
				inspectionTypeDao.save(val);
		}

		@Transactional
		public void update(int id, InspectionType val) {
			 inspectionTypeDao.update(id, val);
   }

		@Transactional
		public void delete(int id) {
				inspectionTypeDao.delete(id);
		}

		@Transactional(readOnly = true)
		public InspectionType get(int id) {
				return inspectionTypeDao.get(id);
		}		

		@Transactional(readOnly = true)
		public List<InspectionType> list() {
				return inspectionTypeDao.list();
   }

}
