package in.bloomington.rental.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import in.bloomington.rental.model.InspectionTemplate;
import in.bloomington.rental.dao.InspectionTemplateDao;

@Service
public class InspectionTemplateServiceImp implements InspectionTemplateService{
		
		@Autowired
		private InspectionTemplateDao inspectionTemplateDao;
		
		@Transactional
		public void save(InspectionTemplate inspectionTemplate){
				inspectionTemplateDao.save(inspectionTemplate);
		}
		@Transactional(readOnly = true)
		public InspectionTemplate get(int id){
				InspectionTemplate inspectionTemplate = inspectionTemplateDao.get(id);
        return  inspectionTemplate;
		}

		@Transactional
    public void update(int id, InspectionTemplate inspectionTemplate) {
        inspectionTemplateDao.update(id, inspectionTemplate);
    }
 		
		@Transactional
    public void delete(int id) {
				inspectionTemplateDao.delete(id);
    }
		
		@Transactional(readOnly = true)		
		public List<InspectionTemplate> findByRentalId(int id){
        return inspectionTemplateDao.findByRentalId(id);
		}

		
}
