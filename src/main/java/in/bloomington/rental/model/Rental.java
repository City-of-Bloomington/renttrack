package in.bloomington.rental.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Entity
@Table(name = "rentals")
public class Rental implements java.io.Serializable
{
    private static final long   serialVersionUID = 1L;
    private static final Logger           logger = LogManager.getLogger(Rental.class);
    private static final SimpleDateFormat dtf    = new SimpleDateFormat("MM/dd/yyyy");
    
    private int                   id;
    private Owner                 agent;
    private RentalStatus          rentalStatus;
    private Zoning                zoning;
    private Date                  registeredDate;
    private Date                  lastCycleDate;
    private Date                  permitIssued;
    private Date                  permitExpires;
    private Short                 permitLength;
    private Character             grandfathered;
    private Character             cdbgFunding;
    private Short                 NHood;
    private Character             affordable;
    private Character             inactive;
    private List<RentalOwner>     rentalOwners     = new ArrayList<RentalOwner>();
    private List<Variance>        variances        = new ArrayList<Variance>();
    private List<Address>         addresses        = new ArrayList<Address>();
    private List<RentalStructure> rentalStructures = new ArrayList<RentalStructure>();
    private List<RentalNote>      rentalNotes      = new ArrayList<RentalNote>();
    private List<PullHistory>     pullHistories    = new ArrayList<PullHistory>();
    private List<Bill>            bills            = new ArrayList<Bill>();
    private List<Attachment>      attachments      = new ArrayList<Attachment>();
    private List<RentalLog>       rentalLogs       = new ArrayList<RentalLog>();
    private List<LegalItEmailLog> legalItEmailLogs = new ArrayList<LegalItEmailLog>();
    private List<Inspection>      inspections      = new ArrayList<Inspection>();
    private List<RentalLegal>     rentalLegals     = new ArrayList<RentalLegal>();
    @Transient
    private List<Owner>           owners           = new ArrayList<Owner>();

    public Rental()
    {
    }

    public Rental(int id)
    {
        this.id = id;
    }

