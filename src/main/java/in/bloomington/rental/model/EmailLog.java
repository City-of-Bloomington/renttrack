package in.bloomington.rental.model;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "email_logs")
public class EmailLog implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;

		@Transient
    SimpleDateFormat dtf = new SimpleDateFormat("MM/dd/yyyy");
		
		@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int                  id;
		
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private RentUser             user;
		
		@Temporal(TemporalType.DATE)
		@Column(name = "date", length = 13)
    private Date                 date;
		
    @Column(name = "type")
    private String               type;
		
		@Column(name = "email_from")
    private String               emailFrom;

		@OneToMany(fetch = FetchType.LAZY, mappedBy = "emailLog")
    private List<EmailDetailLog> emailDetailLogs = new ArrayList<>(0);

    public EmailLog()
    {
    }

    public EmailLog(int id)
    {
        this.id = id;
    }

    public EmailLog(int id, RentUser user,
                            Date     date,
                            String   type,
                            String   emailFrom,
              List<EmailDetailLog>   emailDetailLogs)
    {
        this.id              = id;
        this.user            = user;
        this.date            = date;
        this.type            = type;
        this.emailFrom       = emailFrom;
        this.emailDetailLogs = emailDetailLogs;
    }

    // for saving
    public EmailLog(RentUser user, Date date, String type, String emailFrom)
    {
        this.user      = user;
        this.date      = date;
        this.type      = type;
        this.emailFrom = emailFrom;
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

    public void setUser(RentUser user)
    {
        this.user = user;
    }

    public Date getDate()
    {
        return this.date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    @Transient
    public String getDateFr()
    {
        String ret = "";
        if (date != null) {
            try {
                ret = dtf.format(date);
            }
            catch (Exception ex) {
            }
        }
        return ret;
    }

    public String getType()
    {
        return this.type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getEmailFrom()
    {
        return this.emailFrom;
    }

    public void setEmailFrom(String val)
    {
        if (val != null && !val.equals("")) this.emailFrom = val.trim();
    }

    public List<EmailDetailLog> getEmailDetailLogs()
    {
        return this.emailDetailLogs;
    }

    public void setEmailDetailLogs(List<EmailDetailLog> vals)
    {
        this.emailDetailLogs = vals;
    }

}
