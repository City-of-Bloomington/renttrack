package in.bloomington.rental.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import org.springframework.stereotype.Component;

@Component
public class Report
{

    SimpleDateFormat dtf       = new SimpleDateFormat("MM/dd/yyyy");
		String dateFrom="", dateTo="";
		String dateType = "", outputType="";
		String violations="";
		Integer buildingTypeId,
				propertyTypeId,
				inspectionTypeId,
				inspectedBy,
				pullReasonId;
		String unitsFrom="", bedroomsFrom="";
		
    public Report()
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


    public Integer getPropertyTypeId()
    {
        return propertyTypeId;
    }
		

    public Integer getInspectionTypeId()
    {
        return inspectionTypeId;
    }

    public void setInspectionTypeId(Integer val)
    {
        inspectionTypeId = val;
    }
    public Integer getInspectedBy()
    {
        return inspectedBy;
    }

    public void setInspectedBy(Integer val)
    {
        inspectedBy = val;
    }		
    public Integer getPullReasonId()
    {
        return pullReasonId;
    }

    public void setPullReasonId(Integer val)
    {
        pullReasonId = val;
    }

		
    public String getOutputType()
    {
        return outputType;
    }

    public void setOutputType(String val)
    {
        outputType = val;
    }
		
    public String getDateType()
    {
        return dateType;
    }

    public void setDateType(String val)
    {
        dateType = val;
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
    public String getUnitsFrom()
    {
        return unitsFrom;
    }

    public void setUnitsFrom(String val)
    {
        unitsFrom = val;
    }
    public String getBedroomsFrom()
    {
        return bedroomsFrom;
    }

    public void setBedroomsFrom(String val)
    {
        bedroomsFrom = val;
    }

		public Date findTwoYearsAgoDate(){
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.YEAR, -2);
				Date date = cal.getTime();
				return date;
		}
}
