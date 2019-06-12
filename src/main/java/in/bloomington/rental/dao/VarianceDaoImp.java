package in.bloomington.rental.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.bloomington.rental.model.Variance;
import in.bloomington.rental.model.Variance_;

@Repository
public class VarianceDaoImp implements VarianceDao
{

    private static final Logger logger = LogManager.getLogger(VarianceDaoImp.class);
    SimpleDateFormat            dtf    = new SimpleDateFormat("MM/dd/yyyy");
    private int                 limit  = 30;
    
    @Autowired
    private SessionFactory      sessionFactory;

    @Override
    public Variance get(int id)
    {
        return sessionFactory.getCurrentSession().get(Variance.class, id);
    }

    @Override
    public void save(Variance variance)
    {
        sessionFactory.getCurrentSession().save(variance);
    }

    @Override
    public void update(int id, Variance variance)
    {
        Session  session   = sessionFactory.getCurrentSession();
        Variance variance2 = session.byId(Variance.class).load(id);
        variance2.setVariance(variance.getVariance());
        variance2.setDate    (variance.getDate());
        variance2.setUser    (variance.getUser());
        session.flush();
    }

    @Override
    public void delete(int id)
    {
        Variance v = (Variance) sessionFactory.getCurrentSession()
                                              .load(Variance.class, id);
        if (v != null) {
            this.sessionFactory.getCurrentSession().delete(v);
        }
    }

    @Override
    public List<Variance> getAll()
    {
        Session  session  = sessionFactory.getCurrentSession();
        CriteriaBuilder       builder = session.getCriteriaBuilder();
        CriteriaQuery<Variance> select = builder.createQuery(Variance.class);
        Root<Variance>            root = select.from(Variance.class);
        select.orderBy(builder.desc(root.get(Variance_.id)));
        return session.createQuery(select)
                      .setMaxResults(limit)
                      .getResultList();
				
    }

    //
    public List<Variance> findByText(String name)
    {
        Session  session  = sessionFactory.getCurrentSession();
        CriteriaBuilder       builder = session.getCriteriaBuilder();
        CriteriaQuery<Variance> select = builder.createQuery(Variance.class);
        Root<Variance>            root = select.from(Variance.class);
				select.where(builder.like(root.get(Variance_.variance), "%"+name+"%"));
        return session.createQuery(select)
                      .setMaxResults(limit)
                      .getResultList();

    }

    //
    public List<Variance> find(String rental_id, String text, String dateFrom, String dateTo)
    {
        List<Variance> variances = null;
        String         qq        = "from Variance o where ";
        boolean        and       = false;
        if (rental_id != null) {
            qq  += " o.rental_id : rental_id ";
            and  = true;
        }
        if (text != null) {
            if (and) { qq += ", "; }
            qq  += " o.variance like: text ";
            and  = true;
        }
        if (dateFrom != null) {
            if (and) { qq += ", "; }
            qq  += " o.date >=: dateFrom ";
            and  = true;
        }
        if (dateTo != null) {
            if (and) { qq += ", "; }
            qq  += " o.date <=: dateTo ";
            and  = true;
        }
        //
        Session session = sessionFactory.getCurrentSession();
        Query   query   = session.createQuery(qq);
        if (rental_id != null) {
            query.setParameter("rental_id", rental_id);
        }
        if (text != null) {
            query.setParameter("text", "%" + text + "%");
        }
        if (dateFrom != null) {
            Date date = null;
            try {
                date = dtf.parse(dateFrom);
                query.setParameter("dateFrom", date);
            }
            catch (Exception ex) {
                logger.error(ex);
            }
        }
        if (dateTo != null) {
            Date date = null;
            try {
                date = dtf.parse(dateTo);
                query.setParameter("dateFrom", date);
            }
            catch (Exception ex) {
                logger.error(ex);
            }
        }
        variances = query.list();
        return variances;
    }
}
