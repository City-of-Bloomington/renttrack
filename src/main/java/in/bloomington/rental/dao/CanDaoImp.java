package in.bloomington.rental.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import in.bloomington.rental.model.Can;

@Repository
public class CanDaoImp implements CanDao
{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Can get(int id)
    {
        return sessionFactory.getCurrentSession().get(Can.class, id);
    }

    @Override
    public void save(Can can)
    {
        sessionFactory.getCurrentSession().save(can);
    }

    @Override
    public void update(int id, Can can)
    {
        Session session = sessionFactory.getCurrentSession();
        Can     can2    = session.byId(Can.class).load(id);
                can2    = (Can) session.merge(can);
        session.update(can2);
        session.flush();
    }

    @Override
    public void delete(int id)
    {
        Can can = (Can) sessionFactory.getCurrentSession().load(Can.class, id);
        if (can != null) {
            this.sessionFactory.getCurrentSession().delete(can);
        }
    }

    @Override
    public List<Can> getAll()
    {
        Session  session  = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Can.class);
        // criteria.setMaxResults(limit); we list all for now
        criteria.addOrder(Order.desc("id"));
        return criteria.list();
    }

    // needed for auto_complete
    @Override
    public List<Can> findByName(String name)
    {
        String  qq      = "from Can c where c.title like :name ";
        Session session = sessionFactory.getCurrentSession();
        Query   query   = session.createQuery(qq);
        query.setParameter("name", "%" + name + "%");
        List<Can> cans = query.list();
        return cans;
    }
}
