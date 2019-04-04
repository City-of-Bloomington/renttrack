package in.bloomington.rental.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import in.bloomington.rental.model.Inspection;

public interface InspectionDao
{
    Inspection get   (int id);
    void       save  (Inspection inspection);
    void       update(int id, Inspection inspection);
    void       delete(int id);

    List<Inspection> findByRentalId     (int id);
    int              findCountByRentalId(int id);
    List<Inspection> getAll();
}
