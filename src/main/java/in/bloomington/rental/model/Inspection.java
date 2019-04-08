package in.bloomington.rental.model;

import java.math.BigDecimal;
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import in.bloomington.rental.util.Helper;

@Entity
@Table(name = "inspections")

public class Inspection implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;

    SimpleDateFormat            dtf            = new SimpleDateFormat("MM/dd/yyyy");
    private static final Logger logger         = LogManager.getLogger(Inspection.class);
    private int                 id;
    private Rental              rental;
    private RentUser            inspector;
    private Date                inspectionDate;
    private InspectionType      inspectionType;
    private Date                complianceDate;
    private Short               violations;
    private String              inspectFile;
    private String              comments;

    //
    // these four will be removed in production
    //
    private String              foundation;
    private Short               storyCnt;
    private String              heatSource;

    private Character           atticAccess;

    private String              accessory;
    private Short               smokeDetectors;
    private Short               lifeSafety;
    private BigDecimal          timeSpent;
    private String              timeStatus;

    private Character           cancelled;
    private Character           approved;
    private RentUser            approver;
    private Date                approvedDate;

    private List<Attachment>    attachments    = new ArrayList<Attachment>();
    private List<InspectionCan> inspectionCans = new ArrayList<InspectionCan>();

    @Transient
    private InspectionTemplate  template;

    public Inspection()
    {
    }

    public Inspection(int id)
    {
        this.id = id;
    }

    public Inspection(int id, Rental         rental,
                              RentUser       inspector,
                              Date           inspectionDate,
                              InspectionType inspectionType,
                              Date           complianceDate,
                              Short          violations,
                              String         inspectFile,
                              String         comments,
                              String         accessory,
                              Character      atticAccess,
                              String         foundation,
                              Short          storyCnt,
                              String         heatSource,
                              Short          smokeDetectors,
                              Short          lifeSafety,
                              BigDecimal     timeSpent,
                              String         timeStatus,
                              Character      cancelled,
                              Character      approved,
                              RentUser       approver,
                              Date           approvedDate,
                      List<Attachment>       attachments,
                    List<InspectionCan>      inspectionCans)
    {
        this.id             = id;
        this.rental         = rental;
        this.inspector      = inspector;
        this.inspectionDate = inspectionDate;
        this.inspectionType = inspectionType;
        this.complianceDate = complianceDate;
        this.violations     = violations;
        this.inspectFile    = inspectFile;
        this.comments       = comments;

        this.foundation     = foundation;
        this.atticAccess    = atticAccess;
        this.storyCnt       = storyCnt;
        this.heatSource     = heatSource;

        this.accessory      = accessory;
        this.smokeDetectors = smokeDetectors;
        this.lifeSafety     = lifeSafety;
        this.timeSpent      = timeSpent;
        this.timeStatus     = timeStatus;

        this.cancelled      = cancelled;
        this.approved       = approved;
        this.approver       = approver;
        this.approvedDate   = approvedDate;

        this.attachments   = attachments;
        this.inspectionCans = inspectionCans;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_id", nullable = false)
    public Rental getRental()
    {
        return this.rental;
    }

    public void setRental(Rental rental)
    {
        this.rental = rental;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inspected_by", nullable = false)
    public RentUser getInspector()
    {
        return this.inspector;
    }

    public void setInspector(RentUser val)
    {
        this.inspector = val;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approved_by")
    public RentUser getApprover()
    {
        return this.approver;
    }

    public void setApprover(RentUser val)
    {
        this.approver = val;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "approved_date", length = 13)
    public Date getApprovedDate()
    {
        return this.approvedDate;
    }

    public void setApprovedDate(Date val)
    {
        this.approvedDate = val;
    }

    @Transient
    public void setApprovedDateFr(String val)
    {
        if (val != null) {
            try {
                approvedDate = dtf.parse(val);
            }
            catch (Exception ex) {

            }
        }
    }

    @Transient
    public String getApprovedDateFr()
    {
        if (approvedDate != null) {
            return dtf.format(approvedDate);
        }
        return "";
    }

    @Transient
    public boolean isCycleType()
    {
        boolean ret = false;
        if (inspectionType != null) return inspectionType.getName().startsWith("Cycle");
        return ret;
    }

    @Transient
    public boolean hasComplianceDate()
    {
        return complianceDate != null;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "inspection_date", length = 13)
    public Date getInspectionDate()
    {
        if (id == 0) {
            this.inspectionDate = new Date();
        }
        return this.inspectionDate;
    }

    public void setInspectionDate(Date inspectionDate)
    {
        this.inspectionDate = inspectionDate;
    }

    @Transient
    public String getInspectionDateFr()
    {
        getInspectionDate();
        if (inspectionDate != null) {
            return dtf.format(inspectionDate);
        }
        return "";
    }

    @Transient
    public void setInspectionDateFr(String val)
    {
        if (val != null) {
            try {
                inspectionDate = dtf.parse(val);
            }
            catch (Exception ex) {

            }
        }
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inspection_type_id", nullable = false)
    public InspectionType getInspectionType()
    {
        return this.inspectionType;
    }

    public void setInspectionType(InspectionType inspectionType)
    {
        this.inspectionType = inspectionType;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "compliance_date", length = 13)
    public Date getComplianceDate()
    {
        return this.complianceDate;
    }

    public void setComplianceDate(Date complianceDate)
    {
        this.complianceDate = complianceDate;
    }

    @Transient
    public String getComplianceDateFr()
    {
        if (complianceDate != null) {
            return dtf.format(complianceDate);
        }
        return "";
    }

    @Transient
    public void setComplianceDateFr(String val)
    {
        if (val != null) {
            try {
                complianceDate = dtf.parse(val);
            }
            catch (Exception ex) {

            }
        }
    }

    @Column(name = "violations")
    public Short getViolations()
    {
        return this.violations;
    }

    public void setViolations(Short violations)
    {
        this.violations = violations;
    }

    @Column(name = "inspect_file", length = 70)
    public String getInspectFile()
    {
        return this.inspectFile;
    }

    public void setInspectFile(String inspectFile)
    {
        this.inspectFile = inspectFile;
    }

    @Transient
    public boolean hasFile()
    {
        return inspectFile != null && !inspectFile.equals("");
    }

    // since file name is saved in the following format
    // yyyy/mm/rentId_inspectionType_seq.doc
    // remove path from the file name so that we get
    // file_inspection_type.doc
    // example
    // 2017/09/1234cy_003.doc
    @Transient
    public String getFileNameOnly()
    {
        String ret = "";
        if (inspectFile != null) {
            if (inspectFile.indexOf("/") > -1) {
                ret = inspectFile.substring(inspectFile.lastIndexOf("/") + 1);
            }
            else {
                ret = inspectFile;
            }
        }
        return ret;
    }

    // we create the file after the inspection is saved
    // so we can use rental_id, inspection_type and
    // last 3 digits of number of inspections
    // 1234cy_001, 1234cy_002 and so on
    @Transient
    public String createFileName(String path, String groupName, int cnt)
    {
        String filePath    = "";
        String ret         = "" + getRental().getId();
        ret += "_";
        if (cnt < 10) {
            ret += "00" + cnt;
        }
        else if (cnt < 100) {
            ret += "0" + cnt;
        }
        else {
            ret += cnt;
        }
        ret += "_";
        if (inspectionType != null) {
            ret += inspectionType.getAlias().toLowerCase();
        }
        int yy = Helper.getCurrentYear();
        int mm = Helper.getCurrentMonth();
        // for uploaded file
        if (inspectionDate != null) {
            String date = getInspectionDateFr();
            try {
                String str = date.substring(date.lastIndexOf("/") + 1);
                yy  = Integer.parseInt(str);
                str = date.substring(0, date.indexOf("/"));
                mm  = Integer.parseInt(str);
            }
            catch (Exception ex) {
                System.err.println(ex);
            }
        }
        filePath = path + yy + "/";
        String back = Helper.checkFilePath(filePath, groupName);
        if (!back.equals("")) {
            System.err.println(back);
            logger.error(back);
        }
        if (mm < 10) {
            filePath    = path + "" + yy + "/0" + mm + "/";
            inspectFile = "" + yy + "/0" + mm + "/" + ret + ".rtf";
        }
        else {
            filePath    = path + "" + yy + "/" + mm + "/";
            inspectFile = "" + yy + "/" + mm + "/" + ret + ".rtf";
        }
        back = Helper.checkFilePath(filePath, groupName);
        if (!back.equals("")) {
            System.err.println(back);
            logger.error(back);
        }
        //
        // make sure this path exist, if not create one
        //
        // System.err.println(" inspect file "+inspectFile);
        return path + inspectFile;
    }

    @Column(name = "comments", length = 500)
    public String getComments()
    {
        return this.comments;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
    }

    @Column(name = "accessory", length = 30)
    public String getAccessory()
    {
        return this.accessory;
    }

    public void setAccessory(String accessory)
    {
        this.accessory = accessory;
    }

    @Column(name = "foundation")
    public String getFoundation()
    {
        return this.foundation;
    }

    public void setFoundation(String foundation)
    {
        this.foundation = foundation;
    }

    @Column(name = "story_cnt")
    public Short getStoryCnt()
    {
        return this.storyCnt;
    }

    public void setStoryCnt(Short storyCnt)
    {
        this.storyCnt = storyCnt;
    }

    @Column(name = "heat_source")
    public String getHeatSource()
    {
        return this.heatSource;
    }

    public void setHeatSource(String heatSource)
    {
        this.heatSource = heatSource;
    }

    @Column(name = "attic_access", length = 1)
    public Character getAtticAccess()
    {
        return this.atticAccess;
    }

    public void setAtticAccess(Character atticAccess)
    {
        this.atticAccess = atticAccess;
    }

    @Transient
    public boolean hasAtticAccess()
    {
        return atticAccess != null;
    }

    @Column(name = "cancelled", length = 1)
    public Character getCancelled()
    {
        return this.cancelled;
    }

    public void setCancelled(Character val)
    {
        this.cancelled = val;
    }

    @Column(name = "approved", length = 1)
    public Character getApproved()
    {
        return this.approved;
    }

    public void setApproved(Character val)
    {
        this.approved = val;
    }

    @Transient
    public boolean canBeApproved()
    {
        return approved == null && cancelled == null;
    }

    @Transient
    public boolean wasCancelled()
    {
        return cancelled != null;
    }

    @Transient
    public boolean wasApproved()
    {
        return approved != null;
    }

    @Column(name = "smoke_detectors")
    public Short getSmokeDetectors()
    {
        return this.smokeDetectors;
    }

    public void setSmokeDetectors(Short smokeDetectors)
    {
        this.smokeDetectors = smokeDetectors;
    }

    @Column(name = "life_safety")
    public Short getLifeSafety()
    {
        return this.lifeSafety;
    }

    public void setLifeSafety(Short lifeSafety)
    {
        this.lifeSafety = lifeSafety;
    }

    @Column(name = "time_spent", precision = 6)
    public BigDecimal getTimeSpent()
    {
        return this.timeSpent;
    }

    public void setTimeSpent(BigDecimal timeSpent)
    {
        this.timeSpent = timeSpent;
        if (timeSpent != null && timeSpent.doubleValue() > 0) {
            timeStatus = "Completed";
        }
        else {
            timeStatus = "In Progress";
        }
    }

    @Transient
    public boolean hasTimeSpent()
    {
        return timeSpent != null && timeSpent.doubleValue() > 0;
    }

    @Column(name = "time_status")
    public String getTimeStatus()
    {
        if (!hasTimeSpent()) {
            this.timeStatus = "In Progress";
        }
        return this.timeStatus;
    }

    public void setTimeStatus(String timeStatus)
    {
        if (timeStatus != null) {
            this.timeStatus = timeStatus;
        }
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "inspection")
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

    @Transient
    public InspectionTemplate getInspectionTemplate()
    {
        return template;
    }

    @Transient
    public void setInspectionTemplate(InspectionTemplate val)
    {
        template = val;
        if (template != null) {
            if (hasInspectionCans()) {
                for (InspectionCan one : inspectionCans) {
                    TemplateComponent comp = one.getTemplateComponent();
                    if (comp != null) {
                        int compId = comp.getId();
                        template.addToVisited(compId, one.getId());
                    }
                }
            }
            if (template.hasTemplateComponents()) {
                List<TemplateComponent> list = template.getTemplateComponents();
                for (TemplateComponent one : list) {
                    if (template.isVisited(one.getId())) {
                        int val2 = template.getInspectionCanId(one.getId());
                        one.setVisited();
                        one.setInspectionCanId(val2);
                    }
                }
            }
        }
    }

    @Transient
    public boolean hasInspectionTemplate()
    {
        return template != null;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "inspection")
    @OrderBy("templateComponent.id ASC, id ASC")
    public List<InspectionCan> getInspectionCans()
    {
        return this.inspectionCans;
    }

    public void setInspectionCans(List<InspectionCan> inspectionCans)
    {
        this.inspectionCans = inspectionCans;
    }

    @Transient
    public boolean hasInspectionCans()
    {
        return inspectionCans != null && inspectionCans.size() > 0;
    }

    //
    // only cycle inspection (id=1) require using templates
    //
    @Transient
    public boolean requireTemplate()
    {
        return inspectionType != null && inspectionType.getId() == 1;
    }

    @Transient
    public String getFeatures()
    {
        String features = "";
        if (accessory != null && !accessory.equals("")) {
            features += "accessory: " + accessory;
        }
        if (smokeDetectors != null && smokeDetectors > 0) {
            if (!features.equals("")) features += ", ";
            features += "smoke detectors #: " + smokeDetectors;
        }
        if (lifeSafety != null && lifeSafety > 0) {
            if (!features.equals("")) features += ", ";
            features += "life safety #: " + lifeSafety;
        }
        if (timeSpent != null && timeSpent.doubleValue() > 0) {
            if (!features.equals("")) features += ", ";
            features += "time spent: " + timeSpent.doubleValue();
        }
        if (timeStatus != null) {
            if (!features.equals("")) features += ", ";
            features += "time status: " + timeStatus;
        }
        return features;
    }

    // we need to make sure the following fields are set
    @Transient
    public String validateInspection()
    {
        String back = "";
        if (rental == null) {
            back = "rental record not set ";
        }
        if (inspector == null) {
            if (!back.equals("")) back += ", ";
            back += "Inspector info is required";
        }
        if (inspectionType == null) {
            if (!back.equals("")) back += ", ";
            back += "inspection type not set ";
        }
        return back;
    }
}
