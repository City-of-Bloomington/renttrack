package in.bloomington.rental.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "inspection_templates")

public class InspectionTemplate implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;

    SimpleDateFormat                dtf                = new SimpleDateFormat("MM/dd/yyyy");
    private int                     id;
    private Integer                 rentalId;
    private int                     buildingCnt        = 1;
    private Date                    date;
    private RentUser                user;
    // this item come from a form that will be submitted
    // stored temporary in this object
    @Transient
    private String[]                components         = null;
    @Transient
    private int[]                   del_ids            = null;
    @Transient
    private int                     buildingNum        = 0;
    @Transient
    private int                     unitNum            = 0;
    @Transient
    private int                     floorNum           = 0;
    @Transient
    private Map<Integer, Integer>   visitedMap         = new HashMap<>();
    private List<TemplateComponent> templateComponents = new ArrayList<>();

    public InspectionTemplate()
    {
    }

    public InspectionTemplate(int id)
    {
        this.id = id;
    }

    public InspectionTemplate(int id, Integer  rentalId,
                                      int      buildingCnt,
                                      Date     date,
                                      RentUser user,
                       List<TemplateComponent> vals)
    {
        this.id                 = id;
        this.rentalId           = rentalId;
        this.buildingCnt        = buildingCnt;
        this.date               = date;
        this.user               = user;
        this.templateComponents = vals;
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

    @Column(name = "rental_id")
    public Integer getRentalId()
    {
        return this.rentalId;
    }

    public void setRentalId(Integer val)
    {
        this.rentalId = val;
    }

    @Column(name = "building_cnt")
    public int getBuildingCnt()
    {
        return this.buildingCnt;
    }

    public void setBuildingCnt(int val)
    {
        this.buildingCnt = val;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public RentUser getUser()
    {
        return this.user;
    }

    public void setUser(RentUser val)
    {
        this.user = val;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "date", length = 13)
    public Date getDate()
    {
        return this.date;
    }

    public void setDate(Date val)
    {
        this.date = val;
    }

    @Transient
    public void setDateFr(String val)
    {
        if (val != null) {
            try {
                date = dtf.parse(val);
            }
            catch (Exception ex) {

            }
        }
    }

    @Transient
    public String getDateFr()
    {
        if (date != null) {
            return dtf.format(date);
        }
        return "";
    }

    @Transient
    public String[] getComponents()
    {
        return components;
    }

    @Transient
    public void setComponents(String[] vals)
    {
        components = vals;
    }

    @Transient
    public boolean hasComponents()
    {
        return components != null && components.length > 0;
    }

    @Transient
    public void addToVisited(int componentId, int canId)
    {
        visitedMap.put(componentId, canId);
    }

    @Transient
    public boolean isVisited(int componentId)
    {
        return visitedMap.containsKey(componentId);
    }

    @Transient
    public int getInspectionCanId(int val)
    {
        int retVal = 0;
        if (isVisited(val)) {
            retVal = visitedMap.get(val);
        }
        return retVal;
    }

    @Transient
    public int[] getDel_ids()
    {
        return del_ids;
    }

    @Transient
    public void setDel_ids(int[] vals)
    {
        del_ids = vals;
    }

    @Transient
    public boolean hasDelIds()
    {
        return del_ids != null && del_ids.length > 0;
    }

    // needed to handle checkboxes, we do not need the values
    @Transient
    public void setComponents_2(String[] vals)
    {
        // just ignore
    }

    @Transient
    public void setBuildingNum(int val)
    {
        buildingNum = val;
    }

    @Transient
    public int getBuildingNum()
    {
        return buildingNum;
    }

    @Transient
    public void setFloorNum(int val)
    {
        floorNum = val;
    }

    @Transient
    public int getFloorNum()
    {
        return floorNum;
    }

    @Transient
    public void setUnitNum(int val)
    {
        unitNum = val;
    }

    @Transient
    public int getUnitNum()
    {
        return unitNum;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "inspectionTemplate")
    @OrderBy("buildingNum ASC, unitNum ASC, floorNum ASC, id ASC")
    public List<TemplateComponent> getTemplateComponents()
    {
        return this.templateComponents;
    }

    public void setTemplateComponents(List<TemplateComponent> templateComponents)
    {
        this.templateComponents = templateComponents;
    }

    @Transient
    public boolean hasTemplateComponents()
    {
        return templateComponents != null && templateComponents.size() > 0;
    }

    @Override
    public String toString()
    {
        String ret = "" + rentalId + ":" + buildingCnt;
        return ret;
    }

}
