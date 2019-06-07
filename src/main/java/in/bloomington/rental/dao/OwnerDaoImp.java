package in.bloomington.rental.dao;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.bloomington.rental.model.Item;
import in.bloomington.rental.model.Owner;
import in.bloomington.rental.model.Owner_;
import in.bloomington.rental.model.Rental;
import in.bloomington.rental.model.Rental_;
import in.bloomington.rental.model.RentalOwner_;
import in.bloomington.rental.model.RentalOwner;

@Repository
public class OwnerDaoImp implements OwnerDao
{
		SimpleDateFormat format = new SimpleDateFormat("mm/dd/YYYY");
    @Autowired
    private SessionFactory sessionFactory;
    private int limit = 30;
		
    @Override
    public Owner get(int id)
    {
				Session session = sessionFactory.getCurrentSession();
        return session.get(Owner.class, id);
    }

    @Override
    public RentalOwner getRentalOwner(int id)
    {
				Session session = sessionFactory.getCurrentSession();				
        return session.get(RentalOwner.class, id);
    }

    @Override
    public void save(Owner owner)
    {
				Session session = sessionFactory.getCurrentSession();							

        session.save(owner);
    }

    @Override
    public void save(RentalOwner val)
    {
				Session session = sessionFactory.getCurrentSession();			
        session.save(val);
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
				Session session = sessionFactory.getCurrentSession();			
        Owner owner = (Owner) session.load(Owner.class, id);
        if (owner != null) {
            this.sessionFactory.getCurrentSession().delete(owner);
        }
    }

    @Override
    public void removeRentalOwner(int id)
    {
				Session session = sessionFactory.getCurrentSession();			
        RentalOwner val = (RentalOwner) session.load(RentalOwner.class, id);
        if (val != null) {
            session.delete(val);
        }
    }

    @Override
    public List<Owner> getAll()
    {
				Session session = sessionFactory.getCurrentSession();			
        CriteriaBuilder     builder = session.getCriteriaBuilder();
        CriteriaQuery<Owner> select = builder.createQuery(Owner.class);
        Root<Owner>            root = select.from(Owner.class);

        select.distinct(true)
              .orderBy(builder.asc(root.get(Owner_.name)));

        return session.createQuery(select)
                      .setMaxResults(limit)
                      .getResultList();
    }

    //
    // we used for auto_complete too
    public List<Owner> findByName(String name)
    {
				Session session = sessionFactory.getCurrentSession();			
        CriteriaBuilder     builder = session.getCriteriaBuilder();
        CriteriaQuery<Owner> select = builder.createQuery(Owner.class);
        Root<Owner>            root = select.from(Owner.class);
        select.where(builder.like(root.get(Owner_.name), name + "%"));
        return session.createQuery(select)
                      .getResultList();
    }
		//
    @Override
    public List<Owner> findAgentByName(String name)
    {
				Session session = sessionFactory.getCurrentSession();			
        String   qq      = "select distinct o.*"
                         + " from owners  o"
                         + " join rentals r on o.id=r.agent_id"
                         + " where name like :name";
        return session.createNativeQuery(qq, Owner.class)
                      .setParameter("name", "%"+name+"%")
                      .list();
				/*
					 // need to be refined
				CriteriaBuilder     builder = session.getCriteriaBuilder();
        CriteriaQuery<Owner> cq = builder.createQuery(Owner.class);
        Root<Owner>            root = cq.from(Owner.class);
				ListJoin<Rental, Owner> agent = cq.join(Rental_.agent);
				cq.select(root);
        cq.where(builder.like(root.get(Owner_.name), "%"+name+"%"));
				*/
    }

