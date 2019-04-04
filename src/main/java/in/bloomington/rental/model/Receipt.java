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

    SimpleDateFormat   dtf = new SimpleDateFormat("MM/dd/yyyy");
    private int        id;
    private Bill       bill;
    private Integer    receiptNo;
    private BigDecimal receivedSum;
    private Date       receivedDate;
    private String     receivedFrom;
    private String     paidBy;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id")
    public Bill getBill()
    {
        return this.bill;
    }

    public void setBill(Bill bill)
    {
        this.bill = bill;
    }

    @Column(name = "receipt_no")
    public Integer getReceiptNo()
    {
        if (receiptNo == null && id > 0) receiptNo = new Integer(id);
        return this.receiptNo;
    }

    public void setReceiptNo(Integer receiptNo)
    {
        this.receiptNo = receiptNo;
    }

    @Column(name = "received_sum", precision = 7)
    public BigDecimal getReceivedSum()
    {
        return this.receivedSum;
    }

    public void setReceivedSum(BigDecimal receivedSum)
    {
        this.receivedSum = receivedSum;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "received_date", length = 13)
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

    @Column(name = "received_from", length = 70)
    public String getReceivedFrom()
    {
        return this.receivedFrom;
    }

    public void setReceivedFrom(String receivedFrom)
    {
        this.receivedFrom = receivedFrom;
    }

    @Column(name = "paid_by")
    public String getPaidBy()
    {
        return this.paidBy;
    }

    public void setPaidBy(String paidBy)
    {
        this.paidBy = paidBy;
    }

    @Column(name = "check_no", length = 50)
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
