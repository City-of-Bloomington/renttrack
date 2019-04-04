package in.bloomington.rental.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "rental_status")

public class RentalStatus implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int    id;
    @Column(name = "alias", length = 5)
    private String alias;
    @Column(name = "name", length = 30, nullable = false)
    private String name;

    /*
     * @OneToMany(fetch=FetchType.EAGER, mappedBy="rentalStatus") private
     * Set<Rental> rentals = new HashSet<>(0);
     */
    public RentalStatus()
    {
    }

    public RentalStatus(int id)
    {
        this.id = id;
    }

    public RentalStatus(int id, String alias, String name)
    {
        // Set<Rental> rentals) {
        this.id    = id;
        this.alias = alias;
        this.name  = name;
        // this.rentals = rentals;
    }

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getAlias()
    {
        return this.alias;
    }

    public void setAlias(String alias)
    {
        this.alias = alias;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    /*
     * public Set<Rental> getRentals() { return this.rentals; }
     * 
     * public void setRentals(Set<Rental> rentals) { this.rentals = rentals; }
     */
    public String toString()
    {
        return name;
    }
}
