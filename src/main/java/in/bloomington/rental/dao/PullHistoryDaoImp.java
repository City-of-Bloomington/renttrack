package in.bloomington.rental.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.bloomington.rental.model.PullHistory;

@Repository
public class PullHistoryDaoImp implements PullHistoryDao
{
    @Autowired
    private SessionFactory sessionFactory;
    private int            limit = 5;

    @Override
    public PullHistory get(int id)
    {
        return sessionFactory.getCurrentSession().get(PullHistory.class, id);
    }

    @Override
    public void save(PullHistory pullHistory)
    {
        sessionFactory.getCurrentSession().save(pullHistory);
    }

    @Override
    public void update(int id, PullHistory pullHistory)
    {
        Session     session      = sessionFactory.getCurrentSession();
        PullHistory pullHistory2 = session.byId(PullHistory.class).load(id);
        pullHistory2 = (PullHistory) session.merge(pullHistory);
        session.update(pullHistory2); // instead of saveOrUpdate
        session.flush();
    }

    @Override
    public void delete(int id)
    {
        PullHistory h = (PullHistory) sessionFactory.getCurrentSession()
                                                    .load(PullHistory.class, id);
        if (h != null) {
            this.sessionFactory.getCurrentSession().delete(h);
        }
    }

    @Override
    public List<PullHistory> getPullHistoryForRental(int rental_id)
    {
        Session            session = sessionFactory.getCurrentSession();
        CriteriaBuilder    builder = session.getCriteriaBuilder();
        CriteriaQuery<PullHistory> select = builder.createQuery(PullHistory.class);
        Root<PullHistory>            root = select.from(PullHistory.class);

        select.where  (builder.equal(root.get("rental_id"), rental_id))
              .orderBy(builder.desc (root.get("id")));

        return session.createQuery(select)
                      .setMaxResults(limit)
                      .getResultList();
    }
}
