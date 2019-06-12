package in.bloomington.rental.dao;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

// import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.bloomington.rental.model.StandardFees;

@Repository
public class StandardFeesDaoImp implements StandardFeesDao
{
    @Autowired
    private SessionFactory sessionFactory;
    private int            limit = 30;

    @Override
    public StandardFees get(int id)
    {
        return sessionFactory.getCurrentSession().get(StandardFees.class, id);
    }

    @Override
    public void save(StandardFees standardFees)
    {
        sessionFactory.getCurrentSession().save(standardFees);
    }

    @Override
    public void update(int id, StandardFees standardFees)
    {
        Session      session = sessionFactory.getCurrentSession();
        StandardFees fees    = session.byId(StandardFees.class).load(id);
                     fees    = (StandardFees) session.merge(standardFees);
        session.update(fees);
        session.flush();
    }

    @Override
    public void delete(int id)
    {
        StandardFees fees = (StandardFees) sessionFactory.getCurrentSession()
                                                         .load(StandardFees.class, id);
        if (fees != null) {
            this.sessionFactory.getCurrentSession().delete(fees);
        }
    }

    @Override
    public List<StandardFees> getAll()
    {
				Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<StandardFees> select = builder.createQuery(StandardFees.class);
				Root<StandardFees> root = select.from(StandardFees.class);
				select.orderBy(builder.desc (root.get("id")));				
        return session.createQuery(select)
                      .getResultList();
				
    }

    @Override
    public StandardFees getLatest()
    {
				Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<StandardFees> select = builder.createQuery(StandardFees.class);
				Root<StandardFees> root = select.from(StandardFees.class);
				select.orderBy(builder.desc (root.get("id")));				
        StandardFees ret = session.createQuery(select)
						.getSingleResult();
				if(ret != null){
						return ret;
				}
				else{
						return new StandardFees();
				}
						
    }
}
