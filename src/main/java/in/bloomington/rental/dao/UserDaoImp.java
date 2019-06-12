package in.bloomington.rental.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.bloomington.rental.model.RentUser;
import in.bloomington.rental.model.RentUser_;

@Repository
public class UserDaoImp implements UserDao
{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public RentUser get(int id)
    {
        return sessionFactory.getCurrentSession().get(RentUser.class, id);
    }

    @Override
    public void save(RentUser user)
    {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public RentUser findByUsername(String username)
    {
        Session  session  = sessionFactory.getCurrentSession();
        CriteriaBuilder       builder = session.getCriteriaBuilder();
        CriteriaQuery<RentUser> select = builder.createQuery(RentUser.class);
        Root<RentUser>            root = select.from(RentUser.class);
				select.where(builder.like(root.get(RentUser_.username),username));
        return session.createQuery(select)
						.getSingleResult();

				
    }

    @Override
    public void update(int id, RentUser user)
    {
        Session  session = sessionFactory.getCurrentSession();
        RentUser user2   = session.byId(RentUser.class).load(id);
        user2.setUsername (user.getUsername());
        user2.setFull_name(user.getFull_name());
        user2.setRole     (user.getRole());
        session.flush();
    }

    @Override
    public void delete(int id)
    {
        Session  session = sessionFactory.getCurrentSession();
        RentUser user    = session.byId(RentUser.class).load(id);
        session.delete(user);
    }

    @Override
    public List<RentUser> list()
    {
        Session  session  = sessionFactory.getCurrentSession();
        CriteriaBuilder       builder = session.getCriteriaBuilder();
        CriteriaQuery<RentUser> select = builder.createQuery(RentUser.class);
        Root<RentUser>            root = select.from(RentUser.class);
        return session.createQuery(select)
						.getResultList();				
    }

    @Override
    public List<RentUser> getInspectors()
    {
        Session  session  = sessionFactory.getCurrentSession();
        CriteriaBuilder       builder = session.getCriteriaBuilder();
        CriteriaQuery<RentUser> select = builder.createQuery(RentUser.class);
        Root<RentUser>            root = select.from(RentUser.class);
				select.where(builder.like(root.get(RentUser_.role),"Inspect"));
        return session.createQuery(select)
						.getResultList();
				
    }

    @Override
    public List<RentUser> getAllInspectors()
    {
        Session  session  = sessionFactory.getCurrentSession();
        CriteriaBuilder       builder = session.getCriteriaBuilder();
        CriteriaQuery<RentUser> select = builder.createQuery(RentUser.class);
        Root<RentUser>            root = select.from(RentUser.class);
				select.where(builder.like(root.get(RentUser_.role),"Inspect"));
        return session.createQuery(select)
						.getResultList();
    }
    @Override
    public List<RentUser> getActiveInspectors()
    {
				Session  session  = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<RentUser> select = builder.createQuery(RentUser.class);
        Root<RentUser> root = select.from(RentUser.class);
				select.where(builder.and(builder.like(root.get(RentUser_.role),"Inspect"), builder.isNull(root.get(RentUser_.inactive))));
        return session.createQuery(select)
						.getResultList();

				
    }		
}
