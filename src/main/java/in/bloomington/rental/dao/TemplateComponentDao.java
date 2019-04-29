package in.bloomington.rental.dao;

import in.bloomington.rental.model.TemplateComponent;

public interface TemplateComponentDao
{
    void save(TemplateComponent val);
    void update(int id, TemplateComponent val);
    void delete(int id);

    TemplateComponent get(int id);
}
