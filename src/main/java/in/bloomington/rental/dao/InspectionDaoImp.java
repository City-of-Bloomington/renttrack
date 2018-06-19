package in.bloomington.rental.dao;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import in.bloomington.rental.model.Inspection;

@Repository
public class InspectionDaoImp implements InspectionDao{
		@Autowired
		private SessionFactory sessionFactory;
		int limit = 5;

		@Override
		public Inspection get(int id){
				return sessionFactory.getCurrentSession().get(Inspection.class, id);
		}
		@Override
		public void save(Inspection inspection){
				sessionFactory.getCurrentSession().save(inspection);
		}
		@Override
    public void update(int id, Inspection inspection) {
      Session session = sessionFactory.getCurrentSession();
      Inspection inspection2 = session.byId(Inspection.class).load(id);
			inspection2 = (Inspection)session.merge(inspection);
			session.update(inspection2); 
      session.flush();				
    }
 		
		@Override
    public void delete(int id) {
        Inspection inspection = (Inspection) sessionFactory.getCurrentSession().load(
                Inspection.class, id);
        if (inspection != null) {
            this.sessionFactory.getCurrentSession().delete(inspection);
        }
    }
		@Override		
		public List<Inspection> getAll(){
				Session session = sessionFactory.getCurrentSession();
				Criteria criteria = session.createCriteria(Inspection.class);
				criteria.setMaxResults(limit);
        criteria.addOrder(Order.desc("id"));				
				return criteria.list();
		}
		@Override
		public List<Inspection> findByRentalId(int id){
				String qq = "from Inspection i where i.rental.id = :rentalId order by i.id desc ";
				Session session = sessionFactory.getCurrentSession();
				Query query = session.createQuery(qq);
				query.setParameter("rentalId", id);
				List<Inspection> inspections = query.list();
				return inspections;

		}
		@Override
		public int findCountByRentalId(int id){
				Session session = sessionFactory.getCurrentSession();
				Query query = session.createQuery("select COUNT(i) from Inspection i where i.rental.id=:rental_id");
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
