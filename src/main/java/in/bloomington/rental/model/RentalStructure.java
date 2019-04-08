package in.bloomington.rental.model;

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
@Table(name = "rental_structures")
public class RentalStructure implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int              id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "building_type_id")
    private BuildingType     buildingType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prop_type_id")
    private PropertyType     propertyType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_id")
    private Rental           rental;

    @Column(name = "identifier")
    private String           identifier;

    @Column(name = "foundation")
    private String           foundation;

    @Column(name = "story_cnt")
    private Short            storyCnt;

    @Column(name = "year_built")
    private Short            yearBuilt;

    @Column(name = "heat_source")
    private String           heatSource;

    @Column(name = "egress_height")
    private Integer          egressHeight;

    @Column(name = "egress_width")
    private Integer          egressWidth;

    @Column(name = "egress_sill_height")
    private Integer          egressSillHeight;

    @Column(name = "egress_area")
    private Float            egressArea;

    @Column(name = "egress_area2")
    private Float            egressArea2;

    @Column(name = "egress_decree_years")
    private String           egressDecreeYears;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rentalStructure")
    @OrderBy("id ASC")
    private List<RentalUnit> rentalUnits = new ArrayList<RentalUnit>();

    public RentalStructure()
    {
    }

    public RentalStructure(int id)
    {
        this.id = id;
    }

    public RentalStructure(int id, BuildingType buildingType,
                                   PropertyType propertyType,
                                   Rental       rental,
                                   String       identifier,
                                   String       foundation,
                                   Short        storyCnt,
                                   Short        yearBuilt,
                                   String       heatSource,
                                   Integer      egressHeight,
                                   Integer      egressWidth,
                                   Integer      egressSillHeight,
                                   Float        egressArea,
                                   Float        egressArea2,
                                   String       egressDecreeYears,
                         List<RentalUnit>       rentalUnits)
    {
        this.id                = id;
        this.buildingType      = buildingType;
        this.propertyType      = propertyType;
        this.rental            = rental;
        this.identifier        = identifier;
        this.rentalUnits       = rentalUnits;
        this.foundation        = foundation;
        this.storyCnt          = storyCnt;
        this.yearBuilt         = yearBuilt;
        this.heatSource        = heatSource;
        this.egressHeight      = egressHeight;
        this.egressWidth       = egressWidth;
        this.egressSillHeight  = egressSillHeight;
        this.egressArea        = egressArea;
        this.egressArea2       = egressArea2;
        this.egressDecreeYears = egressDecreeYears;
    }

    //-----------------------------------------------------
    // Generic Getters and Setters
    //-----------------------------------------------------
    public int          getId               () { return this.id; }
    public BuildingType getBuildingType     () { return this.buildingType; }
    public PropertyType getPropertyType     () { return this.propertyType; }
    public Rental       getRental           () { return this.rental; }
    public String       getIdentifier       () { return this.identifier; }
    public String       getFoundation       () { return this.foundation; }
    public String       getEgressDecreeYears() { return this.egressDecreeYears;}
    public Short        getStoryCnt         () { return this.storyCnt; }
    public Short        getYearBuilt        () { return this.yearBuilt; }
    public String       getHeatSource       () { return this.heatSource; }
    public Integer      getEgressHeight     () { return this.egressHeight; }
    public Integer      getEgressWidth      () { return this.egressWidth; }
    public Integer      getEgressSillHeight () { return this.egressSillHeight; }
    public Float        getEgressArea       () { return this.egressArea; }
    public Float        getEgressArea2      () { return this.egressArea2; }
    public List<RentalUnit> getRentalUnits  () { return this.rentalUnits; }

    public void setId               (int         id) { this.id                = id; }
    public void setBuildingType     (BuildingType t) { this.buildingType      = t; }
    public void setPropertyType     (PropertyType t) { this.propertyType      = t; }
    public void setRental           (Rental       r) { this.rental            = r; }
    public void setIdentifier       (String       s) { this.identifier        = s; }
    public void setFoundation       (String       s) { this.foundation        = s; }
    public void setEgressDecreeYears(String       s) { this.egressDecreeYears = s; }
    public void setStoryCnt         (Short        i) { this.storyCnt          = i; }
    public void setYearBuilt        (Short        i) { this.yearBuilt         = i; }
    public void setHeatSource       (String       s) { this.heatSource        = s; }
    public void setEgressHeight     (Integer      i) { this.egressHeight      = i; }
    public void setEgressWidth      (Integer      i) { this.egressWidth       = i; }
    public void setEgressSillHeight (Integer      i) { this.egressSillHeight  = i; }
    public void setEgressArea       (Float        d) { this.egressArea        = d; }
    public void setEgressArea2      (Float        d) { this.egressArea2       = d; }
    public void setRentalUnits      (List<RentalUnit> u) { this.rentalUnits   = u; }

    //-----------------------------------------------------
    // Transients
    //-----------------------------------------------------
    @Transient
    public void setPropertyTypeId(int val)
    {
        if (val > 0) {
            propertyType = new PropertyType(val);
        }
    }

    @Transient
    public int getPropertyTypeId()
    {
        if (propertyType != null) {
            return propertyType.getId();
        }
        return 0;
    }

    @Transient
    public void setBuildingTypeId(int val)
    {
        if (val > 0) {
            buildingType = new BuildingType(val);
        }
    }

    @Transient
    public int getBuildingTypeId()
    {
        if (buildingType != null) {
            return buildingType.getId();
        }
        return 0;
    }

    @Transient
    public int getRentalId()
    {
        if (rental != null) {
            return rental.getId();
        }
        return 0;
    }

    @Transient
    public void setRentalId(int val)
    {
        if (rental == null) {
            rental = new Rental(val);
        }
    }


    @Transient
    public void setYearBuiltType(String val)
    {
        // needed for auto_complete
    }

    @Transient
    public String getYearBuiltType()
    {
        return "";
    }


    // all these measures must be available
    @Transient
    public boolean hasEgressInfo()
    {
        return egressHeight     != null
            && egressWidth      != null
            && egressSillHeight != null
            && egressArea       != null;
    }

    @Transient
    public String getEgressInfo()
    {
        String ret = "";
        if (egressDecreeYears != null && !egressDecreeYears.equals("")) {
            ret += "Related egress years " + egressDecreeYears;
        }
        ret += " height:"       + egressHeight
            + ", width:"        + egressWidth
            + ", sill height: " + egressSillHeight
            + ", area :"        + egressArea;
        if (egressArea2 != null) {
            ret += " area (other floors) :" + egressArea2;
        }
        return ret;
    }

    @Transient
    public boolean hasUnits()
    {
        return this.rentalUnits.size() > 0;
    }

    @Transient
    public int getUnitsCount()
    {
        return this.rentalUnits.size();
    }

    //
    // this related to units
    @Transient
    public boolean hasUninspected()
    {
        boolean ret = false;
        if (hasUnits()) {
            for (RentalUnit one : rentalUnits) {
                if (one.isNotInspected()) return true;
            }
        }
        return ret;
    }
}
