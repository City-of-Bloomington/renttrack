package in.bloomington.rental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.bloomington.rental.dao.UnitRoomDao;
import in.bloomington.rental.model.UnitRoom;

@Service
public class UnitRoomServiceImp implements UnitRoomService{
		
		@Autowired
		private UnitRoomDao unitRoomDao;

		@Transactional
		public void save(UnitRoom unitRoom){
				unitRoomDao.save(unitRoom);
		}
		@Transactional(readOnly = true)
		public UnitRoom get(int id){
				UnitRoom unitRoom = unitRoomDao.get(id);
        return  unitRoom;
		}

		@Transactional
    public void update(int id, UnitRoom unitRoom) {
        unitRoomDao.update(id, unitRoom);
    }
 		
		@Transactional
    public void delete(int id) {
				unitRoomDao.delete(id);
    }

}
