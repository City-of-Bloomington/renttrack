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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Transient;

@Entity
@Table(name = "rental_logs")
public class RentalLog implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;

		@Transient
    SimpleDateFormat dtf = new SimpleDateFormat("MM/dd/yyyy");

		@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int      id;

		@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_id")
    private Rental   rental;

		@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private RentUser user;

		@Temporal(TemporalType.DATE)
    @Column(name = "date", length = 13)
    private Date     date;
		
    @Column(name = "action_taken")
    private String   actionTaken;

    public RentalLog()
    {
    }

    public RentalLog(int id)
    {
        this.id = id;
    }

    public RentalLog(int id, Rental   rental,
                             RentUser user,
                             Date     date,
                             String   actionTaken)
    {
        this.id          = id;
        this.rental      = rental;
        this.user        = user;
        this.date        = date;
        this.actionTaken = actionTaken;
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


    public Date getDate()
    {
        return this.date;
    }

    @Transient
    public String getDateFr()
    {
        if (date != null) {
            return dtf.format(date);
        }
        return "";
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    // Save, Update
    public String getActionTaken()
    {
        return this.actionTaken;
    }

    public void setActionTaken(String actionTaken)
    {
        this.actionTaken = actionTaken;
    }
}
