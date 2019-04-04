package in.bloomington.rental.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "attachement_seq")
public class AttachementSeq implements java.io.Serializable
{

    private int id;

    public AttachementSeq()
    {
    }

    public AttachementSeq(int id)
    {
        this.id = id;
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
}
