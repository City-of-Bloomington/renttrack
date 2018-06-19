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
@Table(name="bills")
public class Bill implements java.io.Serializable {

		SimpleDateFormat dtf = new SimpleDateFormat("MM/dd/yyyy");
		private int id;
		private Rental rental;
		
		private Date issueDate;
		private Date dueDate;
		private BigDecimal buildingRate = new BigDecimal("0");
		private BigDecimal unitRate = new BigDecimal("0");
		private BigDecimal bathRate = new BigDecimal("0");
		private BigDecimal reinspRate = new BigDecimal("0");
		private BigDecimal noshowRate = new BigDecimal("0");
		private BigDecimal bhqaFine = new BigDecimal("0");
		private Short buildingCnt = new Short("0");
		private Short unitCnt = new Short("0");
		private Short bathCnt = new Short("0");
		private Short noshowCnt=new Short("0");
		private Short reinspCnt= new Short("0");
		private String reinspDates;
		private String noshowDates;
		private String status ="Unpaid";
		private Character appeal;
		private BigDecimal appealFee = new BigDecimal("0");
		private BigDecimal credit = new BigDecimal(0.0);
		private BigDecimal summaryRate = new BigDecimal("0");;
		private BigDecimal idlRate = new BigDecimal("0");
		private Character summaryFlag;
		private Character idlFlag;
		private Short summaryCnt = new Short("0");
		private Short idlCnt = new Short("0");
		private List<Receipt> receipts = new ArrayList<>(0);
		private String propertyTypes = "";
		private double balance = 0;
		//
		// insepctionFee is building + units + rooms cost except noshow or reinspection etc 
		private Double inspectionFee = new Double(0); 
		
    public Bill() {
    }

	
    public Bill(int id) {
        this.id = id;
    }
    public Bill(int id, Rental rental, Date issueDate, Date dueDate, BigDecimal buildingRate, BigDecimal unitRate, BigDecimal bathRate, BigDecimal reinspRate, BigDecimal noshowRate, BigDecimal bhqaFine, Short buildingCnt, Short unitCnt, Short bathCnt, Short noshowCnt, Short reinspCnt, String reinspDates, String noshowDates, String status, Character appeal, BigDecimal appealFee, BigDecimal credit, BigDecimal summaryRate, BigDecimal idlRate, Character summaryFlag, Character idlFlag, Short summaryCnt, Short idlCnt, List<Receipt> receipts) {
				this.id = id;
				this.rental = rental;
				this.issueDate = issueDate;
				this.dueDate = dueDate;
				this.buildingRate = buildingRate;
				this.unitRate = unitRate;
				this.bathRate = bathRate;
				this.reinspRate = reinspRate;
				this.noshowRate = noshowRate;
				this.bhqaFine = bhqaFine;
				this.buildingCnt = buildingCnt;
				this.unitCnt = unitCnt;
				this.bathCnt = bathCnt;
				this.noshowCnt = noshowCnt;
				this.reinspCnt = reinspCnt;
				this.reinspDates = reinspDates;
				this.noshowDates = noshowDates;
				this.status = status;
				this.appeal = appeal;
				this.appealFee = appealFee;
				this.credit = credit;
				this.summaryRate = summaryRate;
				this.idlRate = idlRate;
				this.summaryFlag = summaryFlag;
				this.idlFlag = idlFlag;
				this.summaryCnt = summaryCnt;
				this.idlCnt = idlCnt;
				this.receipts = receipts;
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
    @JoinColumn(name="rental_id")
    public Rental getRental() {
        return this.rental;
    }
    
    public void setRental(Rental rental) {
        this.rental = rental;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="issue_date", length=13)
		@DateTimeFormat(pattern="mm/dd/yyyy") 
    public Date getIssueDate() {
				if(issueDate == null)
						issueDate = new Date();
        return this.issueDate;
    }
    
    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }
		@Transient
		public String getIssueDateFr(){
				if(issueDate == null){
						issueDate = new Date();// today
				}
				if(issueDate != null){
						return dtf.format(issueDate);
				}
				return "";
		}
		@Transient
		public void setIssueDateFr(String val){
				if(val != null){
						try{
								issueDate = dtf.parse(val);
						}catch(Exception ex){}
				}
		}
		
