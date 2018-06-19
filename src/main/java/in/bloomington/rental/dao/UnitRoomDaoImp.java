package in.bloomington.rental.dao;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import in.bloomington.rental.model.UnitRoom;
import in.bloomington.rental.model.Rental;

@Repository
public class UnitRoomDaoImp implements UnitRoomDao{
		@Autowired
		private SessionFactory sessionFactory;
		int limit = 30;

		@Override
		public UnitRoom get(int id){
				return sessionFactory.getCurrentSession().get(UnitRoom.class, id);
		}
		@Override
		public void save(UnitRoom unitRoom){
				sessionFactory.getCurrentSession().save(unitRoom);
		}
		@Override
    public void update(int id, UnitRoom unitRoom) {
      Session session = sessionFactory.getCurrentSession();
      UnitRoom unitRoom2 = session.byId(UnitRoom.class).load(id);
			unitRoom2 = (UnitRoom)session.merge(unitRoom);
			session.update(unitRoom2); 
      session.flush();				
    }
 		
		@Override
    public void delete(int id) {
        UnitRoom unitRoom = (UnitRoom) sessionFactory.getCurrentSession().load(
																																	 UnitRoom.class, id);
        if (unitRoom != null) {
            this.sessionFactory.getCurrentSession().delete(unitRoom);
        }
    }
		
}
