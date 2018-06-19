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
import in.bloomington.rental.model.AttachementSeq;


@Repository
public class AttachementSeqDaoImp implements AttachementSeqDao {

		int limit = 30;
		@Autowired
		private SessionFactory sessionFactory;

		@Override
		public AttachementSeq get(int id) {
				return sessionFactory.getCurrentSession().get(AttachementSeq.class, id);
		}
		@Override
		public void save(AttachementSeq attachementSeq) {
				sessionFactory.getCurrentSession().save(attachementSeq);
		}
		
}
