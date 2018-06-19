package in.bloomington.rental.service;

import java.util.List;

import in.bloomington.rental.model.Zoning;

public interface ZoningService {
		void save(Zoning val);
		void update(int id, Zoning val);

		void delete(int id);
		
		Zoning get(int id);

		List<Zoning> list();
		
}
