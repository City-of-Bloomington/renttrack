package in.bloomington.rental.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.bloomington.rental.model.Attachment;
import in.bloomington.rental.dao.AttachmentDao;

@Service
public class AttachmentServiceImp implements AttachmentService
{
    @Autowired
    private AttachmentDao attachmentDao;

    @Transactional
    public void save(Attachment attachment)
    {
        attachmentDao.save(attachment);
    }

    @Transactional(readOnly = true)
    public Attachment get(int id)
    {
        return attachmentDao.get(id);
    }

    @Transactional
    public void delete(int id)
    {
        attachmentDao.delete(id);
    }

    @Transactional
    public void delete(Attachment val)
    {
        attachmentDao.delete(val);
    }

    @Transactional(readOnly = true)
    public List<Attachment> findByRentalId(Integer val)
    {
        return attachmentDao.findByRentalId(val);
    }

    @Transactional(readOnly = true)
    public List<Attachment> findByInspectionId(Integer val)
    {
        return attachmentDao.findByInspectionId(val);
    }
}
