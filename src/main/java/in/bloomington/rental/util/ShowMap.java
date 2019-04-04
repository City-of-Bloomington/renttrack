package in.bloomington.rental.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import in.bloomington.rental.model.Address;


public class ShowMap extends HttpServlet{

    String url="", url5="";
		String key="";
    boolean debug = false;
		final static long serialVersionUID = 890L;
		static Logger logger = LogManager.getLogger(ShowMap.class);
    /**
     * Generates the bill.
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest req, 
											HttpServletResponse res) 
				throws ServletException, IOException{
				boolean connectDbOk = false;
				res.setContentType("text/html");
				PrintWriter out = res.getWriter();
				String lat="", lng="", id="", rid="", address="";
				String name, value;
				String action="", message="";
				boolean success = true;
				Enumeration<String> values = req.getParameterNames();
				String [] vals;

				while (values.hasMoreElements()){
						name = values.nextElement().trim();
						vals = req.getParameterValues(name);
						value = vals[vals.length-1].trim();	
						if (name.equals("id")){ // address id
								id = value;
						}
						else if (name.equals("rid")){ // rental id
								rid = value;
						}
						else if (name.equals("lat")){
								lat = value;
						}
						else if (name.equals("lng")){
								lng = value;
						}
				}
				if(url.equals("")){
						url  = getServletContext().getInitParameter("url");
						url5  = getServletContext().getInitParameter("url5");
						String str = getServletContext().getInitParameter("debug");
						if(str.equals("true")) debug = true;
				}
				try{
						if(!id.equals("")){
								Address ad = new Address(); // id
								String str = ""; // ad.doSelect();
								if(str.equals("")){
										address = ad.getStreetAddress();
								}
								else{
										message = " Rental address error: "+str;
										logger.error(message);
								}
						}
						else{
								success = false;
						}
				}
				catch(Exception ex){
						success = false;
						logger.error(ex);
				}
				out.println("<html>");
				out.println("<meta name=\"viewport\" content=\"initial-scale=1.0, user-scalable=no\" />");
				out.println("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"/>");
				out.println("<title>Address Map</title>");
				//
				// we need apps key
				//
				out.println("<script type=\"text/javascript\" src=\"http://maps.google.com/maps/api/js?sensor=false\"></script>");
				out.println("<script type=\"text/javascript\"> ");
				out.println("  //<![CDATA[                     ");
				out.println("                                  ");
				out.println(" var myLatlng = new google.maps.LatLng("+lat+","+ lng+");");
				out.println("function initialize() { ");
				out.println(" var myOptions = { ");
				out.println("     zoom: 17, ");
				out.println("     center: myLatlng, ");
				out.println("     mapTypeId: google.maps.MapTypeId.ROADMAP ");
				out.println(" } ");
				out.println(" var map = new google.maps.Map(document.getElementById('map'), myOptions); ");
				out.println(" var marker = new google.maps.Marker({ ");
				out.println("          position: myLatlng, ");
				out.println("          map: map, ");
				out.println("          title: '"+address+"', ");
				out.println("          visible: true, ");
				out.println("          clickable: true  ");
				out.println("  });    ");
				out.println(" } ");
				out.println("                                                ");
				out.println("    //]]>                                       ");
				out.println("    </script>                                   ");
				out.println("  </head>                                       ");
				out.println("  <body onload='initialize()' onunload='GUnload()'>   ");
				//
				out.println("<center>");
				out.println(" <h2>Address Map</h2>         ");
				out.println("<table border='5' cellspacing='0' cellpadding='0'>"+
										"<tr><td>");
				out.println("<div id='map' style='width: 400px; height: 300px'></div>");
				out.println("<div id='pano' style='width: 400px;'></div>");
				out.println("</td></tr></table><br />");
				out.println(address);
				out.println("<br /><br />");
				// out.println("Click on the street to show \"Street View\"");
				out.println("<br /><br />");
				out.println("<a href='javascript:window.close();'>Close This Window"+
										"</a>");
				//
				out.println("</body>");
				out.println("</html>");
		
    }
    //
    /**
     * Generates the query form for bills.
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest req, 
											 HttpServletResponse res) 
				throws ServletException, IOException {
				doGet(req, res);
    }

}























































