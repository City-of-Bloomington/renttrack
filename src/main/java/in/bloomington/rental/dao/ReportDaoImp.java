package in.bloomington.rental.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import in.bloomington.rental.model.Owner;
import in.bloomington.rental.util.Report;

@Repository
public class ReportDaoImp implements ReportDao
{
		@PersistenceContext
		EntityManager em;

    private int            limit = 30;

		// used for testing only
    @Override
    public List<Owner> getAll()
    {
				String qq = "SELECT owner from Owner owner where owner.id = ?1";
				System.err.println(" get doa all called ");
				try{
						List<Owner> owners = em.createQuery(qq)
								.setParameter(1, 7641)
								.getResultList();
						if(owners != null){
								for(Owner one:owners){
										System.err.println(one);
								}
								return owners;
						}
						else{
								System.err.println(" not found ");
						}
				}catch(Exception ex){
						System.err.println(ex);
				}
				return null;				

    }
    @Override
    public List<Object[]> getOwnerWithNoEmail()
    {
				String qq = "SELECT distinct(o.id),o.name,o.address,o.city,o.state,o.zip from owners o where o.email is null and o.id in (select ro.owner_id from rental_owners ro left join rentals r on r.id=ro.rental_id and r.inactive is null and r.permit_expires > ?) order by o.name";
				Report report = new Report();
				try{
						List<Object[]> results = em.createNativeQuery(qq)
								.setParameter(1, report.findTwoYearsAgoDate())
								// .setParameter(2, report.findTwoYearsAgoDate())
								.setMaxResults(30)
								.getResultList();
						if(results != null && results.size() > 0){
								return results;
						}
				}catch(Exception ex){
						System.err.println(ex);
				}
				return null;				

    }
    @Override
    public List<Object[]> getAgentWithNoEmail()
    {
				String qq = "SELECT distinct(o.id),o.name,o.address,o.city,o.state,o.zip from owners o JOIN rentals r on r.agent_id=o.id WHERE o.email is null and r.inactive is null and r.permit_expires > ? order by o.name";
				Report report = new Report();
				try{
						List<Object[]> results = em.createNativeQuery(qq)
								.setParameter(1, report.findTwoYearsAgoDate())
								.setMaxResults(30)
								.getResultList();
						if(results != null && results.size() > 0){
								return results;
						}
				}catch(Exception ex){
						System.err.println(ex);
				}
				return null;				

    }				
		@Override
		public List<Object[]> getInspectionReport(Report report){

				String qq = "SELECT i.id, i.rental_id,a.street_address,bt.name building_type,i.inspection_date,it.name inspection_type,i.compliance_date,i.violations,i.smoke_detectors,i.life_safety,u.full_name ";
				String qf = " FROM inspections i";
				qf += " LEFT JOIN inspection_types it on it.id=i.inspection_type_id ";
				qf += " JOIN users u on u.id=i.inspected_by ";				
				qf += " JOIN rentals r on r.id=i.rental_id ";
				qf += " LEFT JOIN rental_structures rt on rt.rental_id=r.id ";
				qf += " LEFT JOIN building_types bt on bt.id=rt.building_type_id ";
				qf += " LEFT JOIN addresses a on a.rental_id=r.id ";

				String qw = "";
				String dateFrom = report.getDateFrom();
				String dateTo = report.getDateTo();
				String violations = report.getViolations();
				Integer buildingTypeId = report.getBuildingTypeId();
				Integer inspectionTypeId = report.getInspectionTypeId();
				Integer inspectedBy = report.getInspectedBy();
				if(dateFrom != null && !dateFrom.equals("")){
						if(!qw.equals("")) qw += " and ";												
						qw += " i.inspection_date >= ? ";
				}
				if(dateTo != null && !dateTo.equals("")){
						if(!qw.equals("")) qw += " and ";
						qw += " i.inspection_date <= ? ";
				}
				if(violations != null && !violations.equals("")){
						if(!qw.equals("")) qw += " and ";
						qw += " i.violations > ? ";
				}
				if(buildingTypeId != null && buildingTypeId > 0){
						if(!qw.equals("")) qw += " and ";
						qw += " bt.id = ? ";
				}
				if(inspectionTypeId != null && inspectionTypeId > 0){
						if(!qw.equals("")) qw += " and ";
						qw += " i.inspection_type_id = ? ";
				}
				if(inspectedBy != null && inspectedBy > 0){
						if(!qw.equals("")) qw += " and ";
						qw += " i.inspected_by = ? ";
				}				
				if(!qw.equals("")){
						qw = " where "+qw;
				}
				qq += qf + qw;
				Query q = em.createNativeQuery(qq);
				int jj=1;
				if(dateFrom != null && !dateFrom.equals("")){
						q.setParameter(jj++, report.getDateFromTime());
				}
				if(dateTo != null && !dateTo.equals("")){
						q.setParameter(jj++, report.getDateToTime());
				}
				if(violations != null && !violations.equals("")){
						try{
								int viol = Integer.parseInt(violations);
								q.setParameter(jj++, viol);
						}catch(Exception ex){};
				}
				if(buildingTypeId != null && buildingTypeId > 0){
						q.setParameter(jj++, buildingTypeId);
				}
				if(inspectionTypeId != null && inspectionTypeId > 0){
						q.setParameter(jj++, inspectionTypeId);
				}
				if(inspectedBy != null && inspectedBy > 0){
						q.setParameter(jj++, inspectedBy);
				}
				List<Object[]> results = q.getResultList(); // for multiple
				return results;
		}
		@Override
		public List<Object[]> getRentalReport(Report report){
				Integer buildingTypeId = report.getBuildingTypeId();
				Integer propertyTypeId = report.getPropertyTypeId();	
				String dateFrom = report.getDateFrom();
				String dateTo = report.getDateTo();
				String unitsFrom = report.getUnitsFrom();
				String bedroomsFrom = report.getBedroomsFrom();
				String dateType = report.getDateType();
				boolean active_only = false;
				
				if(dateType != null && !dateType.equals("")){
						if(dateType.equals("registered")){
								dateType = "r.registered_date ";
						}
						else if(dateType.equals("expires")){
								dateType = "r.permit_expires ";
						}
						else if(dateType.equals("cycle")){
								dateType = "r.last_cycle_date ";
						}
				}
				else if((dateFrom != null && !dateFrom.equals(""))
								|| (dateTo != null &&  !dateTo.equals(""))){
						//
						// if dateFrom or dateTo were set but not dateType
						// then we set it to default
						//
						dateType = "r.registered_date ";
				}
				else{ // valid rentals
						// this means inactive is null and permit_expirss greter than
						// 2 years as renewal may take some time
						dateType = " r.permit_expires ";
						dateTo = "";dateFrom="";
						active_only = true;
				}
				//
				String qq = "SELECT r.id,r.registered_date,r.permit_expires,";
				qq += " a.street_address, ";
				qq += " o.name owner_name,o.address owner_address,";
				qq += " o.city owner_city,o.state owner_state,o.zip owner_zip,"; 
				qq += " ag.name agent_name,ag.address agent_address,ag.zip agent_zip,"; 
				qq += " bt.name building_type, pt.name property_type,"; 
				qq += " st.identifier building_num,"; 
				qq += " ru.identifier unit_num,ru.bedrooms,ru.occup_load, ";
				qq += " CASE WHEN ru.efficiency is not null THEN 'Efficiency' ELSE '' END efficiency, ";
				qq += " CASE WHEN ru.sleep_room is not null THEN 'Sleeping Room' ELSE '' END sleep_room";				
				//
				String qf =" FROM rentals r LEFT JOIN addresses a on a.rental_id=r.id ";
				qf += " LEFT JOIN owners ag on r.agent_id= ag.id ";
				qf += " LEFT JOIN rental_owners ro on ro.rental_id=r.id ";
				qf += " LEFT JOIN owners o on ro.owner_id=o.id ";
				qf += " LEFT JOIN rental_structures st on st.rental_id=r.id ";
				qf += " LEFT JOIN rental_units ru on ru.structure_id=st.id ";
				qf += " LEFT JOIN property_types pt on pt.id=st.prop_type_id ";
				qf += " LEFT JOIN building_types bt on bt.id=st.building_type_id ";
				String qw = "r.inactive is null ";
				if(buildingTypeId != null && buildingTypeId > 0){
						if(!qw.equals("")) qw += " and ";
						qw += " bt.id = ? ";
				}
				if(propertyTypeId != null &&  propertyTypeId > 0){
						if(!qw.equals("")) qw += " and ";
						qw += " pt.id = ? ";
				}
				if(dateFrom != null && !dateFrom.equals("")){
						if(!qw.equals("")) qw += " and ";
						qw += dateType+">= ? ";
				}
				if(dateTo != null && !dateTo.equals("")){
						if(!qw.equals("")) qw += " and ";
						qw += dateType+" <= ? ";
				}
				if(active_only){
						if(!qw.equals("")) qw += " and ";
						qw += dateType+">= ? ";
				}
				if(!qw.equals("")){
						qw = " where "+qw;
				}
				qq += qf + qw;
				Query q = em.createNativeQuery(qq);
				int jj=1;
				if(buildingTypeId != null && buildingTypeId > 0){
						q.setParameter(jj++, buildingTypeId);
				}
				if(propertyTypeId != null && propertyTypeId > 0){
						q.setParameter(jj++, propertyTypeId);
				}				
				if(dateFrom != null && !dateFrom.equals("")){						
						q.setParameter(jj++, report.getDateFromTime());
				}
				if(dateTo != null && !dateTo.equals("")){
						q.setParameter(jj++, report.getDateToTime());
				}
				if(active_only){
						q.setParameter(jj++, report.findTwoYearsAgoDate());
				}
				List<Object[]> results = q.getResultList(); 
				return results;
		}
		@Override
		public List<Object[]> getPullReport(Report report){
				Integer pullReasonId = report.getPullReasonId();
				String dateFrom = report.getDateFrom();
				String dateTo = report.getDateTo();
				//
				String qq = "SELECT r.id,pl.date pull_date,pr.reason pull_reason,";
				qq += " a.street_address, ";
				qq += " o.name owner_name,o.address owner_address,";
				qq += " o.city owner_city,o.state owner_state,o.zip owner_zip,"; 
				qq += " ag.name agent_name,ag.address agent_address,ag.zip agent_zip"; 
				//
				String qf =" FROM rentals r LEFT JOIN addresses a on a.rental_id=r.id ";
				qf += " LEFT JOIN owners ag on r.agent_id= ag.id ";
				qf += " LEFT JOIN rental_owners ro on ro.rental_id=r.id ";
				qf += " LEFT JOIN owners o on ro.owner_id=o.id ";
				qf += " JOIN pull_history pl on pl.rental_id=r.id ";
				qf += " JOIN pull_reasons pr on pl.pull_reason_id=pr.id ";
				String qw = "";
				if(pullReasonId != null && pullReasonId > 0){
						if(!qw.equals("")) qw += " and ";
						qw += " pr.id = ? ";
				}				
				if(dateFrom != null && !dateFrom.equals("")){
						if(!qw.equals("")) qw += " and ";
						qw += " pl.date >= ? ";
				}
				if(dateTo != null && !dateTo.equals("")){
						if(!qw.equals("")) qw += " and ";
						qw += " pl.date <= ? ";
				}
				if(!qw.equals("")){
						qw = " where "+qw;
				}
				qq += qf + qw;
				Query q = em.createNativeQuery(qq);
				int jj=1;
				if(pullReasonId != null && pullReasonId > 0){
						q.setParameter(jj++, pullReasonId);
				}
				if(dateFrom != null && !dateFrom.equals("")){						
						q.setParameter(jj++, report.getDateFromTime());
				}
				if(dateTo != null && !dateTo.equals("")){
						q.setParameter(jj++, report.getDateToTime());
				}
				List<Object[]> results = q.getResultList(); 
				return results;
		}
		@Override
		public List<Object[]> getNoPullReport(){
				//
				String qq = "SELECT r.id,";
				qq += " a.street_address, ";
				qq += " o.name owner_name,o.address owner_address,";
				qq += " o.city owner_city,o.state owner_state,o.zip owner_zip,"; 
				qq += " ag.name agent_name,ag.address agent_address,ag.zip agent_zip"; 
				//
				String qf =" FROM rentals r LEFT JOIN addresses a on a.rental_id=r.id ";
				qf += " LEFT JOIN owners ag on r.agent_id= ag.id ";
				qf += " LEFT JOIN rental_owners ro on ro.rental_id=r.id ";
				qf += " LEFT JOIN owners o on ro.owner_id=o.id ";
				String qw = "WHERE r.inactive is null ";
						qw += " and r.id not in (select rental_id from pull_history) ";
				qq += qf + qw;
				Query q = em.createNativeQuery(qq);
				List<Object[]> results = q.getResultList(); 
				return results;
		}
		@Override
		public List<Object[]> getVarianceReport(){
				//
				String qq = "SELECT r.id,a.street_address, ";
				qq += " v.variance,v.date varinace_date,";
				qq += " o.name owner_name,o.address owner_address,";
				qq += " o.city owner_city,o.state owner_state,o.zip owner_zip,"; 
				qq += " ag.name agent_name,ag.address agent_address,ag.zip agent_zip"; 
				//
				String qf =" FROM rentals r LEFT JOIN addresses a on a.rental_id=r.id ";
				qf += " LEFT JOIN owners ag on r.agent_id= ag.id ";
				qf += " LEFT JOIN rental_owners ro on ro.rental_id=r.id ";
				qf += " LEFT JOIN owners o on ro.owner_id=o.id ";
				qf += " JOIN variances v on v.rental_id=r.id ";
				String qw = " where r.inactive is null";
				qq += qf + qw;
				Query q = em.createNativeQuery(qq);
				List<Object[]> results = q.getResultList(); 
				return results;
		}
		public List<Object[]> getOverDueBillsReport(Report report){
				//
				String dateFrom = report.getDateFrom();
				String dateTo = report.getDateTo();				
				String qq = "SELECT distinct r.id rental_id,a.street_address, b.id bill_id,get_bill_balance(b.id) balance,b.issue_date, ";
				qq += " b.due_date,";
				qq += " o.name owner_name,o.address owner_address,";
				qq += " o.city owner_city,o.state owner_state,o.zip owner_zip,"; 
				qq += " ag.name agent_name,ag.address agent_address,ag.zip agent_zip"; 
				//
				String qf =" FROM rentals r LEFT JOIN addresses a on a.rental_id=r.id ";
				qf += " LEFT JOIN owners ag on r.agent_id= ag.id ";
				qf += " JOIN rental_owners ro on ro.rental_id=r.id ";
				qf += " JOIN owners o on ro.owner_id=o.id ";
				qf += " JOIN rental_bills b on b.rental_id=r.id ";
				String qw = " where r.inactive is null and b.status like 'Unpaid'";
				if(dateFrom != null && !dateFrom.equals(""))
						qw += " and b.due_date >= ? ";
				if(dateTo != null && !dateTo.equals(""))
						qw += " and b.due_date <= ? ";				
				qq += qf + qw;
				Query q = em.createNativeQuery(qq);
				int jj=1;
				if(dateFrom != null && !dateFrom.equals(""))				
						q.setParameter(jj++, report.getDateFromTime());
				if(dateTo != null && !dateTo.equals(""))
						q.setParameter(jj++, report.getDateToTime());
				List<Object[]> results = q.getResultList(); 
				return results;
		}		
		
		
		
}
