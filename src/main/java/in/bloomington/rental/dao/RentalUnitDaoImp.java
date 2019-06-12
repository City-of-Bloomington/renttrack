package in.bloomington.rental.dao;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.bloomington.rental.model.Address;
import in.bloomington.rental.model.RentalUnit;
import in.bloomington.rental.model.RentalUnit_;

@Repository
public class RentalUnitDaoImp implements RentalUnitDao
{
    @Autowired
    private SessionFactory sessionFactory;
    private int            limit = 30;

    @Override
    public RentalUnit get(int id)
    {
        return sessionFactory.getCurrentSession().get(RentalUnit.class, id);
    }

    @Override
    public void save(RentalUnit rentalUnit)
    {
        sessionFactory.getCurrentSession().save(rentalUnit);
    }

    @Override
    public void update(int id, RentalUnit rentalUnit)
    {
        Session    session = sessionFactory.getCurrentSession();
        RentalUnit unit    = session.byId(RentalUnit.class).load(id);
                   unit    = (RentalUnit) session.merge(rentalUnit);
        session.update(unit);
        session.flush();
    }

    @Override
    public void updateAddressInfo(int id, int addressId)
    {
        Session    session = sessionFactory.getCurrentSession();
        RentalUnit unit    = session.byId(RentalUnit.class).load(id);
        Address    address = session.byId(Address.class).load(addressId);
        if (unit != null && address != null) {
            unit.setAddress(address);
            session.update(unit);
        }
        session.flush();

    }

    @Override
    public void delete(int id)
    {
        RentalUnit unit = (RentalUnit) sessionFactory.getCurrentSession()
                                                     .load(RentalUnit.class, id);
        if (unit != null) {
            this.sessionFactory.getCurrentSession().delete(unit);
        }
    }

    @Override
    public List<RentalUnit> getAll()
    {
				/*
        Session  session  = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(RentalUnit.class);
        criteria.setMaxResults(limit);
        criteria.addOrder(Order.desc("id"));
        // remove dups
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
				*/
				return null;

    }

    //
    /*
     * instead of join
     */
    public List<RentalUnit> findByStructureId(Integer structureId)
    {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder          builder = session.getCriteriaBuilder();
        CriteriaQuery<RentalUnit> select = builder.createQuery(RentalUnit.class);
        Root<RentalUnit>            root = select.from(RentalUnit.class);
        select.where  (builder.equal(root.get(RentalUnit_.rentalStructure), structureId));
        return session.createQuery(select)
                      .getResultList();
				
    }
}
