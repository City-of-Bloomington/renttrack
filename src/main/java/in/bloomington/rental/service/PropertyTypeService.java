package in.bloomington.rental.service;

import java.util.List;

import in.bloomington.rental.model.PropertyType;

public interface PropertyTypeService {
		void save(PropertyType propertyType);
		void update(int id, PropertyType propertyType);

		void delete(int id);
		
		PropertyType get(int id);
		
		List<PropertyType> list();

		
}
