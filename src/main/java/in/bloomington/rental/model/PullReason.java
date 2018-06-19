package in.bloomington.rental.model;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name="pull_reasons")

public class PullReason implements java.io.Serializable {

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)   
    @Column(name="id")		
		private int id;
    @Column(name="alias", length=8)		
		private String alias;
    
    @Column(name="reason", length=30, nullable=false)
		private String reason;
		// private Set rentals = new HashSet(0);

    public PullReason() {
    }

	
    public PullReason(int id) {
        this.id = id;
    }
    public PullReason(int id, String alias, String reason){
				// , Set rentals) {
       this.id = id;
       this.alias = alias;
       this.reason = reason;
			 // this.rentals = rentals;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getAlias() {
        return this.alias;
    }
    
    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getReason() {
        return this.reason;
    }
    
    public void setReason(String name) {
        this.reason = name;
    }
		/*
		@OneToMany(fetch=FetchType.LAZY, mappedBy="pullReason")
    public Set getRentals() {
        return this.rentals;
    }
    public void setRentals(Set rentals) {
        this.rentals = rentals;
    }
		*/    
		public String toString(){
				return reason;
		}
		
}


