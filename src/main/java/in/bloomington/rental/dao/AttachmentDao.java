package in.bloomington.rental.dao;

import java.util.List;
import in.bloomington.rental.model.Attachment;

public interface AttachmentDao
{
    void save  (Attachment attachment);
    void delete(int        id);
    void delete(Attachment attachment);

    Attachment       get(int id);

    List<Attachment> findByRentalId    (Integer val);
    List<Attachment> findByInspectionId(Integer val);
}