    @Temporal(TemporalType.DATE)
    @Column(name="due_date", length=13)
		@DateTimeFormat(pattern="mm/dd/yyyy") 
    public Date getDueDate() {
				if(dueDate == null){
						dueDate = getIssueDate();
						Calendar cal = Calendar.getInstance();
						cal.setTime(dueDate);
						cal.add(Calendar.DATE, 30); 
						dueDate =  cal.getTime();						
				}
        return this.dueDate;
    }
    
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
		@Transient
		public String getDueDateFr(){
				getDueDate();
				if(dueDate != null){
						return dtf.format(dueDate);
				}
				return "";
		}
		@Transient
		public void setDueDateFr(String val){
				if(val != null){
						try{
								dueDate = dtf.parse(val);
						}catch(Exception ex){}
				}
		}
    
    @Column(name="building_rate", precision=6)
    public BigDecimal getBuildingRate() {
        return this.buildingRate;
    }
    
    public void setBuildingRate(BigDecimal buildingRate) {
        this.buildingRate = buildingRate;
    }
    
    @Column(name="unit_rate", precision=6)
    public BigDecimal getUnitRate() {
        return this.unitRate;
    }
    
    public void setUnitRate(BigDecimal unitRate) {
        this.unitRate = unitRate;
    }

    
    @Column(name="bath_rate", precision=6)
    public BigDecimal getBathRate() {
        return this.bathRate;
    }
    
    public void setBathRate(BigDecimal bathRate) {
        this.bathRate = bathRate;
    }

    
    @Column(name="reinsp_rate", precision=6)
    public BigDecimal getReinspRate() {
        return this.reinspRate;
    }
    
    public void setReinspRate(BigDecimal reinspRate) {
        this.reinspRate = reinspRate;
    }

    
    @Column(name="noshow_rate", precision=6)
    public BigDecimal getNoshowRate() {
        return this.noshowRate;
    }
    
    public void setNoshowRate(BigDecimal noshowRate) {
        this.noshowRate = noshowRate;
    }

    
    @Column(name="bhqa_fine", precision=6)
    public BigDecimal getBhqaFine() {
        return this.bhqaFine;
    }
    
    public void setBhqaFine(BigDecimal bhqaFine) {
        this.bhqaFine = bhqaFine;
    }

    
    @Column(name="building_cnt")
    public Short getBuildingCnt() {
        return this.buildingCnt;
    }
    
    public void setBuildingCnt(Short buildingCnt) {
        this.buildingCnt = buildingCnt;
    }

    
    @Column(name="unit_cnt")
    public Short getUnitCnt() {
        return this.unitCnt;
    }
    
    public void setUnitCnt(Short unitCnt) {
        this.unitCnt = unitCnt;
    }

    
    @Column(name="bath_cnt")
    public Short getBathCnt() {
        return this.bathCnt;
    }
    
    public void setBathCnt(Short bathCnt) {
        this.bathCnt = bathCnt;
    }
		@Transient
		public boolean hasBathCount(){
				return bathCnt != null && bathCnt > 0;
		}

    
    @Column(name="noshow_cnt")
    public Short getNoshowCnt() {
        return this.noshowCnt;
    }
    
    public void setNoshowCnt(Short noshowCnt) {
        this.noshowCnt = noshowCnt;
    }

    
    @Column(name="reinsp_cnt")
    public Short getReinspCnt() {
        return this.reinspCnt;
    }
    
    public void setReinspCnt(Short reinspCnt) {
        this.reinspCnt = reinspCnt;
    }

    
    @Column(name="reinsp_dates", length=80)
    public String getReinspDates() {
        return this.reinspDates;
    }
    
    public void setReinspDates(String reinspDates) {
        this.reinspDates = reinspDates;
    }

    
    @Column(name="noshow_dates", length=80)
    public String getNoshowDates() {
        return this.noshowDates;
    }
    
