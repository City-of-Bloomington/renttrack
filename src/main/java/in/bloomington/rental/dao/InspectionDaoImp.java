package in.bloomington.rental.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.bloomington.rental.model.Inspection;

@Repository
public class InspectionDaoImp implements InspectionDao
{
    @Autowired
    private SessionFactory sessionFactory;
    int                    limit = 5;

    @Override
    public Inspection get(int id)
    {
        return sessionFactory.getCurrentSession().get(Inspection.class, id);
    }

    @Override
    public void save(Inspection inspection)
    {
        sessionFactory.getCurrentSession().save(inspection);
    }

    @Override
    public void update(int id, Inspection inspection)
    {
        Session    session     = sessionFactory.getCurrentSession();
        Inspection inspection2 = session.byId(Inspection.class).load(id);
                   inspection2 = (Inspection) session.merge(inspection);
        session.update(inspection2);
        session.flush();
    }

    @Override
    public void delete(int id)
    {
        Inspection i = (Inspection) sessionFactory.getCurrentSession()
                                                  .load(Inspection.class, id);
        if (i != null) {
            this.sessionFactory.getCurrentSession()
                               .delete(i);
        }
    }

    @Override
    public List<Inspection> getAll()
    {
        Session                  session = sessionFactory.getCurrentSession();
        CriteriaBuilder          builder = session.getCriteriaBuilder();
        CriteriaQuery<Inspection> select = builder.createQuery(Inspection.class);
        Root<Inspection>            root = select.from(Inspection.class);
        
        select.orderBy(builder.desc(root.get("id")));
        
        return session.createQuery(select)
                      .setMaxResults(limit)
                      .getResultList();
    }

    @Override
    public List<Inspection> findByRentalId(int id)
    {
        Session                  session = sessionFactory.getCurrentSession();
        CriteriaBuilder          builder = session.getCriteriaBuilder();
        CriteriaQuery<Inspection> select = builder.createQuery(Inspection.class);
        Root<Inspection>            root = select.from(Inspection.class);
        
        select.where  (builder.equal(root.get("rental_id"), id));
        select.orderBy(builder.desc (root.get("id")));
        
        return session.createQuery(select)
                      .getResultList();
    }

    @Override
    public int findCountByRentalId(int id)
    {
        String  qq      = "select count(*) from inspections where rental_id=:id";
        Session session = sessionFactory.getCurrentSession();
        return  session.createQuery(qq, Integer.class)
                       .setParameter("id", id)
                       .getSingleResult();
    }
}
