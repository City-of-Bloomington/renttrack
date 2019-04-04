package in.bloomington.rental.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import in.bloomington.rental.model.Bill;

public interface BillDao
{
    Bill       get(int id);
    void       save(Bill bill);
    void       update(int id, Bill bill);
    void       delete(int id);
    List<Bill> getAll();
}
