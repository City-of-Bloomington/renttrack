package in.bloomington.rental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.bloomington.rental.dao.TemplateComponentDao;
import in.bloomington.rental.model.TemplateComponent;

@Service
public class TemplateComponentServiceImp implements TemplateComponentService{
		
		@Autowired
		private TemplateComponentDao templateComponentDao;
		
		@Transactional
		public void save(TemplateComponent val){
				templateComponentDao.save(val);
		}
		@Transactional(readOnly = true)
		public TemplateComponent get(int id){
				TemplateComponent templateComponent = templateComponentDao.get(id);
        return  templateComponent;
		}

		@Transactional
    public void update(int id, TemplateComponent templateComponent) {
        templateComponentDao.update(id, templateComponent);
    }
 		
		@Transactional
    public void delete(int id) {
				templateComponentDao.delete(id);
    }
		
}
