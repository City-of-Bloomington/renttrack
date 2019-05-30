package in.bloomington.rental.dao;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.bloomington.rental.model.RentalStructure;
import in.bloomington.rental.model.RentalStructure_;
import in.bloomington.rental.model.Rental;

@Repository
public class RentalStructureDaoImp implements RentalStructureDao
{
    @Autowired
    private SessionFactory sessionFactory;
    private int            limit = 30;

    @Override
    public RentalStructure get(int id)
    {
        return sessionFactory.getCurrentSession()
                             .get(RentalStructure.class, id);
    }

    @Override
    public void save(RentalStructure rentalStructure)
    {
        sessionFactory.getCurrentSession().save(rentalStructure);
    }

    @Override
    public void update(int id, RentalStructure rentalStructure)
    {
        Session         session   = sessionFactory.getCurrentSession();
        RentalStructure structure = session.byId(RentalStructure.class)
                                           .load(id);
                        structure = (RentalStructure) session.merge(rentalStructure);
        session.update (structure);
        session.flush();
    }

    @Override
    public void delete(int id)
    {
        RentalStructure s = (RentalStructure) sessionFactory.getCurrentSession()
                                                            .load(RentalStructure.class, id);
        if (s != null) {
            this.sessionFactory.getCurrentSession().delete(s);
        }
    }

    @Override
    public void delete(RentalStructure val)
    {
        if (val != null) {
            this.sessionFactory.getCurrentSession().delete(val);
        }
    }

    @Override
    public List<RentalStructure> getAll()
    {
				/*
        Session  session  = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(RentalStructure.class);
        criteria.setMaxResults(limit);
        criteria.addOrder(Order.desc("id"));
        // remove dups
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
				*/
				return null;
    }

		

    //
    public List<RentalStructure> findByRentalId(Integer rentalId)
    {
				//
        Session session               = sessionFactory.getCurrentSession();
        CriteriaBuilder builder       = session.getCriteriaBuilder();
        CriteriaQuery<RentalStructure> select = builder.createQuery(RentalStructure.class);
        Root<RentalStructure> root = select.from(RentalStructure.class);

        select.where (builder.equal(root.get(RentalStructure_.rental), rentalId));				
        return session.createQuery(select).getResultList();
				
    }
}
