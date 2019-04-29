package in.bloomington.rental.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import in.bloomington.rental.util.Helper;

@Entity
@Table(name = "email_detail_logs")

public class EmailDetailLog implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int      id;

    @Column(name =  "log_to")
    private String   logTo;

    @Column(name =  "log_cc")
    private String   logCc;

    @Column(name =  "log_bcc")
    private String   logBcc;

    @Column(name =  "owners")
    private String   ownersId;

    @Column(name =  "agents")
    private String   agentsId;

    @Column(name =  "rentals")
    private String   rentalsId;

    @Column(name =  "error_text")
    private String   errorText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email_log_id")
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
        this.agentsId  = agentId;
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
        this.agentsId  = agentId;
        this.rentalsId = rentalsId;
        this.errorText = errorText;
    }

    //-----------------------------------------------------
    // Generic Getters and Setters
    //-----------------------------------------------------
    public int      getId       () { return this.id;        }
    public String   getLogTo    () { return this.logTo;     }
    public String   getLogCc    () { return this.logCc;     }
    public String   getLogBcc   () { return this.logBcc;    }
    public String   getOwnersId () { return this.ownersId;  }
    public String   getAgentId  () { return this.agentsId;  }
    public String   getRentalsId() { return this.rentalsId; }
    public String   getErrorText() { return this.errorText; }
    public EmailLog getEmailLog () { return this.emailLog;  }




    public void setId       (int     id) { this.id        = id; }
    public void setLogTo    (String   s) { this.logTo     = s; }
    public void setLogCc    (String   s) { this.logCc     = s; }
    public void setLogBcc   (String   s) { this.logBcc    = s; }
    public void setOwnersId (String   s) { this.ownersId  = s; }
    public void setAgentId  (String   s) { this.agentsId  = s; }
    public void setRentalsId(String   s) { this.rentalsId = s; }
    public void setErrorText(String   s) { this.errorText = s; }
    public void setEmailLog (EmailLog l) { this.emailLog  = l; }

    //-----------------------------------------------------
    // Transients
    //-----------------------------------------------------
    @Transient
    public String getLogToClean()
    {
        if (logTo != null) {
            return Helper.doCleanTag(getLogTo());
        }
        return logTo;
    }

    @Transient
    public String getLogCcClean()
    {
        if (logCc != null) {
            return Helper.doCleanTag(logCc);
        }
        return logCc;
    }

    @Transient
    public String getLogBccClean()
    {
        if (logBcc != null) {
            return Helper.doCleanTag(logBcc);
        }
        return this.logBcc;
    }
}
