package in.bloomington.rental.util;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.ArrayList;
// import java.text.SimpleDateFormat;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Component
public class Emailer{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");
		private static final Logger logger = LogManager.getLogger(Emailer.class);
		String dateFrom="", dateTo="",
				cc = "Enter an email address here (optional)",
				subject="Rental Permit Expires Soon",
				emailFrom = "hand@bloomington.in.gov",
				intro="",
				para1="",
				para2="";
		public Emailer(){
		}

		/**
			 parsing and formatting dates
			 LocalDate date = LocalDate.parse(input, formatter);
			 String out = date.format(formatter);

		 */
		// normally this 2 month from now
		public String getDateFrom(){
				if(dateFrom == null || dateFrom.equals("")){
						LocalDate date = LocalDate.now().plusMonths(2);
						dateFrom = date.format(formatter);
				}
				return dateFrom;
		}
		public void setDateFrom(String val){
				dateFrom = val;
		}
		public String getDateTo(){
				if(dateTo == null || dateTo.equals("")){
						LocalDate date = LocalDate.now().plusMonths(3);
						dateTo = date.format(formatter);
				}
				return dateTo;
		}
		public void setDateTo(String val){
				dateTo = val;
		}
		public String getCc(){
				return cc;
		}
		public void setCc(String val){
				if(val != null && !val.equals("")){
						if(val.trim().indexOf(emailFrom) == -1){
								if(cc.indexOf("optional") ==  -1){
										cc = val;
								}
						}
				}
		}
		public String getEmailFrom(){
				return emailFrom;
		}
		public void setEmailFrom(String val){
				if(val != null && !val.equals(""))
						emailFrom = val;
		}		
		public String getSubject(){
				return subject;
		}
		public void setSubject(String val){
				subject = val;
		}		
		public String getIntro(){
				return intro;
		}
		public void setIntro(String val){
				intro = val;
		}
		public String getPara1(){
				return para1;
		}
		public void setPara1(String val){
				para1 = val;
		}
		public String getPara2(){
				return para2;
		}
		public void setPara2(String val){
				para2 = val;
		}		
		/*
			change colors in mvn console maven\bin\mvn.cmd
		set MAVEN_OPTS=-Dstyle.info=bold,white	-Dstyle.error=bold.white -Dstyle.failure=bold.white -Dstyle.mojo=white -Dstyle.project=white
		*/
		}

		
