package in.bloomington.rental.dao;
import java.util.List;
import org.hibernate.SessionFactory;
import in.bloomington.rental.model.Receipt;

public interface ReceiptDao {

		Receipt get(int id);
		void save(Receipt receipt);
		void update(int id, Receipt receipt);
		void delete(int id);
		int getNextReceiptNo();
		List<Receipt> getAll();
		
				
}
