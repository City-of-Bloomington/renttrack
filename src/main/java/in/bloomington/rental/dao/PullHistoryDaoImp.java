package in.bloomington.rental.dao;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import in.bloomington.rental.model.PullHistory;

@Repository
public class PullHistoryDaoImp implements PullHistoryDao{
		@Autowired
		private SessionFactory sessionFactory;
		int limit = 5;

		@Override
		public PullHistory get(int id) {
				return sessionFactory.getCurrentSession().get(PullHistory.class, id);
		}		
		@Override
		public void save(PullHistory pullHistory){
				sessionFactory.getCurrentSession().save(pullHistory);
		}
		@Override
    public void update(int id, PullHistory pullHistory) {
      Session session = sessionFactory.getCurrentSession();
      PullHistory pullHistory2 = session.byId(PullHistory.class).load(id);
			pullHistory2 = (PullHistory)session.merge(pullHistory);
			session.update(pullHistory2); // instead of saveOrUpdate
      session.flush();				
    }
 		
		@Override
    public void delete(int id) {
        PullHistory pullHistory = (PullHistory) sessionFactory.getCurrentSession().load(PullHistory.class, id);
        if (pullHistory != null) {
            this.sessionFactory.getCurrentSession().delete(pullHistory);
        }
    }
		@Override
		public List<PullHistory> getPullHistoryForRental(int rental_id){
				String qq = "from pull_history o where o.rental_id = :rental_id";
				Session session = sessionFactory.getCurrentSession();
				Criteria criteria = session.createCriteria(PullHistory.class);
				criteria.setMaxResults(limit);
        criteria.addOrder(Order.desc("id"));	
				Query query = session.createQuery(qq);
				query.setParameter("rental_id", rental_id);
				List<PullHistory> pullHistories = query.list();
				return pullHistories;
				
		}
		/*		
		@Override		
		public List<PullHistory> getAll(){
				Session session = sessionFactory.getCurrentSession();
				Criteria criteria = session.createCriteria(PullHistory.class);
				criteria.setMaxResults(limit);
        criteria.addOrder(Order.desc("id"));				
				return criteria.list();
		}
		//

		public List<PullHistory> findByName(String name){
				String qq = "from pullHistorys o where o.name like :name";
				Session session = sessionFactory.getCurrentSession();
				Query query = session.createQuery(qq);
				query.setParameter("name", name);
				List<PullHistory> pullHistorys = query.list();
				return pullHistorys;
		}
		*/		
		//
		/*
		public List<PullHistory> find(String name, String address, String city, String state, String zip, String email){
				List<PullHistory> pullHistorys = null;
				String qq = "from pullHistorys o where ";
				boolean and = false;
				if(name != null){
						qq += " name like: name ";
						and = true;
				}
				if(address != null){
						if(and) qq +=", ";
						qq += " address: address ";
						and = true;
				}
				if(city != null){
						if(and) qq +=", ";
						qq += " city: city ";
						and = true;
				}
				if(state != null){
						if(and) qq +=", ";
						qq += " state: state ";
						and = true;
				}				
				if(zip != null){
						if(and) qq +=", ";
						qq += " zip: zip ";
						and = true;
				}
				if(email != null){
						if(and) qq +=", ";
						qq += " email like: email ";
						and = true;
				}				
				//
				Session session = sessionFactory.getCurrentSession();
				Query query = session.createQuery(qq);				
				if(name != null){
						query.setParameter("name", name+"%");
				}
				if(address != null){
						query.setParameter("address", address);
				}
				if(city != null){
						query.setParameter("city", city);
				}
				if(state != null){
						query.setParameter("state", state);
				}				
				if(zip != null){
						query.setParameter("zip", zip);
				}
				if(email != null){
						query.setParameter("email", "%"+email+"%");
				}
				pullHistorys = query.list();
				return pullHistorys;
		}
		*/
		
}
