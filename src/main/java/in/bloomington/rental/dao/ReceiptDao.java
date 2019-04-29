package in.bloomington.rental.dao;

import java.util.List;

import in.bloomington.rental.model.Receipt;

public interface ReceiptDao
{
    void save(Receipt receipt);
    void update(int id, Receipt receipt);
    void delete(int id);

    int           getNextReceiptNo();
    Receipt       get(int id);
    List<Receipt> getAll();
}
