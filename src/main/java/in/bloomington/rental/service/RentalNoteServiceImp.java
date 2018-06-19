package in.bloomington.rental.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.bloomington.rental.dao.RentalNoteDao;
import in.bloomington.rental.model.RentalNote;

@Service
public class RentalNoteServiceImp implements RentalNoteService {

		@Autowired
		private RentalNoteDao rentalNoteDao;

		@Transactional
		public void save(RentalNote val) {
				rentalNoteDao.save(val);
		}

		@Transactional
		public void update(int id, RentalNote val) {
			 rentalNoteDao.update(id, val);
   }

		@Transactional
		public void delete(int id) {
				rentalNoteDao.delete(id);
		}

		@Transactional(readOnly = true)
		public RentalNote get(int id) {
				return rentalNoteDao.get(id);
		}		

		@Transactional(readOnly = true)
		public List<RentalNote> list() {
				return rentalNoteDao.list();
   }
		
		@Transactional(readOnly = true)
		public List<RentalNote> findByRentalId(int rental_id){
				return rentalNoteDao.findByRentalId(rental_id);
		}
		
}
