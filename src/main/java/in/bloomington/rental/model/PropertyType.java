package in.bloomington.rental.model;



import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name="property_types")
public class PropertyType  implements java.io.Serializable {

		@Id 
		@GeneratedValue(strategy=GenerationType.IDENTITY)    
		@Column(name="id")
		private int id;
    @Column(name="name", nullable=false, length=30)		
		private String name;
		
    public PropertyType() {
    }

    public PropertyType(int id) {
        this.id = id;
    }	
    public PropertyType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
		@Override
		public String toString(){
				return name;
		}


}


