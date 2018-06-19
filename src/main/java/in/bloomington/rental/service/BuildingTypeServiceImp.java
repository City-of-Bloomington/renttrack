package in.bloomington.rental.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.bloomington.rental.dao.BuildingTypeDao;
import in.bloomington.rental.model.BuildingType;

@Service
public class BuildingTypeServiceImp implements BuildingTypeService {

		@Autowired
		private BuildingTypeDao buildingTypeDao;

		@Transactional
		public void save(BuildingType buildingType) {
				buildingTypeDao.save(buildingType);
		}

		@Transactional
		public void update(int id, BuildingType buildingType) {
			 buildingTypeDao.update(id, buildingType);
   }

		@Transactional
		public void delete(int id) {
				buildingTypeDao.delete(id);
		}

		@Transactional(readOnly = true)
		public BuildingType get(int id) {
				return buildingTypeDao.get(id);
		}		

		@Transactional(readOnly = true)
		public List<BuildingType> list() {
				return buildingTypeDao.list();
   }

}
