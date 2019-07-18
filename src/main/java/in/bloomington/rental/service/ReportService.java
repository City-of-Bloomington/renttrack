package in.bloomington.rental.service;

import java.util.List;

import in.bloomington.rental.model.Owner;
import in.bloomington.rental.util.ReportInspection;
import in.bloomington.rental.util.ReportRental;

public interface ReportService
{

		public List<Owner> getAll();
		public List<Object[]> getInspectionReport(ReportInspection report);
		public List<Object[]> getRentalReport(ReportRental report);
		
}
