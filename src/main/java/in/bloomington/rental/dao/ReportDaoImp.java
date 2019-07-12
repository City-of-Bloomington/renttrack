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

@Repository
public class ReportDaoImp implements ReportDao
{
		@PersistenceContext
		EntityManager em;

    private int            limit = 30;

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
				String qq = "SELECT i.id, i.rental_id,a.streetAddress,bt.name,i.inspection_date,it.name,i.compliance_date,i.violations,i.smoke_detectors,i.life_safety,u.full_name ";
				String qf = " FROM Inspection i, Rental r, Address a, User u,InspectionType it, left join RentalStructure rt on rt.rental_id=r.id left join BuildingType bt on bt.id=rt.building_type_id ";
				String qw = " WHERE i.rental_id = r.id and a.rental_id=r.id and u.id=r.inspected_by and it.id=i.inspection_type_id ";
				String dateFrom = report.getDateFrom();
				String dateTo = report.getDateTo();
				Integer violations = report.getViolations();
				Integer buildingTypeId = report.getBuildingTypeId();
				Integer inspectionTypeId = report.getInspectionTypeId();				
				if(dateFrom != null && !dateFrom.equals("")){
						qw += " and i.inspection_date >= ? ";
				}
				if(dateTo != null && !dateTo.equals("")){
						qw += " and i.inspection_date <= ? ";
				}
				if(violations != null){
						qw += " and i.violations > ? ";
				}
				if(buildingTypeId != null){
						qw += " and bt.id = ? ";
				}
				if(inspectionTypeId != null){
						qw += " and i.inspection_type_id = ? ";
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
				if(violations != null){
						q.setParameter(jj++, violations);
				}
				if(buildingTypeId != null){
						q.setParameter(jj++, buildingTypeId);
				}
				if(inspectionTypeId != null){
						q.setParameter(jj++, inspectionTypeId);
				}				
				List<Object[]> results = q.getResultList(); // for multiple			
				return results;
		}
		
}
