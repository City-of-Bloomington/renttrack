package in.bloomington.rental.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.bloomington.rental.model.InspectionTemplate;
import in.bloomington.rental.model.InspectionTemplate_;

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
        Session                          session = sessionFactory.getCurrentSession();
        CriteriaBuilder                  builder = session.getCriteriaBuilder();
        CriteriaQuery<InspectionTemplate> select = builder.createQuery(InspectionTemplate.class);
        Root<InspectionTemplate>            root = select.from(InspectionTemplate.class);
        select.where  (builder.equal(root.get(InspectionTemplate_.rentalId), id));
        return session.createQuery(select)
                      .getResultList();

    }
}
