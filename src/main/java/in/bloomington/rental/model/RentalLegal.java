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

@Entity
@Table(name = "rental_legals")

public class RentalLegal implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;

		@Transient
    SimpleDateFormat dtf = new SimpleDateFormat("MM/dd/yyyy");
		
    @Id
    @Column(name = "id")		
    private int      id;

		@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_id")
    private Rental   rental;

		@Temporal(TemporalType.DATE)
    @Column(name = "date", length = 13)
    private Date     date;
		
		@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "start_by")
    private RentUser startByUser;

    public RentalLegal()
    {
    }

    public RentalLegal(int id)
    {
        this.id = id;
    }

    public RentalLegal(int id, Rental rental, Date date, RentUser user)
    {
        this.id          = id;
        this.rental      = rental;
        this.date        = date;
        this.startByUser = user;
    }


    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
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
    public String getDateFr()
    {
        String ret = "";
        if (date != null) {
            ret = dtf.format(date);
        }
        return ret;
    }


    public Rental getRental()
    {
        return this.rental;
    }

    public void setRental(Rental val)
    {
        this.rental = val;
    }


    public RentUser getStartByUser()
    {
        return this.startByUser;
    }

    public void setStartByUser(RentUser val)
    {
        this.startByUser = val;
    }

    @Override
    public String toString()
    {
        return "" + id;
    }

}
