package in.bloomington.rental.model;

/**
 * general use class useful for auto complete owners, addresses, etc
 *
 */
public class Item implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;

    private int    id;
    private String alias;
    private String name;

    //
    public Item()
    {
    }

    public Item(int id, String name)
    {
        this.id   = id;
        this.name = name;
    }

    public Item(int id, String alias, String name)
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

    public String toString()
    {
        return name;
    }

}
