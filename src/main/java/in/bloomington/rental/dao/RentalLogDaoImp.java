package in.bloomington.rental.dao;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.bloomington.rental.model.RentalLog;
import in.bloomington.rental.model.RentalLog_;

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
        Session session = sessionFactory.getCurrentSession();
				CriteriaBuilder          builder = session.getCriteriaBuilder();
        CriteriaQuery<RentalLog> select = builder.createQuery(RentalLog.class);
        Root<RentalLog>         root = select.from(RentalLog.class);
        
        select.where  (builder.equal(root.get(RentalLog_.rental), rentalId));
        select.orderBy(builder.desc (root.get(RentalLog_.id)));
        return session.createQuery(select)
						.setMaxResults(limit)
						.getResultList();

				
    }
}
