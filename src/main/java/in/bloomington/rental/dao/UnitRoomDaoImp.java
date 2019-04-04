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
public class UnitRoomDaoImp implements UnitRoomDao
{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public UnitRoom get(int id)
    {
        return sessionFactory.getCurrentSession().get(UnitRoom.class, id);
    }

    @Override
    public void save(UnitRoom unitRoom)
    {
        sessionFactory.getCurrentSession().save(unitRoom);
    }

    @Override
    public void update(int id, UnitRoom unitRoom)
    {
        Session  session = sessionFactory.getCurrentSession();
        UnitRoom room    = session.byId(UnitRoom.class).load(id);
                 room    = (UnitRoom) session.merge(unitRoom);
        session.update(room);
        session.flush();
    }

    @Override
    public void delete(int id)
    {
        UnitRoom room = (UnitRoom) sessionFactory.getCurrentSession()
                                                 .load(UnitRoom.class, id);
        if (room != null) {
            this.sessionFactory.getCurrentSession().delete(room);
        }
    }
}
