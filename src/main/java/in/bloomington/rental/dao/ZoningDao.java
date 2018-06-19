package in.bloomington.rental.dao;
import java.util.List;
import in.bloomington.rental.model.Zoning;

public interface ZoningDao {

		Zoning get(int id);
		void save(Zoning zoning);
		void update(int id, Zoning zoning);
		void delete(int id);
		List<Zoning> list();

}
