package in.bloomington.rental.dao;

import in.bloomington.rental.model.UnitRoom;

public interface UnitRoomDao
{
    void save(UnitRoom unitRoom);
    void update(int id, UnitRoom unitRoom);
    void delete(int id);

    UnitRoom get(int id);
}
