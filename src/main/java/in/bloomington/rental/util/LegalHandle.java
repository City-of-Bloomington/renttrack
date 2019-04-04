package in.bloomington.rental.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import in.bloomington.rental.model.LegalType;

@Component
public class LegalHandle
{

    private static final Logger logger     = LogManager.getLogger(LegalHandle.class);
    static String               url        = "";

    @Autowired
    private Environment         env;

    List<LegalType>             legalTypes = null;

    public LegalHandle()
    {

    }

    public List<LegalType> getLegalTypes()
    {
        return legalTypes;
    }

    public boolean foundTypes()
    {
        return legalTypes != null && legalTypes.size() > 0;
    }

    /*
     * given certain address, find similar addresses in master_address app
     */
    public String findLegalTypes()
    {
        //
        String back = "";

        if (url.equals("")) {
            if (env != null) {
                url = env.getProperty("legal_url");
            }
        }
        String                  urlStr          = url + "TypeService?format=json&deptId=3";

        // HttpClient httpclient = HttpClientBuilder.create().build();
        HttpClient              httpclient      = new DefaultHttpClient();
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        try {
            HttpGet httpget = new HttpGet(urlStr);
            logger.debug(url);
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.err.println(" response " + responseBody);
            logger.debug("----------------------------------------");
            logger.debug(responseBody);
            logger.debug("----------------------------------------");
            JSONArray jArray = new JSONArray(responseBody);
            legalTypes = new ArrayList<>();
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jObj = jArray.getJSONObject(i);
                String     id   = "", name = "";
                if (jObj.has("id")) {
                    id = jObj.getString("id");
                    if (!jObj.isNull("description")) {
                        name = jObj.getString("description");
                    }
                    LegalType one = new LegalType(id, name);
                    legalTypes.add(one);
                }
            }
        }
        catch (Exception ex) {
            back = ex + " " + urlStr;
            logger.error(back);
        }
        finally {
            //
            httpclient.getConnectionManager().shutdown();
        }
        return back;
    }

    public String doLegalPost(JSONObject legalOjb)
    {
        String     back       = "";
        HttpClient httpClient = new DefaultHttpClient();
        // this require ssl
        // HttpClient httpClient = HttpClientBuilder.create().build();
        if (url.equals("")) {
            if (env != null) {
                url = env.getProperty("legal_url");
            }
        }
        String postUrl = url + "LegalAndCase.do";
        System.err.println(" post url " + postUrl);
        try {
            HttpPost     request = new HttpPost(postUrl);
            /*
             * List<NameValuePair> valuePairs = new ArrayList<NameValuePair>(1);
             * valuePairs.add(new BasicNameValuePair("jsonLegal",legalOjb.toString()));
             */
            StringEntity params  = new StringEntity("jsonLegal=" + legalOjb.toString());
            request.addHeader("content-type", "application/x-www-form-urlencoded");
            request.setEntity(params);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            // request.setEntity(new UrlEncodedFormEntity(valuePairs));
            String                  responseBody    = httpClient.execute(request, responseHandler);
            // HttpResponse response = httpClient.execute(request);
            System.err.println(responseBody);
            return responseBody;
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
        finally {
            //
            httpClient.getConnectionManager().shutdown();
        }
        return back;
    }

}
