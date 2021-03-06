package in.bloomington.rental.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.bloomington.rental.model.Bill;
import in.bloomington.rental.model.Bill_;
import in.bloomington.rental.model.Rental;

@Repository
public class BillDaoImp implements BillDao
{
    @Autowired
    private SessionFactory sessionFactory;
    private int            limit = 30;

    @Override
    public Bill get(int id)
    {
        return sessionFactory.getCurrentSession().get(Bill.class, id);
    }

    @Override
    public void save(Bill bill)
    {
        sessionFactory.getCurrentSession().save(bill);
        Rental rental = bill.getRental();
        if (rental != null) {
            rental.getRentalStructures();
        }
    }

    @Override
    public void update(int id, Bill bill)
    {
        Session session = sessionFactory.getCurrentSession();
        Bill    bill2   = session.byId(Bill.class).load(id);
                bill2   = (Bill) session.merge(bill);
        session.update(bill2);
        Rental rental = bill2.getRental();
        if (rental != null) {
            rental.getRentalStructures();
        }
        session.flush();
    }

    @Override
    public void delete(int id)
    {
        Bill bill = (Bill) sessionFactory.getCurrentSession().load(Bill.class, id);
        if (bill != null) {
            this.sessionFactory.getCurrentSession().delete(bill);
        }
    }

    @Override
    public List<Bill> getAll()
    {
        Session            session = sessionFactory.getCurrentSession();
        CriteriaBuilder    builder = session.getCriteriaBuilder();
        CriteriaQuery<Bill> select = builder.createQuery(Bill.class);
        Root<Bill>            root = select.from(Bill.class);

        select.orderBy(builder.desc(root.get(Bill_.id)));

        return session.createQuery(select)
                      .setMaxResults(limit)
                      .getResultList();
    }
}
