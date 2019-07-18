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
import in.bloomington.rental.util.ReportInspection;
import in.bloomington.rental.util.ReportRental;

@Repository
public class ReportDaoImp implements ReportDao
{
		@PersistenceContext
		EntityManager em;

    private int            limit = 30;

		// need visit 
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
		/**
			 String inspTitles[] = {
			 "Inspection",
			 "Rental ID",
			 "Address",
			 "Building type",
			 "Inspection Date",
			 "Inspection Type",
			 "Compliance Date",
			 "Violations",
			 "Smoke Detectors",
			 "Life Safety",
			 "Inspected by"
			 };
								String qy = "select insp.insp_id,insp.id,"+
													 " initcap(ad.street_num||' '||ad.street_dir||' '"+
										"||ad.street_name"+
										"||' '||ad.street_type||' '||ad.sud_type||' '||"+
										"ad.sud_num),"+
										" pd.building_type,"+ 
										"to_char(insp.inspection_date,'mm/dd/yyyy'),"+
										"it.insp_desc,to_char(insp.compliance_date,'mm/dd/yyyy'),"+
										"insp.violations,insp.smook_detectors,insp.life_safety,"+ // 7
										"initcap(ds.name)";
								String qf = " from inspections insp,registr pd,inspection_types it,"+
										" inspectors ds,address2 ad ";
								String qw =" where insp.id=pd.id and pd.id=ad.registr_id and "+
										"it.insp_type=insp.inspection_type and ds.initials=insp.inspected_by";
								if(!violations.equals("")){
										qw += " and insp.violations > "+violations;
								}
								if(!building_type.equals("")){
										qw += " and pd.building_type like '"+building_type+"'";
								}
								qs = " order by insp.id,insp.inspection_date "+sortby;

		 */
		@Override
		public List<Object[]> getInspectionReport(ReportInspection report){

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
				List<Object[]> results = q.getResultList(); // for multiple
				return results;
		}
		@Override
		public List<Object[]> getRentalReport(ReportRental report){
				Integer buildingTypeId = report.getBuildingTypeId();
				Integer propertyTypeId = report.getPropertyTypeId();	
				Integer pullReasonId = report.getPullReasonId();
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
				else{ // valid rentals
						// this means inactive is null and permit_expirss greter than
						// 2 years as renew may take some time
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
				System.err.println(qq);
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
}
