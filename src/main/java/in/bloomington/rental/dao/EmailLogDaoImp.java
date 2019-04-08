package in.bloomington.rental.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        Session                session = sessionFactory.getCurrentSession();
        CriteriaBuilder        builder = session.getCriteriaBuilder();
        CriteriaQuery<EmailLog> select = builder.createQuery(EmailLog.class);
        Root<EmailLog>            root = select.from(EmailLog.class);
        
        select.orderBy(builder.desc(root.get("id")));
        
        return session.createQuery(select)
                      .setMaxResults(limit)
                      .getResultList();
    }
}
