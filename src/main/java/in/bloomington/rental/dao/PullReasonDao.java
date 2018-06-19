package in.bloomington.rental.dao;
import java.util.List;
import in.bloomington.rental.model.PullReason;

public interface PullReasonDao {

		PullReason get(int id);
		void save(PullReason pullReason);
		void update(int id, PullReason pullReason);
		void delete(int id);
		List<PullReason> list();

}
