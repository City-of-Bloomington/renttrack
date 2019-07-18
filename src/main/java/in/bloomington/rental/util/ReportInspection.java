package in.bloomington.rental.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class ReportInspection
{

    SimpleDateFormat dtf       = new SimpleDateFormat("MM/dd/yyyy");
		String dateFrom="", dateTo="";
		String violations="";
		Integer buildingTypeId, inspectionTypeId;

		public ReportInspection(String dateFrom,
														String dateTo,
														String violations,
														Integer buildingTypeId,
														Integer inspectionTypeId){
				this.dateFrom = dateFrom;
				this.dateTo = dateTo;
				this.violations = violations;
				this.buildingTypeId = buildingTypeId;
				this.inspectionTypeId = inspectionTypeId;
		}
    public ReportInspection()
    {
    }

    public String getViolations()
    {
        return violations;
    }

    public void setViolations(String val)
    {
        violations = val;
    }

    public Integer getBuildingTypeId()
    {
        return buildingTypeId;
    }

    public void setBuildingTypeId(Integer val)
    {
        buildingTypeId = val;
    }

    public Integer getInspectionTypeId()
    {
        return inspectionTypeId;
    }

    public void setInspectionTypeId(Integer val)
    {
        inspectionTypeId = val;
    }


    public String getDateFrom()
    {
        return dateFrom;
    }

    public void setDateFrom(String val)
    {
        dateFrom = val;
    }

    public Date getDateFromTime()
    {
        Date date = null;
        if (dateFrom != null && !dateFrom.equals("")) {
            try {
                date = dtf.parse(dateFrom);
            }
            catch (Exception ex) {
                System.err.println(ex);
            }
        }
        return date;
    }

    public String getDateTo()
    {
        return dateTo;
    }

    public void setDateTo(String val)
    {
        dateTo = val;
    }

    public Date getDateToTime()
    {
        Date date = null;
        if (dateTo != null && !dateTo.equals("")) {
            try {
                date = dtf.parse(dateTo);
            }
            catch (Exception ex) {
                System.err.println(ex);
            }
        }
        return date;
    }

}
