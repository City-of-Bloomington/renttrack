package in.bloomington.rental.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.bloomington.rental.model.Can;
import in.bloomington.rental.model.Can_;

@Repository
public class CanDaoImp implements CanDao
{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Can get(int id)
    {
        return sessionFactory.getCurrentSession().get(Can.class, id);
    }

    @Override
    public void save(Can can)
    {
        sessionFactory.getCurrentSession().save(can);
    }

    @Override
    public void update(int id, Can can)
    {
        Session session = sessionFactory.getCurrentSession();
        Can     can2    = session.byId(Can.class).load(id);
                can2    = (Can) session.merge(can);
        session.update(can2);
        session.flush();
    }

    @Override
    public void delete(int id)
    {
        Can can = (Can) sessionFactory.getCurrentSession().load(Can.class, id);
        if (can != null) {
            this.sessionFactory.getCurrentSession().delete(can);
        }
    }

    @Override
    public List<Can> getAll()
    {
        Session            session = sessionFactory.getCurrentSession();
        CriteriaBuilder    builder = session.getCriteriaBuilder();
        CriteriaQuery<Can> select = builder.createQuery(Can.class);
        Root<Can>            root = select.from(Can.class);

        select.orderBy(builder.desc(root.get(Can_.id)));

        return session.createQuery(select)
                      .getResultList();
    }

    // needed for auto_complete
    @Override
    public List<Can> findByName(String name)
    {
        Session            session = sessionFactory.getCurrentSession();
        CriteriaBuilder    builder = session.getCriteriaBuilder();
        CriteriaQuery<Can> select = builder.createQuery(Can.class);
        Root<Can>            root = select.from(Can.class);
        
        select.where(builder.like(root.get(Can_.title), name + "%"));
				select.orderBy(builder.desc(root.get(Can_.title)));
        return session.createQuery(select)
                      .getResultList();
    }
		/*
		 *
		 select u.fullname fullname,t.dt date,c.name,c.nws_name,
		 ((out_hour+out_minute/60) - (in_hour+in_minute/60)) hours 
		 from timeinterval t
		 join categories c on t.category_id=c.id
		 join users u on u.id = t.user_id
		 where c.name like 'FMLA%' and dt >='2018-01-01'
		 order by name,date
		 
		 select u.fullname fullname,t.dt date,c.name,c.nws_name,(out_hour+out_minute/60) - (in_hour+in_minute/60) from timeinterval t join categories c on t.category_id=c.id join users u on u.id = t.user_id where c.name like 'FMLA%' and dt >='2009-01-01' order by fullname,date INTO OUTFILE '/var/lib/mysql-files/fmla_times2.csv' FIELDS ENCLOSED BY '"' TERMINATED BY ',' ESCAPED BY '"' LINES TERMINATED BY '\r\n';
		 


		 */
		
}
