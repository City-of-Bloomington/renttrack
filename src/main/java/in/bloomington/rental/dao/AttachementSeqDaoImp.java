package in.bloomington.rental.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import in.bloomington.rental.model.AttachementSeq;

@Repository
public class AttachementSeqDaoImp implements AttachementSeqDao
{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public AttachementSeq get(int id)
    {
        return sessionFactory.getCurrentSession()
                             .get(AttachementSeq.class, id);
    }

    @Override
    public void save(AttachementSeq attachementSeq)
    {
        sessionFactory.getCurrentSession()
                      .save(attachementSeq);
    }
}
