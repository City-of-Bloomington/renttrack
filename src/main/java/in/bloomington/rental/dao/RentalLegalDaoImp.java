package in.bloomington.rental.dao;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import in.bloomington.rental.model.RentalLegal;

@Repository
public class RentalLegalDaoImp implements RentalLegalDao{
		@Autowired
		private SessionFactory sessionFactory;
		int limit = 30;

		@Override
		public RentalLegal get(int id){
				return sessionFactory.getCurrentSession().get(RentalLegal.class, id);
		}
		@Override
		public void save(RentalLegal val){
				sessionFactory.getCurrentSession().save(val);
		}

}
