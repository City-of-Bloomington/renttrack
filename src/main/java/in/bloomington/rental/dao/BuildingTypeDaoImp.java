package in.bloomington.rental.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.bloomington.rental.model.BuildingType;
import in.bloomington.rental.model.BuildingType_;

@Repository
public class BuildingTypeDaoImp implements BuildingTypeDao
{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public BuildingType get(int id)
    {
        return sessionFactory.getCurrentSession().get(BuildingType.class, id);
    }

    @Override
    public void save(BuildingType val)
    {
        sessionFactory.getCurrentSession().save(val);
    }

    @Override
    public void update(int id, BuildingType val)
    {
        Session      session = sessionFactory.getCurrentSession();
        BuildingType val2    = session.byId(BuildingType.class).load(id);
        val2.setName(val.getName());
        session.flush();
    }

    @Override
    public void delete(int id)
    {
        Session      session = sessionFactory.getCurrentSession();
        BuildingType val     = session.byId(BuildingType.class).load(id);
        session.delete(val);
    }

    @Override
    public List<BuildingType> list()
    {
        Session                    session = sessionFactory.getCurrentSession();
        CriteriaBuilder            builder = session.getCriteriaBuilder();
        CriteriaQuery<BuildingType> select = builder.createQuery(BuildingType.class);
        Root<BuildingType>            root = select.from(BuildingType.class);

        select.orderBy(builder.desc(root.get(BuildingType_.name)));

        return session.createQuery(select)
                      .getResultList();
    }

}
