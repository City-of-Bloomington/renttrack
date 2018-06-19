package in.bloomington.rental.service;
import java.util.List;
import in.bloomington.rental.model.Bill;
import java.util.List;

public interface BillService {

		public Bill get(int id);
		public void save(Bill val);
		public void update(int id, Bill val);
		public void delete(int id);

		public List<Bill> getAll();

}
