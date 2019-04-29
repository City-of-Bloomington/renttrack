package in.bloomington.rental.dao;

import java.util.List;

import in.bloomington.rental.model.InspectionTemplate;

public interface InspectionTemplateDao
{
    InspectionTemplate       get(int id);
    void                     save(InspectionTemplate inspectionTemplate);
    void                     update(int id, InspectionTemplate val);
    void                     delete(int id);
    List<InspectionTemplate> findByRentalId(int id);
}
