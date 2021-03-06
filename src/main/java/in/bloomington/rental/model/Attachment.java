package in.bloomington.rental.model;

import java.util.Date;
import java.util.Calendar;
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

@Entity
@Table(name = "attachments")
public class Attachment implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;

    @Transient
    SimpleDateFormat   dtf = new SimpleDateFormat("MM/dd/yyyy");
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int        id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inspection_id")
    private Inspection inspection;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_id")
    private Rental     rental;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "date", length = 13)
    private Date       date;
    
    @Column(name = "file_name")
    private String     fileName;
    
    @Column(name = "old_file_name")
    private String     oldFileName;
    
    @Column(name = "notes")
    private String     notes;

    public Attachment()
    {
    }

    public Attachment(int id)
    {
        this.id = id;
    }

    public Attachment(int id,
											Inspection inspection,
											Rental     rental,
											Date       date,
											String     fileName,
											String     oldFileName,
											String     notes)
    {
        this.id          = id;
        this.inspection  = inspection;
        this.rental      = rental;
        this.date        = date;
        this.fileName    = fileName;
        this.oldFileName = oldFileName;
        this.notes       = notes;
    }

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Inspection getInspection()
    {
        return this.inspection;
    }

    public void setInspection(Inspection inspection)
    {
        this.inspection = inspection;
    }

    public Rental getRental()
    {
        return this.rental;
    }

    public void setRental(Rental rental)
    {
        this.rental = rental;
    }

    public Date getDate()
    {
        return this.date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    /**
     * we need this to build the path to the file since it is in the format
     * path/year/file_name;
     */
    @Transient
    public String getYearFromDate()
    {
        String year = "";
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            year = "" + cal.get(Calendar.YEAR);
        }
        return year;
    }

    @Transient
    public String getDateFr()
    {
        if (date != null) {
            try {
                return dtf.format(date);
            }
            catch (Exception ex) {
            }
        }
        return "";
    }

    @Transient
    public void setRentalId(Integer val)
    {
        if (val != null) {
            rental = new Rental(val);
        }
    }

    public String getFileName()
    {
        return this.fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getOldFileName()
    {
        return this.oldFileName;
    }

    public void setOldFileName(String oldFileName)
    {
        this.oldFileName = oldFileName;
    }

    public String getNotes()
    {
        return this.notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }
}