    public void setNoshowDates(String noshowDates) {
        this.noshowDates = noshowDates;
    }

    
    @Column(name="status")
    public String getStatus() {
				if(status == null || status.equals("Unpaid")){
						checkStatus();
				}
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

		@Transient
		public boolean isUnpaid(){
				getStatus();
				return status.equals("Unpaid");
		}
    
    @Column(name="appeal", length=1)
    public Character getAppeal() {
        return this.appeal;
    }
    
    public void setAppeal(Character appeal) {
        this.appeal = appeal;
    }
    
    @Column(name="appeal_fee")
    public BigDecimal getAppealFee() {
        return this.appealFee;
    }
    
    public void setAppealFee(BigDecimal appealFee) {
        this.appealFee = appealFee;
    }
    
    @Column(name="credit", precision=6)
    public BigDecimal getCredit() {
        return this.credit;
    }
    
    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }
    @Transient
		public boolean hasCredit(){
				return credit != null && credit.doubleValue() > 0;
		}
		@Transient
		public double getBalance(){
				return balance;
		}
		@Transient
		public boolean hasBalance(){
				checkBalance();
				return balance > 0.01;
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
    
    @Column(name="summary_flag", length=1)
    public Character getSummaryFlag() {
        return this.summaryFlag;
    }
    
    public void setSummaryFlag(Character summaryFlag) {
        this.summaryFlag = summaryFlag;
    }

    
    @Column(name="idl_flag", length=1)
    public Character getIdlFlag() {
        return this.idlFlag;
    }
    
    public void setIdlFlag(Character idlFlag) {
        this.idlFlag = idlFlag;
    }

    
    @Column(name="summary_cnt")
    public Short getSummaryCnt() {
        return this.summaryCnt;
    }
    
    public void setSummaryCnt(Short summaryCnt) {
        this.summaryCnt = summaryCnt;
    }

    
    @Column(name="idl_cnt")
    public Short getIdlCnt() {
        return this.idlCnt;
    }
    
    public void setIdlCnt(Short idlCnt) {
        this.idlCnt = idlCnt;
    }

		@OneToMany(fetch=FetchType.LAZY, mappedBy="bill")
    public List<Receipt> getReceipts() {
        return this.receipts;
    }
    
    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }
		@Transient
		public boolean hasReceipts(){
				return receipts != null && receipts.size() > 0;
		}
		@Transient
		private void checkStatus(){
				status="Unpaid";
				balance = getTotal();
				getReceipts();
				if(hasReceipts()){
						checkBalance();
						if(balance < 0.01) status = "Paid";
				}
		}
		@Transient
		private void checkBalance(){
				if(status == null || status.equals("Unpaid")){
						double total = getTotal();
						balance = total;
						getReceipts();
						if(hasReceipts()){
								double paid_sum = 0;
								for(Receipt one:receipts){
										paid_sum += one.getReceivedSum().doubleValue();
								}
								balance = total - paid_sum;
						}
				}
		}				
		@Transient
		public String getPropertyTypes(){
				if(propertyTypes.equals("")){
						findPropertyTypes(); // needed for edit
				}
				return propertyTypes;
		}

		@Transient
		public double getInspectionFee(){
				// find total inspection only fee
				// includes buiding rate * cnt + units * cnt + bath*cnt
				// exlude noshow, summary etc
				//
				// Need calculations
				if(buildingCnt != null && buildingRate != null){
						inspectionFee = buildingCnt*(buildingRate.doubleValue());
				}
				// this is for rooming house
				if(hasBathCount()){
						inspectionFee += bathRate.doubleValue()*bathCnt;
				}				
				if(unitRate != null && unitRate != null){
						inspectionFee += unitRate.doubleValue()*unitCnt;
				}
				return inspectionFee;
		}
		@Transient
		public double getReinspFine(){
				double ret = 0;
				if(reinspRate != null && reinspCnt != null){
						ret = reinspCnt*(reinspRate.doubleValue());
				}
				return ret;				
		}
		@Transient
		public boolean hasReinspFine(){
				return getReinspFine() > 0;
		}
		
