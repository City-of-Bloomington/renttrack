package in.bloomington.rental.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.bloomington.rental.dao.PropertyTypeDao;
import in.bloomington.rental.model.PropertyType;

@Service
public class PropertyTypeServiceImp implements PropertyTypeService {

		@Autowired
		private PropertyTypeDao propertyTypeDao;

		@Transactional
		public void save(PropertyType propertyType) {
				propertyTypeDao.save(propertyType);
		}

		@Transactional
		public void update(int id, PropertyType propertyType) {
			 propertyTypeDao.update(id, propertyType);
   }

		@Transactional
		public void delete(int id) {
				propertyTypeDao.delete(id);
		}

		@Transactional(readOnly = true)
		public PropertyType get(int id) {
				return propertyTypeDao.get(id);
		}		

		@Transactional(readOnly = true)
		public List<PropertyType> list() {
				return propertyTypeDao.list();
   }

}
