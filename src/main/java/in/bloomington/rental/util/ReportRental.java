package in.bloomington.rental.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import org.springframework.stereotype.Component;

@Component
public class ReportRental
{

    SimpleDateFormat dtf       = new SimpleDateFormat("MM/dd/yyyy");
		String dateFrom="", dateTo="", unitsFrom="", bedroomsFrom="";
		Integer buildingTypeId, inspectionTypeId, pullReasonId,
				propertyTypeId;
		String dateType = "", outputType="";

		public ReportRental(String dateFrom,
												String dateTo,
												Integer buildingTypeId,
												Integer inspectionTypeId,
												Integer pullReasonId,
												Integer propertyTypeId,
												String unitsFrom,
												String bedroomsFrom,
												String dateType,
												String outputType
												){
				this.dateFrom = dateFrom;
				this.dateTo = dateTo;
				this.buildingTypeId = buildingTypeId;
				this.inspectionTypeId = inspectionTypeId;
				this.propertyTypeId = propertyTypeId;				
				this.pullReasonId = pullReasonId;
				this.unitsFrom = unitsFrom;
				this.bedroomsFrom = bedroomsFrom;
				this.dateType = dateType;
				this.outputType = outputType;
		}
    public ReportRental()
    {
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

    public void setPropertyTypeId(Integer val)
    {
        propertyTypeId = val;
    }		

    public Integer getInspectionTypeId()
    {
        return inspectionTypeId;
    }

    public void setInspectionTypeId(Integer val)
    {
        inspectionTypeId = val;
    }

    public Integer getPullReasonId()
    {
        return pullReasonId;
    }

    public void setPullReasonId(Integer val)
    {
        pullReasonId = val;
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
		public Date findTwoYearsAgoDate(){
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.YEAR, -2);
				Date date = cal.getTime();
				return date;
		}

}
