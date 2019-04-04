package in.bloomington.rental.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import in.bloomington.rental.model.Variance;

public interface VarianceDao
{
    void save(Variance variance);
    void update(int id, Variance variance);
    void delete(int id);

    Variance get(int id);

    List<Variance> findByText(String name);
    List<Variance> getAll();
    List<Variance> find(String rental_id, String text, String dateFrom, String dateTo);
}