    public Rental(int id, Owner         agent,
                          RentalStatus  rentalStatus,
                          Zoning        zoning,
                          Date          registeredDate,
                          Date          lastCycleDate,
                          Date          permitIssued,
                          Date          permitExpires,
                          Short         permitLength,
                          Character     grandfathered,
                          Character     cdbgFunding,
                          Short         NHood,
                          Character     affordable,
                          Character     inactive,
                  List<RentalOwner>     rentalOwners,
                  List<Variance>        variances,
                  List<RentalStructure> rentalStructures,
                  List<RentalNote>      rentalNotes,
                  List<PullHistory>     pullHistories,
                  List<Bill>            bills,
                  List<Attachment>      attachments,
                  List<RentalLog>       rentalLogs,
                  List<LegalItEmailLog> legalItEmailLogs,
                  List<RentalLegal>     rentalLegals)
    {
        /*
         * Set<Inspection> inspections
         *
         */
        this.id               = id;
        this.agent            = agent;
        this.rentalStatus     = rentalStatus;
        this.zoning           = zoning;
        this.registeredDate   = registeredDate;
        this.lastCycleDate    = lastCycleDate;
        this.permitIssued     = permitIssued;
        this.permitExpires    = permitExpires;
        this.permitLength     = permitLength;
        this.grandfathered    = grandfathered;
        this.cdbgFunding      = cdbgFunding;
        this.NHood            = NHood;
        this.affordable       = affordable;
        this.inactive         = inactive;
        this.rentalOwners     = rentalOwners;
        this.variances        = variances;
        this.rentalStructures = rentalStructures;
        this.rentalNotes      = rentalNotes;
        this.pullHistories    = pullHistories;
        this.bills            = bills;
        this.attachments      = attachments;
        this.rentalLogs       = rentalLogs;
        this.legalItEmailLogs = legalItEmailLogs;
        this.rentalLegals     = rentalLegals;
        /*
         *
         * this.inspections = inspections;
         */
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agent_id")
    public Owner getAgent()
    {
        return this.agent;
    }

    public void setAgent(Owner agent)
    {
        this.agent = agent;
    }

    @Transient
    public Integer getAgentId()
    {
        if (agent != null) return agent.getId();
        return null;
    }

    @Transient
    public void setAgentId(Integer val)
    {
        if (val != null && !val.equals("")) this.agent = new Owner(val);
    }

    @Transient
    public void setAgent_name(String val)
    {
        // needed for auto_complete
    }

    @Transient
    public boolean hasAgent()
    {
        return agent != null;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    @NotNull(message = "{rental.status.empty}")
    // @Min(value=1, message = "{rental.status.invalid}")
    public RentalStatus getRentalStatus()
    {
        return this.rentalStatus;
    }

    public void setRentalStatus(RentalStatus rentalStatus)
    {
        this.rentalStatus = rentalStatus;
    }

    @Transient
    public void setStatus_id(Integer val)
    {
        if (val != null && val > 0) {
            rentalStatus = new RentalStatus(val);
        }
    }

    @Transient
    public int getStatus_id()
    {
        if (rentalStatus != null) return rentalStatus.getId();
        return 0;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "zoning_id")
    @NotNull(message = "{rental.zoning.empty}")
    public Zoning getZoning()
    {
        return this.zoning;
    }

    public void setZoning(Zoning zoning)
    {
        this.zoning = zoning;
    }

    @Transient
    public void setZoning_id(Integer val)
    {
        if (val != null && val > 0) {
            zoning = new Zoning(val);
        }
    }

    @Transient
    public int getZoning_id()
    {
        if (zoning != null) return zoning.getId();
        return 0;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "registered_date", length = 13)
    public Date getRegisteredDate()
    {
        return this.registeredDate;
    }

    @Transient
    public String getRegisteredDateFr()
    {
        if (registeredDate != null) {
            return dtf.format(registeredDate);
        }
        return "";
    }

    @Transient
    public void setRegisteredDateFr(String val)
    {
        if (val != null && !val.trim().equals("")) {
            try {
                this.registeredDate = dtf.parse(val);
            }
            catch (Exception ex) {
                logger.error(ex);
            }
        }
    }

    public void setRegisteredDate(Date val)
    {
        this.registeredDate = val;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "last_cycle_date", length = 13)
    public Date getLastCycleDate()
    {
        return this.lastCycleDate;
    }

    @Transient
    public String getLastCycleDateFr()
    {
        if (lastCycleDate != null) return dtf.format(lastCycleDate);
        return "";
    }

    @Transient
    public void setLastCycleDateFr(String val)
    {
        if (val != null && !val.trim().equals("")) {
            try {
                this.lastCycleDate = dtf.parse(val);
            }
            catch (Exception ex) {
                logger.error(ex);
            }
        }
    }

    public void setLastCycleDate(Date lastCycleDate)
    {
        this.lastCycleDate = lastCycleDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "permit_issued", length = 13)
    public Date getPermitIssued()
    {
        return this.permitIssued;
    }

    public void setPermitIssued(Date permitIssued)
    {
        this.permitIssued = permitIssued;
    }

    @Transient
    public String getPermitIssuedFr()
    {
        if (permitIssued != null) return dtf.format(permitIssued);
        return "";
    }

    public void setPermitIssuedFr(String val)
    {
        if (val != null && !val.trim().equals("")) {
            try {
                this.permitIssued = dtf.parse(val);
            }
            catch (Exception ex) {
                logger.error(ex);
            }
        }
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "permit_expires", length = 13)
    public Date getPermitExpires()
    {
        return this.permitExpires;
    }

    public void setPermitExpires(Date permitExpires)
    {
        this.permitExpires = permitExpires;
    }

    @Transient
    public String getPermitExpiresFr()
    {
        if (permitExpires != null) return dtf.format(permitExpires);
        return "";
    }

    public void setPermitExpiresFr(String val)
    {
        if (val != null && !val.trim().equals("")) {
            try {
                this.permitExpires = dtf.parse(val);
            }
            catch (Exception ex) {
                logger.error(ex);
            }
        }
    }

    @Column(name = "permit_length")
    public Short getPermitLength()
    {
        return this.permitLength;
    }

    public void setPermitLength(Short permitLength)
    {
        this.permitLength = permitLength;
    }

    @Column(name = "grandfathered", length = 1)
    public Character getGrandfathered()
    {
        return this.grandfathered;
    }

    public void setGrandfathered(Character grandfathered)
    {
        this.grandfathered = grandfathered;
    }

    @Column(name = "cdbg_funding", length = 1)
    public Character getCdbgFunding()
    {
        return this.cdbgFunding;
    }

    public void setCdbgFunding(Character cdbgFunding)
    {
        this.cdbgFunding = cdbgFunding;
    }

    @Column(name = "n_hood")
    public Short getNHood()
    {
        return this.NHood;
    }

    public void setNHood(Short NHood)
    {
        if (NHood != null && NHood > 0) this.NHood = NHood;
    }

    @Column(name = "affordable", length = 1)
    public Character getAffordable()
    {
        return this.affordable;
    }

    public void setAffordable(Character affordable)
    {
        this.affordable = affordable;
    }

    @Transient
    public String getFeatures()
    {
        String features = "";
        if (affordable != null && !affordable.equals("")) features += "affordable";
        if (cdbgFunding != null && !cdbgFunding.equals("")) {
            if (!features.equals("")) features += ", ";
            features += "CDBG Funding";
        }
        if (grandfathered != null && !grandfathered.equals("")) {
            if (!features.equals("")) features += ", ";
            features += "Grandfathered";
        }
        return features;
    }

    public void setFeatures(String val)
    {
        // just ignore
    }

    @Column(name = "inactive", length = 1)
    public Character getInactive()
    {
        return this.inactive;
    }

    public void setInactive(Character inactive)
    {
        this.inactive = inactive;
    }

    @Transient
    public boolean getActive()
    {
        return inactive == null || inactive.equals("");
    }

    public void setInactive(boolean val)
    {
        if (!val) inactive = 'y';
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rental")
    @OrderBy("id ASC")
    public List<RentalStructure> getRentalStructures()
    {
        return this.rentalStructures;
    }

    public void setRentalStructures(List<RentalStructure> rentalStructures)
    {
        this.rentalStructures = rentalStructures;
    }

    @Transient
    public int getStructureCount()
    {
        int cnt = 0;
        if (hasStructures()) {
            cnt = rentalStructures.size();
        }
        return cnt;
    }

    @Transient
    public boolean hasStructures()
    {
        return rentalStructures.size() > 0;
    }

    @Transient
    public boolean hasPullHistory()
    {
        getPullHistories();
        return pullHistories.size() > 0;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rental")
    @OrderBy("id desc")
    public List<Attachment> getAttachments()
    {
        return this.attachments;
    }

    public void setAttachments(List<Attachment> attachments)
    {
        this.attachments = attachments;
    }

    @Transient
    public boolean hasAttachments()
    {
        return attachments != null && attachments.size() > 0;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rental")
    @OrderBy("id DESC")
    public List<RentalLog> getRentalLogs()
    {
        return this.rentalLogs;
    }

    public void setRentalLogs(List<RentalLog> rentalLogs)
    {
        this.rentalLogs = rentalLogs;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rental")
    @OrderBy("id DESC")
    public List<RentalLegal> getRentalLegals()
    {
        return rentalLegals;
    }

    public void setRentalLegals(List<RentalLegal> rentalLegals)
    {
        this.rentalLegals = rentalLegals;
    }

    @Transient
    public boolean hasLegals()
    {
        return rentalLegals != null && rentalLegals.size() > 0;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rental")
    public List<LegalItEmailLog> getLegalItEmailLogs()
    {
        return this.legalItEmailLogs;
    }

    public void setLegalItEmailLogs(List<LegalItEmailLog> legalitEmailLogs)
    {
        this.legalItEmailLogs = legalitEmailLogs;
    }

    @Transient
    public boolean hasLegalEmailLogs()
    {
        return legalItEmailLogs != null && legalItEmailLogs.size() > 0;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rental")
    @OrderBy("id DESC")
    public List<PullHistory> getPullHistories()
    {
        return this.pullHistories;
    }

    public void setPullHistories(List<PullHistory> pullHistories)
    {
        this.pullHistories = pullHistories;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rental")
    @OrderBy("id DESC")
    public List<RentalNote> getRentalNotes()
    {
        return this.rentalNotes;
    }

    public void setRentalNotes(List<RentalNote> rentalNotes)
    {
        this.rentalNotes = rentalNotes;
    }

    @Transient
    public boolean hasNotes()
    {
        getRentalNotes();
        return rentalNotes != null && rentalNotes.size() > 0;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "rental")
    public List<RentalOwner> getRentalOwners()
    {
        return this.rentalOwners;
    }

    public void setRentalOwners(List<RentalOwner> rentalOwners)
    {
        this.rentalOwners = rentalOwners;
        if (rentalOwners != null && rentalOwners.size() > 0) {
            for (RentalOwner one : rentalOwners) {
                owners.add(one.getOwner());
            }
        }
    }

    @Transient
    public List<Owner> getOwners()
    {
        return owners;
    }

    @Transient
    public String getOwnersInfo()
    {
        String ret = "";
        getRentalOwners();
        if (rentalOwners != null) {
            for (RentalOwner one : rentalOwners) {
                if (!ret.equals("")) ret += ", ";
                ret += one.getOwner().getName();
            }
        }
        return ret;
    }

    @Transient
    public boolean hasOwners()
    {
        getRentalOwners();
        return rentalOwners != null && rentalOwners.size() > 0;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rental")
    public List<Variance> getVariances()
    {
        return this.variances;
    }

    public void setVariances(List<Variance> variances)
    {
        this.variances = variances;
    }

    public boolean hasVariances()
    {
        return variances != null && variances.size() > 0;
    }

    //
    // @OneToMany(fetch=FetchType.LAZY, mappedBy="rental")
    @Transient
    public List<Inspection> getInspections()
    {
        return this.inspections;
    }

    @Transient
    public void setInspections(List<Inspection> inspections)
    {
        this.inspections = inspections;
    }

    @Transient
    public boolean hasInspections()
    {
        return inspections != null && inspections.size() > 0;
    }

    @Transient
    public boolean hasUnispected()
    {
        boolean ret = false;
        if (hasStructures()) {
            for (RentalStructure one : rentalStructures) {
                if (one.hasUninspected()) return true;
            }
        }
        return ret;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rental")
    @OrderBy("id DESC")
    public List<Bill> getBills()
    {
        return this.bills;
    }

    public void setBills(List<Bill> bills)
    {
        this.bills = bills;
    }

    @Transient
    public String getRentalAddresses()
    {
        String all = "";
        if (!hasAddresses()) {
            prepareAddresses();
        }
        if (addresses.size() > 0) {
            for (Address one : addresses) {
                String str = one.getStreetAddress();
                if (str != null) {
                    if (all.indexOf(str) == -1) {
                        if (!all.equals("")) all += ", ";
                        all += str;
                    }
                }
            }
        }
        return all;
    }

    @Transient
    public boolean hasBills()
    {
        getBills();
        return bills != null && bills.size() > 0;
    }

    @Transient
    public void prepareAddresses()
    {
        getAddresses();
        if (addresses == null || addresses.size() == 0) {
            getRentalStructures();
            if (rentalStructures != null) {
                for (RentalStructure one : rentalStructures) {
                    List<RentalUnit> units = one.getRentalUnits();
                    if (units != null && units.size() > 0) {
                        for (RentalUnit one2 : units) {
                            if (one2.hasAddress()) {
                                Address adr = one2.getAddress();
                                if (adr != null) {
                                    if (!addresses.contains(adr)) {
                                        addresses.add(adr);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Transient
    public List<Address> getAddresses()
    {
        return this.addresses;
    }

    @Transient
    public void setAddresses(List<Address> addresses)
    {
        if (addresses != null) this.addresses = addresses;
    }

    @Transient
    public boolean hasAddresses()
    {
        return addresses != null && addresses.size() > 0;
    }

    @Transient
    public boolean hasLogs()
    {
        return rentalLogs != null && rentalLogs.size() > 0;
    }

    @Transient
    public int getUnitCount()
    {
        int ret = 0;
        if (rentalStructures != null) {
            for (RentalStructure one : rentalStructures) {
                ret += one.getUnitsCount();
            }
        }
        return ret;
    }

    @Override
    public String toString()
    {
        String ret = "" + id;
        return ret;
    }

    @Override
    public boolean equals(Object o)
    {

        if (!(o instanceof Rental)) {
            return false;
        }
        Rental rental = (Rental) o;
        return (rental.getId() == id);
    }

    @Override
    public int hashCode()
    {
        int ret = 17;
        if (id > 0) ret += 37 * id;
        return ret;
    }
}
