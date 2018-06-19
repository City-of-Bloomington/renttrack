package in.bloomington.rental.service;

import java.util.List;

import in.bloomington.rental.model.PullReason;

public interface PullReasonService {
		void save(PullReason val);
		void update(int id, PullReason val);

		void delete(int id);
		
		PullReason get(int id);

		List<PullReason> list();
		
}
