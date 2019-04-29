package in.bloomington.rental.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.bloomington.rental.model.RentalLegal;

@Repository
public class RentalLegalDaoImp implements RentalLegalDao
{
    @Autowired
    private SessionFactory sessionFactory;
    private int            limit = 30;

    @Override
    public RentalLegal get(int id)
    {
        return sessionFactory.getCurrentSession().get(RentalLegal.class, id);
    }

    @Override
    public void save(RentalLegal val)
    {
        sessionFactory.getCurrentSession().save(val);
    }
}
