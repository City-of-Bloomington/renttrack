package in.bloomington.rental.dao;

import java.util.List;

import in.bloomington.rental.model.Can;

public interface CanDao
{
    Can       get(int id);
    void      save(Can can);
    void      update(int id, Can can);
    void      delete(int id);

    List<Can> findByName(String str);
    List<Can> getAll();
}
