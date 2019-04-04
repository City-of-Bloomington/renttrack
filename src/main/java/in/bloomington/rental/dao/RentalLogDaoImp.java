package in.bloomington.rental.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.bloomington.rental.model.RentalLog;

@Repository
public class RentalLogDaoImp implements RentalLogDao
{
    private int limit = 10;
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public RentalLog get(int id)
    {
        return sessionFactory.getCurrentSession().get(RentalLog.class, id);
    }

    @Override
    public void save(RentalLog rentalLog)
    {
        try {
            sessionFactory.getCurrentSession().save(rentalLog);
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
    }

    @Override
    public List<RentalLog> findByRentalId(Integer rentalId)
    {
        String  qq      = "from RentalLog l where l.rentalId =:rentalId order by l.id";
        Session session = sessionFactory.getCurrentSession();
        Query   query   = session.createQuery(qq);
        // query.setFirstResult(0); // for paging
        query.setMaxResults(limit);
        query.setParameter("rentalId", rentalId);
        return query.list();
    }
}
