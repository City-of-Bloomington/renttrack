package in.bloomington.rental.service;

import java.util.List;

import in.bloomington.rental.model.Attachment;

public interface AttachmentService
{

    public void save  (Attachment attachment);
    public void delete(int        id        );
    public void delete(Attachment val       );

    public Attachment get(int id);
    
    public List<Attachment> findByRentalId    (Integer val);
    public List<Attachment> findByInspectionId(Integer val);
}
