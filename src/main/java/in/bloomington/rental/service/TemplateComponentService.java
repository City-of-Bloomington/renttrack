package in.bloomington.rental.service;
import java.util.List;
import in.bloomington.rental.model.TemplateComponent;
import java.util.List;

public interface TemplateComponentService {

		public TemplateComponent get(int id);
		public void save(TemplateComponent val);
		public void update(int id, TemplateComponent val);
		public void delete(int id);

}
