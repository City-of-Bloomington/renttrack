package in.bloomington.rental.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import in.bloomington.rental.model.Zoning;
import in.bloomington.rental.model.Zoning_;

@Repository
public class ZoningDaoImp implements ZoningDao
{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Zoning get(int id)
    {
        return sessionFactory.getCurrentSession().get(Zoning.class, id);
    }

    @Override
    public void save(Zoning val)
    {
        sessionFactory.getCurrentSession().save(val);
    }

    @Override
    public void update(int id, Zoning val)
    {
        Session session = sessionFactory.getCurrentSession();
        Zoning  val2    = session.byId(Zoning.class).load(id);
        val2.setName (val.getName());
        val2.setAlias(val.getAlias());
        session.flush();
    }

    @Override
    public void delete(int id)
    {
        Session session = sessionFactory.getCurrentSession();
        Zoning  val     = session.byId(Zoning.class).load(id);
        session.delete(val);
    }

    @Override
    public List<Zoning> list()
    {
        Session  session  = sessionFactory.getCurrentSession();
        CriteriaBuilder       builder = session.getCriteriaBuilder();
        CriteriaQuery<Zoning> select = builder.createQuery(Zoning.class);
        Root<Zoning>            root = select.from(Zoning.class);
        select.orderBy(builder.asc(root.get(Zoning_.name)));
        return session.createQuery(select)
						.getResultList();

				
    }


		
}
