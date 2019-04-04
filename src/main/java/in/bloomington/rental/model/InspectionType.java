package in.bloomington.rental.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "inspection_types")
public class InspectionType implements java.io.Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int    id;

    @Column(name = "alias", length = 4)
    private String alias;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    public InspectionType()
    {
    }

    public InspectionType(int id)
    {
        this.id = id;
    }

    public InspectionType(int id, String name)
    {
        this.id   = id;
        this.name = name;
    }

    public InspectionType(int id, String alias, String name)
    {
        this.id    = id;
        this.alias = alias;
        this.name  = name;
    }

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getAlias()
    {
        return this.alias;
    }

    public void setAlias(String alias)
    {
        this.alias = alias;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }

}
