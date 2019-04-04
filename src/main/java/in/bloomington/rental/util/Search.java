package in.bloomington.rental.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class Search
{

    private static final Logger logger    = LogManager.getLogger(Search.class);
    SimpleDateFormat dtf       = new SimpleDateFormat("MM/dd/yyyy");
    Integer          statusId, zoningId, NHood, addressId;
    Integer          id;
    Integer          agentId, ownerId;
    String           ownerName   = "",
                     agentName   = "",
                     addressText = "",
                     dateType    = "registeredDate",
                     dateFrom    = "",
                     dateTo      = "";

    public Search()
    {
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer val)
    {
        id = val;
    }

    public Integer getAgentId()
    {
        return agentId;
    }

    public void setAgentId(Integer val)
    {
        agentId = val;
    }

    public Integer getOwnerId()
    {
        return ownerId;
    }

    public void setOwnerId(Integer val)
    {
        ownerId = val;
    }

    public Integer getAddressId()
    {
        return addressId;
    }

    public void setAddressId(Integer val)
    {
        addressId = val;
    }

    public Integer getZoningId()
    {
        return zoningId;
    }

    public void setZoningId(Integer val)
    {
        zoningId = val;
    }

    public Integer getStatusId()
    {
        return statusId;
    }

    public void setStatusId(Integer val)
    {
        statusId = val;
    }

    public Integer getNHood()
    {
        return NHood;
    }

    public void setNHood(Integer val)
    {
        NHood = val;
    }

    public String getOwnerName()
    {
        return ownerName;
    }

    public void setOwnerName(String val)
    {
        ownerName = val;
    }

    public String getAgentName()
    {
        return agentName;
    }

    public void setAgentName(String val)
    {
        agentName = val;
    }

    public String getAddressText()
    {
        return addressText;
    }

    public void setAddressText(String val)
    {
        addressText = val;
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

}
