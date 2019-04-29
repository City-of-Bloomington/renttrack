package in.bloomington.rental.dao;

import java.util.List;
import in.bloomington.rental.model.RentalLog;

public interface RentalLogDao
{
    void save(RentalLog val);

    RentalLog       get(int id);
    List<RentalLog> findByRentalId(Integer val);
}
