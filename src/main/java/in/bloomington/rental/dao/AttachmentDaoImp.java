package in.bloomington.rental.dao;

import java.util.List;

import org.hibernate.Query;
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
        Session     session     = sessionFactory.getCurrentSession();
        Attachment attachment = session.byId(Attachment.class).load(id);
        session.delete(attachment);
    }

    @Override
    public void delete(Attachment attachment)
    {
        Session session = sessionFactory.getCurrentSession();
        if (attachment != null)
            session.delete(attachment);
    }

    @Override
    public List<Attachment> findByRentalId(Integer rentalId)
    {
        String  qq      = "from attachments where rentalId =:rentalId";
        Session session = sessionFactory.getCurrentSession();
        Query   query   = session.createQuery(qq);
        query.setParameter("rentalId", rentalId);
        return query.list();
    }

    @Override
    public List<Attachment> findByInspectionId(Integer val)
    {
        String  qq      = "from attachments where inspection.id=:inspectionId";
        Session session = sessionFactory.getCurrentSession();
        Query   query   = session.createQuery(qq);
        query.setParameter("inspectionId", val);
        return query.list();

    }
}