    @Override
    public List<Owner> search(Owner owner)
    {
				Session session = sessionFactory.getCurrentSession();			
        String  name       = null, address = null, city = null, state = null, zip = null, phone = null, email = null;
        boolean hasNoEmail = false;
				name       = owner.getName();
				address    = owner.getAddress();
				email      = owner.getEmail();
				city       = owner.getCity();
				state      = owner.getState();
				zip        = owner.getZip();
				phone      = owner.getPhone();
				hasNoEmail = owner.hasNoEmail();
				return find(name, address, city, state, zip, email, hasNoEmail);
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
				Date dfrom = null, dto = null;
				try{
						dfrom = format.parse(startDate);
						dto = format.parse(endDate);
				}catch(Exception ex){
						System.err.println(ex);
				}
				Session session = sessionFactory.getCurrentSession();			
				CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<Owner> criteria = builder.createQuery(Owner.class);
        Root<RentalOwner> root = criteria.from(RentalOwner.class);
				Root<Owner> root2 = criteria.from(Owner.class);				
				Join<RentalOwner, Owner> join1 = root.join(RentalOwner_.owner);
				Join<RentalOwner, Rental> join2 = root.join(RentalOwner_.rental);
				Predicate p0 = builder.equal(join1.get(Owner_.id),
																		 root2.get(Owner_.id));
				Predicate p1 = builder.isNotNull(join1.get(Owner_.email));
				Predicate p2 = builder.isNotNull(join2.get(Rental_.inactive));
				Predicate p3 = builder.between(join2.get(Rental_.permitExpires), dfrom,dto);
				List<Predicate> preds = new ArrayList<>();
				preds.add(p0);
				preds.add(p1);
				preds.add(p2);
				preds.add(p3);
				criteria.where(builder.and(preds.toArray(new Predicate[0])));
				List<Owner> owners = session.createQuery(criteria).getResultList();
				return owners;
    }

    @Override
    public List<RentalOwner> getAllForOwner(int owner_id)
    {
				Session session = sessionFactory.getCurrentSession();			
				CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<RentalOwner> criteria = builder.createQuery(RentalOwner.class);
        Root<RentalOwner> root = criteria.from(RentalOwner.class);
				Join<RentalOwner, Owner> theJoin = root.join(RentalOwner_.owner);
				Predicate pred = builder.equal(theJoin.get(Owner_.id),owner_id);
				criteria.where(pred);
				return session.createQuery(criteria)
						.getResultList();
    }

    @Override
    public List<RentalOwner> getAllForRental(int rental_id)
    {
        Session session = sessionFactory.getCurrentSession();
				CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<RentalOwner> criteria = builder.createQuery(RentalOwner.class);
        Root<RentalOwner> root = criteria.from(RentalOwner.class);

				Join<RentalOwner, Rental> theJoin = root.join(RentalOwner_.rental);
				Predicate pred = builder.equal(theJoin.get(Rental_.id),rental_id);
				criteria.where(pred);
				return session.createQuery(criteria)
						.getResultList();
				
    }

