package in.bloomington.rental.dao;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.bloomington.rental.model.Receipt;

@Repository
public class ReceiptDaoImp implements ReceiptDao
{
    @Autowired
    private SessionFactory sessionFactory;
    private int            limit = 30;

    @Override
    public Receipt get(int id)
    {
        Session session = sessionFactory.getCurrentSession();
        Receipt receipt = session.byId(Receipt.class).load(id);
        session.flush();
        return receipt;
    }

    @Override
    public void save(Receipt receipt)
    {
        sessionFactory.getCurrentSession().save(receipt);
    }

    @Override
    public void update(int id, Receipt receipt)
    {
        Session session  = sessionFactory.getCurrentSession();
        Receipt receipt2 = session.byId(Receipt.class).load(id);
                receipt2 = (Receipt) session.merge(receipt);
        session.update(receipt2); // instead of saveOrUpdate
        session.flush();
    }

    @Override
    public int getNextReceiptNo()
    {
        Session  session = sessionFactory.getCurrentSession();
				CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<Long> select = builder.createQuery(Long.class);
				Root<Receipt> root = select.from(Receipt.class);
				select.select(builder.max(root.<Long>get("receiptNo")));
				Long ret = session.createQuery(select)
						.getSingleResult();
				int nextVal = 1;
				if(ret != null){
						nextVal = ret.intValue()+1;
				}
				return nextVal;
    }

    @Override
    public void delete(int id)
    {
        Receipt receipt = (Receipt) sessionFactory.getCurrentSession()
                                                  .load(Receipt.class, id);
        if (receipt != null) {
            this.sessionFactory.getCurrentSession().delete(receipt);
        }
    }

    @Override
    public List<Receipt> getAll()
    {
        Session                  session = sessionFactory.getCurrentSession();
        CriteriaBuilder          builder = session.getCriteriaBuilder();
        CriteriaQuery<Receipt> select = builder.createQuery(Receipt.class);
        Root<Receipt>            root = select.from(Receipt.class);
        select.orderBy(builder.desc(root.get("id")));
        return session.createQuery(select)
                      .setMaxResults(limit)
                      .getResultList();
				
    }
}
