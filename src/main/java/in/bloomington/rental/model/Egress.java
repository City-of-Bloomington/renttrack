package in.bloomington.rental.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "egresses")
public class Egress implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int     id;

    @Column(name = "start_year")
    private Integer start_year;

    @Column(name = "end_year")
    private Integer end_year;

    @Column(name = "type")
    private String  type = "Single"; // Single (default), Multi

    @Column(name = "height")
    private Integer height;

    @Column(name = "width")
    private Integer width;

    @Column(name = "sill_height")
    private Integer sill_height;

    @Column(name = "area")
    private Float   area;            // 1st floor

    @Column(name = "area2")
    private Float   area2;           // other floors

    public Egress()
    {
    }

    public Egress(int id)
    {
        this.id = id;
    }

    public Egress(int id, Integer start_year,
                          Integer end_year,
                          Integer height,
                          Integer width,
                          Integer sill_height,
                          Float   area,
                          Float   area2,
                          String  type)
    {
        this.id          = id;
        this.start_year  = start_year;
        this.end_year    = end_year;
        this.height      = height;
        this.width       = width;
        this.sill_height = sill_height;
        this.area        = area;
        this.area2       = area2;
        this.type        = type;
    }

    //-----------------------------------------------------
    // Generic Getters and Setters
    //-----------------------------------------------------
    public int     getId        () { return this.id;          }
    public Integer getStartYear () { return this.start_year;  }
    public Integer getEndYear   () { return this.end_year;    }
    public Integer getHeight    () { return this.height;      }
    public Integer getWidth     () { return this.width;       }
    public Integer getSillHeight() { return this.sill_height; }
    public Float   getArea      () { return this.area;        }
    public Float   getArea2     () { return this.area2;       }
    public String  getType      () { return this.type;        }

    public void setId        (int     id ) { this.id          = id;  }
    public void setStartYear (Integer val) { this.start_year  = val; }
    public void setEndYear   (Integer val) { this.end_year    = val; }
    public void setHeight    (Integer val) { this.height      = val; }
    public void setWidth     (Integer val) { this.width       = val; }
    public void setSillHeight(Integer val) { this.sill_height = val; }
    public void setArea      (Float   val) { this.area        = val; }
    public void setArea2     (Float   val) { this.area2       = val; }
    public void setType      (String  val) { this.type        = val; }

    //-----------------------------------------------------
    // Transients
    //-----------------------------------------------------
    @Transient
    public String getStartEndYear()
    {
        String ret = "";
        if (start_year != null) { ret += start_year; }
        if (end_year != null) {
            if (!ret.equals("")) { ret += " - "; }
            ret += end_year;
        }
        return ret;
    }

    @Transient
    public String getStartEndYearType()
    {
        String ret = getStartEndYear();
        if (type != null) {
            if (!ret.equals("")) { ret += " "; }
            ret += type;
        }
        return ret;
    }

    @Override
    public String toString()
    {
        String ret = "";
        if (start_year != null) { ret += start_year; }
        if (end_year   != null) {
            if (!ret.equals("")) { ret += " "; }
            ret += end_year;
        }
        if (height != null) {
            if (!ret.equals("")) { ret += " "; }
            ret += height;
        }
        if (width != null) {
            if (!ret.equals("")) { ret += " "; }
            ret += width;
        }
        if (sill_height != null) {
            if (!ret.equals("")) { ret += " "; }
            ret += sill_height;
        }
        if (area != null) {
            if (!ret.equals("")) { ret += " "; }
            ret += area;
        }
        if (area2 != null) {
            if (!ret.equals("")) { ret += " "; }
            ret += area2;
        }
        return ret;
    }
}
