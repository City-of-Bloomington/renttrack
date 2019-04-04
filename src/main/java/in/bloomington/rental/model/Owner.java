package in.bloomington.rental.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import in.bloomington.rental.util.Helper;

@Entity
@Table(name = "owners")
public class Owner implements java.io.Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int              id;
    @Column(name = "name", nullable = false, length = 70)
    @Size(max = 70, min = 5, message = "{owner.name.invalid}")
    private String           name;
    @Column(name = "address", nullable = false, length = 70)
    @Size(max = 70, min = 5, message = "{owner.address.invalid}")
    private String           address;
    @Column(name = "city", nullable = false, length = 70)
    private String           city;
    @Column(name = "state", nullable = false, length = 2)
    private String           state;
    @Column(name = "zip", nullable = false, length = 12)
    private String           zip;
    @Column(name = "notes", length = 500)
    private String           notes;
    @Column(name = "email", length = 150)
    @Email(message = "{owner.email.invalid}")
    private String           email;

    @Transient
    private String           phone;                          // needed for search
    @Transient
    private Integer          ownerId;                        // needed for search
    @Transient
    private Character        noEmail;
    //
    @Transient
    private int              rentalId;                       // needed for adding new owner

    // agent related
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    private Set<OwnerPhone>  ownerPhones  = new HashSet<>(0);

    // for agent
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "agent")
    private Set<Rental>      rentals      = new HashSet<>(0);

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    private Set<RentalOwner> rentalOwners = new HashSet<>(0);

    public Owner()
    {
    }

    public Owner(int id)
    {
        this.id = id;
    }

    public Owner(int id, String name)
    {
        this.id   = id;
        this.name = name;
    }

    public Owner(int id, String name,
                         String address,
                         String city,
                         String state,
                         String zip,
                         String notes,
                         String email,
               Set<OwnerPhone>  ownerPhones,
               Set<Rental>      rentals,
               Set<RentalOwner> rentalOwners)
    {
        this.id = id;
        setName(name);
        setAddress(address);
        setCity(city);
        setState(state);
        setZip(zip);
        setNotes(notes);
        setEmail(email);
        this.ownerPhones  = ownerPhones;
        this.rentals      = rentals;
        this.rentalOwners = rentalOwners;

    }

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        if (name != null)
            this.name = name.trim();
        else
            this.name = name;
    }

    //
    // needed for emails, remove any "," in the name
    //
    @Transient
    public String getCleanName()
    {
        String str = "";
        if (name != null) {
            if (name.indexOf(",") > 0) {
                str = name.replace(",", "").trim();
            }
            else {
                str = name.trim();
            }
        }
        return str;
    }

    public String getAddress()
    {
        return this.address;
    }

    public void setAddress(String address)
    {
        if (address != null)
            this.address = address.trim();
        else
            this.address = address;
    }

    public String getCity()
    {
        return this.city;
    }

    public void setCity(String city)
    {
        if (city != null)
            this.city = city.trim();
        else
            this.city = city;
    }

    public String getState()
    {
        return this.state;
    }

    public void setState(String state)
    {
        if (state != null) this.state = state.trim();
    }

    public String getZip()
    {
        return this.zip;
    }

    public void setZip(String zip)
    {
        if (zip != null) this.zip = zip.trim();
    }

    public String getCityStateZip()
    {
        String ret = "";
        if (city != null) ret += city;
        if (state != null) {
            if (!ret.equals("")) ret += ", ";
            ret += state;
        }
        if (zip != null) {
            if (!ret.equals("")) ret += " ";
            ret += zip;
        }
        return ret;

    }

    public String getNotes()
    {
        return this.notes;
    }

    public void setNotes(String notes)
    {
        if (notes != null) this.notes = notes.trim();
    }

    public String getEmail()
    {
        return this.email;
    }

    public String getOneEmail()
    {
        String ret = "";
        if (email != null) {
            if (email.indexOf(",") > 0) {
                String arr[] = email.split(",");
                ret = arr[0].trim();
            }
            else {
                ret = email;
            }
        }
        return ret;
    }

    public void setEmail(String email)
    {
        if (email != null) this.email = email.trim();
    }

    @Transient
    public boolean hasEmail()
    {
        return this.email != null && !this.email.equals("");
    }

    @Transient
    public boolean hasPhones()
    {
        getOwnerPhones();
        return ownerPhones != null && ownerPhones.size() > 0;
    }

    @Transient
    public String getPhonesInfo()
    {
        String ret = "";
        getOwnerPhones();
        for (OwnerPhone one : ownerPhones) {
            if (!ret.equals("")) ret += ", ";
            ret += one.getType() + ":" + one.getPhoneNum();
        }
        return ret;
    }

    public Set<OwnerPhone> getOwnerPhones()
    {
        return this.ownerPhones;
    }

    public void setOwnerPhones(Set<OwnerPhone> ownerPhones)
    {
        this.ownerPhones = ownerPhones;
    }

    // for agent
    public Set<Rental> getRentals()
    {
        return this.rentals;
    }

    public void setRentals(Set<Rental> rentals)
    {
        this.rentals = rentals;
    }

    public Set<RentalOwner> getRentalOwners()
    {
        return this.rentalOwners;
    }

    public void setRentalOwners(Set<RentalOwner> rentalOwners)
    {
        this.rentalOwners = rentalOwners;
    }

    // output will be 'John Smith<smithj@blah.blah>
    @Transient
    public List<String> getEmailList()
    {
        List<String> emails = new ArrayList<>();
        if (hasValidEmail()) {
            String cleanName = getCleanName();
            if (email.indexOf(",") > 0) {
                String[] arr = email.split(",");
                for (String str : arr) {
                    emails.add(cleanName + "<" + str.trim() + ">");
                }
            }
            else {
                emails.add(cleanName + "<" + email.trim() + ">");
            }
        }
        return emails;
    }

    // for agent
    @Transient
    public boolean hasRentals()
    {
        return rentals != null && rentals.size() > 0;
    }

    // for owner
    @Transient
    public boolean hasOwnerRentals()
    {
        getRentalOwners();
        return rentalOwners != null && rentalOwners.size() > 0;
    }

    // for adding new link
    @Transient
    public int getRentalId()
    {
        return rentalId;
    }

    @Transient
    public void setRentalId(int val)
    {
        rentalId = val;
    }

    @Override
    public String toString()
    {
        String ret = "";
        if (!name.equals("")) {
            ret += name;
        }
        return ret;
    }

    @Override
    public boolean equals(Object o)
    {

        if (!(o instanceof Owner)) {
            return false;
        }
        Owner owner = (Owner) o;
        return owner.getId() == id;
    }

    @Override
    public int hashCode()
    {
        int ret = 17;
        if (id > 0) ret += 37 * id;
        if (name != null) ret += name.hashCode();
        return ret;
    }

    @Transient
    public boolean hasValidEmail()
    {
        if (email != null && !email.equals("")) {
            return Helper.isValidEmails(email);
        }
        return false;
    }

    @Transient
    public Integer getOwnerId()
    {
        return ownerId;
    }

    @Transient
    public void setOwnerId(Integer val)
    {
        ownerId = val;
    }

    @Transient
    public String getPhone()
    {
        return phone;
    }

    @Transient
    public void setPhone(String val)
    {
        phone = val;
    }

    @Transient
    public Character getNoEmail()
    {
        return noEmail;
    }

    @Transient
    public void setNoEmail(Character val)
    {
        noEmail = val;
    }

    @Transient
    public boolean hasNoEmail()
    {
        return noEmail != null;
    }
}
