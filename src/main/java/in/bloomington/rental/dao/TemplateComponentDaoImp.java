package in.bloomington.rental.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.bloomington.rental.model.TemplateComponent;

@Repository
public class TemplateComponentDaoImp implements TemplateComponentDao
{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public TemplateComponent get(int id)
    {
        return sessionFactory.getCurrentSession()
                             .get(TemplateComponent.class, id);
    }

    @Override
    public void save(TemplateComponent templateComponent)
    {
        sessionFactory.getCurrentSession().save(templateComponent);
    }

    @Override
    public void update(int id, TemplateComponent templateComponent)
    {
        Session           session = sessionFactory.getCurrentSession();
        TemplateComponent comp    = session.byId(TemplateComponent.class)
                                           .load(id);
                          comp    = (TemplateComponent) session.merge(templateComponent);
        session.update(comp);
        session.flush();
    }

    @Override
    public void delete(int id)
    {
        TemplateComponent comp = (TemplateComponent) sessionFactory.getCurrentSession()
                                                                   .load(TemplateComponent.class, id);
        if (comp != null) {
            this.sessionFactory.getCurrentSession().delete(comp);
        }
    }
}
