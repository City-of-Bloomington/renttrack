package in.bloomington.rental.dao;

import java.util.List;
import in.bloomington.rental.model.Owner;
import in.bloomington.rental.util.ReportInspection;

public interface ReportDao
{
    public List<Owner> getAll();
		public List<Object[]> getInspectionReport(ReportInspection report);
		
}
