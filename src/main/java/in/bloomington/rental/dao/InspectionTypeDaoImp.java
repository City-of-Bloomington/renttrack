package in.bloomington.rental.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.bloomington.rental.model.InspectionType;

@Repository
public class InspectionTypeDaoImp implements InspectionTypeDao
{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public InspectionType get(int id)
    {
        return sessionFactory.getCurrentSession()
                             .get(InspectionType.class, id);
    }

    @Override
    public void save(InspectionType val)
    {
        sessionFactory.getCurrentSession().save(val);
    }

    @Override
    public void update(int id, InspectionType val)
    {
        Session        session = sessionFactory.getCurrentSession();
        InspectionType val2    = session.byId(InspectionType.class).load(id);
        val2.setName (val.getName());
        val2.setAlias(val.getAlias());
        session.flush();
    }

    @Override
    public void delete(int id)
    {
        Session        session = sessionFactory.getCurrentSession();
        InspectionType val     = session.byId(InspectionType.class).load(id);
        session.delete(val);
    }

    @Override
    public List<InspectionType> list()
    {
        Session          session = sessionFactory.getCurrentSession();
        CriteriaBuilder   builder = session.getCriteriaBuilder();
        CriteriaQuery<InspectionType> select = builder.createQuery(InspectionType.class);
        Root<InspectionType>  root = select.from(InspectionType.class);
        select.orderBy(builder.asc(root.get("id")));				
        return session.createQuery(select)
                      .getResultList();
    }
}
