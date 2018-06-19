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
import in.bloomington.rental.model.Attachement;


@Repository
public class AttachementDaoImp implements AttachementDao {

		int limit = 30;
		@Autowired
		private SessionFactory sessionFactory;

		@Override
		public Attachement get(int id) {
				return sessionFactory.getCurrentSession().get(Attachement.class, id);
		}
		@Override
		public void save(Attachement attachement) {
				sessionFactory.getCurrentSession().save(attachement);
		}

		@Override
		public void delete(int id) {
				Session session = sessionFactory.getCurrentSession();
				Attachement attachement = session.byId(Attachement.class).load(id);
				session.delete(attachement);
		}
		@Override
		public void delete(Attachement attachement) {
				Session session = sessionFactory.getCurrentSession();
				if(attachement != null)
						session.delete(attachement);
		}		
		@Override
		public List<Attachement> findByRentalId(Integer rentalId) {
				String qq = "from Attachement where rentalId =:rentalId";
				Session session = sessionFactory.getCurrentSession();
				Query query = session.createQuery(qq);
				query.setParameter("rentalId", rentalId);
				return query.list();
		}
		@Override
		public List<Attachement> findByInspectionId(Integer val){
				String qq = "from Attachement where inspection.id=:inspectionId";
				Session session = sessionFactory.getCurrentSession();
				Query query = session.createQuery(qq);
				query.setParameter("inspectionId", val);
				return query.list();				

		}

		
}
