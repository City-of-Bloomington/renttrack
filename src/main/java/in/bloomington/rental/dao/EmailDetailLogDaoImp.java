package in.bloomington.rental.dao;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import in.bloomington.rental.model.EmailDetailLog;

@Repository
public class EmailDetailLogDaoImp implements EmailDetailLogDao{
		@Autowired
		private SessionFactory sessionFactory;
		int limit = 30;

		@Override
		public EmailDetailLog get(int id){
				return sessionFactory.getCurrentSession().get(EmailDetailLog.class, id);
		}
		@Override
		public void save(EmailDetailLog val){
				sessionFactory.getCurrentSession().save(val);
		}

		@Override		
		public List<EmailDetailLog> getAll(){
				Session session = sessionFactory.getCurrentSession();
				Criteria criteria = session.createCriteria(EmailDetailLog.class);
				criteria.setMaxResults(limit);
        criteria.addOrder(Order.desc("id"));				
				return criteria.list();
		}
		
}
