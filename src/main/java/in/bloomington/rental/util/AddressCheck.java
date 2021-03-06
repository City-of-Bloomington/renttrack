package in.bloomington.rental.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import in.bloomington.rental.model.Address;

@Component
public class AddressCheck{

		private static final Logger logger = LogManager.getLogger(AddressCheck.class);
		static String url = "";
		
		@Autowired
		private Environment env;
		
		Address address = null, exactMatchAddress = null;
		List<Address> addresses = null;

		public AddressCheck(){

		}
		public AddressCheck(Address val){
				setAddress(val);
		}		
		public void setAddress(Address val){
				if(val != null)
						address = val;
		}
		public Address getExactMatchAddress(){
				return exactMatchAddress;
		}
		public List<Address> getAddresses(){
				return addresses;
		}
		public boolean foundAddresses(){
				return addresses != null && addresses.size() > 0;
		}
		public boolean findExactMatch(){
				if(address != null && addresses != null){
						String val = address.getStreetAddress().toUpperCase();
						for(Address one:addresses){
								String val2 = one.getStreetAddress().toUpperCase();
								if(val.equals(val2)){
										exactMatchAddress = one;
										return true;
								}
						}
				}
				return false;
		}
		/* 
		 * given certain address, find similar addresses in master_address app
		 * we are going to use Cliff js addressCchooser to pick the right address
		 */
		public String findSimilarAddr(){
				//
				String back = "";
				String urlStr = "";
				// String query="format=json;queryType=address;query=";
				String query="url"+"/addresses?address=401+n+morton+st;format=json";
						
				if(url.equals("")){
						if(env != null){
								url = env.getProperty("addressCheckUrl");
						}
				}
				urlStr = url+"/?";
				System.err.println(" addr url "+urlStr);
				if(address == null){
						back = " No address set ";
						return back;
				}				
				String addr = address.getStreetAddress();
				if(addr == null || addr.equals("")){
						back = " No address set ";
						return back;
				}
				DefaultHttpClient httpclient = new DefaultHttpClient();		
				ResponseHandler<String> responseHandler = new BasicResponseHandler();
				try{
						query += java.net.URLEncoder.encode(addr, "UTF-8");
						query +="+Bloomington;";
						urlStr += query;
						HttpGet httpget = new HttpGet(urlStr);
						System.err.println(urlStr);
						logger.debug(urlStr);
            String responseBody = httpclient.execute(httpget, responseHandler);
						System.err.println(" response "+responseBody);
            logger.debug("----------------------------------------");
            logger.debug(responseBody);
            logger.debug("----------------------------------------");
						JSONArray jArray = new JSONArray(responseBody);
						addresses = new ArrayList<>();
						for (int i = 0; i < jArray.length(); i++) {
								JSONObject jObj = jArray.getJSONObject(i);
								if(jObj.has("id")){
										String master_address_id = jObj.getString("id");
										String location_id = jObj.getString("location_id");
										//
										//
										String latVal = jObj.getString("latitude");
										String lngVal = jObj.getString("longitude");
										if(latVal != null){
												address.setLatitude(new Double(latVal));
										}
										if(lngVal != null){
												address.setLongitude(new Double(lngVal));
										}
										String street = "";
										String full_addr="";
										if(!jObj.isNull("streetAddress")){
												street = jObj.getString("streetAddress");
												address.setStreetAddress(street);
												full_addr = street;
										}
										if(!jObj.isNull("subunit_count")){
												JSONArray jArr2 = jObj.getJSONArray("subunits");
												if(jArr2 != null){
														for(int j=0;j<jArr2.length();j++){
																JSONObject jObj2 = jArr2.getJSONObject(j);
																if(!jObj2.isNull("id")){
																		full_addr = street;
																		Address one = new Address();
																		one.setMaAddressId(new Integer(master_address_id));
																		one.setMaSubunitId(new Integer(jObj2.getString("id")));
																		// one.setMaLocationId(new Integer(location_id));
																		String type = jObj2.getString("type_code");
																		String ident = jObj2.getString("identifier");
																		full_addr += " "+type;
																		full_addr += " "+ident;
																		one.setStreetAddress(full_addr);
																		addresses.add(one);
																}
														}
												}
										}
										else{ // no subunit
												Address one = new Address();
												if(latVal != null){
														one.setLatitude(new Double(latVal));
														address.setLatitude(new Double(latVal));
												}
												if(lngVal != null){
														one.setLongitude(new Double(lngVal));
														address.setLongitude(new Double(lngVal));
												}												
												one.setStreetAddress(full_addr);
												one.setMaAddressId(new Integer(master_address_id));
												address.setMaAddressId(new Integer(master_address_id));
												addresses.add(one);
										}
								}
						}
				}
				catch(Exception ex){
						back = ex+" "+urlStr;
						logger.error(back);
				}
				finally{
						// 
						httpclient.getConnectionManager().shutdown();
				}
				return back;
    }
		/* 
		 * given certain address, find similar addresses in master_address app
		 */
		public String findSimilarAddrOld(){
				//
				String back = "";
				String urlStr = "";
				String query="format=json;queryType=address;query=";
				if(url.equals("")){
						if(env != null){
								url = env.getProperty("addressCheckUrl");
						}
				}
				urlStr = url+"/?";
				System.err.println(" addr url "+urlStr);
				if(address == null){
						back = " No address set ";
						return back;
				}				
				String addr = address.getStreetAddress();
				if(addr == null || addr.equals("")){
						back = " No address set ";
						return back;
				}
				DefaultHttpClient httpclient = new DefaultHttpClient();		
				ResponseHandler<String> responseHandler = new BasicResponseHandler();
				try{
						query += java.net.URLEncoder.encode(addr, "UTF-8");
						query +="+Bloomington;";
						urlStr += query;
						HttpGet httpget = new HttpGet(urlStr);
						System.err.println(urlStr);
						logger.debug(urlStr);
            String responseBody = httpclient.execute(httpget, responseHandler);
						System.err.println(" response "+responseBody);
            logger.debug("----------------------------------------");
            logger.debug(responseBody);
            logger.debug("----------------------------------------");
						JSONArray jArray = new JSONArray(responseBody);
						addresses = new ArrayList<>();
						for (int i = 0; i < jArray.length(); i++) {
								JSONObject jObj = jArray.getJSONObject(i);
								if(jObj.has("street_id")){
										String street_id = jObj.getString("street_id");
										//
										// location_id is in an array name locations
										// String location_id = jObj.getString("location_id");
										//
										String latVal = jObj.getString("latitude");
										String lngVal = jObj.getString("longitude");
										if(latVal != null){
												address.setLatitude(new Double(latVal));
										}
										if(lngVal != null){
												address.setLongitude(new Double(lngVal));
										}
										String street = "";
										String full_addr="";
										if(!jObj.isNull("streetAddress")){
												street = jObj.getString("streetAddress");
												address.setStreetAddress(street);
												full_addr = street;
										}
										if(!jObj.isNull("subunits")){
												JSONArray jArr2 = jObj.getJSONArray("subunits");
												if(jArr2 != null){
														for(int j=0;j<jArr2.length();j++){
																JSONObject jObj2 = jArr2.getJSONObject(j);
																if(!jObj2.isNull("id")){
																		full_addr = street;
																		Address one = new Address();
																		one.setMaAddressId(new Integer(street_id));
																		one.setMaSubunitId(new Integer(jObj2.getString("id")));
																		// one.setMaLocationId(new Integer(location_id));
																		String type = jObj2.getString("type");;
																		String ident = jObj2.getString("identifier");
																		full_addr += " "+type;
																		full_addr += " "+ident;
																		one.setStreetAddress(full_addr);
																		addresses.add(one);
																}
														}
												}
										}
										else{ // no subunit
												Address one = new Address();
												if(latVal != null){
														one.setLatitude(new Double(latVal));
														address.setLatitude(new Double(latVal));
												}
												if(lngVal != null){
														one.setLongitude(new Double(lngVal));
														address.setLongitude(new Double(lngVal));
												}												
												one.setStreetAddress(full_addr);
												one.setMaAddressId(new Integer(street_id));
												address.setMaAddressId(new Integer(street_id));
												// one.setMaLocationId(new Integer(location_id));
												addresses.add(one);
										}
								}
						}
				}
				catch(Exception ex){
						back = ex+" "+urlStr;
						logger.error(back);
				}
				finally{
						// 
						httpclient.getConnectionManager().shutdown();
				}
				return back;
    }		
		
}

		
