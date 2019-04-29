package in.bloomington.rental.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.bloomington.rental.model.Rental;
import in.bloomington.rental.util.Search;

@Repository
public class RentalDaoImp implements RentalDao
{
    SimpleDateFormat       dtf   = new SimpleDateFormat("MM/dd/yyyy");
    @Autowired
    private SessionFactory sessionFactory;
    private int            limit = 30;

    @Override
    public Rental get(int id)
    {
        Session session = sessionFactory.getCurrentSession();
        Rental  rental  = session.byId(Rental.class).load(id);
        rental.getRentalNotes().size(); // to start lazy initialize
        session.flush();
        return rental;
    }

    @Override
    public void save(Rental rental)
    {
        try {
            sessionFactory.getCurrentSession().save(rental);
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
    }

    @Override
    public void update(int id, Rental rental)
    {
        Session session = sessionFactory.getCurrentSession();
        Rental  rental2 = session.byId(Rental.class).load(id);
                rental2 = (Rental) session.merge(rental);
        session.update(rental2); // instead of saveOrUpdate
        session.flush();
    }

    @Override
    public void delete(int id)
    {
        Rental rental = (Rental) sessionFactory.getCurrentSession()
                                               .load(Rental.class, id);
        if (rental != null) {
            this.sessionFactory.getCurrentSession().delete(rental);
        }
    }

    @Override
    public List<Rental> getAll()
    {
        Session  session  = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Rental.class);
        criteria.setMaxResults(limit);
        criteria.addOrder(Order.desc("id"));
        return criteria.list();
    }

    @Override
    public Rental getRentalWithNotes(int id)
    {
        Rental rental = sessionFactory.getCurrentSession()
                                      .get(Rental.class, id);
        rental.getRentalNotes().size();
        return rental;
    }

    @Override
    public List<Rental> findExpireDate(String dateFrom, String dateTo)
    {
        Session session = sessionFactory.getCurrentSession();
        String  qq      = "from Rental r  where r.inactive is null ";
        if (dateFrom != null && !dateFrom.equals("")) {
            qq += "and r.permitExpires >=:dateFrom";
        }
        if (dateTo != null && !dateTo.equals("")) {
            qq += " and r.permitExpires <=:dateTo";
        }
        Query query = session.createQuery(qq);
        if (dateFrom != null && !dateFrom.equals("")) {
            try {
                Date date = dtf.parse(dateFrom);
                query.setParameter("dateFrom", date);
            }
            catch (Exception ex) {
                System.err.println(ex);
            }
        }
        if (dateTo != null && !dateTo.equals("")) {
            try {
                Date date = dtf.parse(dateTo);
                query.setParameter("dateTo", date);
            }
            catch (Exception ex) {
                System.err.println(ex);
            }
        }
        List<Rental> rentals = query.list();
        return rentals;
    }

    //
    public List<Rental> search(Search search)
    {
        String  qq        = "from Rental r ";
        String  qw        = "";

        Integer id        = search.getId();
        Integer ownerId   = search.getOwnerId();
        Integer agentId   = search.getAgentId();
        Integer addressId = search.getAddressId();
        Integer zoningId  = search.getZoningId();
        Integer statusId  = search.getStatusId();
        Integer NHood     = search.getNHood();
        String  dateType  = search.getDateType();
        String  dateFrom  = search.getDateFrom();
        String  dateTo    = search.getDateTo();
        boolean in        = false;
        if (id != null && id > 0) {
            in  = true;
            qw += " r.id=:id";
        }
        else if (ownerId != null && ownerId > 0) {
            qq  = "select r.* from rentals r "
                + ", rental_owners ro where ro.rental_id=r.id and ro.owner_id=" + ownerId;
        }
        else if (addressId != null && addressId > 0) {
            qq  = "select r.* from rentals r, "
                + "rental_structures s, rental_units u "
                + " where s.rental_id=r.id and u.structure_id=s.id "
                + " and u.address_id=" + addressId;
        }
        else {
            if (agentId != null && agentId > 0) {
                if (in) qw += " and ";
                in  = true;
                qw += " r.agent.id=:agentId";
            }
            if (zoningId != null && zoningId > 0) {
                if (in) qw += " and ";
                in  = true;
                qw += " r.zoning.id=:zoningId";
            }
            if (statusId != null && statusId > 0) {
                if (in) qw += " and ";
                in  = true;
                qw += " r.rentalStatus.id=:statusId";
            }
            if (NHood != null && NHood > 0) {
                if (in) qw += " and ";
                in  = true;
                qw += " r.NHood=:NHood";
            }
            if (dateFrom != null && !dateFrom.equals("")) {
                if (in) qw += " and ";
                in  = true;
                qw += "r." + dateType + ">=:dateFrom";
            }
            if (dateTo != null && !dateTo.equals("")) {
                if (in) qw += " and ";
                in  = true;
                qw += "r." + dateType + "<=:dateTo";
            }
        }
        if (!qw.equals("")) {
            qq += " where " + qw;
        }
        // System.err.println(qq);
        Session      session = sessionFactory.getCurrentSession();
        List<Rental> rentals = null;
        if (id != null && id > 0) {
            Query query = session.createQuery(qq);
            query.setParameter("id", id);
            rentals = query.list();
        }
        else if (ownerId != null && ownerId > 0) {
            SQLQuery query2 = session.createSQLQuery(qq);
            query2.addEntity("rental", Rental.class);
            rentals = query2.list();
        }
        else if (addressId != null && addressId > 0) {
            SQLQuery query2 = session.createSQLQuery(qq);
            query2.addEntity("rental", Rental.class);
            rentals = query2.list();
        }
        else {
            Query query = session.createQuery(qq);
            if (agentId != null && agentId > 0) {
                query.setParameter("agentId", agentId);
            }
            if (zoningId != null && zoningId > 0) {
                query.setParameter("zoningId", zoningId);
            }
            if (statusId != null && statusId > 0) {
                query.setParameter("statusId", statusId);
            }
            if (NHood != null && NHood > 0) {
                int   val  = NHood.intValue();
                short val2 = (short) val;
                query.setParameter("NHood", val2);
            }
            if (dateFrom != null && !dateFrom.equals("")) {
                Date date = search.getDateFromTime();
                if (date != null) {
                    query.setParameter("dateFrom", date);
                }
            }
            if (dateTo != null && !dateTo.equals("")) {
                Date date = search.getDateToTime();
                if (date != null) {
                    query.setParameter("dateTo", date);
                }
            }
            rentals = query.list();
        }
        return rentals;
    }
}
