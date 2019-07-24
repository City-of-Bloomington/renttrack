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
@Table(name = "rental_bills") // old name bills
public class Bill implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;

		@Transient
    SimpleDateFormat dtf = new SimpleDateFormat("MM/dd/yyyy");
		@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int           id;
		
		@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_id")
    private Rental        rental;
		
    @Temporal(TemporalType.DATE)
    @Column(name = "issue_date", length = 13)
    // @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date          issueDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "due_date", length = 13)
    // @DateTimeFormat(pattern = "MM/dd/yyyy")		
    private Date          dueDate;

    @Column(name = "single_building_rate", precision = 6)		
    private BigDecimal    singleBuildingRate  = new BigDecimal("0");
		
		@Column(name = "multi_building_rate", precision = 6)
    private BigDecimal    multiBuildingRate   = new BigDecimal("0");
		
		@Column(name = "condo_building_rate", precision = 6)
    private BigDecimal    condoBuildingRate   = new BigDecimal("0");
		
		@Column(name = "rooming_building_rate", precision = 6)
    private BigDecimal    roomingBuildingRate = new BigDecimal("0");

		@Column(name = "unit_rate", precision = 6)
    private BigDecimal    unitRate            = new BigDecimal("0");

		@Column(name = "bath_rate", precision = 6)
    private BigDecimal    bathRate            = new BigDecimal("0");

		@Column(name = "reinsp_rate", precision = 6)
    private BigDecimal    reinspRate          = new BigDecimal("0");

		@Column(name = "noshow_rate", precision = 6)
    private BigDecimal    noshowRate          = new BigDecimal("0");

		@Column(name = "bhqa_fine", precision = 6)
    private BigDecimal    bhqaFine            = new BigDecimal("0");

		@Column(name = "single_building_cnt")
    private Short         singleBuildingCnt   = new Short("0");

		@Column(name = "multi_building_cnt")
    private Short         multiBuildingCnt    = new Short("0");
		
		@Column(name = "condo_building_cnt")
    private Short         condoBuildingCnt    = new Short("0");
		
		@Column(name = "rooming_building_cnt")
    private Short         roomingBuildingCnt  = new Short("0");
		
		@Column(name = "unit_cnt")
    private Short         unitCnt             = new Short("0");

		@Column(name = "bath_cnt")
    private Short         bathCnt             = new Short("0");

		@Column(name = "noshow_cnt")
    private Short         noshowCnt           = new Short("0");
		
		@Column(name = "reinsp_cnt")
    private Short         reinspCnt           = new Short("0");
		
		@Column(name = "reinsp_dates", length = 80)
    private String        reinspDates;
		
		@Column(name = "noshow_dates", length = 80)
    private String        noshowDates;
		
		@Column(name = "status")
    private String        status              = "Unpaid";
		
		@Column(name = "appeal", length = 1)
    private Character     appeal;
		
    @Column(name = "appeal_fee")
    private BigDecimal    appealFee           = new BigDecimal("0");
		
		@Column(name = "credit", precision = 6)
    private BigDecimal    credit              = new BigDecimal("0");
		
		@Column(name = "summary_rate", precision = 6)
    private BigDecimal    summaryRate         = new BigDecimal("0");;
		
		@Column(name = "idl_rate", precision = 6)
    private BigDecimal    idlRate             = new BigDecimal("0");

		@Column(name = "summary_flag", length = 1)
    private Character     summaryFlag;
		
		@Column(name = "idl_flag", length = 1)
    private Character     idlFlag;
		
		@Column(name = "summary_cnt")
    private Short         summaryCnt          = new Short("0");

    @Column(name = "idl_cnt")
    private Short         idlCnt              = new Short("0");

    @Column(name = "other_fee_title", length = 80)
		private String otherFeeTitle;

    @Column(name = "other_fee")
		private BigDecimal  otherFee = new BigDecimal("0");;		
		
    @Column(name = "other_fee2_title", length = 80)
		private String  otherFee2Title;		

    @Column(name = "other_fee2")
		private BigDecimal  otherFee2 = new BigDecimal("0");;
		
		@OneToMany(fetch = FetchType.LAZY, mappedBy = "bill")
    private List<Receipt> receipts            = new ArrayList<>(0);

		@Transient
    private String        propertyTypes       = "";
		
		@Transient
    private double        balance             = 0;
    //
    // inspectionFee is building + units + rooms cost except
		// noshow or reinspection etc
		//
		@Transient
    private Double        inspectionFee       = new Double(0);

    public Bill()
    {
    }

    public Bill(int id)
    {
        this.id = id;
    }

    public Bill(int id, Rental rental,
								Date issueDate,
								Date dueDate,
								BigDecimal singleBuildingRate,
                BigDecimal multiBuildingRate,
								BigDecimal condoBuildingRate,
								BigDecimal roomingBuildingRate,
                BigDecimal unitRate,
								BigDecimal bathRate,
								BigDecimal reinspRate,
								BigDecimal noshowRate,
                BigDecimal bhqaFine,
								Short singleBuildingCnt,
								Short multiBuildingCnt,
								Short condoBuildingCnt,
                Short roomingBuildingCnt,
								Short unitCnt,
								Short bathCnt,
								Short noshowCnt,
								Short reinspCnt,
                String reinspDates,
								String noshowDates,
								String status,
								Character appeal,
								BigDecimal appealFee,
                BigDecimal credit,
								BigDecimal summaryRate,
								BigDecimal idlRate,
								Character summaryFlag,
								Character idlFlag,
                Short summaryCnt,
								Short idlCnt,
								String otherFeeTitle,
								BigDecimal otherFee,
								String otherFee2Title,
								BigDecimal otherFee2,
								List<Receipt> receipts)
    {
        this.id                  = id;
        this.rental              = rental;
        this.issueDate           = issueDate;
        this.dueDate             = dueDate;
        this.reinspDates         = reinspDates;
        this.noshowDates         = noshowDates;
        this.status              = status;
        this.appeal              = appeal;
        this.summaryFlag         = summaryFlag;
        this.idlFlag             = idlFlag;
				this.otherFeeTitle       = otherFeeTitle;
				this.otherFee2Title      = otherFee2Title;
        this.receipts            = receipts;
				//
        setSingleBuildingRate(singleBuildingRate);
        setMultiBuildingRate(multiBuildingRate);
        setCondoBuildingRate(condoBuildingRate);
        setRoomingBuildingRate(roomingBuildingRate);
        setUnitRate(unitRate);
        setBathRate(bathRate);
        setReinspRate(reinspRate);
        setNoshowRate(noshowRate);
        setSummaryRate(summaryRate);
        setIdlRate(idlRate);
        setAppealFee(appealFee);
        setBhqaFine(bhqaFine);
        setSingleBuildingCnt(singleBuildingCnt);
        setMultiBuildingCnt(multiBuildingCnt);
        setCondoBuildingCnt(condoBuildingCnt);
        setRoomingBuildingCnt(roomingBuildingCnt);
        setUnitCnt(unitCnt);
        setBathCnt(bathCnt);
        setNoshowCnt(noshowCnt);
        setCredit(credit);				
        setReinspCnt(reinspCnt);
        setSummaryCnt(summaryCnt);
        setIdlCnt(idlCnt);
				setOtherFee(otherFee);
				setOtherFee2(otherFee2);								
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

    public Date getIssueDate()
    {
        if (issueDate == null && id == 0) issueDate = new Date();
        return this.issueDate;
    }

    public void setIssueDate(Date issueDate)
    {
        this.issueDate = issueDate;
    }

    @Transient
    public String getIssueDateFr()
    {
        if (issueDate == null && id == 0) {
            issueDate = new Date();// today
        }
        if (issueDate != null) {
            return dtf.format(issueDate);
        }
        return "";
    }

    @Transient
    public void setIssueDateFr(String val)
    {
        if (val != null) {
            try {
                issueDate = dtf.parse(val);
            }
            catch (Exception ex) {
            }
        }
    }

    public Date getDueDate()
    {
        if (dueDate == null && id == 0) {
            dueDate = getIssueDate();
            Calendar cal = Calendar.getInstance();
            cal.setTime(dueDate);
            cal.add(Calendar.DATE, 30);
            dueDate = cal.getTime();
        }
        return this.dueDate;
    }

    public void setDueDate(Date dueDate)
    {
        this.dueDate = dueDate;
    }

    @Transient
    public String getDueDateFr()
    {
				getDueDate();
        if (dueDate != null) {
            return dtf.format(dueDate);
        }
        return "";
    }

    @Transient
    public void setDueDateFr(String val)
    {
        if (val != null) {
            try {
                dueDate = dtf.parse(val);
            }
            catch (Exception ex) {
            }
        }
    }

    public BigDecimal getSingleBuildingRate()
    {
        return this.singleBuildingRate;
    }

    public void setSingleBuildingRate(BigDecimal val)
    {
				if(val != null)
						this.singleBuildingRate = val;
    }

    public BigDecimal getMultiBuildingRate()
    {
        return this.multiBuildingRate;
    }

    public void setMultiBuildingRate(BigDecimal val)
    {
				if(val != null)
						this.multiBuildingRate = val;
    }

		public String getOtherFeeTitle()
    {
        return this.otherFeeTitle;
    }

    public void setOtherFeeTitle(String val)
    {
						this.otherFeeTitle = val;
    }
		public String getOtherFee2Title()
    {
        return this.otherFee2Title;
    }

    public void setOtherFee2Title(String val)
    {
        this.otherFee2Title = val;
    }		
		
    public BigDecimal getOtherFee()
    {
        return this.otherFee;
    }

    public void setOtherFee(BigDecimal val)
    {
				if(val != null)
						this.otherFee = val;
    }
		
    public BigDecimal getOtherFee2()
    {
        return this.otherFee2;
    }

    public void setOtherFee2(BigDecimal val)
    {
				if(val != null)				
						this.otherFee2 = val;
    }		

    public BigDecimal getCondoBuildingRate()
    {
        return this.condoBuildingRate;
    }

    public void setCondoBuildingRate(BigDecimal val)
    {
				if(val != null)								
        this.condoBuildingRate = val;
    }

    public BigDecimal getRoomingBuildingRate()
    {
        return this.roomingBuildingRate;
    }

    public void setRoomingBuildingRate(BigDecimal val)
    {
				if(val != null)				
						this.roomingBuildingRate = val;
    }

    public BigDecimal getUnitRate()
    {
        return this.unitRate;
    }

    public void setUnitRate(BigDecimal val)
    {
				if(val != null)				
						this.unitRate = val;
    }

    public BigDecimal getBathRate()
    {
        return this.bathRate;
    }

    public void setBathRate(BigDecimal val)
    {
				if(val != null)				
						this.bathRate = val;
    }

    public BigDecimal getReinspRate()
    {
        return this.reinspRate;
    }

    public void setReinspRate(BigDecimal val)
    {
				if(val != null)				
						this.reinspRate = val;
    }

    public BigDecimal getNoshowRate()
    {
        return this.noshowRate;
    }

    public void setNoshowRate(BigDecimal val)
    {
				if(val != null)				
						this.noshowRate = noshowRate;
    }

    public BigDecimal getBhqaFine()
    {
        return this.bhqaFine;
    }

    public void setBhqaFine(BigDecimal val)
    {
        this.bhqaFine = val;
    }

    public Short getSingleBuildingCnt()
    {
        return this.singleBuildingCnt;
    }

    public void setSingleBuildingCnt(Short val)
    {
				if(val != null)				
						this.singleBuildingCnt = val;
    }

    public Short getMultiBuildingCnt()
    {
        return this.multiBuildingCnt;
    }

    public void setMultiBuildingCnt(Short val)
    {
				if(val != null)				
						this.multiBuildingCnt = val;
    }

    public Short getCondoBuildingCnt()
    {
        return this.condoBuildingCnt;
    }

    public void setCondoBuildingCnt(Short val)
    {
				if(val != null)				
        this.condoBuildingCnt = val;
    }

    public Short getRoomingBuildingCnt()
    {
        return this.roomingBuildingCnt;
    }

    public void setRoomingBuildingCnt(Short val)
    {
				if(val != null)				
        this.roomingBuildingCnt = val;
    }


    public Short getUnitCnt()
    {
        return this.unitCnt;
    }

    public void setUnitCnt(Short val)
    {
				if(val != null)				
						this.unitCnt = val;
    }

    public Short getBathCnt()
    {
        return this.bathCnt;
    }

    public void setBathCnt(Short val)
    {
				if(val != null)				
        this.bathCnt = val;
    }

    @Transient
    public boolean hasBathCount()
    {
        return bathCnt != null && bathCnt > 0;
    }

    @Transient
    public boolean hasSingleCount()
    {
        return singleBuildingCnt != null && singleBuildingCnt > 0;
    }

    @Transient
    public boolean hasMultiCount()
    {
        return multiBuildingCnt != null && multiBuildingCnt > 0;
    }

    @Transient
    public boolean hasCondoCount()
    {
        return condoBuildingCnt != null && condoBuildingCnt > 0;
    }

    public Short getNoshowCnt()
    {
        return this.noshowCnt;
    }

    public void setNoshowCnt(Short val)
    {
				if(val != null)				
        this.noshowCnt = val;
    }

    public Short getReinspCnt()
    {
        return this.reinspCnt;
    }

    public void setReinspCnt(Short val)
    {
        this.reinspCnt = val;
    }


    public String getReinspDates()
    {
        return this.reinspDates;
    }

    public void setReinspDates(String reinspDates)
    {
        this.reinspDates = reinspDates;
    }

    public String getNoshowDates()
    {
        return this.noshowDates;
    }

    public void setNoshowDates(String noshowDates)
    {
        this.noshowDates = noshowDates;
    }

    public String getStatus()
    {
        if (status == null || status.equals("Unpaid")) {
            checkStatus();
        }
        return this.status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    @Transient
    public boolean isUnpaid()
    {
        getStatus();
        return status.equals("Unpaid");
    }


    public Character getAppeal()
    {
        return this.appeal;
    }

    public void setAppeal(Character appeal)
    {
        this.appeal = appeal;
    }

    public BigDecimal getAppealFee()
    {
        return this.appealFee;
    }

    public void setAppealFee(BigDecimal val)
    {
				if(val != null)				
						this.appealFee = val;
    }


    public BigDecimal getCredit()
    {
        return this.credit;
    }

    public void setCredit(BigDecimal val)
    {
				if(val != null)				
						this.credit = credit;
    }

    @Transient
    public boolean hasCredit()
    {
        return credit != null && credit.doubleValue() > 0;
    }

    @Transient
    public double getBalance()
    {
        return balance;
    }

    @Transient
    public boolean hasBalance()
    {
        checkBalance();
        return balance > 0.01;
    }

    public BigDecimal getSummaryRate()
    {
        return this.summaryRate;
    }

    public void setSummaryRate(BigDecimal val)
    {
				if(val != null)				
						this.summaryRate = val;
    }

    public BigDecimal getIdlRate()
    {
        return this.idlRate;
    }

    public void setIdlRate(BigDecimal val)
    {
				if(val != null)				
						this.idlRate = val;
    }

    public Character getSummaryFlag()
    {
        return this.summaryFlag;
    }

    public void setSummaryFlag(Character summaryFlag)
    {
        this.summaryFlag = summaryFlag;
    }

    public Character getIdlFlag()
    {
        return this.idlFlag;
    }

    public void setIdlFlag(Character idlFlag)
    {
        this.idlFlag = idlFlag;
    }

    public Short getSummaryCnt()
    {
        return this.summaryCnt;
    }

    public void setSummaryCnt(Short val)
    {
				if(val != null)				
						this.summaryCnt = val;
    }

    public Short getIdlCnt()
    {
        return this.idlCnt;
    }

    public void setIdlCnt(Short val)
    {
				if(val != null)				
						this.idlCnt = idlCnt;
    }

    public List<Receipt> getReceipts()
    {
        return this.receipts;
    }

    public void setReceipts(List<Receipt> receipts)
    {
        this.receipts = receipts;
    }

    @Transient
    public boolean hasReceipts()
    {
        return receipts != null && receipts.size() > 0;
    }

    @Transient
    private void checkStatus()
    {
        status  = "Unpaid";
        balance = getTotal();
        getReceipts();
        if (hasReceipts()) {
            checkBalance();
            if (balance < 0.01) status = "Paid";
        }
    }

    @Transient
    private void checkBalance()
    {
        if (status == null || status.equals("Unpaid")) {
            double total = getTotal();
            balance = total;
            getReceipts();
            if (hasReceipts()) {
                double paid_sum = 0;
                for (Receipt one : receipts) {
                    paid_sum += one.getReceivedSum().doubleValue();
                }
                balance = total - paid_sum;
            }
        }
    }

    @Transient
    public String getPropertyTypes()
    {
        if (propertyTypes.equals("")) {
            findPropertyTypes(); // needed for edit
        }
        return propertyTypes;
    }

    @Transient
    public double getInspectionFee()
    {
        // find total inspection only fee
        // includes buiding rate * cnt + units * cnt + (bath*cnt for rooming)
        // exlude noshow, summary etc
        //
        // Need calculations
        if (singleBuildingCnt != null && singleBuildingRate != null) {
            inspectionFee = singleBuildingCnt * (singleBuildingRate.doubleValue());
        }
        if (multiBuildingCnt != null && multiBuildingRate != null) {
            inspectionFee += multiBuildingCnt * (multiBuildingRate.doubleValue());
        }
        if (condoBuildingCnt != null && condoBuildingRate != null) {
            inspectionFee += condoBuildingCnt * (condoBuildingRate.doubleValue());
        }
        if (roomingBuildingCnt != null && roomingBuildingRate != null) {
            inspectionFee += roomingBuildingCnt * (roomingBuildingRate.doubleValue());
        }
        // this is for rooming house
        if (hasBathCount()) {
            inspectionFee += bathRate.doubleValue() * bathCnt;
        }
        // condo multi or single
        if (unitRate != null && unitCnt != null) {
            inspectionFee += unitRate.doubleValue() * unitCnt;
        }
        return inspectionFee;
    }

    @Transient
    public double getReinspFine()
    {
        double ret = 0;
        if (reinspRate != null && reinspCnt != null) {
            ret = reinspCnt * (reinspRate.doubleValue());
        }
        return ret;
    }

    @Transient
    public boolean hasReinspFine()
    {
        return getReinspFine() > 0;
    }

    @Transient
    public double getNoshowFine()
    {
        double ret = 0;
        if (noshowCnt != null && noshowRate != null) {
            ret = noshowCnt * noshowRate.doubleValue();
        }
        return ret;
    }

    @Transient
    public boolean hasNoshowFine()
    {
        return getNoshowFine() > 0;
    }

    @Transient
    public double getIdlFine()
    {
        double ret = 0;
        if (idlFlag != null) {
            if (idlRate != null) {
                if (idlCnt != null && idlCnt > 0) {
                    ret = idlCnt * idlRate.doubleValue();
                }
                else if (unitCnt != null) {
                    ret = unitCnt * idlRate.doubleValue();
                }
            }
        }
        return ret;
    }

    @Transient
    public boolean hasIdlFine()
    {
        return getIdlFine() > 0;
    }

    @Transient
    public boolean hasBhqaFine()
    {
        return bhqaFine != null && bhqaFine.doubleValue() > 0;
    }

    @Transient
    public boolean hasOtherFee()
    {
        return otherFee != null && otherFee.doubleValue() > 0;
    }
    @Transient
    public boolean hasOtherFee2()
    {
        return otherFee2 != null && otherFee2.doubleValue() > 0;
    }				

    @Transient
    public double getSummaryFine()
    {
        double ret = 0;
        if (summaryFlag != null) {
            if (summaryRate != null) {
                if (summaryCnt != null && summaryCnt > 0) {
                    ret = summaryCnt * summaryRate.doubleValue();
                }
                else if (unitCnt != null) {
                    ret = unitCnt * summaryRate.doubleValue();
                }
            }
        }
        return ret;
    }

    @Transient
    public boolean hasSummaryFine()
    {
        return getSummaryFine() > 0;
    }

    @Transient
    public double getTotal()
    {
        double total = 0;
        try {
            if (appeal != null && appealFee != null) {
                total += appealFee.doubleValue();
            }
            else {
                total  = getInspectionFee();
                total += getReinspFine();
                total += getNoshowFine();
                total += getIdlFine();
                total += getSummaryFine();
                if (bhqaFine != null) {
                    total += bhqaFine.doubleValue();
                }
								if(otherFee != null){
                    total += otherFee.doubleValue();
								}
								if(otherFee2 != null){
                    total += otherFee2.doubleValue();
								}								
                if (credit != null) {
                    total -= credit.doubleValue();
                }
            }
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
        return total;
    }

    @Transient
    private void findPropertyTypes()
    {
        getRental();
        propertyTypes = "";
        PropertyType type = null;
        if (rental == null) {
            System.err.println(" rental is null");
        }
        if (rental != null) {
            List<RentalStructure> buildings = rental.getRentalStructures();
            if (buildings == null) {
                System.err.println(" buildings is null");
            }
            if (buildings != null) {
                for (RentalStructure one : buildings) {
                    type = one.getPropertyType();
                    if (type == null) {
                        System.err.println(" type is null");
                    }
                    if (type != null) {
                        if (!propertyTypes.equals("")) propertyTypes += ", ";
                        propertyTypes += type.getName();
                    }
                }
            }
        }
    }

    @Transient
    public void setRentalInfo()
    {

        getRental();
        PropertyType type = null;
        if (rental != null) {
            List<RentalStructure> buildings = rental.getRentalStructures();
            if (buildings != null) {
                short  short_one  = 1;
                short  single_cnt = 0, multi_cnt = 0, condo_cnt = 0, rooming_cnt = 0;
                short  unit_cnt   = 0,  bath_cnt = 0;
                // size = (short)buildings.size();
                // buildingCnt = new Short(size);
                // we use the first one
                String type_name  = "";
                for (RentalStructure one : buildings) {
                    type = one.getPropertyType();
                    if (type != null) {
                        if (!propertyTypes.equals("")) propertyTypes += ", ";
                        type_name      = type.getName();
                        propertyTypes += type_name;
                        if (type_name.equals("House")) {
                            single_cnt += 1;
                            unit_cnt = 1; // normally one unit
                        }
                        else if (type_name.startsWith("Rooming")) {
                            rooming_cnt += 1;
                            if (one.hasUnits()) {
                                List<RentalUnit> units = one.getRentalUnits();
                                for (RentalUnit one2 : units) {
                                    bath_cnt += (short) one2.getBedrooms(); // baths
                                }
                            }
                        }
                        else if (type_name.equals("Condo")) {
                            condo_cnt += 1;
                            if (one.hasUnits()) {
                                unit_cnt += (short) (one.getRentalUnits().size());
                            }
                        }
                        else if (type_name.equals("Apartment")) {
                            multi_cnt += 1;
                            if (one.hasUnits()) {
                                unit_cnt += (short) (one.getRentalUnits().size());
                            }
                        }
                    }
                }
                if (unit_cnt > 0) {
                    unitCnt = new Short(unit_cnt);
                }
                if (bath_cnt > 0) {
                    bathCnt = new Short(bath_cnt);
                }
                if (single_cnt > 0) {
                    singleBuildingCnt = new Short(single_cnt);
                }
                if (multi_cnt > 0) {
                    multiBuildingCnt = new Short(multi_cnt);
                }
                if (rooming_cnt > 0) {
                    roomingBuildingCnt = new Short(rooming_cnt);
                }
                if (condo_cnt > 0) {
                    condoBuildingCnt = new Short(condo_cnt);
                }
            }
        }
    }

    @Transient
    public double getAmountDue()
    {
        double total = 0, amount_due = 0, amount_paid = 0;
        if (isUnpaid()) {
            total = getTotal();
            getReceipts();
            if (receipts != null && receipts.size() > 0) {
                for (Receipt one : receipts) {
                    if (one.getReceivedSum() != null) {
                        amount_paid += one.getReceivedSum().doubleValue();
                    }
                }
            }
            amount_due = total - amount_paid;
            if (amount_due < 0.01) amount_due = 0;
        }
        return amount_due;
    }

    @Transient
    public void setStandardFees(StandardFees fees)
    {
        if (fees != null) {
            singleBuildingRate  = fees.getSingleUnitBuildingRate();
            multiBuildingRate   = fees.getMultiUnitBuildingRate();
            condoBuildingRate   = fees.getCondoUnitBuildingRate();
            roomingBuildingRate = fees.getRoomingBuildingRate();
            unitRate            = fees.getUnitRate();
            bathRate            = fees.getRoomingBathRate();
            reinspRate          = fees.getReinspectionRate();
            noshowRate          = fees.getNoShowRate();
            summaryRate         = fees.getSummaryRate();
            idlRate             = fees.getIdlRate();
            appealFee           = fees.getAppealFee();
        }
    }
}
