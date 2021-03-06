package in.bloomington.rental.model;

import java.util.Date;
import java.text.SimpleDateFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Entity
@Table(name = "inspection_file_logs")

public class InspectionFileLog implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;

		@Transient
    SimpleDateFormat            dtf    = new SimpleDateFormat("MM/dd/yyyy");
		
    private static final Logger logger = LogManager.getLogger(InspectionFileLog.class);
		
		@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int                 id;

		@Column(name = "rental_id")
    private Integer             rentalId;

		@Column(name = "inspection_id")
    private Integer             inspectionId;
		
		@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private RentUser            user;
		
		@Temporal(TemporalType.DATE)
    @Column(name = "date", length = 13)
    private Date                date;

		@Column(name = "inspect_file", length = 70)
    private String              inspectFile;

    public InspectionFileLog()
    {
    }

    public InspectionFileLog(int id)
    {
        this.id = id;
    }

    public InspectionFileLog(int id, Integer  rentalId,
                                     Integer  inspectionId,
                                     RentUser user,
                                     Date     date,
                                     String   inspectFile)
    {
        this.id           = id;
        this.rentalId     = rentalId;
        this.inspectionId = inspectionId;
        this.user         = user;
        this.date         = date;
        this.inspectFile  = inspectFile;
    }

    // for new records
    public InspectionFileLog(Integer  rentalId,
                             Integer  inspectionId,
                             RentUser user,
                             Date     date,
                             String   inspectFile)
    {
        this.rentalId     = rentalId;
        this.inspectionId = inspectionId;
        this.user         = user;
        this.date         = date;
        this.inspectFile  = inspectFile;
    }


    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public RentUser getUser()
    {
        return this.user;
    }

    public void setUser(RentUser val)
    {
        this.user = val;
    }


    public Integer getRentalId()
    {
        return this.rentalId;
    }

    public void setRentalId(Integer val)
    {
        this.rentalId = val;
    }

    public Integer getInspectionId()
    {
        return this.inspectionId;
    }

    public void setInspectionId(Integer val)
    {
        this.inspectionId = val;
    }

    public Date getDate()
    {
        return this.date;
    }

    public void setDate(Date val)
    {
        this.date = val;
    }

    @Transient
    public void setDateFr(String val)
    {
        if (val != null) {
            try {
                date = dtf.parse(val);
            }
            catch (Exception ex) {

            }
        }
    }

    @Transient
    public String getDateFr()
    {
        if (date != null) {
            return dtf.format(date);
        }
        return "";
    }


    public String getInspectFile()
    {
        return this.inspectFile;
    }

    public void setInspectFile(String inspectFile)
    {
        this.inspectFile = inspectFile;
    }
}