		@Transient
		public double getNoshowFine(){
				double ret = 0;				
				if(noshowCnt != null && noshowRate != null){
						ret = noshowCnt*noshowRate.doubleValue();
				}
				return ret;
		}
		@Transient
		public boolean hasNoshowFine(){
				return getNoshowFine() > 0;
		}		
		@Transient
		public double getIdlFine(){
				double ret = 0;
				if(idlFlag != null){
						if(idlRate != null){
								if(idlCnt != null && idlCnt > 0){
										ret = idlCnt*idlRate.doubleValue();
								}
								else if(unitCnt != null){
										ret = unitCnt*idlRate.doubleValue();
								}
						}
				}
				return ret;
		}
		@Transient
		public boolean hasIdlFine(){
				return getIdlFine() > 0;
		}
		@Transient
		public boolean hasBhqaFine(){
				return bhqaFine != null && bhqaFine.doubleValue() > 0;
		}
		@Transient
		public double getSummaryFine(){
				double ret = 0;
				if(summaryFlag != null){				
						if(summaryRate != null){
								if(summaryCnt != null && summaryCnt > 0){
										ret = summaryCnt*summaryRate.doubleValue();
								}
								else if(unitCnt != null){
										ret = unitCnt*summaryRate.doubleValue();
								}
						}
				}
				return ret;
		}
		@Transient
		public boolean hasSummaryFine(){
				return getSummaryFine() > 0;
		}				
		@Transient
		public double getTotal(){
				double total = 0;
				try{
						if(appeal != null && appealFee != null){
								total += appealFee.doubleValue();
						}
						else{
								total = getInspectionFee();
								total += getReinspFine();
								total += getNoshowFine();
								total += getIdlFine();
								total += getSummaryFine();
								if(bhqaFine !=null){
										total += bhqaFine.doubleValue();
								}
								if(credit != null){
										total -= credit.doubleValue();
								}
						}
				}catch(Exception ex){
						System.err.println(ex);
				}
				return total;
		}
		@Transient
		private void findPropertyTypes(){
				getRental();
				propertyTypes = "";
				PropertyType type = null;
				if(rental == null){
						System.err.println(" rental is null");
				}
				if(rental != null){
						List<RentalStructure> buildings = rental.getRentalStructures();
						if(buildings == null){
								System.err.println(" buildings is null");
						}						
						if(buildings != null){
								System.err.println(" building size "+buildings.size());
								for(RentalStructure one:buildings){
										type = one.getPropertyType();
										if(type == null){
												System.err.println(" type is null");
										}										
										if(type != null){
												if(!propertyTypes.equals("")) propertyTypes +=", ";
												propertyTypes += type.getName();												
										}
								}
						}
				}
				System.err.println("Types "+propertyTypes);
		}
		@Transient
		public void setRentalInfo(){
				
				getRental();
				PropertyType type = null;
				if(rental != null){
						List<RentalStructure> buildings = rental.getRentalStructures();
						if(buildings != null){
								short size=0;
								size = (short)buildings.size();
								buildingCnt = new Short(size);
								// we use the first one
								size = 0;
								short size2=0;
								for(RentalStructure one:buildings){
										type = one.getPropertyType();
										if(type != null){
												if(!propertyTypes.equals("")) propertyTypes +=", ";
												propertyTypes += type.getName();												
												if(type.getName().startsWith("Rooming")){
														if(one.hasUnits()){
																List<RentalUnit> units = one.getRentalUnits();
																for(RentalUnit one2:units){
																		size += one2.getBedrooms(); // baths
																}
														}
												}
												else{
														if(one.hasUnits()){
																size2 += (short)(one.getRentalUnits().size());
														}
												}
										}
								}
								bathCnt = new Short(size);
								unitCnt = new Short(size2);										
						}
				}
		}
		@Transient
		public double getAmountDue(){
				double total = 0, amount_due=0, amount_paid=0;
				if(isUnpaid()){
						total = getTotal();
						getReceipts();
						if(receipts != null && receipts.size() > 0){
								for(Receipt one:receipts){
										if(one.getReceivedSum() != null){
												amount_paid += one.getReceivedSum().doubleValue();
										}
								}
						}
						amount_due = total - amount_paid;
						if(amount_due < 0.01) amount_due = 0;
				}
				return amount_due;
		}

}


