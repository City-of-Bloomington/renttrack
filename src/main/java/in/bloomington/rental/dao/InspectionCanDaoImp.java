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

import in.bloomington.rental.model.InspectionCan;

@Repository
public class InspectionCanDaoImp implements InspectionCanDao
{
    @Autowired
    private SessionFactory sessionFactory;
    private int            limit = 30;

    @Override
    public InspectionCan get(int id)
    {
        return sessionFactory.getCurrentSession().get(InspectionCan.class, id);
    }

    @Override
    public void save(InspectionCan inspectionCan)
    {
        sessionFactory.getCurrentSession().save(inspectionCan);
    }

    @Override
    public void update(int id, InspectionCan inspectionCan)
    {
        Session       session = sessionFactory.getCurrentSession();
        InspectionCan can     = session.byId(InspectionCan.class)
                                       .load(id);
                      can     = (InspectionCan) session.merge(inspectionCan);
        session.update(can);
        session.flush();
    }

    @Override
    public void delete(int id)
    {
        InspectionCan can = (InspectionCan) sessionFactory.getCurrentSession()
                                                          .load(InspectionCan.class, id);
        if (can != null) {
            this.sessionFactory.getCurrentSession()
                               .delete(can);
        }
    }

    @Override
    public List<InspectionCan> getAll()
    {
        Session                     session = sessionFactory.getCurrentSession();
        CriteriaBuilder             builder = session.getCriteriaBuilder();
        CriteriaQuery<InspectionCan> select = builder.createQuery(InspectionCan.class);
        Root<InspectionCan>            root = select.from(InspectionCan.class);
        
        select.orderBy(builder.desc(root.get("id")));
        
        return session.createQuery(select)
                      .setMaxResults(limit)
                      .getResultList();
    }

    // needed for auto_complete
    @Override
    public List<InspectionCan> findByName(String name)
    {
        Session                     session = sessionFactory.getCurrentSession();
        CriteriaBuilder             builder = session.getCriteriaBuilder();
        CriteriaQuery<InspectionCan> select = builder.createQuery(InspectionCan.class);
        Root<InspectionCan>            root = select.from(InspectionCan.class);
        Predicate[]                 filters = new Predicate[2];
        
        filters[0] = builder.like(root.get("title"), "%" + name + "%");
        filters[1] = builder.like(root.get("item1"), "%" + name + "%");
        select.where(builder.or(filters));
        
        return session.createQuery(select)
                      .getResultList();
    }

    @Override
    public List<InspectionCan> findByInspectionId(int id)
    {
        Session                     session = sessionFactory.getCurrentSession();
        CriteriaBuilder             builder = session.getCriteriaBuilder();
        CriteriaQuery<InspectionCan> select = builder.createQuery(InspectionCan.class);
        Root<InspectionCan>            root = select.from(InspectionCan.class);
        
        select.where(builder.equal(root.get("inspection_id"), id));
        
        return session.createQuery(select)
                      .getResultList();
        
    }
}
