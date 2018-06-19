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
@Table(name="rental_owners")
public class RentalOwner  implements java.io.Serializable {

     private int id;
     private Owner owner;
     private Rental rental;

    public RentalOwner() {
    }
	
    public RentalOwner(int id) {
        this.id = id;
    }
    public RentalOwner(int id, Owner owner, Rental rental) {
       this.id = id;
       this.owner = owner;
       this.rental = rental;
    }
   
		@Id 
    @Column(name="id")
		@GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

		@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="owner_id")
    public Owner getOwner() {
        return this.owner;
    }
    
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

		@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="rental_id")
    public Rental getRental() {
        return this.rental;
    }
    
    public void setRental(Rental rental) {
        this.rental = rental;
    }
		//
		// needed for auot_complete
		@Transient
		public void setOnwerName(String val){

		}



}


