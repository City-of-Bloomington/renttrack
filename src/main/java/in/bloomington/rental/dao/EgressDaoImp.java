package in.bloomington.rental.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.bloomington.rental.model.Egress;

@Repository
public class EgressDaoImp implements EgressDao
{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Egress get(int id)
    {
        return sessionFactory.getCurrentSession().get(Egress.class, id);
    }

    @Override
    public void save(Egress egress)
    {
        sessionFactory.getCurrentSession().save(egress);
    }

    @Override
    public void update(int id, Egress egress)
    {
        Session session = sessionFactory.getCurrentSession();
        Egress  egress2 = session.byId(Egress.class).load(id);
                egress2 = (Egress) session.merge(egress);
        session.update(egress2);
        session.flush();
    }

    @Override
    public void delete(int id)
    {
        Egress egress = (Egress) sessionFactory.getCurrentSession().load(Egress.class, id);
        if (egress != null) {
            this.sessionFactory.getCurrentSession().delete(egress);
        }
    }

    @Override
    public List<Egress> getAll()
    {
        Session  session  = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Egress.class);
        criteria.addOrder(Order.asc("id"));
        return criteria.list();
    }

    // needed for auto_complete
    @Override
    public List<Egress> findByYear(int year)
    {
        String  qq      = "from Egress c where c.startYear <=:year and c.endYear >=:year2 ";
        Session session = sessionFactory.getCurrentSession();
        Query   query   = session.createQuery(qq);
        query.setParameter("year", year);
        query.setParameter("year2", year);
        List<Egress> egresses = query.list();
        return egresses;
    }
}
