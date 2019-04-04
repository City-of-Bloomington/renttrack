package in.bloomington.rental.dao;

import java.util.List;
import in.bloomington.rental.model.RentalNote;

public interface RentalNoteDao
{
    void save(RentalNote rentalNote);
    void update(int id, RentalNote rentalNote);
    void delete(int id);

    RentalNote       get(int id);
    List<RentalNote> list();
    List<RentalNote> findByRentalId(int rental_id);
}
