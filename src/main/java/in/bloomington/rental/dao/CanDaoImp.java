package in.bloomington.rental.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.bloomington.rental.model.Can;

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
        String  qq       = "select * from cans order by id desc";
        Session session  = sessionFactory.getCurrentSession();
        return  session.createQuery(qq, Can.class)
                       .getResultList();
    }

    // needed for auto_complete
    @Override
    public List<Can> findByName(String name)
    {
        String  qq      = "select * from cans where title like :name";
        Session session = sessionFactory.getCurrentSession();
        return  session.createQuery(qq, Can.class)
                       .setParameter("name", "%" + name + "%")
                       .getResultList();
    }
}
