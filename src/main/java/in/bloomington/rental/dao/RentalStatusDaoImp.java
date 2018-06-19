package in.bloomington.rental.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import in.bloomington.rental.model.RentalStatus;

@Repository
public class RentalStatusDaoImp implements RentalStatusDao {

   @Autowired
   private SessionFactory sessionFactory;

		@Override
   public RentalStatus get(int id) {
      return sessionFactory.getCurrentSession().get(RentalStatus.class, id);
   }
   @Override
   public void save(RentalStatus val) {
      sessionFactory.getCurrentSession().save(val);
   }

		@Override
   public void update(int id, RentalStatus rentalStatus) {
      Session session = sessionFactory.getCurrentSession();
      RentalStatus val2 = session.byId(RentalStatus.class).load(id);
      val2.setName(rentalStatus.getName());
			val2.setAlias(rentalStatus.getAlias());			
      session.flush();
   }

		@Override
   public void delete(int id) {
      Session session = sessionFactory.getCurrentSession();
      RentalStatus val = session.byId(RentalStatus.class).load(id);
      session.delete(val);
   }		

   @Override
   public List<RentalStatus> list() {
      @SuppressWarnings("unchecked")
      TypedQuery<RentalStatus> query = sessionFactory.getCurrentSession().createQuery("from RentalStatus");
      return query.getResultList();
   }

}
