package in.bloomington.rental.dao;
import java.util.List;
import in.bloomington.rental.model.BuildingType;

public interface BuildingTypeDao {

		BuildingType get(int id);
		void save(BuildingType buildingType);
		void update(int id, BuildingType buildingType);
		void delete(int id);
		List<BuildingType> list();

}
