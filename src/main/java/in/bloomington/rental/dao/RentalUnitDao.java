package in.bloomington.rental.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import in.bloomington.rental.model.RentalUnit;

public interface RentalUnitDao
{
    void save(RentalUnit rentalUnit);
    void update(int id, RentalUnit rentalUnit);
    void delete(int id);
    void updateAddressInfo(int id, int addressId);

    RentalUnit       get(int id);
    List<RentalUnit> getAll();
    List<RentalUnit> findByStructureId(Integer structureId);
}
