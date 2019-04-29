package in.bloomington.rental.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.bloomington.rental.model.Item;
import in.bloomington.rental.model.Owner;
import in.bloomington.rental.model.Owner_;
import in.bloomington.rental.model.RentalOwner;

@Repository
public class OwnerDaoImp implements OwnerDao
{
    @Autowired
    private SessionFactory sessionFactory;
    private int            limit = 30;

    @Override
    public Owner get(int id)
    {
        return sessionFactory.getCurrentSession().get(Owner.class, id);
    }

    @Override
    public RentalOwner getRentalOwner(int id)
    {
        return sessionFactory.getCurrentSession().get(RentalOwner.class, id);
    }

    @Override
    public void save(Owner owner)
    {
        sessionFactory.getCurrentSession().save(owner);
    }

    @Override
    public void save(RentalOwner val)
    {
        sessionFactory.getCurrentSession().save(val);
    }

    @Override
    public void update(int id, Owner owner)
    {
        Session session = sessionFactory.getCurrentSession();
        Owner   owner2  = session.byId(Owner.class).load(id);
        owner2.setName   (owner.getName());
        owner2.setAddress(owner.getAddress());
        owner2.setCity   (owner.getCity());
        owner2.setState  (owner.getState());
        owner2.setZip    (owner.getZip());
        owner2.setNotes  (owner.getNotes());
        session.flush();
    }

    @Override
    public void delete(int id)
    {
        Owner owner = (Owner) sessionFactory.getCurrentSession()
                                            .load(Owner.class, id);
        if (owner != null) {
            this.sessionFactory.getCurrentSession().delete(owner);
        }
    }

    @Override
    public void removeRentalOwner(int id)
    {
        RentalOwner val = (RentalOwner) sessionFactory.getCurrentSession()
                                                      .load(RentalOwner.class, id);
        if (val != null) {
            this.sessionFactory.getCurrentSession().delete(val);
        }
    }

    @Override
    public List<Owner> getAll()
    {
        Session             session = sessionFactory.getCurrentSession();
        CriteriaBuilder     builder = session.getCriteriaBuilder();
        CriteriaQuery<Owner> select = builder.createQuery(Owner.class);
        Root<Owner>            root = select.from(Owner.class);

        select.distinct(true)
              .orderBy(builder.desc(root.get(Owner_.id)));

        return session.createQuery(select)
                      .setMaxResults(limit)
                      .getResultList();
    }

    //
    // we used for auto_complete too
    public List<Owner> findByName(String name)
    {
        Session             session = sessionFactory.getCurrentSession();
        CriteriaBuilder     builder = session.getCriteriaBuilder();
        CriteriaQuery<Owner> select = builder.createQuery(Owner.class);
        Root<Owner>            root = select.from(Owner.class);
        
        select.where(builder.like(root.get(Owner_.name), "%" + name + "%"));
        return session.createQuery(select)
                      .getResultList();
    }

    // we used for auto_complete too
    @Override
    public List<Owner> findAgentByName(String name)
    {
        Session  session = sessionFactory.getCurrentSession();
        String   qq      = "select distinct o.*"
                         + " from owners  o"
                         + " join rentals r on o.id=r.agent_id"
                         + " where name like :name";
        return session.createNativeQuery(qq, Owner.class)
                      .setParameter("name", name)
                      .list();
    }

    @Override
    public List<Owner> search(Owner owner)
    {
        Session session    = sessionFactory.getCurrentSession();
        String  qq         = "from Owner o ";
        String  qw         = "";
        String  name       = null, address = null, city = null, state = null, zip = null, phone = null, email = null;
        boolean hasNoEmail = false;
        Integer ownerId    = owner.getOwnerId();
        int     id         = 0;
        if (ownerId != null && ownerId > 0) {
            id  = ownerId.intValue();
            qq += " where o.id=:id";
        }
        else {
            name       = owner.getName();
            address    = owner.getAddress();
            email      = owner.getEmail();
            city       = owner.getCity();
            state      = owner.getState();
            zip        = owner.getZip();
            phone      = owner.getPhone();
            hasNoEmail = owner.hasNoEmail();
            if (phone != null && !phone.equals("")) {
                qq = "select o.* from owners o,owner_phones p where p.owner_id=o.id and p.phone_num like '%"
                   + phone + "%' order by o.name ";
            }
            else {
                if (name != null && !name.equals("")) {
                    if (!qw.equals("")) { qw += " and "; }
                    qw += "o.name like :name";
                }
                if (address != null && !address.equals("")) {
                    if (!qw.equals("")) { qw += " and "; }
                    qw += "o.address like :address";
                }
                if (email != null && !email.equals("")) {
                    if (!qw.equals("")) { qw += " and "; }
                    qw += "o.email like :email";
                }
                if (city != null && !city.equals("")) {
                    if (!qw.equals("")) { qw += " and "; }
                    qw += "o.city like :city";
                }
                if (state != null && !state.equals("")) {
                    if (!qw.equals("")) { qw += " and "; }
                    qw += "o.state like :state";
                }
                if (zip != null && !zip.equals("")) {
                    if (!qw.equals("")) { qw += " and "; }
                    qw += "o.zip like :zip";
                }
                if (hasNoEmail) {
                    if (!qw.equals("")) { qw += " and "; }
                    qw += "o.email is null ";
                }
                if (!qw.equals("")) {
                    qq += " where " + qw + " order by o.name";
                }
            }
        }
        // System.err.println(qq);
        Query       query  = null;
        List<Owner> owners = null;
        if (id > 0) {
            query = session.createQuery(qq);
            query.setParameter("id", id);
            owners = query.list();
        }
        else if (phone != null && !phone.equals("")) {
            SQLQuery query2 = session.createSQLQuery(qq);
            query2.addEntity("owner", Owner.class);
            owners = query2.list();
        }
        else {
            query = session.createQuery(qq);
            if (name != null && !name.equals("")) {
                query.setParameter("name", "%" + name + "%");
            }
            if (address != null && !address.equals("")) {
                query.setParameter("address", "%" + address + "%");
            }
            if (email != null && !email.equals("")) {
                query.setParameter("email", "%" + email + "%");
            }
            if (city != null && !city.equals("")) {
                query.setParameter("city", city);
            }
            if (state != null && !state.equals("")) {
                query.setParameter("state", state);
            }
            if (zip != null && !zip.equals("")) {
                query.setParameter("zip", zip);
            }
            owners = query.list();
        }
        return owners;
    }

