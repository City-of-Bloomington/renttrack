package in.bloomington.rental.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
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
        Session  session  = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(InspectionCan.class);
        criteria.setMaxResults(limit);
        criteria.addOrder(Order.desc("id"));
        return criteria.list();
    }

    // needed for auto_complete
    @Override
    public List<InspectionCan> findByName(String name)
    {
        String  qq      = "from InspectionCan c where c.canTile like :name or c.item1 like :name2 ";
        Session session = sessionFactory.getCurrentSession();
        Query   query   = session.createQuery(qq);
        query.setParameter("name", "%" + name + "%");
        query.setParameter("name2", "%" + name + "%");
        List<InspectionCan> cans = query.list();
        return cans;

    }

    @Override
    public List<InspectionCan> findByInspectionId(int id)
    {
        String  qq      = "from InspectionCan c where c.inspection.id =:inspectionId";
        Session session = sessionFactory.getCurrentSession();
        Query   query   = session.createQuery(qq);
        query.setParameter("inspectionId", id);
        List<InspectionCan> cans = query.list();
        return cans;

    }
}
