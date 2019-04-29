package in.bloomington.rental.dao;

import java.util.List;
import in.bloomington.rental.model.InspectionFileLog;

public interface InspectionFileLogDao
{
    InspectionFileLog       get(int id);
    void                    save(InspectionFileLog val);
    List<InspectionFileLog> findByRentalId(Integer val);
    int                     findCountByRentalId(int id);
}
