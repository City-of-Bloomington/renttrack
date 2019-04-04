package in.bloomington.rental.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;
import in.bloomington.rental.model.RentUser;

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
        String  qq      = "from RentUser s where s.username like :username";
        Session session = sessionFactory.getCurrentSession();
        Query   query   = session.createQuery(qq);
        query.setParameter("username", username);
        List<RentUser> users = query.list();
        if (users != null && users.size() > 0) {
            RentUser user = users.get(0);
            return user;
        }
        return null;
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
        @SuppressWarnings("unchecked")
        TypedQuery<RentUser> query = sessionFactory.getCurrentSession()
                                                   .createQuery("from RentUser");
        return query.getResultList();
    }

    @Override
    public List<RentUser> getInspectors()
    {
        String  qq      = "from RentUser u where u.role like :role and u.inactive is null";
        Session session = sessionFactory.getCurrentSession();
        Query   query   = session.createQuery(qq);
        query.setParameter("role", "Inspect");
        List<RentUser> users = query.list();
        return users;
    }

    @Override
    public List<RentUser> getAllInspectors()
    {
        String  qq      = "from RentUser u where u.role like :role";
        Session session = sessionFactory.getCurrentSession();
        Query   query   = session.createQuery(qq);
        query.setParameter("role", "Inspect");
        List<RentUser> users = query.list();
        return users;
    }
}
