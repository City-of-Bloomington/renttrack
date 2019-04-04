package in.bloomington.rental.service;

import java.util.List;

import in.bloomington.rental.model.Egress;

public interface EgressService {

		public Egress get(int id);
		public void save(Egress val);
		public void update(int id, Egress val);
		public void delete(int id);

		public List<Egress> getAll();
		public List<Egress> findByYear(int val);
}
