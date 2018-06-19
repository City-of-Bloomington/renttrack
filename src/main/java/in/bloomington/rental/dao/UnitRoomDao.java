package in.bloomington.rental.dao;
import java.util.List;
import org.hibernate.SessionFactory;
import in.bloomington.rental.model.UnitRoom;

public interface UnitRoomDao {

		UnitRoom get(int id);
		void save(UnitRoom unitRoom);
		void update(int id, UnitRoom unitRoom);
		void delete(int id);
						
}
