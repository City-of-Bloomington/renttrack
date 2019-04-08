package in.bloomington.rental.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.bloomington.rental.model.LegalItEmailLog;

@Repository
public class LegalItEmailLogDaoImp implements LegalItEmailLogDao
{
    private int limit = 10;
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public LegalItEmailLog get(int id)
    {
        return sessionFactory.getCurrentSession()
                             .get(LegalItEmailLog.class, id);
    }

    @Override
    public void save(LegalItEmailLog val)
    {
        sessionFactory.getCurrentSession().save(val);
    }

    @Override
    public List<LegalItEmailLog> findByRentalId(Integer rentalId)
    {
        Session                       session = sessionFactory.getCurrentSession();
        CriteriaBuilder               builder = session.getCriteriaBuilder();
        CriteriaQuery<LegalItEmailLog> select = builder.createQuery(LegalItEmailLog.class);
        Root<LegalItEmailLog>            root = select.from(LegalItEmailLog.class);
        
        select.where  (builder.equal(root.get("rental_id"), rentalId));
        select.orderBy(builder.desc (root.get("id")));
        
        return session.createQuery(select)
                      .setMaxResults(limit)
                      .getResultList();
    }
}