    /**
     * select o.id,o.name,r.id,r.permit_expires,o.email from owners o,rental_owners
     * p, rentals r where p.owner_id=o.id and p.rental_id=r.id and r.permit_expires
     * >= to_date('01/01/2018','MM/DD/YYYY') and r.permit_expires <=
     * to_date('02/01/2018','MM/DD/YYYY') and r.inactive is null and o.email is not
     * null order by o.name limit 5 ";
     * 
     */
    @Override
    public List<Owner> findOwnersForExpireEmail(String startDate, String endDate)
    {
        Session  session = sessionFactory.getCurrentSession();
        String   qq      = "select o.* from owners o,rental_owners p, rentals r "
                         + "where p.owner_id=o.id and p.rental_id=r.id "
                         +   "and r.permit_expires >= to_date('" + startDate + "','MM/DD/YYYY') "
                         +   "and r.permit_expires <= to_date('" + endDate   + "','MM/DD/YYYY') "
                         +   "and r.inactive is null and o.email is not null order by o.name ";
        SQLQuery query   = session.createSQLQuery(qq);
        query.addEntity("owner", Owner.class);
        List<Owner> owners = query.list();
        return owners;
    }

    @Override
    public List<RentalOwner> getAllForOwner(int owner_id)
    {
        String  qq      = "from RentalOwner ro where ro.owner.id =:owner_id";
        Session session = sessionFactory.getCurrentSession();
        Query   query   = session.createQuery(qq);
        query.setParameter("owner_id", owner_id);
        List<RentalOwner> rentalOwners = query.list();
        return rentalOwners;
    }

    @Override
    public List<RentalOwner> getAllForRental(int rental_id)
    {
        String  qq      = "from RentalOwner ro where ro.rental.id =:rental_id";
        Session session = sessionFactory.getCurrentSession();
        Query   query   = session.createQuery(qq);
        query.setParameter("rental_id", rental_id);
        List<RentalOwner> rentalOwners = query.list();
        return rentalOwners;
    }

    //
    public List<Owner> find(String name, String address, String city, String state, String zip, String email)
    {
        List<Owner> owners = null;
        String      qq     = "from Owner o where ";
        boolean     and    = false;
        if (name != null) {
            qq  += " o.name like: name ";
            and  = true;
        }
        if (address != null) {
            if (and) qq += ", ";
            qq  += " o.address like:address ";
            and  = true;
        }
        if (city != null) {
            if (and) qq += ", ";
            qq  += " o.city like:city ";
            and  = true;
        }
        if (state != null) {
            if (and) qq += ", ";
            qq  += " o.state like:state ";
            and  = true;
        }
        if (zip != null) {
            if (and) qq += ", ";
            qq  += " o.zip =:zip ";
            and  = true;
        }
        if (email != null) {
            if (and) qq += ", ";
            qq  += " o.email like:email ";
            and  = true;
        }
        //
        Session session = sessionFactory.getCurrentSession();
        Query   query   = session.createQuery(qq);
        if (name    != null) { query.setParameter("name",    name + "%"); }
        if (address != null) { query.setParameter("address", address); }
        if (city    != null) { query.setParameter("city",    city); }
        if (state   != null) { query.setParameter("state",   state); }
        if (zip     != null) { query.setParameter("zip",     zip); }
        if (email   != null) { query.setParameter("email", "%" + email + "%"); }
        owners = query.list();
        return owners;
    }

    @Override
    public List<Item> getList(String name)
    {
        Session    session = sessionFactory.getCurrentSession();
        List<Item> items   = session.createSQLQuery("select id, name from owners where name like '%" + name + "%'")
                                    .setResultTransformer(Transformers.aliasToBean(Item.class))
                                    .list();
        return items;
    }

    @Override
    public List<Item> getAgentList(String name)
    {
        Session    session = sessionFactory.getCurrentSession();
        String     qq      = "select distinct o.* from owners o, rentals r where "
                           + " r.agent_id=o.id and o.name like '%" + name + "%'";
        
        List<Item> items   = session.createSQLQuery(qq)
                                    .setResultTransformer(Transformers.aliasToBean(Item.class))
                                    .list();
        return items;
    }
}
