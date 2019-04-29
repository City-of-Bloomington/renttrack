package in.bloomington.rental.dao;

import java.util.List;
import in.bloomington.rental.model.Zoning;

public interface ZoningDao
{
		void save(Zoning zoning);
		void update(int id, Zoning zoning);
		void delete(int id);

		Zoning       get(int id);
		List<Zoning> list();
}
