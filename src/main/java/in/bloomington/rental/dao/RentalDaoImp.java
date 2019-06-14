package in.bloomington.rental.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.bloomington.rental.model.Rental;
import in.bloomington.rental.model.Rental_;
import in.bloomington.rental.model.Owner;
import in.bloomington.rental.model.Owner_;
import in.bloomington.rental.model.Address;
import in.bloomington.rental.model.Address_;
import in.bloomington.rental.model.RentalOwner;
import in.bloomington.rental.model.RentalOwner_;
import in.bloomington.rental.util.Search;
import in.bloomington.rental.util.Helper;

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
				Rental rental = null;
				try{
						rental  = session.byId(Rental.class).load(id);
						rental.getRentalNotes().size(); // to start lazy initialize
						session.flush();
				}catch(Exception ex){
						System.err.println(" Not found ");
				}
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
        CriteriaBuilder       builder = session.getCriteriaBuilder();
        CriteriaQuery<Rental> select = builder.createQuery(Rental.class);
        Root<Rental>            root = select.from(Rental.class);
        select.orderBy(builder.desc(root.get(Rental_.id)));
        return session.createQuery(select)
                      .setMaxResults(limit)
                      .getResultList();
				
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
				Date date_f = null, date_t = null;
				if(dateFrom == null || dateFrom.equals("")){
						dateFrom = Helper.getToday();
				}
				try {
						if(dateFrom != null)
								date_f = dtf.parse(dateFrom);
						if(dateTo != null)
								date_t = dtf.parse(dateTo);
				}catch(Exception ex){
						System.err.println(ex);
				}
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Rental> select = builder.createQuery(Rental.class);
        Root<Rental> root = select.from(Rental.class);
				if(date_t == null){
						select.where(builder.and(builder.greaterThan(root.get(Rental_.permitExpires).as(Date.class), date_f), builder.isNull(root.get(Rental_.inactive))));
				}
				else{
						select.where(builder.and(builder.between(root.get(Rental_.permitExpires).as(Date.class), date_f, date_t),																		 
																		 builder.isNull(root.get(Rental_.inactive))));
				}
        return session.createQuery(select)
						.getResultList();
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
						Rental one = get(id);
						if(one != null){
								List<Rental> ones = new ArrayList<>();
								ones.add(one);
								return ones;
						}
						else{
								return null;
						}
        }
        else if (ownerId != null && ownerId > 0) {
						Session session = sessionFactory.getCurrentSession();			
						CriteriaBuilder builder = session.getCriteriaBuilder();
						CriteriaQuery<RentalOwner> criteria = builder.createQuery(RentalOwner.class);
						Root<RentalOwner> root = criteria.from(RentalOwner.class);
						Join<RentalOwner, Owner> theJoin = root.join(RentalOwner_.owner);
						Predicate pred = builder.equal(theJoin.get(Owner_.id),ownerId);
						criteria.where(pred);
						List<RentalOwner> list = session.createQuery(criteria)
								.getResultList();
						List<Rental> rentals = null;
						if(list != null && list.size() > 0){
								rentals = new ArrayList<>();
								for(RentalOwner ro:list){
										Rental one = ro.getRental();
										if(one != null && !rentals.contains(one)){
												rentals.add(one);
										}
								}
						}
						return rentals;
        }
        else if (addressId != null && addressId > 0) {
						Session session = sessionFactory.getCurrentSession();			
						CriteriaBuilder builder = session.getCriteriaBuilder();
						CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
						Root<Address> root = criteria.from(Address.class);
						Predicate pp = builder.equal(root.get(Address_.id), addressId);
						criteria.where(pp);
						criteria.select(builder.construct(Long.class, root.get(Address_.rentalId).as(Long.class)));
						List<Long> ids =  session.createQuery(criteria).getResultList();
						if(ids != null && ids.size() > 0){
								List<Rental> rentals = new ArrayList<>();
								for(Long idd:ids){
										Rental one = session.byId(Rental.class).load(idd.intValue());
										if(one != null && !rentals.contains(one)){
												rentals.add(one);
										}
								}
								return rentals;
						}
        }
        else {
						Date dfrom = null, dto = null;
						try{
								if(dateFrom != null && !dateFrom.equals(""))
										dfrom = dtf.parse(dateFrom);
								if(dateTo != null && !dateTo.equals(""))
										dto = dtf.parse(dateTo);
						}catch(Exception ex){
								System.err.println(ex);
						}
						Session session = sessionFactory.getCurrentSession();			
						CriteriaBuilder builder = session.getCriteriaBuilder();
						CriteriaQuery<Rental> criteria = builder.createQuery(Rental.class);
						Root<Rental> root = criteria.from(Rental.class);
						List<Predicate> preds = new ArrayList<>();						
            if (agentId != null && agentId > 0) {
								Predicate pp = builder.equal(root.get(Rental_.agent), agentId);
								preds.add(pp);
            }
            if (zoningId != null && zoningId > 0) {
								Predicate pp = builder.equal(root.get(Rental_.zoning), zoningId);
								preds.add(pp);								
								/*
                if (in) qw += " and ";
                in  = true;
                qw += " r.zoning.id=:zoningId";
								*/
            }
            if (statusId != null && statusId > 0) {
								Predicate pp = builder.equal(root.get(Rental_.rentalStatus), statusId);
								preds.add(pp);										
								/*
                if (in) qw += " and ";
                in  = true;
                qw += " r.rentalStatus.id=:statusId";
								*/
            }
            if (NHood != null && NHood > 0) {
								Predicate pp = builder.equal(root.get(Rental_.NHood), NHood);
								preds.add(pp);
								/*
                if (in) qw += " and ";
                in  = true;
                qw += " r.NHood=:NHood";
								*/
            }
            if (dfrom != null) {
								Predicate pp = null;
								if(dateType.indexOf("register") >  -1){
										pp = builder.greaterThan(root.get(Rental_.registeredDate).as(Date.class), dfrom);
								}
								else if(dateType.indexOf("cycle") > -1){ 
										pp = builder.greaterThan(root.get(Rental_.lastCycleDate).as(Date.class), dfrom);
								}
								else if(dateType.indexOf("issue") > -1){ 
										pp = builder.greaterThan(root.get(Rental_.permitIssued).as(Date.class), dfrom);
								}
								else if(dateType.indexOf("expire") > -1){ 
										pp = builder.greaterThan(root.get(Rental_.permitExpires).as(Date.class), dfrom);
								}
								if(pp != null)
										preds.add(pp);										
            }
            if (dto != null && !dto.equals("")) {
								Predicate pp = null;
								if(dateType.indexOf("register") >  -1){
										pp = builder.lessThan(root.get(Rental_.registeredDate).as(Date.class), dto);
								}
								else if(dateType.indexOf("cycle") > -1){ 
										pp = builder.lessThan(root.get(Rental_.lastCycleDate).as(Date.class), dto);
								}
								else if(dateType.indexOf("issue") > -1){ 
										pp = builder.lessThan(root.get(Rental_.permitIssued).as(Date.class), dto);
								}
								else if(dateType.indexOf("expire") > -1){ 
										pp = builder.lessThan(root.get(Rental_.permitExpires).as(Date.class), dto);
								}
								if(pp != null)
										preds.add(pp);								

            }
						if(preds.size() > 1){
								criteria.where(builder.and(preds.toArray(new Predicate[0])));
								return session.createQuery(criteria).getResultList();
						}
						else if(preds.size() == 1){
								criteria.where(preds.get(0));
								return session.createQuery(criteria).getResultList();
						}
        }
				return null;
				/*
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
				*/
    }
}
