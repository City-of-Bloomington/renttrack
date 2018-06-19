package in.bloomington.rental.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import in.bloomington.rental.model.Zoning;

@Repository
public class ZoningDaoImp implements ZoningDao {

   @Autowired
   private SessionFactory sessionFactory;

		@Override
   public Zoning get(int id) {
      return sessionFactory.getCurrentSession().get(Zoning.class, id);
   }
   @Override
   public void save(Zoning val) {
      sessionFactory.getCurrentSession().save(val);
   }

		@Override
   public void update(int id, Zoning val) {
      Session session = sessionFactory.getCurrentSession();
      Zoning val2 = session.byId(Zoning.class).load(id);
      val2.setName(val.getName());
			val2.setAlias(val.getAlias());			
      session.flush();
   }

		@Override
   public void delete(int id) {
      Session session = sessionFactory.getCurrentSession();
      Zoning val = session.byId(Zoning.class).load(id);
      session.delete(val);
   }		

   @Override
   public List<Zoning> list() {
      @SuppressWarnings("unchecked")
      TypedQuery<Zoning> query = sessionFactory.getCurrentSession().createQuery("from Zoning");
      return query.getResultList();
   }

}
