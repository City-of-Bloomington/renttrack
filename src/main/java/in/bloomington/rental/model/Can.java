package in.bloomington.rental.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "cans")

public class Can implements java.io.Serializable
{

    private int    id;
    private String type = "Paragraph";
    private String title;
    private String item1;
    private String item2;
    private String item3;
    private String item4;
    private String item5;
    private String item6;
    private String item7;
    private String item8;

    public Can()
    {
    }

    public Can(int id)
    {
        this.id = id;
    }

    public Can(int id, String type,
                       String title,
                       String item1,
                       String item2,
                       String item3,
                       String item4,
                       String item5,
                       String item6,
                       String item7,
                       String item8)
    {
        this.id    = id;
        this.type  = type;
        this.title = title;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.item4 = item4;
        this.item5 = item5;
        this.item6 = item6;
        this.item7 = item7;
        this.item8 = item8;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @Column(name = "title", length = 180)
    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String val)
    {
        if (val != null)
            this.title = val.trim();
        else
            this.title = val;
    }

    // Paragraph or List
    @Column(name = "type", length = 10)
    public String getType()
    {
        return this.type;
    }

    public void setType(String val)
    {
        if (val != null) this.type = val;
    }

    @Transient
    public boolean isParagraph()
    {
        return type != null && type.equals("Paragraph");
    }

    // this is always paragraph
    @Column(name = "item1", length = 1024)
    public String getItem1()
    {
        return this.item1;
    }

    public void setItem1(String val)
    {
        if (val != null) {
            val = val.trim();
        }
        this.item1 = val;
    }

    //
    // list items start from item2
    //
    @Column(name = "item2", length = 1024)
    public String getItem2()
    {
        return this.item2;
    }

    public void setItem2(String val)
    {
        if (val != null) {
            val = val.trim();
        }
        this.item2 = val;
    }

    @Column(name = "item3", length = 1024)
    public String getItem3()
    {
        return this.item3;
    }

    public void setItem3(String val)
    {
        if (val != null) {
            val = val.trim();
        }
        this.item3 = val;
    }

    @Column(name = "item4", length = 1024)
    public String getItem4()
    {
        return this.item4;
    }

    public void setItem4(String val)
    {
        if (val != null) {
            val = val.trim();
        }
        this.item4 = val;
    }

    @Column(name = "item5", length = 256)
    public String getItem5()
    {
        return this.item5;
    }

    public void setItem5(String val)
    {
        if (val != null) {
            val = val.trim();
        }
        this.item5 = val;
    }

    @Column(name = "item6", length = 256)
    public String getItem6()
    {
        return this.item6;
    }

    public void setItem6(String val)
    {
        if (val != null) {
            val = val.trim();
        }
        this.item6 = val;
    }

    @Column(name = "item7", length = 256)
    public String getItem7()
    {
        return this.item7;
    }

    public void setItem7(String val)
    {
        if (val != null) {
            val = val.trim();
        }
        this.item7 = val;
    }

    @Column(name = "item8", length = 256)
    public String getItem8()
    {
        return this.item8;
    }

    public void setItem8(String val)
    {
        if (val != null) {
            val = val.trim();
        }
        this.item8 = val;
    }

    // all the rest except title and item1
    @Transient
    public String getOther()
    {
        String ret = "";
        if (item2 != null && !item2.equals("")) {
            ret += item2;
        }
        if (item3 != null && !item3.equals("")) {
            if (!ret.equals("")) ret += ", ";
            ret += item3;
        }
        if (item4 != null && !item4.equals("")) {
            if (!ret.equals("")) ret += ", ";
            ret += item4;
        }
        if (item5 != null && !item5.equals("")) {
            if (!ret.equals("")) ret += ", ";
            ret += item5;
        }
        if (item6 != null && !item6.equals("")) {
            if (!ret.equals("")) ret += ", ";
            ret += item6;
        }
        if (item7 != null && !item7.equals("")) {
            if (!ret.equals("")) ret += ", ";
            ret += item7;
        }
        if (item8 != null && !item8.equals("")) {
            if (!ret.equals("")) ret += ", ";
            ret += item8;
        }
        return ret;

    }

    @Override
    public String toString()
    {
        String ret = "";
        if (title != null && !title.equals("")) {
            ret = title;
        }
        if (item1 != null && !item1.equals("")) {
            if (!ret.equals("")) ret += ", ";
            ret += item1;
        }
        if (item2 != null && !item2.equals("")) {
            if (!ret.equals("")) ret += ", ";
            ret += item2;
        }
        if (item3 != null && !item3.equals("")) {
            if (!ret.equals("")) ret += ", ";
            ret += item3;
        }
        if (item4 != null && !item4.equals("")) {
            if (!ret.equals("")) ret += ", ";
            ret += item4;
        }
        if (item5 != null && !item5.equals("")) {
            if (!ret.equals("")) ret += ", ";
            ret += item5;
        }
        if (item6 != null && !item6.equals("")) {
            if (!ret.equals("")) ret += ", ";
            ret += item6;
        }
        if (item7 != null && !item7.equals("")) {
            if (!ret.equals("")) ret += ", ";
            ret += item7;
        }
        if (item8 != null && !item8.equals("")) {
            if (!ret.equals("")) ret += ", ";
            ret += item8;
        }
        return ret;
    }
}
