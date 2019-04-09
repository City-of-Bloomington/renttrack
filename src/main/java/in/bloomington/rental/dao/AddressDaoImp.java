package in.bloomington.rental.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import in.bloomington.rental.model.Item;
import in.bloomington.rental.model.Address;
import in.bloomington.rental.model.Address_;

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
    public List<Address> findByRentalId(Integer id)
    {
        Session session               = sessionFactory.getCurrentSession();
        CriteriaBuilder builder       = session.getCriteriaBuilder();
        CriteriaQuery<Address> select = builder.createQuery(Address.class);
        Root<Address> root            = select.from(Address.class);

        select.where(builder.equal(root.get(Address_.rentalId), id));

        return session.createQuery(select).getResultList();
    }

    @Override
    public List<Address> find(String address, String inValid)
    {
        Session session               = sessionFactory.getCurrentSession();
        CriteriaBuilder builder       = session.getCriteriaBuilder();
        CriteriaQuery<Address> select = builder.createQuery(Address.class);
        Root<Address> root            = select.from(Address.class);

        if (address != null && !address.equals("")) {
            select.where(builder.equal(root.get(Address_.streetAddress), address));
        }
        if (inValid != null && !inValid.equals("")) {
            select.where(builder.isNotNull(root.get("invalid")));
        }
        return session.createQuery(select).getResultList();
    }

    @Override
    public List<Address> getAll()
    {
        Session               session = sessionFactory.getCurrentSession();
        CriteriaBuilder       builder = session.getCriteriaBuilder();
        CriteriaQuery<Address> select = builder.createQuery(Address.class);
        Root<Address>            root = select.from(Address.class);

        select.orderBy(builder.desc(root.get(Address_.id)));

        return session.createQuery(select)
                      .setMaxResults(limit)
                      .getResultList();
    }

    @Override
    public List<Item> getList(String address)
    {
        Session    session = sessionFactory.getCurrentSession();
        String     qq      = "select id, street_address as namefrom addresses"
                           + " from addresses"
                           + " where  street_address like :address";
        return session.createNativeQuery(qq, Item.class)
                      .setParameter("address", "%" + address + "%")
                      .list();
    }
}
