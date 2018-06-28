package in.bloomington.rental.dao;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import in.bloomington.rental.model.StandardFees;

@Repository
public class StandardFeesDaoImp implements StandardFeesDao{
		@Autowired
		private SessionFactory sessionFactory;
		int limit = 30;

		@Override
		public StandardFees get(int id){
				return sessionFactory.getCurrentSession().get(StandardFees.class, id);
		}
		@Override
		public void save(StandardFees standardFees){
				sessionFactory.getCurrentSession().save(standardFees);
		}
		@Override
    public void update(int id, StandardFees standardFees) {
      Session session = sessionFactory.getCurrentSession();
      StandardFees standardFees2 = session.byId(StandardFees.class).load(id);
			standardFees2 = (StandardFees)session.merge(standardFees);
			session.update(standardFees2); 
      session.flush();				
    }
 		
		@Override
    public void delete(int id) {
        StandardFees standardFees = (StandardFees) sessionFactory.getCurrentSession().load(
																																	 StandardFees.class, id);
        if (standardFees != null) {
            this.sessionFactory.getCurrentSession().delete(standardFees);
        }
    }
		@Override		
		public List<StandardFees> getAll(){
				Session session = sessionFactory.getCurrentSession();
				Criteria criteria = session.createCriteria(StandardFees.class);
				criteria.setMaxResults(limit);
        criteria.addOrder(Order.desc("id"));				
				return criteria.list();
		}

		
}
