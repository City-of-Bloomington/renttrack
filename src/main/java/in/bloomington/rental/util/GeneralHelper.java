package in.bloomington.rental.util;
import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.naming.*;
import javax.naming.directory.*;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.json.*;
import in.bloomington.rental.model.Bill;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Component
public class GeneralHelper{

		private static final Logger logger = LogManager.getLogger(GeneralHelper.class);
		static String url = "",image_url="", group_name="";
		@Autowired
		private Environment env;

		Bill bill = null;
		String file_path="", inspection_file_path="";
		String legal_type_service = "";
		String ldap_host = ""; 
		boolean isSet = false;
		public GeneralHelper(){
				populatePaths();
		}
		public void setBill(Bill val){
				if(val != null)
						bill = val;
		}
		public void populatePaths(){
				if(env != null){				
						String str = env.getProperty("file_path");
						if(str != null){
								file_path = str;
						}
						str = env.getProperty("image_url");
						if(str != null){
								image_url = str;
						}						
						str = env.getProperty("inspection_file_path");
						if(str != null){
								inspection_file_path = str;
						}
						str = env.getProperty("group_name");
						if(str != null){
								group_name = str;
						}
						str = env.getProperty("legal_type_service");
						if(str != null){
								legal_type_service = str;
						}
						str = env.getProperty("ldap_host");
						if(str != null){
								ldap_host = str;						
						}						
				}
		}
		public String populateRates(){
				String str = null, back="";
				System.err.println(" reading env ");
				if(env != null && bill != null){
						str = env.getProperty("building_rate");						
						if(str != null){
								bill.setBuildingRate(new BigDecimal(str));
						}
						str = env.getProperty("unit_rate");						
						if(str != null){
								bill.setUnitRate(new BigDecimal(str));
						}
						str = env.getProperty("bath_rate");						
						if(str != null){
								bill.setBathRate(new BigDecimal(str));
						}
						str = env.getProperty("reinsp_rate");						
						if(str != null){
								bill.setReinspRate(new BigDecimal(str));
						}
						str = env.getProperty("noshow_rate");						
						if(str != null){
								bill.setNoshowRate(new BigDecimal(str));
						}
						str = env.getProperty("appeal_fee");						
						if(str != null){
								bill.setAppealFee(new BigDecimal(str));
						}
						str = env.getProperty("summary_rate");						
						if(str != null){
								bill.setSummaryRate(new BigDecimal(str));
						}
						str = env.getProperty("idl_rate");						
						if(str != null){
								bill.setIdlRate(new BigDecimal(str));
						}
				}
				if(env != null){
						str = env.getProperty("file_path");
						if(str != null){
								file_path = str;
						}
						System.err.println(" file_path "+file_path);
						str = env.getProperty("ldap_host");
						if(str != null){
								ldap_host = str;						
						}
				}
				else{
						back = "Could not set rates ";
				}
				return back;
		}
		public String getFilePath(){
				return file_path;
		}
		public String getGroupName(){
				return group_name;
		}		
		public String getInspectionFilePath(){
				return inspection_file_path;
		}
		public String getImageUrl(){
				return image_url;
		}
		public String getLegalTypeService(){
				return legal_type_service;
		}
		public String getLdapHost(){
				return ldap_host;
		}
		public boolean isSet(){
				return isSet();
		}
		public void doReset(){
				populatePaths();
		}
		public boolean findUser(String username, String password){
				String returnedAtts[] ={ "sn", "givenName", "mail" };
				String filter ="(&(objectCategory=person)(objectClass=user))";
				SearchControls ctls = new SearchControls();
				ctls.setReturningAttributes(returnedAtts);
				
				//Specify the search scope
				ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
				
				Hashtable env = new Hashtable();
				env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
				env.put(Context.PROVIDER_URL, ldap_host);
				env.put(Context.SECURITY_AUTHENTICATION, "simple");
				env.put(Context.SECURITY_PRINCIPAL, username + "@bloomington.in.gov");
				env.put(Context.SECURITY_CREDENTIALS, password);
				
				LdapContext ctx = null;
				boolean found = false;
				try{
						ctx = new InitialLdapContext(env, null);
						NamingEnumeration answer = ctx.search("", filter, ctls);
						if(answer.hasMoreElements()){
								//
								// one is good enough
								//
								SearchResult sr = (SearchResult) answer.next();
								Attributes attrs = sr.getAttributes();
								if (attrs != null){
										NamingEnumeration ne = attrs.getAll();
										while (ne.hasMore()){
												Attribute attr = (Attribute) ne.next();
												System.err.println(attr.getID()+" "+ attr.get());
												found = true;
										}
										ne.close();
								}
						}
				}
				catch (NamingException ex){
						System.err.println(ex);
				}						
				return found;

		}
		
}

		
