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
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Entity
@Table(name = "pull_history")
public class PullHistory implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;

    private final static SimpleDateFormat dtf    = new SimpleDateFormat("MM/dd/yyyy");
    private static final Logger           logger = LogManager.getLogger(PullHistory.class);
    private int                           id;
    private PullReason                    pullReason;
    private Rental                        rental;
    private RentUser                      user;
    private Date                          date;
    private Character                     completed;
    private Date                          completedDate;

    public PullHistory()
    {
    }

    public PullHistory(int id)
    {
        this.id = id;
    }

    public PullHistory(int id, PullReason pullReason,
                               Rental     rental,
                               RentUser   user,
                               Date       date,
                               Character  completed,
                               Date       completedDate)
    {
        this.id            = id;
        this.pullReason    = pullReason;
        this.rental        = rental;
        this.user          = user;
        this.date          = date;
        this.completed     = completed;
        this.completedDate = completedDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pull_reason_id")
    public PullReason getPullReason()
    {
        return this.pullReason;
    }

    public void setPullReason(PullReason pullReason)
    {
        this.pullReason = pullReason;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_id")
    public Rental getRental()
    {
        return this.rental;
    }

    public void setRental(Rental rental)
    {
        this.rental = rental;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pull_by_id")
    public RentUser getUser()
    {
        return this.user;
    }

    public void setUser(RentUser user)
    {
        this.user = user;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "date", length = 13)
    public Date getDate()
    {
        if (date != null) {
            date = new Date();
        }
        return this.date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    @Transient
    public String getDateFr()
    {
        if (date == null) {
            date = new Date();
        }
        return dtf.format(date);
    }

    @Transient
    public void setDateFr(String val)
    {
        if (val != null && !val.equals("")) {
            try {
                date = dtf.parse(val);
            }
            catch (Exception ex) {
                logger.error(ex);
            }
        }
    }

    @Transient
    public void setDateNow()
    {
        date = new Date();
    }

    @Transient
    public String getRental_id()
    {
        getRental();
        if (rental != null) {
            return "" + rental.getId();
        }
        return "";
    }

    @Column(name = "completed", length = 1)
    public Character getCompleted()
    {
        return this.completed;
    }

    public void setCompleted(Character completed)
    {
        this.completed = completed;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "completed_date", length = 13)
    public Date getCompletedDate()
    {
        if (completedDate == null && isDone()) {
            if (date != null) this.completedDate = date;
        }
        return this.completedDate;
    }

    public void setCompletedDate(Date completedDate)
    {
        this.completedDate = completedDate;
    }

    @Transient
    public boolean hasCompletedDate()
    {
        return completedDate != null;
    }

    @Transient
    public String getCompletedDateFr()
    {
        getCompletedDate();
        if (completedDate != null) {
            return dtf.format(completedDate);
        }
        return "";
    }

    @Transient
    public boolean isDone()
    {
        return completed != null && !completed.equals("");
    }

    @Transient
    public boolean isPuller()
    {
        return user != null;
    }

}
