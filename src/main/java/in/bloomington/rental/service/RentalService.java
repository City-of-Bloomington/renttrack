package in.bloomington.rental.service;

import java.util.List;

import in.bloomington.rental.model.Rental;
import in.bloomington.rental.util.Search;

public interface RentalService {

		public Rental get(int id);
		public void save(Rental rental);
		public void update(int id, Rental rental);
		public void delete(int id);

		// needed for search
		public List<Rental> getAll();
		public List<Rental> search(Search search);
		public List<Rental> findExpireDate(String dateFrom, String dateTo);
		/*
		public List<Rental> findByName(String name);
		public List<Rental> find(String name, String address, String city, String state, String zip, String email);
		*/				
}
