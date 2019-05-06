package in.bloomington.rental.model;

import java.math.BigDecimal;
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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Transient;

@Entity
@Table(name = "receipts")
public class Receipt implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;

		@Transient
    SimpleDateFormat   dtf = new SimpleDateFormat("MM/dd/yyyy");
		
		@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int        id;

		@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id")
    private Bill       bill;

		@Column(name = "receipt_no")
    private Integer    receiptNo;

		@Column(name = "received_sum", precision = 7)
    private BigDecimal receivedSum;

		@Temporal(TemporalType.DATE)
    @Column(name = "received_date", length = 13)
    private Date       receivedDate;

    @Column(name = "received_from", length = 70)
    private String     receivedFrom;
		
		@Column(name = "paid_by")
    private String     paidBy;

		@Column(name = "check_no", length = 50)
    private String     checkNo;

    public Receipt()
    {
    }

    public Receipt(int id)
    {
        this.id = id;
    }

    public Receipt(int id, Bill       bill,
                           Integer    receiptNo,
                           BigDecimal receivedSum,
                           Date       receivedDate,
                           String     receivedFrom,
                           String     paidBy,
                           String     checkNo)
    {
        this.id           = id;
        this.bill         = bill;
        this.receiptNo    = receiptNo;
        this.receivedSum  = receivedSum;
        this.receivedDate = receivedDate;
        this.receivedFrom = receivedFrom;
        this.paidBy       = paidBy;
        this.checkNo      = checkNo;
    }

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Bill getBill()
    {
        return this.bill;
    }

    public void setBill(Bill bill)
    {
        this.bill = bill;
    }

    public Integer getReceiptNo()
    {
        if (receiptNo == null && id > 0) receiptNo = new Integer(id);
        return this.receiptNo;
    }

    public void setReceiptNo(Integer receiptNo)
    {
        this.receiptNo = receiptNo;
    }

    public BigDecimal getReceivedSum()
    {
        return this.receivedSum;
    }

    public void setReceivedSum(BigDecimal receivedSum)
    {
        this.receivedSum = receivedSum;
    }

    public Date getReceivedDate()
    {
        if (id == 0) receivedDate = new Date();
        return this.receivedDate;
    }

    public void setReceivedDate(Date receivedDate)
    {
        if (id == 0) receivedDate = new Date();
        this.receivedDate = receivedDate;
    }

    @Transient
    public String getReceivedDateFr()
    {
        getReceivedDate();
        if (receivedDate != null) {
            return dtf.format(receivedDate);
        }
        return "";
    }

    @Transient
    public void setReceivedDateFr(String val)
    {
        if (val != null) {
            try {
                receivedDate = dtf.parse(val);
            }
            catch (Exception ex) {
            }
        }
    }

    public String getReceivedFrom()
    {
        return this.receivedFrom;
    }

    public void setReceivedFrom(String receivedFrom)
    {
        this.receivedFrom = receivedFrom;
    }

    public String getPaidBy()
    {
        return this.paidBy;
    }

    public void setPaidBy(String paidBy)
    {
        this.paidBy = paidBy;
    }


    public String getCheckNo()
    {
        return this.checkNo;
    }

    public void setCheckNo(String checkNo)
    {
        this.checkNo = checkNo;
    }

    /*
     * we allow the record to be updated on the same day only
     */
    @Transient
    public boolean isNewRecord()
    {
        Date   today = new Date();
        String str   = dtf.format(today);
        String str2  = getReceivedDateFr();
        return str.equals(str2);
    }

}
