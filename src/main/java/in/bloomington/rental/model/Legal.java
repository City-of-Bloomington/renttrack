package in.bloomington.rental.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import in.bloomington.rental.util.Helper;
/*
 * This class is not a Database class, it is a place holder we need to
 * send StartLegal action to legaltrack app
 */
public class Legal implements java.io.Serializable {

		String 
				reason="",
				startDate = Helper.getToday(),
				status="New", // for legal
				startBy="",
				startByName="", pullDate="", pullReason="",
				attention="Legal";
		int rental_id = 0;
		Integer[] address_ids = null;
		Integer[] owner_ids = null;
		String case_id="", case_type="",
				case_status="PD"; // pending
		RentUser user = null;
		Rental rental = null;
		List<Owner> owners = null;
		List<Address> addresses = null;
				
    public Legal() {
    }
		public void setRental(Rental val){
				if(val != null){
						rental = val;
						rental_id = rental.getId();
				}
		}
    public void setRental_id(int val) {
				rental_id = val;
    }
		public int getRental_id(){
				return rental_id;
		}
		public void setUser(RentUser val){
				if(val != null){
						user = val;
						startBy = user.getUsername();
						startByName = user.getFullName();
				}
		}   
    public String getReason() {
        return reason;
    }		
	
    public void setReason(String val) {
				if(val != null)
						reason = val.trim();
    }
    public String getPullReason() {
        return pullReason;
    }		
	
    public void setPullReason(String val) {
				if(val != null)
						pullReason = val.trim();
    }		
   
     public void setStartDate(String val) {
				if(val != null)
						startDate = val;
    }
   
    public String getStartDate() {
        return startDate;
    }
     public void setPullDate(String val) {
				if(val != null)
						pullDate = val;
    }
   
    public String getPullDate() {
        return pullDate;
    }		
    public void setStartBy(String val) {
				if(val != null)
						startBy = val.trim();
    }
   
    public String getStartBy() {
				if(user != null){
						startBy = user.getUsername();
				}
        return startBy;
    }
		public void setOwner_ids(Integer[] vals){
				if(vals != null)
						owner_ids = vals;
		}
		public void setAddress_ids(Integer[] vals){
				if(vals != null)
						address_ids = vals;
		}
		public Integer[] getOwner_ids(){
				return owner_ids;
		}
		public Integer[] getAddress_ids(){
				return address_ids;
		}
		public boolean hasOwnerIds(){
				return owner_ids != null && owner_ids.length > 0;
		}
		public boolean hasAddressIds(){
				return address_ids != null && address_ids.length > 0;
		}
		/**
		 * all these are required to have a valid legal case
		 */
		public boolean isValid(){
				return
						hasOwnerIds() &&
						hasAddressIds() &&
						case_type != null &&
						!case_type.equals("");
		}
    public String getAttention() {
        return attention;
    }		
    public void setStartByName(String val) {
				if(val != null)
						startByName = val.trim();
    }
		public String getCase_status(){
				return case_status;
		}
		public String getStatus(){
				return status;
		}
   
    public String getStartByName() {
        return startByName;
    }
		
    public void setCase_type(String val) {
				if(val != null)
						case_type = val.trim();
    }
    public String getCase_type() {
        return case_type;
    }
		public Rental getRental(){
				return rental;
		}
		public RentUser getUser(){
				return user;
		}		
		public void setAddresses(List<Address> vals){
				if(vals != null)
						addresses = vals;
		}
		public List<Address> getAddresses(){
				return addresses;
		}
		public void addAddress(Address val){
				if(addresses == null){
						addresses = new ArrayList<>();
				}
				addresses.add(val);
				
		}
		public void setOwners(List<Owner> vals){
				if(vals != null)
						owners = vals;
		}
		public List<Owner> getOwners(){
				return owners;
		}
		public void addOwner(Owner val){
				if(val != null){
						if(owners == null)
								owners = new ArrayList<>();
						owners.add(val);
				}
		}
		public boolean hasOwners(){
				return owners != null && owners.size() > 0;
		}
		public boolean hasAddresses(){
				return addresses != null && addresses.size() > 0;
		}		
		public String toString(){
				String ret = "";
				if(rental_id > 0)
						ret += "rental_id:"+rental_id;
				if(!reason.equals(""))
						ret += "reason:"+reason;
				if(!startDate.equals(""))
						ret += "date:"+startDate;
				if(!startBy.equals(""))
						ret += "startBy:"+startBy;
				if(!case_type.equals(""))
						ret += "type:"+case_type;
				return ret;
		}
		@Override
    public int hashCode() {
        int ret = 17;
				if(rental_id > 0)
						ret += 37*rental_id;
        return ret;
    }		
}


