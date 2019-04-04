package in.bloomington.rental.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import in.bloomington.rental.model.Egress;

public interface EgressDao
{
    Egress       get(int id);
    void         save(Egress val);
    void         update(int id, Egress egress);
    void         delete(int id);

    List<Egress> findByYear(int year);
    List<Egress> getAll();
}
