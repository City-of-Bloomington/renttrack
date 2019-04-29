package in.bloomington.rental.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.bloomington.rental.model.EmailDetailLog;

@Repository
public class EmailDetailLogDaoImp implements EmailDetailLogDao
{
    @Autowired
    private SessionFactory sessionFactory;
    private int            limit = 30;

    @Override
    public EmailDetailLog get(int id)
    {
        return sessionFactory.getCurrentSession()
                             .get(EmailDetailLog.class, id);
    }

    @Override
    public void save(EmailDetailLog val)
    {
        sessionFactory.getCurrentSession()
                      .save(val);
    }

    @Override
    public List<EmailDetailLog> getAll()
    {
        Session                      session = sessionFactory.getCurrentSession();
        CriteriaBuilder              builder = session.getCriteriaBuilder();
        CriteriaQuery<EmailDetailLog> select = builder.createQuery(EmailDetailLog.class);
        Root<EmailDetailLog>            root = select.from(EmailDetailLog.class);
        
        select.orderBy(builder.desc(root.get("id")));

        return session.createQuery(select)
                      .setMaxResults(limit)
                      .getResultList();
    }
}
