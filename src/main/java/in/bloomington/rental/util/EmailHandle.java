package in.bloomington.rental.util;
import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.Address;
import javax.mail.internet.*;
import javax.activation.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * msgmail creates a very simple text/plain message and sends it.
 * <p>
 * usage: <code>java msgmail <i>to from smtphost true|false</i></code>
 * where <i>to</i> and <i>from</i> are the destination and
 * origin email addresses, respectively, and <i>smtphost</i>
 * is the hostname of the machine that has the smtp server
 * running. The last parameter either turns on or turns off
 * debugging during sending.
 *
 * @author Sun microsystems
 */
public class EmailHandle{

		private static final Logger logger = LogManager.getLogger(EmailHandle.class);
		final static long serialVersionUID = 580L;
    static String msgText = "This is a message body.\nHere's the second line.";
    static String to = "sibow@bloomington.in.gov";
    static String from = "hand@bloomington.in.gov";
    // static String host = "smtp.bloomington.in.gov"; 
		static String host = "smtp.bloomington.in.gov"; // localhost
    static boolean debug = true;
    static String subject = "Rental Expire Email";
    static String _subject = "Rental Expire Email";
    static String _msgText = "This is a message body.\nHere's the second line.";
    static String _to = "sibow@bloomington.in.gov";
    static String _from = "hand@bloomington.in.gov";
    static String _host = "smtp.bloomington.in.gov";
    static boolean _debug = true;
    static String cc = null;
		static String bcc = null;
		String file_name = null, file_path = "";
    /**
     * The main initializer.
     *
     * For test purpose.
     * @param args the message arguements
     */
    public static void main(String[] args) {

				if (args.length > 0 && args.length != 5) {
						System.err.println("Not enough argument");
						System.exit(1);
				}
				else if(args.length == 0){
						EmailHandle mm = new EmailHandle();
						mm.send();
				}
				else {
						to = args[0];
						from = args[1];
						host = args[2];
						msgText = args[4];
						EmailHandle mm = new EmailHandle();
						mm.send();
				}
    }
		public String toString(){
				String ret = "";
				if(!to.equals("")) ret += "to: "+to+"\n";
				if(!from.equals("")) ret += "from: "+from+"\n";
				if(!subject.equals("")) ret += "subject: "+subject+"\n";
				if(cc != null && !cc.equals("")) ret += "cc: "+cc+"\n";
				if(bcc != null && !bcc.equals("")) ret += "bcc: "+bcc+"\n";
				if(file_name != null && !file_name.equals("")) ret += "file: "+file_name+"\n";
				if(file_path != null && !file_path.equals("")) ret += "path: "+file_path+"\n";				
				if(!msgText.equals("")) ret += "msg: "+msgText;
				return ret;
		}
    /**
     * The main constructor.
     *
     * @param to2 to email address
     * @param from2 from email address
     * @param msg2 the message
     * @param cc2 the cc email address
		 * @param bcc2 the blind carbon copy list 
     */
    public EmailHandle( String to2,
												String from2,
												String subject2,
												String msg2,
												String cc2,
												String bcc2){
				
				if(to2 != null && to2.equals("Bulk")){
						to = null;
				}
				else{
						to = to2;
				}
				cc = cc2;
				bcc = bcc2;
				from = from2;
				msgText = msg2;
				if(subject2 != null) subject = subject2;
		}
	
