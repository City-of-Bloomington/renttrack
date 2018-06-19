package in.bloomington.rental.service;

import java.util.List;

import in.bloomington.rental.model.BuildingType;

public interface BuildingTypeService {
		void save(BuildingType buildingType);
		void update(int id, BuildingType buildingType);

		void delete(int id);
		
		BuildingType get(int id);
		
		List<BuildingType> list();

		
}
