package in.bloomington.rental.util;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class GeneralHelper
{

    private static final Logger logger = LogManager.getLogger(GeneralHelper.class);
    static String url        = "",
                  image_url  = "",
                  group_name = "";

    @Autowired
    private Environment         env;

    String  file_path            = "",
            inspection_file_path = "",
            legal_type_service   = "",
            ldap_host            = "";
    boolean isSet                = false;

    public GeneralHelper()
    {
        populatePaths();
    }

    public void populatePaths()
    {
        if (env != null) {
            String str = env.getProperty("file_path");
            if (str != null) {
                file_path = str;
            }
            str = env.getProperty("image_url");
            if (str != null) {
                image_url = str;
            }
            str = env.getProperty("inspection_file_path");
            if (str != null) {
                inspection_file_path = str;
            }
            str = env.getProperty("group_name");
            if (str != null) {
                group_name = str;
            }
            str = env.getProperty("legal_type_service");
            if (str != null) {
                legal_type_service = str;
            }
            str = env.getProperty("ldap_host");
            if (str != null) {
                ldap_host = str;
            }
        }
    }

    /*
     * public String populateRates(){ String str = null, back="";
     * System.err.println(" reading env "); if(env != null){ str =
     * env.getProperty("file_path"); if(str != null){ file_path = str; }
     * System.err.println(" file_path "+file_path); str =
     * env.getProperty("ldap_host"); if(str != null){ ldap_host = str; } } else{
     * back = "Could not set rates "; } return back; }
     */
    public String getFilePath()
    {
        return file_path;
    }

    public String getGroupName()
    {
        return group_name;
    }

    public String getInspectionFilePath()
    {
        return inspection_file_path;
    }

    public String getImageUrl()
    {
        return image_url;
    }

    public String getLegalTypeService()
    {
        return legal_type_service;
    }

    public String getLdapHost()
    {
        return ldap_host;
    }

    public boolean isSet()
    {
        return isSet();
    }

    public void doReset()
    {
        populatePaths();
    }

    public boolean findUser(String username, String password)
    {
        String         returnedAtts[] = { "sn", "givenName", "mail" };
        String         filter         = "(&(objectCategory=person)(objectClass=user))";
        SearchControls ctls           = new SearchControls();
        ctls.setReturningAttributes(returnedAtts);

        // Specify the search scope
        ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, ldap_host);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, username + "@bloomington.in.gov");
        env.put(Context.SECURITY_CREDENTIALS, password);

        LdapContext ctx   = null;
        boolean     found = false;
        try {
            ctx = new InitialLdapContext(env, null);
            NamingEnumeration answer = ctx.search("", filter, ctls);
            if (answer.hasMoreElements()) {
                //
                // one is good enough
                //
                SearchResult sr    = (SearchResult) answer.next();
                Attributes   attrs = sr.getAttributes();
                if (attrs != null) {
                    NamingEnumeration ne = attrs.getAll();
                    while (ne.hasMore()) {
                        Attribute attr = (Attribute) ne.next();
                        System.err.println(attr.getID() + " " + attr.get());
                        found = true;
                    }
                    ne.close();
                }
            }
        }
        catch (NamingException ex) {
            System.err.println(ex);
        }
        return found;
    }
}
