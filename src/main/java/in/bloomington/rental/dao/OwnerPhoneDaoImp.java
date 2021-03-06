package in.bloomington.rental.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.bloomington.rental.model.OwnerPhone;

@Repository
public class OwnerPhoneDaoImp implements OwnerPhoneDao
{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public OwnerPhone get(int id)
    {
        return sessionFactory.getCurrentSession().get(OwnerPhone.class, id);
    }

    @Override
    public void save(OwnerPhone val)
    {
        sessionFactory.getCurrentSession().save(val);
    }

    @Override
    public void update(int id, OwnerPhone val)
    {
        Session    session = sessionFactory.getCurrentSession();
        OwnerPhone val2    = session.byId(OwnerPhone.class).load(id);
        val2.setPhoneNum(val.getPhoneNum());
        val2.setType(val.getType());
        session.flush();
    }

    @Override
    public void delete(int id)
    {
        Session    session = sessionFactory.getCurrentSession();
        OwnerPhone val     = session.byId(OwnerPhone.class).load(id);
        session.delete(val);
    }

    @Override
    public List<OwnerPhone> list()
    {
        Session                  session = sessionFactory.getCurrentSession();
        CriteriaBuilder          builder = session.getCriteriaBuilder();
        CriteriaQuery<OwnerPhone> select = builder.createQuery(OwnerPhone.class);
        
        return session.createQuery(select)
                      .getResultList();
    }

    @Override
    public List<OwnerPhone> findByOwnerId(int owner_id)
    {
        Session                  session = sessionFactory.getCurrentSession();
        CriteriaBuilder          builder = session.getCriteriaBuilder();
        CriteriaQuery<OwnerPhone> select = builder.createQuery(OwnerPhone.class);
        Root<OwnerPhone>            root = select.from(OwnerPhone.class);
        select.where(builder.equal(root.get("owner_id"), owner_id));
        return session.createQuery(select)
                      .getResultList();
    }
}
