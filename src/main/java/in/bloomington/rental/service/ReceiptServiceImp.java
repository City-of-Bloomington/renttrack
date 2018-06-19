package in.bloomington.rental.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import in.bloomington.rental.model.Receipt;
import in.bloomington.rental.dao.ReceiptDao;

@Service
public class ReceiptServiceImp implements ReceiptService{
		
		@Autowired
		private ReceiptDao receiptDao;
		
		@Transactional
		public void save(Receipt receipt){
				receiptDao.save(receipt);
		}
		@Transactional(readOnly = true)
		public Receipt get(int id){
				Receipt receipt = receiptDao.get(id);
        return  receipt;
		}

		@Transactional
    public void update(int id, Receipt receipt) {
        receiptDao.update(id, receipt);
    }
 		
		@Transactional
    public void delete(int id) {
				receiptDao.delete(id);
    }
		@Transactional(readOnly = true)		
		public int getNextReceiptNo(){
				return receiptDao.getNextReceiptNo();
		}

		@Transactional(readOnly = true)		
		public List<Receipt> getAll(){
        return receiptDao.getAll();
    }


}
