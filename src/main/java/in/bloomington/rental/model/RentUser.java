package in.bloomington.rental.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
@Component
@Scope("session")
public class RentUser implements java.io.Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int       id;

    @Column(name = "username")
    @Size(max = 10, min = 2, message = "{user.username.invalid}")
    private String    username = "";

    @Column(name = "full_name")
    @Size(max = 70, min = 2, message = "{user.full_name.invalid}")
    private String    full_name;
    //
    @Column(name = "role")
    private String    role;
    @Column(name = "inactive", length = 1)
    private Character inactive = null;

    @Transient
    String            password = "";

    public RentUser()
    {
    }

    public RentUser(int id)
    {
        this.id = id;
    }

    public RentUser(int id, String username, String full_name, String role, Character inactive)
    {
        this.id = id;
        setUsername(username);
        this.full_name = full_name;
        this.role      = role;
        this.inactive  = inactive;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setUsername(String val)
    {
        if (val != null) this.username = val.toLowerCase();
    }

    public void setFullName(String val)
    {
        this.full_name = val;
    }

    @Transient
    public void setFull_name(String val)
    {
        this.full_name = val;
    }

    public void setRole(String val)
    {
        if (val != null && !val.equals("0")) this.role = val;
    }

    public int getId()
    {
        return id;
    }

    public String getUsername()
    {
        return username;
    }

    public String getFullName()
    {
        return full_name;
    }

    @Transient
    public String getFull_name()
    {
        return full_name;
    }

    public String getRole()
    {
        return role;
    }

    public boolean isValid()
    {
        return username != null && !username.equals("");
    }

    public Character getInactive()
    {
        return this.inactive;
    }

    @Transient
    public boolean isActive()
    {
        return inactive == null;
    }

    public void setInactive(Character val)
    {
        this.inactive = val;
    }

    @Transient
    public String getPassword()
    {
        return password;
    }

    @Transient
    public void setPassword(String val)
    {
        if (val != null) password = val;
    }

    public String toString()
    {
        String ret = "";
        if (full_name != null && !full_name.equals("")) {
            if (!ret.equals("")) ret += ", ";
            ret = full_name;
        }
        else if (username != null) {
            ret = username;
        }
        return ret;
    }

    @Override
    public boolean equals(Object o)
    {

        if (!(o instanceof RentUser)) {
            return false;
        }
        RentUser user = (RentUser) o;
        return user.id == id
            && (   (username != null && user.getUsername().equals(username))
                || (username == null && user.getUsername() == null));

    }

    @Override
    public int hashCode()
    {
        int ret = 17;
        if (id > 0) ret += 37 * id;
        if (username != null) ret += username.hashCode();
        return ret;
    }

}
