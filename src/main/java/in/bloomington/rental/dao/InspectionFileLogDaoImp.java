package in.bloomington.rental.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.bloomington.rental.model.InspectionFileLog;
import in.bloomington.rental.model.InspectionFileLog_;
import in.bloomington.rental.model.Rental;

@Repository
public class InspectionFileLogDaoImp implements InspectionFileLogDao
{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public InspectionFileLog get(int id)
    {
        return sessionFactory.getCurrentSession()
                             .get(InspectionFileLog.class, id);
    }

    @Override
    public void save(InspectionFileLog val)
    {
        sessionFactory.getCurrentSession()
                      .save(val);
    }

    @Override
    public List<InspectionFileLog> findByRentalId(Integer rentalId)
    {
        Session             session = sessionFactory.getCurrentSession();
        CriteriaBuilder     builder = session.getCriteriaBuilder();
        CriteriaQuery<InspectionFileLog> select = builder.createQuery(InspectionFileLog.class);
        Root<InspectionFileLog>            root = select.from(InspectionFileLog.class);
        
        select.where  (builder.equal(root.get(InspectionFileLog_.rentalId), rentalId));
        select.orderBy(builder.desc (root.get("id")));
        
        return session.createQuery(select)
                      .getResultList();
    }

    @Override
    public int findCountByRentalId(int id)
    {
        Session session = sessionFactory.getCurrentSession();
				CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<Long> cq = builder.createQuery(Long.class);
				Root<InspectionFileLog> root = cq.from(InspectionFileLog.class);
				cq.select(builder.count(root));
				cq.where (builder.equal(root.get(InspectionFileLog_.rentalId), id));
				Long cnt = session.createQuery(cq).getSingleResult();
				if(cnt  != null){
						return cnt.intValue();
				}
				return 0;
				
    }
}
