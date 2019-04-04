package in.bloomington.rental.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import in.bloomington.rental.model.RentalLegal;

public interface RentalLegalDao
{
    void save(RentalLegal val);

    RentalLegal get(int id);
}
