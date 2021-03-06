package in.bloomington.rental.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.text.SimpleDateFormat;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Entity
@Table(name = "variances")
public class Variance implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;

    private static final Logger logger = LogManager.getLogger(Variance.class);
		@Transient
    SimpleDateFormat  dtf = new SimpleDateFormat("MM/dd/yyyy");
		@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int    id;

		@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_id")
    private Rental              rental;
		
		@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private RentUser            user;
		
		@Temporal(TemporalType.DATE)
    @Column(name = "date", length = 13)
    private Date                date;
		
		@NotNull(message = "{variance.text.empty}")
    @Column(name = "variance", length = 4000)
    private String              variance;

    public Variance()
    {
    }

    public Variance(int id)
    {
        this.id = id;
    }

    public Variance(int id, Rental rental, RentUser user, Date date, String variance)
    {
        this.id       = id;
        this.rental   = rental;
        this.user     = user;
        this.date     = date;
        this.variance = variance;
    }

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }


    public Rental getRental()
    {
        return this.rental;
    }

    public void setRental(Rental rental)
    {
        this.rental = rental;
    }


    public RentUser getUser()
    {
        return this.user;
    }

    public void setUser(RentUser user)
    {
        this.user = user;
    }

    public boolean hasUser()
    {
        return user != null && user.isValid();
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
        if (date != null) {
            return dtf.format(date);
        }
        return "";
    }

    @Transient
    public void setDateFr(String val)
    {
        if (val != null) {
            try {
                date = dtf.parse(val);
            }
            catch (Exception ex) {
                logger.error(ex);
            }
        }
    }


    public String getVariance()
    {
        return this.variance;
    }

    public void setVariance(String variance)
    {
        this.variance = variance;
    }

}
