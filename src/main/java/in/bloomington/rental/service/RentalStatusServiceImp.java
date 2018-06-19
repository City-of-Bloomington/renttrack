package in.bloomington.rental.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.bloomington.rental.dao.RentalStatusDao;
import in.bloomington.rental.model.RentalStatus;

@Service
public class RentalStatusServiceImp implements RentalStatusService {

		@Autowired
		private RentalStatusDao rentalStatusDao;

		@Transactional
		public void save(RentalStatus rentalStatus) {
				rentalStatusDao.save(rentalStatus);
		}

		@Transactional
		public void update(int id, RentalStatus rentalStatus) {
			 rentalStatusDao.update(id, rentalStatus);
   }

		@Transactional
		public void delete(int id) {
				rentalStatusDao.delete(id);
		}

		@Transactional(readOnly = true)
		public RentalStatus get(int id) {
				return rentalStatusDao.get(id);
		}		

		@Transactional(readOnly = true)
		public List<RentalStatus> list() {
				return rentalStatusDao.list();
   }

}
