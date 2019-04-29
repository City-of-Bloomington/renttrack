package in.bloomington.rental.dao;

import java.util.List;
import in.bloomington.rental.model.PullReason;

public interface PullReasonDao
{
    void save(PullReason pullReason);
    void update(int id, PullReason pullReason);
    void delete(int id);

    PullReason       get(int id);
    List<PullReason> list();
}
