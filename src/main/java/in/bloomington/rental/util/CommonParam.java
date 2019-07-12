package in.bloomington.rental.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class CommonParam
{
		public final static String[]     foundationTypes = { "Basement", "Slab", "Cellar", "Crawl Space", "Other" };
    public final static String[]     heatSources     = { "Electric", "Gas", "Electric/Gas", "Other" };
		public final static int[] nhoods = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		public final static String[] roomTypes = {"Bedroom","Kitchen","Living Room","Dining Room"};
		public final static String[] roles = {"View","Edit","Inspect","Admin"};
    public final static String[] component1Level   = { "Exterior Components", "Garage", "Unit", "Hallway", "Mechanic Closet", "Other" };
    public final static String[] component2Level_1 = { "Exterior Components", "Garage", "Unit", "Main Level", "Living Room", "Hallway", "Mechanic Closet", "Other" };
    public final static String[] component2Level_2 = { "2nd Level", "Living Room", "Hallway", "Mechanic Closet" };
    public final static String[] component3Level_1 = { "Exterior Components", "Garage", "Unit", "Main Level", "Hallway", "Mechanic Closet", "Other" };
    public final static String[] component3Level_2 = { "Basement", "Mechanic Closet" };
    public final static String[] component3Level_3 = { "2nd Leval", "Mechanic Closet", "Hallway" };
		public final static String[] phoneTypes = {"Home","Work","Cell"};
		public final static String[] payMethods = {"Cash","Check","Money Order","Credit Card"};		
		public final static String[] inspectionParams = {"start_date",
																										 "end_date",
																										 "violations",
																										 "building_type_id",
																										 "inspection_type_id"};
		
}
