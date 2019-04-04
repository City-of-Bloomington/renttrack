package in.bloomington.rental.dao;

import java.util.List;
import java.util.Date;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import java.text.SimpleDateFormat;
import org.hibernate.transform.*;
import in.bloomington.rental.model.Variance;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

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
        Criteria criteria = session.createCriteria(Variance.class);
        criteria.setMaxResults(limit);
        criteria.addOrder(Order.desc("id"));
        // remove dups
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();

    }

    //
    public List<Variance> findByText(String name)
    {
        String  qq      = "from variances o where o.variance like :name";
        Session session = sessionFactory.getCurrentSession();
        Query   query   = session.createQuery(qq);
        query.setParameter("name", name);
        List<Variance> variances = query.list();
        return variances;
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
