package in.bloomington.rental.model;



import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.OrderBy;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name="rental_structures")
public class RentalStructure  implements java.io.Serializable {

		private int id;
		private BuildingType buildingType;
		private PropertyType propertyType;
		private Rental rental;
		private String identifier;
		private String foundation;
		private Short storyCnt;
		private Short yearBuilt;
		private String heatSource;
		private Integer egressHeight, egressWidth,egressSillHeight;
		private Double egressArea;
		private String egressDecreeYears;
		private List<RentalUnit> rentalUnits = new ArrayList<>(0);
		
    public RentalStructure() {
    }
	
    public RentalStructure(int id) {
        this.id = id;
    }
    public RentalStructure(int id,
													 BuildingType buildingType,
													 PropertyType propertyType,
													 Rental rental,
													 String identifier,
													 String foundation,
													 Short storyCnt,
													 Short yearBuilt,
													 String heatSource,
													 Integer egressHeight,
													 Integer egressWidth,
													 Integer egressSillHeight,
													 Double egressArea,
													 String egressDecreeYears,
													 List<RentalUnit> rentalUnits) {
				this.id = id;
				this.buildingType = buildingType;
				this.propertyType = propertyType;
				this.rental = rental;
				this.identifier = identifier;
				this.rentalUnits = rentalUnits;
				this.foundation = foundation;
				this.storyCnt = storyCnt;
				this.yearBuilt = yearBuilt;
				this.heatSource = heatSource;
				this.egressHeight = egressHeight;
				this.egressWidth = egressWidth;
				this.egressSillHeight = egressSillHeight;
				this.egressArea = egressArea;
				this.egressDecreeYears = egressDecreeYears;
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

		@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="building_type_id")
    public BuildingType getBuildingType() {
        return this.buildingType;
    }
    
    public void setBuildingType(BuildingType buildingType) {
        this.buildingType = buildingType;
    }

		@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="prop_type_id")
    public PropertyType getPropertyType() {
        return this.propertyType;
    }
    
    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

		@Transient
		public void setPropertyTypeId(int val){
				if(val > 0){
						propertyType = new PropertyType(val);
				}
		}
		@Transient
		public int getPropertyTypeId(){
				if(propertyType != null){
						return propertyType.getId();
				}
				return 0;
		}
		@Transient
		public void setBuildingTypeId(int val){
				if(val > 0){
						buildingType = new BuildingType(val);
				}
		}
		@Transient
		public int getBuildingTypeId(){
				if(buildingType != null){
						return buildingType.getId();
				}
				return 0;
		}
		@Transient
		public int getRentalId(){
				if(rental != null){
						return rental.getId();
				}
				return 0;
		}
		@Transient
		public void setRentalId(int val){
				if(rental == null){
						rental = new Rental(val);
				}
		}		
		@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="rental_id")
    public Rental getRental() {
        return this.rental;
    }
    
    public void setRental(Rental rental) {
        this.rental = rental;
    }
    
    @Column(name="identifier", length=30)
    public String getIdentifier() {
        return this.identifier;
    }
    
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

		@OneToMany(fetch=FetchType.LAZY, mappedBy="rentalStructure")
		@OrderBy("id ASC")
    public List<RentalUnit> getRentalUnits() {
        return this.rentalUnits;
    }
    
    public void setRentalUnits(List<RentalUnit> rentalUnits) {
        this.rentalUnits = rentalUnits;
    }

    @Column(name="foundation")
    public String getFoundation() {
        return this.foundation;
    }
    
    public void setFoundation(String foundation) {
        this.foundation = foundation;
    }
    @Column(name="egress_decree_years")
    public String getEgressDecreeYears() {
        return this.egressDecreeYears;
    }
    
    public void setEgressDecreeYears(String val) {
        this.egressDecreeYears = val;
    }		
    
    @Column(name="story_cnt")
    public Short getStoryCnt() {
        return this.storyCnt;
    }
    
    public void setStoryCnt(Short storyCnt) {
        this.storyCnt = storyCnt;
    }
    @Column(name="year_built")
    public Short getYearBuilt() {
        return this.yearBuilt;
    }
    
    public void setYearBuilt(Short val) {
        this.yearBuilt = val;
    }		
    
    @Column(name="heat_source")
    public String getHeatSource() {
        return this.heatSource;
    }
    
    public void setHeatSource(String heatSource) {
        this.heatSource = heatSource;
    }
    @Column(name="egress_height")
    public Integer getEgressHeight() {
        return this.egressHeight;
    }
    
    public void setEgressHeight(Integer val) {
        this.egressHeight = val;
    }
    @Column(name="egress_width")
    public Integer getEgressWidth() {
        return this.egressWidth;
    }
    
    public void setEgressWidth(Integer val) {
        this.egressWidth = val;
    }				
    @Column(name="egress_sill_height")
    public Integer getEgressSillHeight() {
        return this.egressSillHeight;
    }
    
    public void setEgressSillHeight(Integer val) {
        this.egressSillHeight = val;
    }
    @Column(name="egress_area")
    public Double getEgressArea() {
        return this.egressArea;
    }
    
    public void setEgressArea(Double val) {
        this.egressArea = val;
    }
		// all these measures must be available
		@Transient
		public boolean hasEgressInfo(){
				return egressHeight != null &&
						egressWidth != null &&
						egressSillHeight != null && 
						egressArea != null;
		}
		@Transient
		public String getEgressInfo(){
				String ret = "";
				if(egressDecreeYears != null && !egressDecreeYears.equals("")){
						ret += "Related egress years "+egressDecreeYears;
				}
				ret += " height:"+egressHeight+", width:"+egressWidth+", sill height: "+egressSillHeight+", area :"+egressArea; 
				return ret;
		}
		@Transient
		public boolean hasUnits(){
				return this.rentalUnits.size() > 0;
		}

		@Transient
		public int getUnitsCount(){
				return this.rentalUnits.size();
		}

		//
		// this related to units 
		@Transient
		public boolean hasUninspected(){
				boolean ret = false;
				if(hasUnits()){
						for(RentalUnit one:rentalUnits){
								if(one.isNotInspected()) return true;
						}
				}
				return ret;
		}

}


