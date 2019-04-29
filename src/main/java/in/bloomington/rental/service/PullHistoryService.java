package in.bloomington.rental.service;

import java.util.List;

import in.bloomington.rental.model.PullHistory;

public interface PullHistoryService {

		public PullHistory get(int id);
		public void save(PullHistory pullHistory);
		public void update(int id, PullHistory pullHistory);
		public void delete(int id);

		public List<PullHistory> getPullHistoryForRental(int rental_id);

}
