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
import in.bloomington.rental.model.RentalStatus;

@Repository
public class RentalStatusDaoImp implements RentalStatusDao
{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public RentalStatus get(int id)
    {
        return sessionFactory.getCurrentSession().get(RentalStatus.class, id);
    }

    @Override
    public void save(RentalStatus val)
    {
        sessionFactory.getCurrentSession().save(val);
    }

    @Override
    public void update(int id, RentalStatus rentalStatus)
    {
        Session      session = sessionFactory.getCurrentSession();
        RentalStatus val2    = session.byId(RentalStatus.class).load(id);
        val2.setName(rentalStatus.getName());
        val2.setAlias(rentalStatus.getAlias());
        session.flush();
    }

    @Override
    public void delete(int id)
    {
        Session      session = sessionFactory.getCurrentSession();
        RentalStatus val     = session.byId(RentalStatus.class).load(id);
        session.delete(val);
    }

    @Override
    public List<RentalStatus> list()
    {
				Session session = sessionFactory.getCurrentSession();				
				CriteriaBuilder          builder = session.getCriteriaBuilder();
        CriteriaQuery<RentalStatus> select = builder.createQuery(RentalStatus.class);
        Root<RentalStatus>     root = select.from(RentalStatus.class);
        return session.createQuery(select)
                      .getResultList();
				
    }
}
