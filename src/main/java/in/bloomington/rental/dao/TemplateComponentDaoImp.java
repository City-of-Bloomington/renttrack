package in.bloomington.rental.dao;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import in.bloomington.rental.model.TemplateComponent;

@Repository
public class TemplateComponentDaoImp implements TemplateComponentDao{
		@Autowired
		private SessionFactory sessionFactory;

		@Override
		public TemplateComponent get(int id){
				return sessionFactory.getCurrentSession().get(TemplateComponent.class, id);
		}
		@Override
		public void save(TemplateComponent templateComponent){
				sessionFactory.getCurrentSession().save(templateComponent);
		}
		@Override
    public void update(int id, TemplateComponent templateComponent) {
      Session session = sessionFactory.getCurrentSession();
      TemplateComponent templateComponent2 = session.byId(TemplateComponent.class).load(id);
			templateComponent2 = (TemplateComponent)session.merge(templateComponent);
			session.update(templateComponent2); 
      session.flush();				
    }
 		
		@Override
    public void delete(int id) {
        TemplateComponent templateComponent = (TemplateComponent) sessionFactory.getCurrentSession().load(
                TemplateComponent.class, id);
        if (templateComponent != null) {
            this.sessionFactory.getCurrentSession().delete(templateComponent);
        }
    }
		
}
