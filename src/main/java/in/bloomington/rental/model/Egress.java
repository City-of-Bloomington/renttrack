package in.bloomington.rental.model;



import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name="egresses")

public class Egress implements java.io.Serializable {

		private int id;
		private Integer start_year;
		private Integer end_year;
		private String type="Single"; // Single (default), Multi
		private Integer height;
		private Integer width;
		private Integer sill_height;
		private Double area; // 1st floor
		private Double area2; // other floors
		
    public Egress() {
    }
	
    public Egress(int id) {
        this.id = id;
    }
    public Egress(int id,
									Integer start_year,
									Integer end_year,
									Integer height,
									Integer width,
									Integer sill_height,
									Double area,
									Double area2,
									String type
							 ) {
				this.id = id;
				this.start_year = start_year;
				this.end_year = end_year;
				this.height = height;
				this.width = width;
				this.sill_height = sill_height;
				this.area = area;
				this.area2 = area2; 
				this.type = type;
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

    @Column(name="start_year")
    public Integer getStartYear() {
        return this.start_year;
    }
    public void setStartYear(Integer val) {
				this.start_year = val;
    }

    @Column(name="end_year")
    public Integer getEndYear() {
        return this.end_year;
    }
    public void setEndYear(Integer val) {
				this.end_year = val;
    }
		
    @Column(name="height")
    public Integer getHeight() {
        return this.height;
    }
    public void setHeight(Integer val) {
				this.height = val;
    }
    @Column(name="width")
    public Integer getWidth() {
        return this.width;
    }
    public void setWidth(Integer val) {
				this.width = val;
    }				
    @Column(name="sill_height")
    public Integer getSillHeight() {
        return this.sill_height;
    }
    public void setSillHeight(Integer val) {
				this.sill_height = val;
    }
    @Column(name="area")
    public Double getArea() {
        return this.area;
    }
    public void setArea(Double val) {
				this.area = val;
    }
    @Column(name="area2")
    public Double getArea2() {
        return this.area2;
    }
    public void setArea2(Double val) {
				this.area2 = val;
    }		
    @Column(name="type")
    public String getType() {
        return this.type;
    }
    public void setType(String val) {
				this.type = val;
    }		
		@Transient
		public String getStartEndYear(){
				String ret="";
				if(start_year != null)
						ret += start_year;
				if(end_year != null){
						if(!ret.equals("")) ret += " - ";
						ret += end_year;
				}
				return ret;
		}
		@Transient
		public String getStartEndYearType(){
				String ret = getStartEndYear();
				if(type != null){
						if(!ret.equals("")) ret += " ";
						ret += type;
				}
				return ret;
		}		
		
		@Override
		public String toString(){
				String ret = "";
				if(start_year != null)
						ret += start_year;
				if(end_year != null){
						if(!ret.equals("")) ret += " "; 
						ret += end_year;
				}
				if(height != null){
						if(!ret.equals("")) ret += " "; 
						ret += height;
				}
				if(width != null){
						if(!ret.equals("")) ret += " "; 
						ret += width;
				}
				if(sill_height != null){
						if(!ret.equals("")) ret += " "; 
						ret += sill_height;
				}
				if(area != null){
						if(!ret.equals("")) ret += " "; 
						ret += area;
				}
				if(area2 != null){
						if(!ret.equals("")) ret += " "; 
						ret += area2;
				}				
				return ret;
		}
}


