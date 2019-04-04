package in.bloomington.rental.model;

import java.util.Date;
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
import in.bloomington.rental.util.Helper;

@Entity
@Table(name = "email_detail_logs")

public class EmailDetailLog implements java.io.Serializable
{

    private int      id;
    private String   logTo;
    private String   logCc;
    private String   logBcc;
    private String   ownersId;
    private String   agentId;
    private String   rentalsId;
    private String   errorText;
    private EmailLog emailLog;

    public EmailDetailLog()
    {
    }

    public EmailDetailLog(int id)
    {
        this.id = id;
    }

    public EmailDetailLog(int id, String logTo,
                                  String logCc,
                                  String logBcc,
                                  String ownersId,
                                  String agentId,
                                  String rentalsId,
                                  String errorText,
                                  EmailLog emailLog)
    {
        this.id        = id;
        this.emailLog  = emailLog;
        this.logTo     = logTo;
        this.logCc     = logCc;
        this.logBcc    = logBcc;
        this.ownersId  = ownersId;
        this.agentId   = agentId;
        this.rentalsId = rentalsId;
        this.errorText = errorText;

    }

    // for save
    public EmailDetailLog(EmailLog emailLog,
                          String logTo,
                          String logCc,
                          String logBcc,
                          String ownersId,
                          String agentId,
                          String rentalsId,
                          String errorText)
    {
        this.emailLog  = emailLog;
        this.logTo     = logTo;
        this.logCc     = logCc;
        this.logBcc    = logBcc;
        this.ownersId  = ownersId;
        this.agentId   = agentId;
        this.rentalsId = rentalsId;
        this.errorText = errorText;
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
    @JoinColumn(name = "email_log_id")
    public EmailLog getEmailLog()
    {
        return this.emailLog;
    }

    public void setEmailLog(EmailLog val)
    {
        this.emailLog = val;
    }

    @Column(name = "log_to", length = 70)
    public String getLogTo()
    {
        return this.logTo;
    }

    @Transient
    public String getLogToClean()
    {
        if (logTo != null) {
            return Helper.doCleanTag(getLogTo());
        }
        return logTo;
    }

    public void setLogTo(String logTo)
    {
        this.logTo = logTo;
    }

    @Column(name = "log_cc", length = 70)
    public String getLogCc()
    {
        return this.logCc;
    }

    @Transient
    public String getLogCcClean()
    {
        if (logCc != null) {
            return Helper.doCleanTag(logCc);
        }
        return logCc;
    }

    public void setLogCc(String logCc)
    {
        this.logCc = logCc;
    }

    @Column(name = "log_bcc", length = 300)
    public String getLogBcc()
    {
        return this.logBcc;
    }

    @Transient
    public String getLogBccClean()
    {
        if (logBcc != null) {
            return Helper.doCleanTag(logBcc);
        }
        return this.logBcc;
    }

    public void setLogBcc(String logBcc)
    {
        this.logBcc = logBcc;
    }

    @Column(name = "owners_id", length = 30)
    public String getOwnersId()
    {
        return this.ownersId;
    }

    public void setOwnersId(String ownersId)
    {
        this.ownersId = ownersId;
    }

    @Column(name = "agent_id", length = 30)
    public String getAgentId()
    {
        return this.agentId;
    }

    public void setAgentId(String agentId)
    {
        this.agentId = agentId;
    }

    @Column(name = "rentals_id", length = 50)
    public String getRentalsId()
    {
        return this.rentalsId;
    }

    public void setRentalsId(String rentalsId)
    {
        this.rentalsId = rentalsId;
    }

    @Column(name = "error_text", length = 500)
    public String getErrorText()
    {
        return this.errorText;
    }

    public void setErrorText(String errorText)
    {
        this.errorText = errorText;
    }
}
