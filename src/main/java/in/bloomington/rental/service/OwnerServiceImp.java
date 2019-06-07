package in.bloomington.rental.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.bloomington.rental.model.Item;
import in.bloomington.rental.model.Owner;
import in.bloomington.rental.model.RentalOwner;
import in.bloomington.rental.dao.OwnerDao;


@Service
public class OwnerServiceImp implements OwnerService{
		
		@Autowired
		private OwnerDao ownerDao;

		@Transactional
		public void save(Owner owner){
				ownerDao.save(owner);
		}
		@Transactional
		public void save(RentalOwner val){
				ownerDao.save(val);
		}		
		@Transactional(readOnly = true)
		public Owner get(int id){
        return  ownerDao.get(id);
		}
		@Transactional(readOnly = true)
		public RentalOwner getRentalOwner(int id){
        return  ownerDao.getRentalOwner(id);
		}

		@Transactional
    public void update(int id, Owner owner) {
        ownerDao.update(id, owner);
    }
 		
		@Transactional
    public void delete(int id) {
				ownerDao.delete(id);
    }
		
		@Transactional
		public void removeRentalOwner(int id){
				ownerDao.removeRentalOwner(id);
		}
		
		@Transactional(readOnly = true)		
		public List<Owner> getAll(){
        return ownerDao.getAll();
    }

		@Transactional(readOnly = true)
		public List<Owner> findByName(String name){
				return ownerDao.findByName(name);
		}

		@Transactional(readOnly = true)
		public List<Owner> findAgentByName(String name){
				return ownerDao.findAgentByName(name);
		}
		@Transactional(readOnly = true)		
		public List<Owner> search(Owner owner){
				return ownerDao.search(owner);
		}
		@Transactional(readOnly = true)				
		public List<Owner> findOwnersForExpireEmail(String startDate,
																								String endDate){
				return ownerDao.findOwnersForExpireEmail(startDate, endDate);
		}
		@Transactional(readOnly = true)
		public List<Owner> find(String name, String address, String city, String state, String zip, String email, boolean hasNoEmail){
				return ownerDao.find(name, address, city, state, zip, email, hasNoEmail);
		}
		@Transactional(readOnly = true)
		public List<RentalOwner> getAllForOwner(int owner_id){
				return ownerDao.getAllForOwner(owner_id);
		}
		@Transactional(readOnly = true)
		public List<RentalOwner> getAllForRental(int rental_id){
				return ownerDao.getAllForRental(rental_id);
		}

		@Transactional(readOnly = true)
		public List<Item> getList(String name){
				return ownerDao.getList(name);
		}

		@Transactional(readOnly = true)
		public List<Item> getAgentList(String name){
				return ownerDao.getAgentList(name);
		}		
}
