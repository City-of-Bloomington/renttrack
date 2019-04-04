package in.bloomington.rental.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.query.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.transform.*;
import in.bloomington.rental.model.Item;
import in.bloomington.rental.model.Address;

@Repository
public class AddressDaoImp implements AddressDao
{

    private int limit = 30;
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Address get(int id)
    {
        return sessionFactory.getCurrentSession().get(Address.class, id);
    }

    @Override
    public void save(Address address)
    {
        sessionFactory.getCurrentSession().save(address);
    }

    @Override
    public void update(int id, Address address)
    {
        Session session = sessionFactory.getCurrentSession();
        Address addr2   = session.byId(Address.class).load(id);
                addr2   = (Address) session.merge(address);
        session.update(addr2);
        session.flush();
    }

    @Override
    public void delete(int id)
    {
        Session session = sessionFactory.getCurrentSession();
        Address address = session.byId(Address.class).load(id);
        session.delete(address);
    }

    @Override
    public void delete(Address address)
    {
        Session session = sessionFactory.getCurrentSession();
        if (address != null) {
            session.delete(address);
        }
    }

    @Override
    public List<Address> findByRentalId(Integer rentalId)
    {
        String  qq      = "from Address where rentalId =:rentalId";
        Session session = sessionFactory.getCurrentSession();
        Query   query   = session.createQuery(qq);
        query.setParameter("rentalId", rentalId);
        return query.list();
    }

    @Override
    public List<Address> find(String address, String inValid)
    {
        Session session = sessionFactory.getCurrentSession();
        String  qq      = "from Address s ";
        String  qw      = "";
        if (address != null && !address.equals("")) {
            qw += " s.streetAddress like :address ";
        }
        if (inValid != null && !inValid.equals("")) {
            if (!qw.equals("")) {
                qw += " and ";
            }
            qw += " s.invalid is not null ";
        }
        if (!qw.equals("")) {
            qq += " where " + qw;
        }
        Query query = session.createQuery(qq);
        if (address != null && !address.equals("")) {
            query.setParameter("address", "%" + address + "%");
        }
        return query.getResultList();
    }

    @Override
    public List<Address> getAll()
    {
        Session  session  = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Address.class);
        criteria.setMaxResults(limit);
        criteria.addOrder(Order.desc("id"));
        return criteria.list();
    }

    @Override
    public List<Item> getList(String address)
    {
        Session    session = sessionFactory.getCurrentSession();
        String     qq      = "select s.id as id,s.street_address as name from addresses s "
                           + " where  s.street_address like '%" + address + "%' ";
        List<Item> items   = session.createNativeQuery(qq)
                                    .setResultTransformer(Transformers.aliasToBean(Item.class))
                                    .list();
        return items;
    }
}
