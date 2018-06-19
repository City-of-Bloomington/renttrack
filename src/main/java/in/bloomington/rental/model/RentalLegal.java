package in.bloomington.rental.model;



import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
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
@Table(name="rental_legals")

public class RentalLegal  implements java.io.Serializable {

		SimpleDateFormat dtf = new SimpleDateFormat("MM/dd/yyyy");
		private int id;
		private Rental rental;
		private Date date;
		private RentUser startByUser;
		
    public RentalLegal() {
    }
	
    public RentalLegal(int id) {
        this.id = id;
    }
    public RentalLegal(int id,
							 Rental rental,
							 Date date,
							 RentUser user												 
							 ) {
				this.id = id;
				this.rental = rental;
				this.date = date;
				this.startByUser = user;
    }
   
		@Id
		@Column(name="id")
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="date", length=13)
    public Date getDate() {
        return this.date;
    }
    public void setDate(Date val) {
				this.date = val;
    }
		
		@Transient
		public String getDateFr(){
				String ret = "";
				if(date != null){
						ret = dtf.format(date);
				}
				return ret;
		}
		@ManyToOne(fetch=FetchType.LAZY)		
    @JoinColumn(name="rental_id")
    public Rental getRental() {
        return this.rental;
    }
		public void setRental(Rental val){
				this.rental = val;
		}
		@ManyToOne(fetch=FetchType.LAZY)		
    @JoinColumn(name="start_by")
    public RentUser getStartByUser() {
        return this.startByUser;
    }
		public void setStartByUser(RentUser val){
				this.startByUser = val;
		}
		@Override
		public String toString(){
				return ""+id;
		}

}


