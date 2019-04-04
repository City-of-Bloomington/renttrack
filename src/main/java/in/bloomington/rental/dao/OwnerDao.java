package in.bloomington.rental.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import in.bloomington.rental.model.Owner;
import in.bloomington.rental.model.Item;
import in.bloomington.rental.model.RentalOwner;

public interface OwnerDao
{
    Owner       get(int id);
    RentalOwner getRentalOwner(int id);
    void        save(Owner val);
    void        save(RentalOwner val);
    void        update(int id, Owner val);
    void        delete(int id);
    void        removeRentalOwner(int id);

    // needed for search
    List<Owner> getAll();
    List<Owner> findByName(String name);
    List<Owner> findAgentByName(String name);
    List<Owner> search(Owner val);
    List<Owner> findOwnersForExpireEmail(String startDate, String endDate);
    List<Owner> find(String name, String address, String city, String state, String zip, String email);

    List<RentalOwner> getAllForOwner(int owner_id);
    List<RentalOwner> getAllForRental(int rental_id);
    List<Item>        getList(String name);
    List<Item>        getAgentList(String name);
}
