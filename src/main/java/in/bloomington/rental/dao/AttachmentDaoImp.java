package in.bloomington.rental.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.bloomington.rental.model.Attachment;

@Repository
public class AttachmentDaoImp implements AttachmentDao
{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Attachment get(int id)
    {
        return sessionFactory.getCurrentSession().get(Attachment.class, id);
    }

    @Override
    public void save(Attachment attachment)
    {
        sessionFactory.getCurrentSession().save(attachment);
    }

    @Override
    public void delete(int id)
    {
        Session    session    = sessionFactory.getCurrentSession();
        Attachment attachment = session.byId(Attachment.class).load(id);
        session.delete(attachment);
    }

    @Override
    public void delete(Attachment attachment)
    {
        if (attachment != null) {
            Session session = sessionFactory.getCurrentSession();
            session.delete(attachment);
        }
    }

    @Override
    public List<Attachment> findByRentalId(Integer rentalId)
    {
        Session                 session = sessionFactory.getCurrentSession();
        CriteriaBuilder          builder = session.getCriteriaBuilder();
        CriteriaQuery<Attachment> select = builder.createQuery(Attachment.class);
        Root<Attachment>            root = select.from(Attachment.class);
        
        select.where(builder.equal(root.get("rental_id"), rentalId));
        
        return session.createQuery(select)
                      .getResultList();
    }

    @Override
    public List<Attachment> findByInspectionId(Integer id)
    {
        Session                 session = sessionFactory.getCurrentSession();
        CriteriaBuilder          builder = session.getCriteriaBuilder();
        CriteriaQuery<Attachment> select = builder.createQuery(Attachment.class);
        Root<Attachment>            root = select.from(Attachment.class);
        
        select.where(builder.equal(root.get("inspection_id"), id));
        
        return session.createQuery(select)
                      .getResultList();
    }
}
