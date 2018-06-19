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
@Table(name="zonings")

public class Zoning  implements java.io.Serializable {

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)   
    @Column(name="id")		
		private int id;
    @Column(name="alias", length=15)		
		private String alias;
    
    @Column(name="name", length=70, nullable=false)
		private String name;
		//
    public Zoning() {
    }

	
    public Zoning(int id) {
        this.id = id;
    }
    public Zoning(int id, String alias, String name){
       this.id = id;
       this.alias = alias;
       this.name = name;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getAlias() {
        return this.alias;
    }
    
    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

		public String toString(){
				return name;
		}

}


