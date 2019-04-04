package in.bloomington.rental.model;

import java.text.SimpleDateFormat;
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
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "rental_notes")
public class RentalNote implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;

    SimpleDateFormat dtf = new SimpleDateFormat("MM/dd/yyyy");
    private int      id;
    private Rental   rental;
    private RentUser user;
    private Date     date;
    private String   notes;

    public RentalNote()
    {
    }

    public RentalNote(int id)
    {
        this.id = id;
    }

    public RentalNote(int id, Rental   rental,
                              RentUser user,
                              Date     date,
                              String   notes)
    {
        this.id     = id;
        this.rental = rental;
        this.user   = user;
        this.date   = date;
        this.notes  = notes;
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
    @JoinColumn(name = "rental_id")
    public Rental getRental()
    {
        return this.rental;
    }

    public void setRental(Rental rental)
    {
        this.rental = rental;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
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
        if (id == 0) {
            date = new Date(); // current date
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
        getDate();
        if (date != null) {
            return dtf.format(date);
        }
        return "";
    }

    @Column(name = "notes", length = 500)
    @NotNull(message = "{rental.notes.empty}")
    public String getNotes()
    {
        return this.notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    @Transient
    public String toString()
    {
        String ret = ""  + id
                   + " " + notes
                   + " " + getDateFr();
        return ret;
    }
}
