package in.bloomington.rental.model;



import java.math.BigDecimal;
import java.util.Date;
import java.util.Calendar;
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
import javax.persistence.Transient;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="standard_fees")
public class StandardFees implements java.io.Serializable {

		SimpleDateFormat dtf = new SimpleDateFormat("MM/dd/yyyy");
		private int id;
		private RentUser user;
		
		private Date date;
		private BigDecimal singleUnitBuildingRate = new BigDecimal("0");
		private BigDecimal multiUnitBuildingRate = new BigDecimal("0");
		private BigDecimal condoUnitBuildingRate = new BigDecimal("0");		
		private BigDecimal roomingBuildingRate = new BigDecimal("0");
		private BigDecimal roomingBathRate = new BigDecimal("0");
		private BigDecimal unitRate = new BigDecimal("0");
		private BigDecimal reinspectionRate = new BigDecimal("0");
		private BigDecimal noShowRate = new BigDecimal("0");
		
		private BigDecimal summaryRate = new BigDecimal("0");
		private BigDecimal idlRate = new BigDecimal("0");
		private BigDecimal appealFee = new BigDecimal("0");
		
		//
		// insepctionFee is building + units + rooms cost except noshow or reinspection etc 
		
    public StandardFees() {
    }

	
    public StandardFees(int id) {
        this.id = id;
    }
    public StandardFees(int id,
												RentUser user,
												Date date, 
												BigDecimal singleUnitBuildingRate,
												BigDecimal multiUnitBuildingRate,
												BigDecimal condoUnitBuildingRate,												
												BigDecimal roomingBuildingRate,
												BigDecimal roomingBathRate,
												BigDecimal unitRate,
												BigDecimal reinspectionRate,
												BigDecimal noShowRate,
												BigDecimal summaryRate,
												BigDecimal idlRate,
												BigDecimal appealFee
												){
				this.id = id;
				this.user = user;
				this.date = date;
				this.singleUnitBuildingRate = singleUnitBuildingRate;
				this.multiUnitBuildingRate = multiUnitBuildingRate;
				this.condoUnitBuildingRate = condoUnitBuildingRate;
				this.roomingBuildingRate = roomingBuildingRate;
				this.roomingBathRate = roomingBathRate;
				this.unitRate = unitRate;
				this.reinspectionRate = reinspectionRate;
				this.noShowRate = noShowRate;
				this.summaryRate = summaryRate;
				this.idlRate = idlRate;
    }
   
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)    
    @Column(name="id")
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

		@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    public RentUser getUser() {
        return this.user;
    }
    
    public void setUser(RentUser user) {
        this.user = user;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="date", length=13)
		@DateTimeFormat(pattern="mm/dd/yyyy") 
    public Date getDate() {
				if(date == null)
						date = new Date();
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
		@Transient
		public String getDateFr(){
				if(date == null){
						date = new Date();// today
				}
				if(date != null){
						return dtf.format(date);
				}
				return "";
		}
    
    @Column(name="single_unit_building_rate", precision=6)
    public BigDecimal getSingleUnitBuildingRate() {
        return this.singleUnitBuildingRate;
    }
    
    public void setSingleUnitBuildingRate(BigDecimal val) {
        this.singleUnitBuildingRate = val;
    }
    @Column(name="multi_unit_building_rate", precision=6)
    public BigDecimal getMultiUnitBuildingRate() {
        return this.multiUnitBuildingRate;
    }
    
    public void setMultiUnitBuildingRate(BigDecimal val) {
        this.multiUnitBuildingRate = val;
    }
    @Column(name="condo_unit_building_rate", precision=6)
    public BigDecimal getCondoUnitBuildingRate() {
        return this.condoUnitBuildingRate;
    }
    
    public void setCondoUnitBuildingRate(BigDecimal val) {
        this.condoUnitBuildingRate = val;
    }		
    
    @Column(name="rooming_building_rate", precision=6)
    public BigDecimal getRoomingBuildingRate() {
        return this.roomingBuildingRate;
    }
    
    public void setRoomingBuildingRate(BigDecimal val) {
        this.roomingBuildingRate = val;
    }

		@Column(name="rooming_bath_rate", precision=6)
    public BigDecimal getRoomingBathRate() {
        return this.roomingBathRate;
    }
    
    public void setRoomingBathRate(BigDecimal val) {
        this.roomingBathRate = val;
    }
    
    @Column(name="unit_rate", precision=6)
    public BigDecimal getUnitRate() {
        return this.unitRate;
    }
    
    public void setUnitRate(BigDecimal unitRate) {
        this.unitRate = unitRate;
    }
        
    @Column(name="reinspection_rate", precision=6)
    public BigDecimal getReinspectionRate() {
        return this.reinspectionRate;
    }
    
    public void setReinspectionRate(BigDecimal reinspRate) {
        this.reinspectionRate = reinspRate;
    }

    
    @Column(name="no_show_rate", precision=6)
    public BigDecimal getNoShowRate() {
        return this.noShowRate;
    }
    
    public void setNoShowRate(BigDecimal val) {
        this.noShowRate = val;
    }

  
    
    @Column(name="appeal_fee")
    public BigDecimal getAppealFee() {
        return this.appealFee;
    }
    
    public void setAppealFee(BigDecimal appealFee) {
        this.appealFee = appealFee;
    }
    
    @Column(name="summary_rate", precision=6)
    public BigDecimal getSummaryRate() {
        return this.summaryRate;
    }
    
    public void setSummaryRate(BigDecimal summaryRate) {
        this.summaryRate = summaryRate;
    }
    
    @Column(name="idl_rate", precision=6)
    public BigDecimal getIdlRate() {
        return this.idlRate;
    }
    
    public void setIdlRate(BigDecimal idlRate) {
        this.idlRate = idlRate;
    }
    

}


