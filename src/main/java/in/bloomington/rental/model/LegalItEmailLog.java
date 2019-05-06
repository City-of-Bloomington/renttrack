package in.bloomington.rental.model;
// Generated Jan 16, 2018 2:07:01 PM by Hibernate Tools 4.3.1

import java.util.Date;
import java.text.SimpleDateFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "legalit_email_logs")

public class LegalItEmailLog implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;

		@Transient
    SimpleDateFormat dtf = new SimpleDateFormat("MM/dd/yyyy");
		
		@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int      id;
		
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_id")
    private Rental   rental;

		@Temporal(TemporalType.DATE)		
    @Column(name = "date", length = 13)		
    private Date     date;

		@Column(name = "e_from", length = 80)
    private String   EFrom;
		
		@Column(name = "e_to", length = 80)
    private String   ETo;
		
		@Column(name = "e_cc", length = 80)
    private String   ECc;
		
		@Column(name = "e_subject", length = 80)
    private String   ESubject;

		@Column(name = "e_msg", length = 1000)
    private String   EMsg;
		
		@Column(name = "error_text", length = 500)
    private String   errorText;

    public LegalItEmailLog()
    {
    }

    public LegalItEmailLog(int id)
    {
        this.id = id;
    }

    public LegalItEmailLog(int id, Rental rental,
                                   Date   date,
                                   String EFrom,
                                   String ETo,
                                   String ECc,
                                   String ESubject,
                                   String EMsg,
                                   String errorText)
    {
        this.id        = id;
        this.rental    = rental;
        this.date      = date;
        this.EFrom     = EFrom;
        this.ETo       = ETo;
        this.ECc       = ECc;
        this.ESubject  = ESubject;
        this.EMsg      = EMsg;
        this.errorText = errorText;
    }

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Rental getRental()
    {
        return this.rental;
    }

    public void setRental(Rental rental)
    {
        this.rental = rental;
    }


    public Date getDate()
    {
        return this.date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    @Transient
    public String getDateFr()
    {
        if (date != null) {
            return dtf.format(date);
        }
        return "";
    }


    public String getEFrom()
    {
        return this.EFrom;
    }

    public void setEFrom(String EFrom)
    {
        this.EFrom = EFrom;
    }

    public String getETo()
    {
        return this.ETo;
    }

    public void setETo(String ETo)
    {
        this.ETo = ETo;
    }

    public String getECc()
    {
        return this.ECc;
    }

    public void setECc(String ECc)
    {
        this.ECc = ECc;
    }

    public String getESubject()
    {
        return this.ESubject;
    }

    public void setESubject(String ESubject)
    {
        this.ESubject = ESubject;
    }


    public String getEMsg()
    {
        return this.EMsg;
    }

    public void setEMsg(String EMsg)
    {
        this.EMsg = EMsg;
    }


    public String getErrorText()
    {
        return this.errorText;
    }

    public void setErrorText(String errorText)
    {
        this.errorText = errorText;
    }

}
