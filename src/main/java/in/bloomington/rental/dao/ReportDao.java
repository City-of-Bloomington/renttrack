package in.bloomington.rental.dao;

import java.util.List;
import in.bloomington.rental.model.Owner;
import in.bloomington.rental.util.Report;

public interface ReportDao
{
    public List<Owner> getAll();
		public List<Object[]> getInspectionReport(Report report);
		public List<Object[]> getRentalReport(Report report);
		public List<Object[]> getPullReport(Report report);
		
}
