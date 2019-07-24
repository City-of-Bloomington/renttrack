package in.bloomington.rental.service;

import java.util.List;

import in.bloomington.rental.model.Owner;
import in.bloomington.rental.util.Report;

public interface ReportService
{

		public List<Owner> getAll();
		public List<Object[]> getOwnerWithNoEmail();
		public List<Object[]> getAgentWithNoEmail();		
		public List<Object[]> getInspectionReport(Report report);
		public List<Object[]> getRentalReport(Report report);
		public List<Object[]> getPullReport(Report report);
		public List<Object[]> getNoPullReport();
		public List<Object[]> getVarianceReport();
		public List<Object[]> getOverDueBillsReport(Report report);
		
}
