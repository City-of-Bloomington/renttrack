package in.bloomington.rental.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import in.bloomington.rental.model.Variance;
import in.bloomington.rental.dao.VarianceDao;

@Service
public class VarianceServiceImp implements VarianceService{
		
		@Autowired
		private VarianceDao varianceDao;

		@Transactional
		public void save(Variance variance){
				varianceDao.save(variance);
		}
		@Transactional(readOnly = true)
		public Variance get(int id){
        return  varianceDao.get(id);
		}

		@Transactional
    public void update(int id, Variance variance) {
        varianceDao.update(id, variance);
    }
 		
		@Transactional
    public void delete(int id) {
				varianceDao.delete(id);
    }

		@Transactional(readOnly = true)		
		public List<Variance> getAll(){
        return varianceDao.getAll();
    }

		@Transactional(readOnly = true)
		public List<Variance> findByText(String name){
				return varianceDao.findByText(name);
		}

		@Transactional(readOnly = true)
		public List<Variance> find(String rental_id, String text, String dateFrom, String dateTo){
				return varianceDao.find(rental_id, text, dateFrom, dateTo);
		}
		
}
