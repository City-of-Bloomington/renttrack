package in.bloomington.rental.service;
import java.util.List;
import in.bloomington.rental.model.UnitRoom;
import java.util.List;

public interface UnitRoomService {

		public UnitRoom get(int id);
		public void save(UnitRoom val);
		public void update(int id, UnitRoom val);
		public void delete(int id);


}
