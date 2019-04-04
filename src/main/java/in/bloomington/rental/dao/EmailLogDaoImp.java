package in.bloomington.rental.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import in.bloomington.rental.model.EmailLog;

@Repository
public class EmailLogDaoImp implements EmailLogDao
{
    @Autowired
    private SessionFactory sessionFactory;
    private int            limit = 30;

    @Override
    public EmailLog get(int id)
    {
        return sessionFactory.getCurrentSession().get(EmailLog.class, id);
    }

    @Override
    public void save(EmailLog val)
    {
        sessionFactory.getCurrentSession().save(val);
    }

    @Override
    public List<EmailLog> getAll()
    {
        Session  session  = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(EmailLog.class);
        criteria.setMaxResults(limit);
        criteria.addOrder(Order.desc("id"));
        return criteria.list();
    }
}
