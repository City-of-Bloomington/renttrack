package in.bloomington.rental.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import in.bloomington.rental.model.InspectionTemplate;

@Repository
public class InspectionTemplateDaoImp implements InspectionTemplateDao
{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public InspectionTemplate get(int id)
    {
        return sessionFactory.getCurrentSession()
                             .get(InspectionTemplate.class, id);
    }

    @Override
    public void save(InspectionTemplate inspectionTemplate)
    {
        sessionFactory.getCurrentSession()
                      .save(inspectionTemplate);
    }

    @Override
    public void update(int id, InspectionTemplate inspectionTemplate)
    {
        Session            session  = sessionFactory.getCurrentSession();
        InspectionTemplate template = session.byId(InspectionTemplate.class)
                                             .load(id);
                           template = (InspectionTemplate) session.merge(inspectionTemplate);
        session.update(template);
        session.flush();
    }

    @Override
    public void delete(int id)
    {
        InspectionTemplate t = (InspectionTemplate) sessionFactory.getCurrentSession()
                                                                  .load(InspectionTemplate.class, id);
        if (t != null) {
            this.sessionFactory.getCurrentSession().delete(t);
        }
    }

    @Override
    public List<InspectionTemplate> findByRentalId(int id)
    {
        String  qq      = "from InspectionTemplate c where c.rentalId =:rentalId";
        Session session = sessionFactory.getCurrentSession();
        Query   query   = session.createQuery(qq);
        query.setParameter("rentalId", id);
        List<InspectionTemplate> vals = query.list();
        return vals;

    }
}
