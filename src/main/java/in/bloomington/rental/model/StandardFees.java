package in.bloomington.rental.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "standard_fees")
public class StandardFees implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;
		
		@Transient
    SimpleDateFormat   dtf = new SimpleDateFormat("MM/dd/yyyy");

		@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int        id;
		
		@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private RentUser   user;
		
    @Temporal(TemporalType.DATE)
    @Column(name = "date", length = 13)
    @DateTimeFormat(pattern = "mm/dd/yyyy")
    private Date       date;
		
		@Column(name = "single_unit_building_rate", precision = 6)
    private BigDecimal singleUnitBuildingRate = new BigDecimal("0");
		
		@Column(name = "multi_unit_building_rate", precision = 6)
    private BigDecimal multiUnitBuildingRate  = new BigDecimal("0");
		
		@Column(name = "condo_unit_building_rate", precision = 6)
    private BigDecimal condoUnitBuildingRate  = new BigDecimal("0");
		
		@Column(name = "rooming_building_rate", precision = 6)
    private BigDecimal roomingBuildingRate    = new BigDecimal("0");
		
		@Column(name = "rooming_bath_rate", precision = 6)
    private BigDecimal roomingBathRate        = new BigDecimal("0");
		
		@Column(name = "unit_rate", precision = 6)
    private BigDecimal unitRate               = new BigDecimal("0");
		
		@Column(name = "reinspection_rate", precision = 6)
    private BigDecimal reinspectionRate       = new BigDecimal("0");
		
		@Column(name = "no_show_rate", precision = 6)
    private BigDecimal noShowRate             = new BigDecimal("0");
		
    @Column(name = "summary_rate", precision = 6)
    private BigDecimal summaryRate            = new BigDecimal("0");
		
		@Column(name = "idl_rate", precision = 6)
    private BigDecimal idlRate                = new BigDecimal("0");
		
		@Column(name = "appeal_fee")
    private BigDecimal appealFee              = new BigDecimal("0");


    public StandardFees()
    {
    }

    public StandardFees(int id)
    {
        this.id = id;
    }

    public StandardFees(int id, RentUser user,
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
                                BigDecimal appealFee)
    {
        this.id                     = id;
        this.user                   = user;
        this.date                   = date;
        this.singleUnitBuildingRate = singleUnitBuildingRate;
        this.multiUnitBuildingRate  = multiUnitBuildingRate;
        this.condoUnitBuildingRate  = condoUnitBuildingRate;
        this.roomingBuildingRate    = roomingBuildingRate;
        this.roomingBathRate        = roomingBathRate;
        this.unitRate               = unitRate;
        this.reinspectionRate       = reinspectionRate;
        this.noShowRate             = noShowRate;
        this.summaryRate            = summaryRate;
        this.idlRate                = idlRate;
    }


    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
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
        if (date == null) date = new Date();
        return this.date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    @Transient
    public String getDateFr()
    {
        if (date == null) {
            date = new Date();// today
        }
        if (date != null) {
            return dtf.format(date);
        }
        return "";
    }


    public BigDecimal getSingleUnitBuildingRate()
    {
        return this.singleUnitBuildingRate;
    }

    public void setSingleUnitBuildingRate(BigDecimal val)
    {
        this.singleUnitBuildingRate = val;
    }


    public BigDecimal getMultiUnitBuildingRate()
    {
        return this.multiUnitBuildingRate;
    }

    public void setMultiUnitBuildingRate(BigDecimal val)
    {
        this.multiUnitBuildingRate = val;
    }


    public BigDecimal getCondoUnitBuildingRate()
    {
        return this.condoUnitBuildingRate;
    }

    public void setCondoUnitBuildingRate(BigDecimal val)
    {
        this.condoUnitBuildingRate = val;
    }

    public BigDecimal getRoomingBuildingRate()
    {
        return this.roomingBuildingRate;
    }

    public void setRoomingBuildingRate(BigDecimal val)
    {
        this.roomingBuildingRate = val;
    }


    public BigDecimal getRoomingBathRate()
    {
        return this.roomingBathRate;
    }

    public void setRoomingBathRate(BigDecimal val)
    {
        this.roomingBathRate = val;
    }


    public BigDecimal getUnitRate()
    {
        return this.unitRate;
    }

    public void setUnitRate(BigDecimal unitRate)
    {
        this.unitRate = unitRate;
    }


    public BigDecimal getReinspectionRate()
    {
        return this.reinspectionRate;
    }

    public void setReinspectionRate(BigDecimal reinspRate)
    {
        this.reinspectionRate = reinspRate;
    }

    public BigDecimal getNoShowRate()
    {
        return this.noShowRate;
    }

    public void setNoShowRate(BigDecimal val)
    {
        this.noShowRate = val;
    }

    public BigDecimal getAppealFee()
    {
        return this.appealFee;
    }

    public void setAppealFee(BigDecimal appealFee)
    {
        this.appealFee = appealFee;
    }

    public BigDecimal getSummaryRate()
    {
        return this.summaryRate;
    }

    public void setSummaryRate(BigDecimal summaryRate)
    {
        this.summaryRate = summaryRate;
    }

    public BigDecimal getIdlRate()
    {
        return this.idlRate;
    }

    public void setIdlRate(BigDecimal idlRate)
    {
        this.idlRate = idlRate;
    }

}
