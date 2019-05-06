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
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Entity
@Table(name = "unit_rooms")
public class UnitRoom implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;

    private static final Logger logger = LogManager.getLogger(RentalUnit.class);

		@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int                 id;
		
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unit_id")
    private RentalUnit          rentalUnit;
		
		@Column(name = "identifier", nullable = false, length = 30)
    private String              identifier;
		
    @Column(name = "type")		
    private String              type;

		@Column(name = "measures")
    private String              measures;

    public UnitRoom()
    {
    }

    public UnitRoom(int id)
    {
        this.id = id;
    }

    public UnitRoom(int id, RentalUnit rentalUnit,
                            String     identifier,
                            String     type,
                            String     measures)
    {

        this.id         = id;
        this.rentalUnit = rentalUnit;
        this.identifier = identifier;
        this.type       = type;
        this.measures   = measures;
    }


    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public RentalUnit getRentalUnit()
    {
        return this.rentalUnit;
    }

    public void setRentalUnit(RentalUnit val)
    {
        this.rentalUnit = val;
    }

    public String getIdentifier()
    {
        return this.identifier;
    }

    public void setIdentifier(String identifier)
    {
        this.identifier = identifier;
    }


    public String getType()
    {
        return this.type;
    }

    public void setType(String val)
    {
        this.type = val;
    }

    public String getMeasures()
    {
        return this.measures;
    }

    public void setMeasures(String measures)
    {
        this.measures = measures;
    }

    @Transient
    public String getInfo()
    {
        String ret = "";
        if (type != null) {
            ret += type;
        }
        else {
            ret += " room ";
            if (identifier != null) ret += identifier;
        }
        if (measures != null) {
            ret += " ";
            ret += measures;
        }
        return ret;
    }

    public String toString()
    {
        return getInfo();
    }
}
