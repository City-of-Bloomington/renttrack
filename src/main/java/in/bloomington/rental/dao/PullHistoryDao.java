package in.bloomington.rental.dao;

import java.util.List;

import in.bloomington.rental.model.PullHistory;

public interface PullHistoryDao
{
    void save(PullHistory pullHistory);
    void update(int id, PullHistory pullHistory);
    void delete(int id);

    PullHistory       get(int id);
    List<PullHistory> getPullHistoryForRental(int rental_id);
}
