package in.bloomington.rental.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import in.bloomington.rental.model.BuildingType;

@Repository
public class BuildingTypeDaoImp implements BuildingTypeDao {

   @Autowired
   private SessionFactory sessionFactory;

		@Override
   public BuildingType get(int id) {
      return sessionFactory.getCurrentSession().get(BuildingType.class, id);
   }
   @Override
   public void save(BuildingType val) {
      sessionFactory.getCurrentSession().save(val);
   }
		
		@Override
   public void update(int id, BuildingType val) {
      Session session = sessionFactory.getCurrentSession();
      BuildingType val2 = session.byId(BuildingType.class).load(id);
      val2.setName(val.getName());
      session.flush();
   }

		@Override
   public void delete(int id) {
      Session session = sessionFactory.getCurrentSession();
      BuildingType val = session.byId(BuildingType.class).load(id);
      session.delete(val);
   }		

   @Override
   public List<BuildingType> list() {
      @SuppressWarnings("unchecked")
      TypedQuery<BuildingType> query = sessionFactory.getCurrentSession().createQuery("from BuildingType");
      return query.getResultList();
   }

}
