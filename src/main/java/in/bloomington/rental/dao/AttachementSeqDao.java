package in.bloomington.rental.dao;
import in.bloomington.rental.model.AttachementSeq;

public interface AttachementSeqDao {

		AttachementSeq get(int id);
		void save(AttachementSeq attachementSeq);
		
}
