package in.bloomington.rental.service;

import java.util.List;

import in.bloomington.rental.model.InspectionType;

public interface InspectionTypeService {
		void save(InspectionType val);
		void update(int id, InspectionType val);

		void delete(int id);
		
		InspectionType get(int id);

		List<InspectionType> list();
		
}
