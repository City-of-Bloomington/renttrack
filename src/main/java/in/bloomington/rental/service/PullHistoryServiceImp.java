package in.bloomington.rental.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import in.bloomington.rental.model.PullHistory;
import in.bloomington.rental.dao.PullHistoryDao;

@Service
public class PullHistoryServiceImp implements PullHistoryService{
		
		@Autowired
		private PullHistoryDao pullHistoryDao;
		
		@Transactional
		public void save(PullHistory pullHistory){
				pullHistoryDao.save(pullHistory);
		}
		@Transactional(readOnly = true)
		public PullHistory get(int id){
				PullHistory pullHistory = pullHistoryDao.get(id);
        return  pullHistory;
		}

		@Transactional
    public void update(int id, PullHistory pullHistory) {
        pullHistoryDao.update(id, pullHistory);
    }
 		
		@Transactional
    public void delete(int id) {
				pullHistoryDao.delete(id);
    }

		@Transactional(readOnly = true)		
		public List<PullHistory> getPullHistoryForRental(int rental_id){
        return pullHistoryDao.getPullHistoryForRental(rental_id);
    }
		/*
		@Transactional(readOnly = true)
		public List<PullHistory> findByName(String name){
				return pullHistoryDao.findByName(name);
		}

		@Transactional(readOnly = true)
		public List<PullHistory> find(String name, String address, String city, String state, String zip, String email){
				return pullHistoryDao.find(name, address, city, state, zip, email);
		}
		*/		
}
