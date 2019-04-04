package in.bloomington.rental.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import in.bloomington.rental.model.EmailLog;

public interface EmailLogDao
{
    EmailLog       get   (int id);
    void           save  (EmailLog val);
    List<EmailLog> getAll();
}
