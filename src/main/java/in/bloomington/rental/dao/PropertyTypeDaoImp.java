package in.bloomington.rental.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import in.bloomington.rental.model.PropertyType;

@Repository
public class PropertyTypeDaoImp implements PropertyTypeDao {

   @Autowired
   private SessionFactory sessionFactory;

		@Override
   public PropertyType get(int id) {
      return sessionFactory.getCurrentSession().get(PropertyType.class, id);
   }
   @Override
   public void save(PropertyType propertyType) {
      sessionFactory.getCurrentSession().save(propertyType);
   }

		@Override
   public void update(int id, PropertyType propertyType) {
      Session session = sessionFactory.getCurrentSession();
      PropertyType propertyType2 = session.byId(PropertyType.class).load(id);
      propertyType2.setName(propertyType.getName());
      session.flush();
   }

		@Override
   public void delete(int id) {
      Session session = sessionFactory.getCurrentSession();
      PropertyType propertyType = session.byId(PropertyType.class).load(id);
      session.delete(propertyType);
   }		

   @Override
   public List<PropertyType> list() {
      @SuppressWarnings("unchecked")
      TypedQuery<PropertyType> query = sessionFactory.getCurrentSession().createQuery("from PropertyType");
      return query.getResultList();
   }

}
