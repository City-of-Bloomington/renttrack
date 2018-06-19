package in.bloomington.rental.dao;
import java.util.List;
import in.bloomington.rental.model.OwnerPhone;

public interface OwnerPhoneDao {

		OwnerPhone get(int id);
		void save(OwnerPhone ownerPhone);
		void update(int id, OwnerPhone val);
		void delete(int id);
		List<OwnerPhone> list();
		List<OwnerPhone> findByOwnerId(int owner_id);

}
