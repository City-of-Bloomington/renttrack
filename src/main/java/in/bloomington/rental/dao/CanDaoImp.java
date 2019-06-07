package in.bloomington.rental.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.bloomington.rental.model.Can;
import in.bloomington.rental.model.Can_;

@Repository
public class CanDaoImp implements CanDao
{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Can get(int id)
    {
        return sessionFactory.getCurrentSession().get(Can.class, id);
    }

    @Override
    public void save(Can can)
    {
        sessionFactory.getCurrentSession().save(can);
    }

    @Override
    public void update(int id, Can can)
    {
        Session session = sessionFactory.getCurrentSession();
        Can     can2    = session.byId(Can.class).load(id);
                can2    = (Can) session.merge(can);
        session.update(can2);
        session.flush();
    }

    @Override
    public void delete(int id)
    {
        Can can = (Can) sessionFactory.getCurrentSession().load(Can.class, id);
        if (can != null) {
            this.sessionFactory.getCurrentSession().delete(can);
        }
    }

    @Override
    public List<Can> getAll()
    {
        Session            session = sessionFactory.getCurrentSession();
        CriteriaBuilder    builder = session.getCriteriaBuilder();
        CriteriaQuery<Can> select = builder.createQuery(Can.class);
        Root<Can>            root = select.from(Can.class);

        select.orderBy(builder.desc(root.get(Can_.id)));

        return session.createQuery(select)
                      .getResultList();
    }

    // needed for auto_complete
    @Override
    public List<Can> findByName(String name)
    {
        Session            session = sessionFactory.getCurrentSession();
        CriteriaBuilder    builder = session.getCriteriaBuilder();
        CriteriaQuery<Can> select = builder.createQuery(Can.class);
        Root<Can>            root = select.from(Can.class);
        
        select.where(builder.like(root.get(Can_.title), name + "%"));
				select.orderBy(builder.desc(root.get(Can_.title)));
        return session.createQuery(select)
                      .getResultList();
    }
}
