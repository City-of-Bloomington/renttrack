package in.bloomington.rental.dao;

import java.util.List;
import in.bloomington.rental.model.RentalStatus;

public interface RentalStatusDao
{
    void save(RentalStatus rentalStatus);
    void update(int id, RentalStatus rentalStatus);
    void delete(int id);

    RentalStatus       get(int id);
    List<RentalStatus> list();
}
