package in.bloomington.rental.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import in.bloomington.rental.util.Helper;

/*
 * This class is not a Database class, it is a place holder for
 * case violation types that we get from legalTrack app
 */
public class LegalType implements java.io.Serializable
{

    String name = "", id = ""; // id is non integer string

    public LegalType()
    {
    }

    public LegalType(String val, String val2)
    {
        setId(val);
        setName(val2);
    }

    public void setId(String val)
    {
        if (val != null) id = val;
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String val)
    {
        if (val != null) name = val.trim();
    }

    public String toString()
    {
        String ret = "";
        ret += "id:" + id;
        if (!name.equals("")) ret += "name:" + name;
        return ret;
    }

    @Override
    public int hashCode()
    {
        int ret = 17;
        if (id != null) ret += 37 * id.hashCode();
        return ret;
    }
}
