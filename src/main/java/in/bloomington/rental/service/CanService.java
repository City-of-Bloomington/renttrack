package in.bloomington.rental.service;
import java.util.List;
import in.bloomington.rental.model.Can;
import java.util.List;

public interface CanService {

		public Can get(int id);
		public void save(Can val);
		public void update(int id, Can val);
		public void delete(int id);

		public List<Can> getAll();
		public List<Can> findByName(String val);
}
