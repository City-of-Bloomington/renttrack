package in.bloomington.rental.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import in.bloomington.rental.model.LegalItEmailLog;


@Repository
public class LegalItEmailLogDaoImp implements LegalItEmailLogDao {

		int limit = 10;
		@Autowired
		private SessionFactory sessionFactory;

		@Override
		public LegalItEmailLog get(int id) {
				return sessionFactory.getCurrentSession().get(LegalItEmailLog.class, id);
		}
		@Override
		public void save(LegalItEmailLog val) {
				sessionFactory.getCurrentSession().save(val);
		}

		@Override
		public List<LegalItEmailLog> findByRentalId(Integer rentalId) {
				String qq = "from LegalItEmailLog l where l.rentalId =:rentalId order by l.id";
				Session session = sessionFactory.getCurrentSession();
				Query query = session.createQuery(qq);
				// query.setFirstResult(0); // for paging
				query.setMaxResults(limit);
				query.setParameter("rentalId", rentalId);
				return query.list();
		}
		
}
