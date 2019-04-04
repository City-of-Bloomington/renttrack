package in.bloomington.rental.service;

import java.util.List;

import in.bloomington.rental.model.Receipt;

public interface ReceiptService {

		public Receipt get(int id);
		public void save(Receipt val);
		public void update(int id, Receipt val);
		public void delete(int id);
		public int getNextReceiptNo();
		public List<Receipt> getAll();

}
