package in.bloomington.rental.dao;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import in.bloomington.rental.model.Receipt;

@Repository
public class ReceiptDaoImp implements ReceiptDao{
		@Autowired
		private SessionFactory sessionFactory;
		int limit = 30;

		@Override
		public Receipt get(int id){
				Session session = sessionFactory.getCurrentSession();
        Receipt receipt = session.byId(Receipt.class).load(id);
				session.flush();			
				return receipt;
		}
		@Override
		public void save(Receipt receipt){
				sessionFactory.getCurrentSession().save(receipt);
		}
		@Override
    public void update(int id, Receipt receipt) {
      Session session = sessionFactory.getCurrentSession();
      Receipt receipt2 = session.byId(Receipt.class).load(id);
			receipt2 = (Receipt)session.merge(receipt);
			session.update(receipt2); // instead of saveOrUpdate
      session.flush();				
    }
		@Override
		public int getNextReceiptNo(){
				Session session = sessionFactory.getCurrentSession();
				Criteria c = session.createCriteria(Receipt.class);
				c.addOrder(Order.desc("receiptNo"));
				c.setMaxResults(1);
				Receipt one = (Receipt)c.uniqueResult();
				int ret = 1;
				if(one != null){
						ret = one.getReceiptNo()+1;
				}
				return ret;
		}
 		
		@Override
    public void delete(int id) {
        Receipt receipt = (Receipt) sessionFactory.getCurrentSession().load(
                Receipt.class, id);
        if (receipt != null) {
            this.sessionFactory.getCurrentSession().delete(receipt);
        }
    }
		@Override		
		public List<Receipt> getAll(){
				Session session = sessionFactory.getCurrentSession();
				Criteria criteria = session.createCriteria(Receipt.class);
				criteria.setMaxResults(limit);
        criteria.addOrder(Order.desc("id"));				
				return criteria.list();
		}
		
}
