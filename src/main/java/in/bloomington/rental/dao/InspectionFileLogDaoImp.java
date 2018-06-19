package in.bloomington.rental.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import in.bloomington.rental.model.InspectionFileLog;

@Repository
public class InspectionFileLogDaoImp implements InspectionFileLogDao {

		int limit = 30;
		@Autowired
		private SessionFactory sessionFactory;

		@Override
		public InspectionFileLog get(int id) {
				return sessionFactory.getCurrentSession().get(InspectionFileLog.class, id);
		}
		@Override
		public void save(InspectionFileLog val) {
				sessionFactory.getCurrentSession().save(val);
		}

		@Override
		public List<InspectionFileLog> findByRentalId(Integer rentalId) {
				String qq = "from InspectionFileLog l where l.rentalId =:rentalId order by l.id desc";
				Session session = sessionFactory.getCurrentSession();
				Query query = session.createQuery(qq);
				// query.setFirstResult(0); // for paging
				// query.setMaxResults(limit);
				query.setParameter("rentalId", rentalId);
				return query.list();
		}
		@Override
		public int findCountByRentalId(int id){
				Session session = sessionFactory.getCurrentSession();
				Query query = session.createQuery("select COUNT(i) from InspectionFileLog i where i.rentalId=:rental_id");
				int cnti = 0;
				try{
						query.setInteger("rental_id",id);
						Long cnt = (Long)query.getSingleResult();
						cnti = cnt.intValue();
				}catch(Exception ex){
						System.err.println(" inspection "+ex);
				}
				return cnti;				
		}
		
		
}
