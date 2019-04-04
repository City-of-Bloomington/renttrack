package in.bloomington.rental.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "template_components")

public class TemplateComponent implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;

    private int                id;
    private InspectionTemplate inspectionTemplate;
    private int                buildingNum     = 0;
    private int                unitNum         = 0;
    private int                floorNum        = 0;
    private String             component;
    //
    @Transient
    private boolean            visited         = false;
    @Transient
    private int                inspectionCanId = 0;

    //
    public TemplateComponent()
    {
    }

    public TemplateComponent(int id)
    {
        this.id = id;
    }

    public TemplateComponent(int id, InspectionTemplate inspectionTemplate,
                                     int                buildingNum,
                                     int                unitNum,
                                     int                floorNum,
                             String component)
    {
        this.id                 = id;
        this.inspectionTemplate = inspectionTemplate;
        this.buildingNum        = buildingNum;
        this.unitNum            = unitNum;
        this.floorNum           = floorNum;
        this.component          = component;
    }

    // for new records
    public TemplateComponent(InspectionTemplate inspectionTemplate,
                             int                buildingNum,
                             int                unitNum,
                             int                floorNum,
                             String             component)
    {
        this.inspectionTemplate = inspectionTemplate;
        this.buildingNum        = buildingNum;
        this.unitNum            = unitNum;
        this.floorNum           = floorNum;
        this.component          = component;
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
    @JoinColumn(name = "template_id")
    public InspectionTemplate getInspectionTemplate()
    {
        return this.inspectionTemplate;
    }

    public void setInspectionTemplate(InspectionTemplate val)
    {
        this.inspectionTemplate = val;
    }

    @Column(name = "building_num")
    public int getBuildingNum()
    {
        return this.buildingNum;
    }

    public void setBuildingNum(int val)
    {
        this.buildingNum = val;
    }

    @Column(name = "unit_num")
    public int getUnitNum()
    {
        return this.unitNum;
    }

    public void setUnitNum(int val)
    {
        this.unitNum = val;
    }

    @Column(name = "floor_num")
    public int getFloorNum()
    {
        return this.floorNum;
    }

    public void setFloorNum(int val)
    {
        this.floorNum = val;
    }

    @Column(name = "component")
    public String getComponent()
    {
        return this.component;
    }

    public void setComponent(String val)
    {
        this.component = val;
    }

    @Transient
    public void setVisited()
    {
        visited = true;
    }

    @Transient
    public boolean isVisited()
    {
        return visited;
    }

    @Transient
    public int getInspectionCanId()
    {
        return inspectionCanId;
    }

    @Transient
    public void setInspectionCanId(int val)
    {
        inspectionCanId = val;
    }

    @Override
    public String toString()
    {
        String ret = component;
        return ret;
    }

}
