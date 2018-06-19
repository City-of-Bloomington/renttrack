package in.bloomington.rental.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import in.bloomington.rental.dao.UserDao;
import in.bloomington.rental.model.RentUser;

@Service
public class UserServiceImp implements UserService {

		@Autowired
		private UserDao userDao;
				
		@Transactional
		public void save(RentUser user) {
				userDao.save(user);
		}
 
		@Transactional
		public void update(int id, RentUser user) {
			 userDao.update(id, user);
   }

		@Transactional
		public void delete(int id) {
				userDao.delete(id);
		}

		@Transactional(readOnly = true)
		public RentUser get(int id) {
				return userDao.get(id);
		}
		
		@Transactional(readOnly = true)
		public RentUser findByUsername(String name) {
				return userDao.findByUsername(name);
		}
		
		@Transactional(readOnly = true)
		public List<RentUser> getInspectors(){
				return userDao.getInspectors();
		}
		
		@Transactional(readOnly = true)
		public List<RentUser> getAllInspectors(){
				return userDao.getAllInspectors();
		}
		
		@Transactional(readOnly = true)
		public List<RentUser> list() {
				return userDao.list();
   }

}
