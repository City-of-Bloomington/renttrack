package in.bloomington.rental.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "addresses")
public class Address implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int       id;
    
    @Column(name = "rental_id")
    private int       rentalId;

    @Column(name = "street_address")
    private String    streetAddress;
    
    private String    city = "Bloomington"; // default
    
    @Column(name = "ma_address_id")
    private Integer   address_id;
    
    @Column(name = "ma_subunit_id")
    private Integer   maSubunitId;
    
    @Column(name = "ma_location_id")
    private Integer   maLocationId;
    
    private Character invalid;

    @Column(columnDefinition="numeric(10,8)")
    @Type(type="java.lang.Double")
    private Double    longitude;

    @Column(columnDefinition="numeric(10,8)")
    @Type(type="java.lang.Double")
    private Double    latitude;

    @Transient
    private Integer   unitId;               // needed for a new address

    public Address()
    {
    }

    public Address(int id)
    {
        this.id = id;
    }

    public Address(int id, String streetAddress)
    {
        this.id            = id;
        this.streetAddress = streetAddress;
    }

    public Address(int id, Integer   rentalId,
                           String    streetAddress,
                           String    city,
                           Integer   address_id,
                           Integer   maSubunitId,
                           Integer   maLocationId,
                           Character invalid,
                           Double    longitude,
                           Double    latitude)
    {
        this.id            = id;
        this.rentalId      = rentalId;
        this.streetAddress = streetAddress;
        this.city          = city;
        this.address_id    = address_id;
        this.maSubunitId   = maSubunitId;
        this.maLocationId  = maLocationId;
        this.invalid       = invalid;
        this.longitude     = longitude;
        this.latitude      = latitude;
    }

    //-----------------------------------------------------
    // Generic Getters and Setters
    //-----------------------------------------------------
    public int       getId           () { return this.id;            }
    public Integer   getRentalId     () { return this.rentalId;      }
    public String    getStreetAddress() { return this.streetAddress; }
    public String    getCity         () { return this.city;          }
    public Integer   getAddressId    () { return this.address_id;    }
    public Integer   getMaSubunitId  () { return this.maSubunitId;   }
    public Integer   getMaLocationId () { return this.maLocationId;  }
    public Double    getLatitude     () { return this.latitude;      }
    public Double    getLongitude    () { return this.longitude;     }
    public Character getInvalid      () { return this.invalid;       }

    public void setId           (int       i) { this.id            = i; }
    public void setRentalId     (Integer   i) { this.rentalId      = i; }
    public void setStreetAddress(String    s) { this.streetAddress = s; }
    public void setCity         (String    s) { this.city          = s; }
    public void setAddressId    (Integer   i) { this.address_id    = i; }
    public void setMaSubunitId  (Integer   i) { this.maSubunitId   = i; }
    public void setMaLocationId (Integer   i) { this.maLocationId  = i; }
    public void setLatitude     (Double    d) { this.latitude      = d; }
    public void setLongitude    (Double    d) { this.longitude     = d; }
    public void setInvalid      (Character c) { this.invalid       = c; }

    //-----------------------------------------------------
    // Transient functions
    //-----------------------------------------------------
    @Transient
    public Integer getUnitId()
    {
        return unitId;
    }

    @Transient
    public void setUnitId(Integer val)
    {
        this.unitId = val;
    }

    @Transient
    public boolean hasLatLong()
    {
        return latitude != null && longitude != null;
    }

    @Transient
    public boolean isInvalidAddr()
    {
        return invalid != null && !invalid.equals("");
    }

    @Transient
    public boolean isNotEmpty()
    {
        return !streetAddress.equals("");
    }

    @Transient
    public boolean isNew()
    {
        return isNotEmpty() && id == 0;
    }

    @Override
    public String toString()
    {
        return streetAddress;
    }

    @Override
    public int hashCode()
    {
        int ret  = 31;
            ret += 17 * streetAddress.hashCode();
        return ret;
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Address)) {
            return false;
        }
        Address addr = (Address) o;
        if (!streetAddress.equals(addr.getStreetAddress())) return false;
        if (address_id  != null && address_id  != addr.getAddressId())  return false;
        if (maSubunitId != null && maSubunitId != addr.getMaSubunitId()) return false;
        return true;
    }

    @Transient
    public void setAddrCombo(String val)
    {
        if (val != null && val.indexOf("_") > -1) {
            String[] vals = val.split("_");
            if (vals != null) {
                if (vals.length == 3) {
                    address_id    = new Integer(vals[0]);
                    maSubunitId   = new Integer(vals[1]);
                    streetAddress = vals[2];
                }
                if (vals.length == 2) {
                    address_id    = new Integer(vals[0]);
                    streetAddress = vals[1];
                }
            }
        }
    }

    @Transient
    public String getAddrCombo()
    {
        String ret = address_id + "_" + maSubunitId + "_" + streetAddress;
        return ret;
    }
}
