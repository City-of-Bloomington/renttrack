package in.bloomington.rental.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.bloomington.rental.model.PullReason;

@Repository
public class PullReasonDaoImp implements PullReasonDao
{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public PullReason get(int id)
    {
        return sessionFactory.getCurrentSession().get(PullReason.class, id);
    }

    @Override
    public void save(PullReason val)
    {
        sessionFactory.getCurrentSession().save(val);
    }

    @Override
    public void update(int id, PullReason val)
    {
        Session    session = sessionFactory.getCurrentSession();
        PullReason val2    = session.byId(PullReason.class).load(id);
        val2.setReason(val.getReason());
        val2.setAlias (val.getAlias());
        session.flush();
    }

    @Override
    public void delete(int id)
    {
        Session    session = sessionFactory.getCurrentSession();
        PullReason val     = session.byId(PullReason.class).load(id);
        session.delete(val);
    }

    @Override
    public List<PullReason> list()
    {
        Session                  session = sessionFactory.getCurrentSession();
        CriteriaBuilder          builder = session.getCriteriaBuilder();
        CriteriaQuery<PullReason> select = builder.createQuery(PullReason.class);

        return session.createQuery(select)
                      .getResultList();
    }
}
