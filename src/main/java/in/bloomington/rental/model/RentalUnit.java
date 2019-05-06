package in.bloomington.rental.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Entity
@Table(name = "rental_units")
public class RentalUnit implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;

    private static final Logger logger = LogManager.getLogger(RentalUnit.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")		
    private int                 id;
		
		@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "structure_id")
    private RentalStructure     rentalStructure;
		
		@OneToMany(fetch = FetchType.LAZY, mappedBy = "rentalUnit")
    @OrderBy("id ASC")
    private List<UnitRoom>      unitRooms;
		
		@Column(name = "old_id")
    private Integer             oldId;
		
		@Column(name = "identifier", nullable = false, length = 30)
    private String              identifier;
		
		@Column(name = "bedrooms")
    private Short               bedrooms;
		
		@Column(name = "occup_load")
    private Short               occupLoad;

		@Column(name = "sleep_room", length = 1)
    private Character           sleepRoom;

		@Column(name = "efficiency", length = 1)
    private Character           efficiency;
		
		@Column(name = "bathrooms")
    private Short               bathrooms;
		
		@Column(name = "half_bath")
    private Short               halfBath;
		
		@Column(name = "uninspected", length = 1)
    private Character           uninspected;
		
		@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address             address;

		@Column(name = "accessory_dwelling", length = 1)
    private Character           accessoryDwelling;
		
		@Column(name = "notes", length = 500)
    private String              notes;
		
		@Column(name = "attic_access", length = 1)
    private Character           atticAccess;

    public RentalUnit()
    {
    }

    public RentalUnit(int id)
    {
        this.id = id;
    }

    public RentalUnit(int id, RentalStructure rentalStructure,
                              Integer         oldId,
                              String          identifier,
                              Short           bedrooms,
                              Short           occupLoad,
                              Character       sleepRoom,
                              Short           bathrooms,
                              Short           halfBath,
                              Character       uninspected,
                              Address         address,
                              Character       accessoryDwelling,
                              String          notes,
                              Character       atticAccess,
                         List<UnitRoom>       unitRooms,
                              Character       efficiency)
    {
        this.id                = id;
        this.rentalStructure   = rentalStructure;
        this.oldId             = oldId;
        this.identifier        = identifier;
        this.bedrooms          = bedrooms;
        this.occupLoad         = occupLoad;
        this.sleepRoom         = sleepRoom;
        this.bathrooms         = bathrooms;
        this.halfBath          = halfBath;
        this.uninspected       = uninspected;
        this.address           = address;
        this.accessoryDwelling = accessoryDwelling;
        this.notes             = notes;
        this.atticAccess       = atticAccess;
        this.unitRooms         = unitRooms;
        this.efficiency        = efficiency;
    }


    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public RentalStructure getRentalStructure()
    {
        return this.rentalStructure;
    }

    public void setRentalStructure(RentalStructure rentalStructure)
    {
        this.rentalStructure = rentalStructure;
    }

    @Transient
    public int getStructureId()
    {
        return rentalStructure.getId();
    }

    @Transient
    public void setStructureId(int val)
    {
        rentalStructure = new RentalStructure(val);
    }

    public Integer getOldId()
    {
        return this.oldId;
    }

    public void setOldId(Integer oldId)
    {
        this.oldId = oldId;
    }


    public String getIdentifier()
    {
        return this.identifier;
    }

    public void setIdentifier(String identifier)
    {
        this.identifier = identifier;
    }


    public Short getBedrooms()
    {
        return this.bedrooms;
    }

    public void setBedrooms(Short bedrooms)
    {
        this.bedrooms = bedrooms;
    }


    public Short getOccupLoad()
    {
        return this.occupLoad;
    }

    public void setOccupLoad(Short occupLoad)
    {
        this.occupLoad = occupLoad;
    }

    public Character getSleepRoom()
    {
        return this.sleepRoom;
    }

    public void setSleepRoom(Character sleepRoom)
    {
        this.sleepRoom = sleepRoom;
    }

    @Transient
    public boolean isSleepingRoom()
    {
        return this.sleepRoom != null;
    }


    public Short getBathrooms()
    {
        return this.bathrooms;
    }

    public void setBathrooms(Short bathrooms)
    {
        this.bathrooms = bathrooms;
    }

    @Transient
    public boolean hasBathrooms()
    {
        return this.bathrooms != null && bathrooms > 0;
    }


    public Short getHalfBath()
    {
        return this.halfBath;
    }

    public void setHalfBath(Short halfBath)
    {
        this.halfBath = halfBath;
    }


    public Character getUninspected()
    {
        return this.uninspected;
    }

    public void setUninspected(Character uninspected)
    {
        this.uninspected = uninspected;
    }

    @Transient
    public boolean isNotInspected()
    {
        return this.uninspected != null;
    }


    public Address getAddress()
    {
        return this.address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    @Transient
    public boolean hasAddress()
    {
        return address != null;
    }

    @Transient
    public void setAddressId(Integer val)
    {
        if (val != null) address = new Address(val);
    }

    @Transient
    public boolean isValid()
    {
        return id > 0 && bedrooms > 0;
    }


    public Character getAccessoryDwelling()
    {
        return this.accessoryDwelling;
    }

    public void setAccessoryDwelling(Character uninspected)
    {
        this.accessoryDwelling = accessoryDwelling;
    }

    public String getNotes()
    {
        return this.notes;
    }

    public void setNotes(String val)
    {
        this.notes = val;
    }


    public Character getAtticAccess()
    {
        return this.atticAccess;
    }

    public void setAtticAccess(Character atticAccess)
    {
        this.atticAccess = atticAccess;
    }


    public Character getEfficiency()
    {
        return this.efficiency;
    }

    public void setEfficiency(Character val)
    {
        this.efficiency = val;
    }

    @Transient
    public boolean isEfficienyType()
    {
        return this.efficiency != null;
    }


    public List<UnitRoom> getUnitRooms()
    {
        return this.unitRooms;
    }

    public void setUnitRooms(List<UnitRoom> unitRooms)
    {
        this.unitRooms = unitRooms;
    }

    public boolean hasRooms()
    {
        return unitRooms != null && unitRooms.size() > 0;
    }

    @Transient
    public boolean hasAddr()
    {
        return address != null && address.isNotEmpty();
    }

    @Transient
    public int getBedroomCount()
    {
        int cnt = 0;
        if (unitRooms != null && unitRooms.size() > 0) {
            cnt = (int) unitRooms.stream()
                                 .filter(room -> room.getType().equals("Bedroom"))
                                 .count();
        }
        return cnt;
    }

    @Transient
    public int getRoomCount()
    {
        int cnt = 0;
        if (unitRooms != null && unitRooms.size() > 0) {
            cnt = unitRooms.size();
        }
        return cnt;
    }

    @Transient
    public String getFeatures()
    {
        String ret = "";
        try {
            if (efficiency != null && !efficiency.equals("")) {
                ret = "efficiency ";
            }
            if (sleepRoom != null && !sleepRoom.equals("")) {
                if (!ret.equals("")) ret += ", ";
                ret += "sleeping room";
            }
            if (accessoryDwelling != null && !accessoryDwelling.equals("")) {
                if (!ret.equals("")) ret += ", ";
                ret += "accessory dwelling";
            }
            if (uninspected != null && !uninspected.equals("")) {
                if (!ret.equals("")) ret += ", ";
                ret += "uninspected";
            }
            if (halfBath != null && halfBath > 0) {
                if (!ret.equals("")) ret += ", ";
                ret += "half bath: " + halfBath;
            }
            if (atticAccess != null && !atticAccess.equals("")) {
                if (!ret.equals("")) ret += ", ";
                ret += "Has attic access";
            }
            else {
                if (!ret.equals("")) ret += ", ";
                ret += "Has no attic access";
            }
        }
        catch (Exception ex) {
            logger.error(ex);
            System.err.println(ex);
        }
        return ret;
    }
}