    //
    public List<Owner> find(String name,
														String address,
														String city,
														String state,
														String zip,
														String email,
														boolean hasNoEmail)
    {
				boolean multiple = false;
				boolean onlyOne = false;
        if (name != null && !name.equals("")) {
						onlyOne = true;
        }
				if (address != null && !address.equals("")) {
						if(onlyOne)
								multiple = true;
						onlyOne = true;
        }
        if (city != null && !city.equals("")) {
						if(onlyOne)
								multiple = true;
						onlyOne = true;
        }
        if (state != null && !state.equals("")) {
						if(onlyOne)
								multiple = true;
						onlyOne = true;
        }
        if (zip != null && !zip.equals("")) {
						if(onlyOne)
								multiple = true;
						onlyOne = true;
        }
        if (email != null && !email.equals("")) {
						if(onlyOne)
								multiple = true;
						onlyOne = true;
        }
				if(hasNoEmail){
						if(onlyOne)
								multiple = true;
						onlyOne = true;
				}
				Session session = sessionFactory.getCurrentSession();			
				CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<Owner> select = builder.createQuery(Owner.class);
        Root<Owner> root = select.from(Owner.class);
				if(multiple){
						List<Predicate> preds = new ArrayList<>();
						if (name != null && !name.equals("")) {								
								Predicate pred = builder.like(root.get(Owner_.name), name);
								preds.add(pred);
						}
						if(address != null && !address.equals("")){
								Predicate pred = builder.like(root.get(Owner_.address),
																							address);
								preds.add(pred);
						};
						if(city != null && !city.equals("")){
								Predicate pred = builder.like(root.get(Owner_.city),city);
								preds.add(pred);
						};
						if(zip != null && !zip.equals("")){
								Predicate pred = builder.equal(root.get(Owner_.zip),zip);
								preds.add(pred);
						};
						if(email != null && !email.equals("")){
								Predicate pred = builder.like(root.get(Owner_.email),email);
								preds.add(pred);
						};
						if(hasNoEmail){
								Predicate pred = builder.isNull(root.get(Owner_.email));
								preds.add(pred);
						};
						select.where(builder.and(preds.toArray(new Predicate[0])));
						select.orderBy(builder.asc (root.get("name")));		
						return session.createQuery(select).getResultList();
				}
				else if(onlyOne){
						Predicate pred = null;
						if (name != null && !name.equals("")) {			
								pred = builder.like(root.get(Owner_.name), name);
						}
						else if(address != null && !address.equals("")){
								pred = builder.like(root.get(Owner_.address), address);
						}
						else if(city != null && !city.equals("")){
								pred = builder.like(root.get(Owner_.city),city);
						}
						else if(zip != null && !zip.equals("")){
								pred = builder.equal(root.get(Owner_.zip),zip);
						}
						else if(email != null && !email.equals("")){
								pred = builder.like(root.get(Owner_.email),email);
						}
						else if(hasNoEmail){
								pred = builder.isNull(root.get(Owner_.email));
						};
						select.where(pred);
						select.orderBy(builder.asc (root.get("name")));		
						return session.createQuery(select).getResultList();
				}
				else{
						return null;
				}
    }

    @Override
    public List<Item> getList(String name)
    {
				Session session = sessionFactory.getCurrentSession();			
        CriteriaBuilder builder = session.getCriteriaBuilder();				
				CriteriaQuery<Object[]> criteria = builder.createQuery( Object[].class );
				Root<Owner> ownerRoot = criteria.from( Owner.class );
				Path<Integer> idPath = ownerRoot.get( Owner_.id );
				Path<String> namePath = ownerRoot.get( Owner_.name );
				criteria.select( builder.array( idPath, namePath ));
				criteria.where( builder.like( ownerRoot.get(Owner_.name ), "%"+name+"%"));
				List<Object[]> valueArray = session.createQuery(criteria).getResultList();
				List<Item> items = new ArrayList<>();
				for ( Object[] values : valueArray ) {
						final Integer id = (Integer) values[0];
						final String str = (String) values[1];
						Item item = new Item(id, str);
						items.add(item);
				}
				return items;
    }

    @Override
    public List<Item> getAgentList(String name)
    {
				Session session = sessionFactory.getCurrentSession();			
        CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<Object[]> criteria = builder.createQuery( Object[].class );
				Root<Rental> rentRoot = criteria.from(Rental.class );
				Join<Rental, Owner> theJoin = rentRoot.join(Rental_.agent);
				
				Path<Integer> idPath = theJoin.get( Owner_.id );
				Path<String> namePath = theJoin.get( Owner_.name);
				Predicate pred = builder.like(theJoin.get(Owner_.name),"%"+name+"%");
				criteria.select( builder.array( idPath, namePath ));
				criteria.where(pred);
				List<Object[]> valueArray = session.createQuery(criteria).getResultList();
				List<Item> items = new ArrayList<>();
				for ( Object[] values : valueArray ) {
						final Integer id = (Integer) values[0];
						final String str = (String) values[1];
						System.err.println(id+" "+str);
						Item item = new Item(id, str);
						items.add(item);
				}
				return items;				
    }
}
