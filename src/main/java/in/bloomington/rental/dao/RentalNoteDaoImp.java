package in.bloomington.rental.dao;

import java.util.List;
import java.util.Date;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.bloomington.rental.model.Rental;
import in.bloomington.rental.model.RentalNote;
import in.bloomington.rental.model.RentalNote_;
@Repository
public class RentalNoteDaoImp implements RentalNoteDao
{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public RentalNote get(int id)
    {
        return sessionFactory.getCurrentSession().get(RentalNote.class, id);
    }

    @Override
    public void save(RentalNote val)
    {
				val.setDate(new Date());
        Session session = sessionFactory.getCurrentSession();
        session.save(val);
        Rental rental = val.getRental();
        session.flush();
    }

    @Override
    public void update(int id, RentalNote val)
    {
        Session    session = sessionFactory.getCurrentSession();
        RentalNote val2    = session.byId(RentalNote.class).load(id);
        val2 = (RentalNote) session.merge(val);
        session.update(val2);
        session.flush();
    }

    @Override
    public void delete(int id)
    {
        Session    session = sessionFactory.getCurrentSession();
        RentalNote val     = session.byId(RentalNote.class).load(id);
        session.delete(val);
    }

    @Override
    public List<RentalNote> list()
    {
				Session session = sessionFactory.getCurrentSession();				
				CriteriaBuilder          builder = session.getCriteriaBuilder();
        CriteriaQuery<RentalNote> select = builder.createQuery(RentalNote.class);
        Root<RentalNote>            root = select.from(RentalNote.class);
        
        select.orderBy(builder.desc (root.get(RentalNote_.id)));
        return session.createQuery(select)
                      .getResultList();
				
    }

    @Override
    public List<RentalNote> findByRentalId(int rental_id)
    {
				Session session = sessionFactory.getCurrentSession();
				CriteriaBuilder          builder = session.getCriteriaBuilder();
        CriteriaQuery<RentalNote> select = builder.createQuery(RentalNote.class);
        Root<RentalNote>            root = select.from(RentalNote.class);
        select.where  (builder.equal(root.get(RentalNote_.rental), rental_id));
        select.orderBy(builder.desc (root.get(RentalNote_.id)));
        return session.createQuery(select)
                      .getResultList();
				
    }
}
