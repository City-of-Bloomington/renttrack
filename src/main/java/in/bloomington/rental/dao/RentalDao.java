package in.bloomington.rental.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import in.bloomington.rental.model.Rental;
import in.bloomington.rental.util.Search;

public interface RentalDao
{
    void save(Rental rental);
    void update(int id, Rental rental);
    void delete(int id);

    Rental get(int id);
    Rental getRentalWithNotes(int id);

    List<Rental> getAll();
    List<Rental> search(Search search);
    List<Rental> findExpireDate(String dateFrom, String dateTo);
}
