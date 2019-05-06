package in.bloomington.rental.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Transient;

@Entity
@Table(name = "rental_owners")
public class RentalOwner implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;

		@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int    id;

		@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private Owner  owner;
		
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rental_id")		
    private Rental rental;

    public RentalOwner()
    {
    }

    public RentalOwner(int id)
    {
        this.id = id;
    }

    public RentalOwner(int id, Owner owner, Rental rental)
    {
        this.id     = id;
        this.owner  = owner;
        this.rental = rental;
    }


    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }


    public Owner getOwner()
    {
        return this.owner;
    }

    public void setOwner(Owner owner)
    {
        this.owner = owner;
    }


    public Rental getRental()
    {
        return this.rental;
    }

    public void setRental(Rental rental)
    {
        this.rental = rental;
    }

    //
    // needed for auot_complete
    @Transient
    public void setOnwerName(String val)
    {

    }

}
