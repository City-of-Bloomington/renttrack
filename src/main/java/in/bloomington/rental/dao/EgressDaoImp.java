package in.bloomington.rental.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
        Session              session = sessionFactory.getCurrentSession();
        CriteriaBuilder      builder = session.getCriteriaBuilder();
        CriteriaQuery<Egress> select = builder.createQuery(Egress.class);
        Root<Egress>            root = select.from(Egress.class);
        
        select.orderBy(builder.asc(root.get("id")));
        return session.createQuery(select)
                      .getResultList();
    }

    // needed for auto_complete
    @Override
    public List<Egress> findByYear(int year)
    {
        Session              session = sessionFactory.getCurrentSession();
        CriteriaBuilder      builder = session.getCriteriaBuilder();
        CriteriaQuery<Egress> select = builder.createQuery(Egress.class);
        Root<Egress>            root = select.from(Egress.class);
        Predicate[]              and = new Predicate[2];
        and[0] = builder.   lessThanOrEqualTo(root.get("start_year"), year);
        and[1] = builder.greaterThanOrEqualTo(root.get(  "end_year"), year);
        select.where(and);
        
        return session.createQuery(select)
                      .getResultList();
    }
}
