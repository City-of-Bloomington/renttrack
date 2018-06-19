package in.bloomington.rental.dao;
import java.util.List;
import org.hibernate.SessionFactory;
import in.bloomington.rental.model.RentalLegal;

public interface RentalLegalDao {

		RentalLegal get(int id);
		void save(RentalLegal val);
				
}
