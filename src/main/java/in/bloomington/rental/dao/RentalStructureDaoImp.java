package in.bloomington.rental.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.transform.*;
import in.bloomington.rental.model.RentalStructure;

@Repository
public class RentalStructureDaoImp implements RentalStructureDao
{
    @Autowired
    private SessionFactory sessionFactory;
    private int            limit = 30;

    @Override
    public RentalStructure get(int id)
    {
        return sessionFactory.getCurrentSession()
                             .get(RentalStructure.class, id);
    }

    @Override
    public void save(RentalStructure rentalStructure)
    {
        sessionFactory.getCurrentSession().save(rentalStructure);
    }

    @Override
    public void update(int id, RentalStructure rentalStructure)
    {
        Session         session   = sessionFactory.getCurrentSession();
        RentalStructure structure = session.byId(RentalStructure.class)
                                           .load(id);
                        structure = (RentalStructure) session.merge(rentalStructure);
        session.update (structure);
        session.flush();
    }

    @Override
    public void delete(int id)
    {
        RentalStructure s = (RentalStructure) sessionFactory.getCurrentSession()
                                                            .load(RentalStructure.class, id);
        if (s != null) {
            this.sessionFactory.getCurrentSession().delete(s);
        }
    }

    @Override
    public void delete(RentalStructure val)
    {
        if (val != null) {
            this.sessionFactory.getCurrentSession().delete(val);
        }
    }

    @Override
    public List<RentalStructure> getAll()
    {
        Session  session  = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(RentalStructure.class);
        criteria.setMaxResults(limit);
        criteria.addOrder(Order.desc("id"));
        // remove dups
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();

    }

    //
    public List<RentalStructure> findByRentalId(Integer rentalId)
    {
        String  qq      = "from RentalStructure o where o.rental.id = :rentalId order by o.id";
        Session session = sessionFactory.getCurrentSession();
        Query   query   = session.createQuery(qq);
        query.setParameter("rentalId", rentalId);
        List<RentalStructure> rentalStructures = query.list();
        return rentalStructures;
    }
}