		//
    // another constructor
    //
    /**
     * The default constructor.
     *
     * for test purpose.
     */
    public EmailHandle(){
				this(_to,_from,_subject, _msgText, null, null);
    }
    public EmailHandle( String to2,
												String from2,
												String subject2,
												String msg2,
												String cc2,
												String bcc2,
												String file_name2,
												String file_path2){

				if(to2 != null && to2.equals("Bulk")){
						to = null;
				}
				else{
						to = to2;
				}
				cc = cc2;
				bcc = bcc2;
				from = from2;
				msgText = msg2;
				if(subject2 != null) subject = subject2;
				file_name = file_name2;
				file_path = file_path2;
		}
		//
		public String send(){
		
				String message = "";
				try {
						//
						// create some properties and get the default Session
						//
						Properties props = new Properties();
						props.put("mail.smtp.host", host);
						if (debug) props.put("mail.debug", "true");
			
						Session session = Session.getDefaultInstance(props, null);
						session.setDebug(debug);
						//
						// create a message
						//
						Message msg = new MimeMessage(session);
						msg.setFrom(new InternetAddress(from));
						if(to != null && !to.equals("")){
								InternetAddress[] address = {new InternetAddress(to)};
								msg.setRecipients(Message.RecipientType.TO, address);
						}
						if(cc != null && !cc.equals("")){
								InternetAddress[] address2 = {new InternetAddress(cc)};
								msg.setRecipients(Message.RecipientType.CC, address2);
						}
						if(bcc != null && !bcc.equals("")){
								InternetAddress[] address3 = javax.mail.internet.InternetAddress.parse(bcc);
								msg.setRecipients(Message.RecipientType.BCC, address3);
						}
						msg.setSubject(subject);
						msg.setSentDate(new Date());

						// If the desired charset is known, you can use
						// setText(text, charset)
						msg.setText(msgText);
						//
						Transport.send(msg);
				} catch (MessagingException mex){

						logger.debug("\n--Exception handling in MailHandle.java");
						//   mex.printStackTrace();
						message += " Exception in MailHandle "+mex;
						Exception ex = mex;
						do {
								if (ex instanceof SendFailedException) {
										SendFailedException sfex = (SendFailedException)ex;
										Address[] invalid = sfex.getInvalidAddresses();
										if (invalid != null) {
												System.out.println("    ** Invalid Addresses");
												if (invalid != null) {
														for (int i = 0; i < invalid.length; i++) 
																message += "         " + invalid[i];
												}
										}
										Address[] validUnsent = sfex.getValidUnsentAddresses();
										if (validUnsent != null) {
												System.out.println("    ** ValidUnsent Addresses");
												if (validUnsent != null) {
														for (int i = 0; i < validUnsent.length; i++) 
																message += "         "+validUnsent[i];
												}
										}
										Address[] validSent = sfex.getValidSentAddresses();
										if (validSent != null) {
												System.out.println("    ** ValidSent Addresses");
												if (validSent != null) {
														for (int i = 0; i < validSent.length; i++) 
																message += "         "+validSent[i];
												}
										}
								}
								System.out.println();
								if (ex instanceof MessagingException){
										ex = ((MessagingException)ex).getNextException();
								}
								else{
										ex = null;
								}
						} while (ex != null);
						logger.error(message);
				}
				return message;
    }
		public String sendWAttach(){
		
				String message = "";
				try {
						//
						// create some properties and get the default Session
						//
						Properties props = new Properties();
						props.put("mail.smtp.host", host);
						if (debug) props.put("mail.debug", "true");
						Session session = Session.getDefaultInstance(props, null);
						session.setDebug(debug);
						//
						// create a message
						//
						Message msg = new MimeMessage(session);
						msg.setFrom(new InternetAddress(from));
						if(to != null && !to.equals("")){
								InternetAddress[] address = {new InternetAddress(to)};
								msg.setRecipients(Message.RecipientType.TO, address);
						}
						if(cc != null && !cc.equals("")){
								InternetAddress[] address2 = {new InternetAddress(cc)};
								msg.setRecipients(Message.RecipientType.CC, address2);
						}
						if(bcc != null && !bcc.equals("")){
								InternetAddress[] address3 = javax.mail.internet.InternetAddress.parse(bcc);
								msg.setRecipients(Message.RecipientType.BCC, address3);
						}
						msg.setSubject(subject);
						msg.setSentDate(new Date());
						// Create the message part 
						BodyPart messageBodyPart = new MimeBodyPart();
						//			
						// Fill the message
						messageBodyPart.setText(msgText);
						Multipart multipart = new MimeMultipart();
						multipart.addBodyPart(messageBodyPart);
						// Part two is attachment
						messageBodyPart = new MimeBodyPart();
						DataSource source = new FileDataSource(file_path+file_name);
						messageBodyPart.setDataHandler(new DataHandler(source));
						messageBodyPart.setFileName(file_name);
						multipart.addBodyPart(messageBodyPart);
						// Put parts in message
						msg.setContent(multipart);
						//
						Transport.send(msg);
				} catch (MessagingException mex){

						logger.debug("\n--Exception handling in MailHandle.java");
						//   mex.printStackTrace();
						message += " Exception in MailHandle "+mex;
						Exception ex = mex;
						do {
								if (ex instanceof SendFailedException) {
										SendFailedException sfex = (SendFailedException)ex;
										Address[] invalid = sfex.getInvalidAddresses();
										if (invalid != null) {
												System.out.println("    ** Invalid Addresses");
												if (invalid != null) {
														for (int i = 0; i < invalid.length; i++) 
																message += "         " + invalid[i];
												}
										}
										Address[] validUnsent = sfex.getValidUnsentAddresses();
										if (validUnsent != null) {
												System.out.println("    ** ValidUnsent Addresses");
												if (validUnsent != null) {
														for (int i = 0; i < validUnsent.length; i++) 
																message += "         "+validUnsent[i];
												}
										}
										Address[] validSent = sfex.getValidSentAddresses();
										if (validSent != null) {
												System.out.println("    ** ValidSent Addresses");
												if (validSent != null) {
														for (int i = 0; i < validSent.length; i++) 
																message += "         "+validSent[i];
												}
										}
								}
								System.out.println();
								if (ex instanceof MessagingException){
										ex = ((MessagingException)ex).getNextException();
								}
								else{
										ex = null;
								}
						} while (ex != null);
						logger.error(message);
				}
				return message;
    }
}
