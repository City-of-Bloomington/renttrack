package in.bloomington.rental.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.hibernate.Query;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "owner_phones")
public class OwnerPhone implements java.io.Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int    id;

    @Column(name = "phone_num", nullable = false, length = 30)
    @Size(max = 20, min = 7, message = "{ownerphone.phone_num.invalid}")
    private String phoneNum;
    @Column(name = "type", nullable = false)
    private String type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private Owner  owner;

    public OwnerPhone()
    {
    }

    public OwnerPhone(int id)
    {
        this.id = id;
    }

    public OwnerPhone(int id, String phoneNum, String type)
    {
        this.id       = id;
        this.phoneNum = phoneNum;
        this.type     = type;
    }

    public OwnerPhone(int id, String phoneNum, String type, Owner owner)
    {
        this.id       = id;
        this.phoneNum = phoneNum;
        this.type     = type;
        this.owner    = owner;
    }

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Owner getOwner()
    {
        return this.owner;
    }

    public void setOwner(Owner owner)
    {
        this.owner = owner;
    }

    public String getPhoneNum()
    {
        return this.phoneNum;
    }

    public void setPhoneNum(String phoneNum)
    {
        this.phoneNum = phoneNum;
    }

    public String getType()
    {
        return this.type;
    }

    public void setType(String type)
    {
        if (type != null && !type.equals("0")) this.type = type;
    }

}
