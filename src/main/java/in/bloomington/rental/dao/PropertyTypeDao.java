package in.bloomington.rental.dao;
import java.util.List;
import in.bloomington.rental.model.PropertyType;

public interface PropertyTypeDao {

		PropertyType get(int id);
		void save(PropertyType propertyType);
		void update(int id, PropertyType propertyType);
		void delete(int id);
		List<PropertyType> list();

}
