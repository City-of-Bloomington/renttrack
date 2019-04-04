package in.bloomington.rental.util;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.headerfooter.RtfHeaderFooter;
import com.lowagie.text.rtf.table.RtfBorder;
import com.lowagie.text.rtf.table.RtfBorderGroup;
import com.lowagie.text.rtf.table.RtfCell;

import in.bloomington.rental.model.Address;
import in.bloomington.rental.model.Inspection;
import in.bloomington.rental.model.InspectionCan;
import in.bloomington.rental.model.InspectionType;
import in.bloomington.rental.model.Owner;
import in.bloomington.rental.model.Rental;
import in.bloomington.rental.model.RentalOwner;
import in.bloomington.rental.model.RentalStructure;
import in.bloomington.rental.model.RentalUnit;
import in.bloomington.rental.model.TemplateComponent;
import in.bloomington.rental.model.Variance;

public class RtfWriter
{

    boolean               debug            = false;
    final static long     serialVersionUID = 505L;
    static Logger         logger           = LogManager.getLogger(RtfWriter.class);
    String                image_url        = "";
    String                fileAndPath      = "";
    Rental                rent             = null;
    Inspection            inspect          = null;
    List<RentalOwner>     rentalOwners     = null;
    Owner                 agent            = null;
    Owner                 owner            = null;
    List<Variance>        variances        = null;
    List<Address>         addresses        = null;
    List<RentalStructure> structures       = null;
    String                varianceText     = "";
    int                   unitCount        = 0,
                          structureCount   = 0,
                          occupLoadCount   = 0;
    String                occupLoad        = "",
                          storyCount       = "",
                          inspectionType   = "",
                          addressStr       = "",
                          heatSource       = "",
                          foundation       = "",
                          storyCnt         = "";
    RentalStructure       structure        = null;
    Font font, fonts, fontb, fontbb, fontlb;

    public RtfWriter(Rental val, Inspection val2, String val3, String val4)
    {
        setRent(val);
        setInspection(val2);
        setImageUrl(val3);
        setFileAndPath(val4);
        prepareFonts();
    }

    void prepareFonts()
    {
        try {
            font   = FontFactory.getFont("Times-New-Roman", 12);
            fonts  = FontFactory.getFont("Times-New-Roman", 8, Font.BOLD);
            fontb  = FontFactory.getFont("Times-New-Roman", 12, Font.BOLD);
            fontbb = FontFactory.getFont("Times-New-Roman", 12, Font.UNDERLINE);
            fontlb = FontFactory.getFont("Times-New-Roman", 14, Font.BOLD);

        }
        catch (Exception ex) {
            logger.error(ex);
        }
    }

    void setRent(Rental val)
    {
        if (val != null) {
            rent         = val;
            rentalOwners = rent.getRentalOwners();
            if (rentalOwners != null && rentalOwners.size() > 0) {
                owner = rentalOwners.get(0).getOwner(); // the first
            }
            agent     = rent.getAgent();
            variances = rent.getVariances();
            if (variances != null && variances.size() > 0) {
                for (Variance var : variances) {
                    varianceText += var.getDate() + " " + var.getVariance() + "\n";
                }
            }
            structures = rent.getRentalStructures();
            if (structures != null && structures.size() > 0) {
                structureCount = structures.size();
                for (RentalStructure one : structures) {
                    if (structure == null) structure = one;
                    if (one.hasEgressInfo()) {
                        structure = one;
                    }
                    if (occupLoad.equals("")) occupLoad += ",";
                    occupLoad += one.getIdentifier() + ":";
                    String heatSrc = one.getHeatSource();
                    if (heatSrc != null) {
                        if (heatSource.indexOf(heatSrc) == -1) {
                            if (!heatSource.equals("")) heatSource += ", ";
                            heatSource += heatSrc;
                        }
                    }
                    String str = one.getFoundation();
                    if (str != null) {
                        if (foundation.indexOf(str) == -1) {
                            if (!foundation.equals("")) foundation += ", ";
                            foundation += str;
                        }
                    }
                    if (one.getStoryCnt() > 0) {
                        str = "" + one.getStoryCnt();
                        if (!storyCnt.equals("")) storyCnt += ", ";
                        storyCnt += str;
                    }
                    List<RentalUnit> ulist = one.getRentalUnits();
                    int              jj    = 1;
                    for (RentalUnit uu : ulist) {
                        if (uu != null) {
                            if (jj > 1) occupLoad += "-";
                            occupLoad += uu.getIdentifier() + "/";
                            occupLoad += uu.getBedroomCount() + "/";
                            unitCount++;
                            occupLoad      += uu.getOccupLoad();
                            occupLoadCount += uu.getOccupLoad();
                            if (uu.hasAddress()) {
                                if (addresses == null) addresses = new ArrayList<>();
                                addresses.add(uu.getAddress());
                            }
                            jj++;
                        }
                    }
                }
            }
            if (addresses == null) {
                addresses = rent.getAddresses();
            }
            if (addresses != null && addresses.size() > 0) {
                for (Address one : addresses) {
                    if (one != null) {
                        if (!addressStr.equals("")) addressStr += ", ";
                        addressStr = one.getStreetAddress();
                    }
                }
            }
        }
    }

    void setInspection(Inspection val)
    {
        if (val != null) {
            inspect = val;
            if (inspect != null) {
                InspectionType type = inspect.getInspectionType();
                if (type != null) {
                    inspectionType = type.getName();
                    // some already has inspection word
                    if (inspectionType.indexOf("Inspection") == -1) {
                        // others we add
                        inspectionType += " Inspection";
                    }
                }
            }
        }
    }

    void setFileAndPath(String val)
    {
        if (val != null) fileAndPath = val;
    }

    void setImageUrl(String val)
    {
        if (val != null) image_url = val;
    }

    //
    // getters
    //
    Rental getRent()
    {
        return rent;
    }

    Inspection getInspection()
    {
        return inspect;
    }

    public String toString()
    {
        if (inspect != null)
            return "" + inspect.getId();
        else
            return "";
    }

    /**
     * writes the cover letter (if needed) and the first inspection page
     */
    public String writeAll()
    {
        String    msg         = "";
        Document  document    = new Document();
        // RtfPhont font, fontb, fontbb;
        int       text_lines  = 32;            // cycle 32, complain 29, tv 27
        int       total_lines = 34, spaces = 0;
        Chunk     chunk;
        Paragraph pp;
        Phrase    phrase;
        boolean   needCover   = true;
        try {
            // RtfFont bigBoldGreenArial=new RtfFont("Arial",12,Font.BOLD,Color.BLACK);
            // Create writer to listen document object
            // and directs RTF Stream to the file
            RtfWriter2 writer = RtfWriter2.getInstance(document, new FileOutputStream(fileAndPath));
            //
            document.open();
            //
            if (inspectionType.startsWith("Complaint")) {
                text_lines  = 29;
                msg         = writeHeader(document);
                msg        += writeComplainText(document);
            }
            else if (inspectionType.startsWith("Cycle")) {
                text_lines  = 34;
                msg         = writeHeader(document);
                msg        += writeCycleText(document);
            }
            else if (inspectionType.startsWith("Fire")) {
                //
                // we do not have it for now
                // when available we will update this
                //
                needCover = false;
            }
            else if (inspectionType.startsWith("Permit")) {
                text_lines  = 32;
                msg         = writeHeader(document);
                msg        += writeCycleText(document);
            }
            else if (inspectionType.startsWith("Tenant")) {
                text_lines  = 27;
                msg         = writeHeader(document);
                msg        += writeForTenant(document);
            }
            else if (inspectionType.startsWith("Home")) {
                text_lines  = 32;
                msg         = writeHeader(document);
                msg        += writeCycleText(document);
            }
            else if (inspectionType.startsWith("Shelter")) {
                text_lines  = 35;
                msg         = writeHeader(document);
                msg        += writeShelterText(document);
            }
            else {
                needCover = false;
            }
            if (needCover) {
                chunk = new Chunk("Sincerely,\n\n\n", font);
                document.add(chunk);
                chunk = new Chunk("Housing & Neighborhood Development\n", font);
                document.add(chunk);
                chunk = new Chunk("Encl:Inspection Report,\n", font);
                document.add(chunk);
                if (agent != null) {
                    chunk  = new Chunk("Xc:" + agent.getName(), font);
                    phrase = new Phrase();
                    phrase.add(chunk);
                    chunk = new Chunk(": " + agent.getAddress(), font);
                    phrase.add(chunk);
                    chunk = new Chunk(", " + agent.getCityStateZip() + "\n", font);
                    phrase.add(chunk);
                    document.add(phrase);
                    text_lines += 1;
                }
                if (rentalOwners != null && rentalOwners.size() > 0) {
                    spaces = total_lines - text_lines;
                    if (rentalOwners.size() == 1) {
                        spaces -= 3;
                    }
                    else if (rentalOwners.size() == 2) {
                        spaces -= 6;
                    }
                }
                if (spaces > 0) {
                    String str  = "\n";
                    String str2 = "";
                    for (int i = 0; i < spaces; i++) {
                        str2 += str;
                    }
                    chunk = new Chunk(str2, font);
                    document.add(chunk);
                }
                msg += writeFooter(document);
                document.newPage();
            }
            //
            // second page, the inspection report
            //
            writeHeader(document);
            //
            chunk  = new Chunk(inspectionType + " Report", fontbb);
            phrase = new Phrase(chunk);
            pp     = new Paragraph();
            pp.setAlignment(Element.ALIGN_CENTER);
            pp.add(phrase);
            document.add(pp);
            chunk  = new Chunk("" + rent.getId(), font);
            phrase = new Phrase(chunk);
            pp     = new Paragraph();
            pp.setAlignment(Element.ALIGN_RIGHT);
            pp.add(phrase);
            document.add(pp);
            //
            text_lines = 14;
            chunk      = new Chunk("Owner(s)\n", fontbb);
            phrase     = new Phrase();
            phrase.add(chunk);
            document.add(phrase);
            if (rentalOwners != null && rentalOwners.size() > 0) {
                for (RentalOwner one : rentalOwners) {
                    text_lines += 4;
                    phrase      = new Phrase();
                    chunk       = new Chunk(one.getOwner().getName() + "\n", font);
                    phrase.add(chunk);
                    chunk = new Chunk(one.getOwner().getAddress() + "\n", font);
                    phrase.add(chunk);
                    chunk = new Chunk(one.getOwner().getCityStateZip() + "\n\n", font);
                    phrase.add(chunk);
                    document.add(phrase);
                }
            }
            if (agent != null) {
                text_lines += 4;
                chunk       = new Chunk("Agent\n", fontbb);
                phrase      = new Phrase();
                phrase.add(chunk);
                document.add(phrase);
                phrase = new Phrase();
                chunk  = new Chunk(agent.getName() + "\n", font);
                phrase.add(chunk);
                chunk = new Chunk(agent.getAddress() + "\n", font);
                phrase.add(chunk);
                chunk = new Chunk(agent.getCityStateZip() + "\n\n", font);
                phrase.add(chunk);
                document.add(phrase);
            }
            chunk = new Chunk("Prop. Location: " + addressStr + "\n", font);
            document.add(chunk);
            chunk = new Chunk("Number of Units/Structures: " + unitCount + "/" + structureCount + "\n", font);
            document.add(chunk);
            phrase = new Phrase();
            chunk  = new Chunk("Building:Unit/Bedrooms/Max # of Occupants: ", font);
            phrase.add(chunk);
            chunk = new Chunk(occupLoadCount + "\n\n", font);
            phrase.add(chunk);
            document.add(phrase);
            //
            Table table = new Table(2);
            table.setBorder(0);
            table.getDefaultCell().setBorder(0);
            table.setWidth(100);
            RtfCell cell = new RtfCell("Date Inspected: " + inspect.getInspectionDateFr());
            cell.setBorder(0);
            table.addCell(cell);
            String str = inspect.getInspector() == null ? "" : inspect.getInspector().getFullName();
            cell = new RtfCell("Inspector: " + str);
            cell.setBorder(0);
            table.addCell(cell);
            cell = new RtfCell("Primary Heat Source: " + heatSource);
            cell.setBorder(0);
            table.addCell(cell);
            //
            cell = new RtfCell("Foundation Type: " + foundation);
            cell.setBorder(0);
            table.addCell(cell);
            //
            cell = new RtfCell("Property Zoning: " + rent.getZoning());
            cell.setBorder(0);
            table.addCell(cell);
            //
            cell = new RtfCell("Attic Access: " + (inspect.hasAtticAccess() ? "Yes" : "No"));
            cell.setBorder(0);
            table.addCell(cell);
            //
            cell = new RtfCell("Number of Stories: " + storyCnt);
            cell.setBorder(0);
            table.addCell(cell);
            //
            cell = new RtfCell("Accessory Structure: " + inspect.getAccessory());
            cell.setBorder(0);
            table.addCell(cell);
            document.add(table);

            if (!varianceText.equals("")) {
                chunk = new Chunk("Variance: " + varianceText + "\n", fontbb);
                document.add(chunk);
                pp = new Paragraph(varianceText, font);
                document.add(pp);
                int len = varianceText.length();
                text_lines += len % 75;
            }
            spaces = total_lines - text_lines;
            if (spaces > 0) {
                str = "\n";
                String str2 = "";
                for (int i = 0; i < spaces; i++) {
                    str2 += str;
                }
                chunk = new Chunk(str2, font);
                document.add(chunk);
            }
            if (inspectionType.startsWith("Cycle")) {
                if (structure != null && structure.hasEgressInfo()) {
                    String yearBuilt = "_____", egressDecreeYears = "____ - ___";
                    str = structure.getYearBuilt() == null ? null : "" + structure.getYearBuilt();
                    if (str != null) yearBuilt = "" + str;
                    str = structure.getEgressDecreeYears();
                    if (str != null) egressDecreeYears = str;
                    str   = " Monroe County Assessor records indicate that this structure was built in " + yearBuilt
                        + ". These are the minimum requirements for emergency egress for structures built between "
                        + egressDecreeYears + ":\n";
                    chunk = new Chunk(str, font);
                    document.add(chunk);
                    str = (structure.getEgressHeight() == null) ? null : "" + structure.getEgressHeight();
                    if (str == null) str = "__";
                    chunk = new Chunk("Clear Height: " + str + " inch\n", font);
                    document.add(chunk);
                    str = structure.getEgressWidth() == null ? null : "" + structure.getEgressWidth();
                    if (str == null) str = "__";
                    chunk = new Chunk("Clear Width: " + str + " inch\n", font);
                    document.add(chunk);
                    str = structure.getEgressArea() == null ? null : "" + structure.getEgressArea();
                    if (str == null) str = "______";
                    chunk = new Chunk("Openable Area: " + str + " sq ft\n", font);
                    document.add(chunk);
                    str = structure.getEgressSillHeight() == null ? null : "" + structure.getEgressSillHeight();
                    if (str == null) str = "__";
                    chunk = new Chunk("Sill Height: " + str + " inch above finished floor\n\n", font);
                    document.add(chunk);
                }
            }
            if (inspect.hasInspectionCans()) {
                writeCans(document);
            }
            writeFooter(document);
        }
        catch (Exception ex) {
            System.out.println(ex);
            msg += ex;
        }
        finally {
            document.close();
        }
        return msg;
    }

    //
    String writeHeader(Document document)
    {
        String    msg = "";
        Paragraph pp;
        try {
            com.lowagie.text.Image image = com.lowagie.text.Image.getInstance(image_url);
            image.scaleAbsolute(50, 50);
            image.setAlignment(Element.ALIGN_MIDDLE);
            image.setAlignment(Element.ALIGN_CENTER);
            pp = new Paragraph(new Phrase(new Chunk(image, 0, 0)));
            pp.setAlignment(Element.ALIGN_CENTER);
            pp.add(new Chunk("\nCity Of Bloomington\nHousing and Neighborhood Development\n", fontlb));
            document.add(pp);
        }
        catch (Exception ex) {
            logger.error(ex);
            System.err.println(ex);
            msg += ex;
        }
        return msg;
    }

    String writeFooter(Document document)
    {
        String    msg = "";
        Chunk     chunk;
        Paragraph pp;
        try {
            Table table = new Table(3);
            table.setBorder(0);
            table.setPadding(0);
            table.setSpacing(0);
            table.getDefaultCell().setBorder(0);
            table.setWidth(100);
            chunk = new Chunk("401 N Morton St", fonts);
            pp    = new Paragraph(chunk);
            RtfCell cell = new RtfCell(pp);
            cell.setBorders(new RtfBorderGroup(Rectangle.TOP, RtfBorder.BORDER_SINGLE, 2, java.awt.Color.BLACK));
            cell.setLeading(0f);
            cell.setUseAscender(false);
            cell.setUseDescender(false);
            cell.setVerticalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            chunk = new Chunk("City Hall", fonts);
            pp    = new Paragraph(chunk);
            pp.setAlignment(Element.ALIGN_CENTER);
            cell = new RtfCell(pp);
            cell.setBorders(new RtfBorderGroup(Rectangle.TOP, RtfBorder.BORDER_SINGLE, 2, java.awt.Color.BLACK));
            cell.setLeading(0f);
            cell.setUseAscender(false);
            cell.setUseDescender(false);
            cell.setVerticalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            chunk = new Chunk("Rental Inspection (812) 349-3420", fonts);
            pp    = new Paragraph(chunk);
            cell  = new RtfCell(pp);
            cell.setBorders(new RtfBorderGroup(Rectangle.TOP, RtfBorder.BORDER_SINGLE, 2, java.awt.Color.BLACK));
            cell.setLeading(0f);
            cell.setUseAscender(false);
            cell.setUseDescender(false);
            cell.setVerticalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            // second row
            chunk = new Chunk("Bloomington, IN 47404", fonts);
            pp    = new Paragraph(chunk);
            cell  = new RtfCell(pp);
            cell.setBorder(0);
            cell.setLeading(0f);
            cell.setUseAscender(false);
            cell.setUseDescender(false);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            chunk = new Chunk(" ", fonts);
            pp    = new Paragraph(chunk);
            cell  = new RtfCell(pp);
            cell.setBorder(0);
            cell.setLeading(0f);
            cell.setUseAscender(false);
            cell.setUseDescender(false);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            chunk = new Chunk("Neighborhood Division (812) 349-3421", fonts);
            pp    = new Paragraph(chunk);
            cell  = new RtfCell(pp);
            cell.setBorder(0);
            cell.setLeading(0f);
            cell.setUseAscender(false);
            cell.setUseDescender(false);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            //
            // third row
            chunk = new Chunk("Fax (812) 349-3582", fonts);
            pp    = new Paragraph(chunk);
            cell  = new RtfCell(pp);
            cell.setBorder(0);
            cell.setLeading(0f);
            cell.setUseAscender(false);
            cell.setUseDescender(false);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            chunk = new Chunk("bloomington.in.gov", fonts);
            pp    = new Paragraph(chunk);
            pp.setAlignment(Element.ALIGN_CENTER);
            //
            cell = new RtfCell(pp);
            cell.setBorder(0);
            cell.setLeading(0f);
            cell.setUseAscender(false);
            cell.setUseDescender(false);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            chunk = new Chunk("Housing Division (812) 349-3401", fonts);
            pp    = new Paragraph(chunk);
            cell  = new RtfCell(pp);
            cell.setBorder(0);
            cell.setLeading(0f);
            cell.setUseAscender(false);
            cell.setUseDescender(false);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            document.add(table);
        }
        catch (Exception ex) {
            System.err.println(ex);
            logger.error(ex);
            msg += ex;
        }
        return msg;
    }

    String writeCans(Document document)
    {
        Chunk     chunk;
        Paragraph pp;
        String    msg = "";
        try {
            if (inspect.hasInspectionCans()) {
                List<InspectionCan> cans = inspect.getInspectionCans();
                for (InspectionCan one : cans) {
                    String title = "";
                    if (one.hasTemplateComponent()) {
                        TemplateComponent template = one.getTemplateComponent();
                        title = template.getComponent();// title
                    }
                    // String title = one.getTitle();
                    String title2 = one.getTitle();
                    if (!title.equals("")) {
                        if (title2 != null && !title2.equals("")) title += "\n" + title2;

                    }
                    else if (title2 != null && !title2.equals("")) {
                        title = title2;
                    }
                    if (!title.equals("")) {
                        chunk = new Chunk(title + "\n", fontbb);
                        document.add(chunk);
                    }
                    String par = one.getItem1(); // always paragraph
                    if (par != null) {
                        chunk = new Chunk(par, font);
                        pp    = new Paragraph(chunk);
                        document.add(pp);
                    }
                    List<String> arr = one.getItemsArray();
                    if (arr != null && arr.size() > 0) {
                        if (one.isParagraph()) {
                            for (String str : arr) {
                                chunk = new Chunk(str, font);
                                pp    = new Paragraph(chunk);
                                document.add(pp);
                            }
                        }
                        else { // list except the first item
                            int jj = 1;
                            for (String str : arr) {
                                chunk = new Chunk(jj + " - " + str + "\n", font);
                                document.add(chunk);
                                jj++;
                            }
                        }
                    }
                    // space after last paragraph or list
                    chunk = new Chunk(" ", font);
                    pp    = new Paragraph(chunk);
                    document.add(pp);
                }
            }
        }
        catch (Exception ex) {
            msg += ex;
            System.err.println(ex);
            logger.error(ex);
        }
        return msg;
    }

    //
    String writeComplainText(Document document)
    {

        Chunk     chunk;
        Paragraph pp;
        Phrase    phrase;
        String    msg = "";
        try {
            //
            // space for header
            //
            for (RentalOwner one2 : rentalOwners) {
                if (one2 != null) {
                    Owner one = one2.getOwner();
                    if (one != null) {
                        phrase = new Phrase();
                        chunk  = new Chunk(one.getName() + "\n", font);
                        phrase.add(chunk);
                        chunk = new Chunk(one.getAddress() + "\n", font);
                        phrase.add(chunk);
                        chunk = new Chunk(one.getCityStateZip() + "\n\n", font);
                        phrase.add(chunk);
                        document.add(phrase);
                    }
                }
            }
            chunk = new Chunk("RE:NOTICE OF " + inspectionType + "\n\n", fontbb);
            document.add(chunk);
            //
            chunk = new Chunk("Dear " + owner.getName() + "\n\n", font);
            document.add(chunk);
            phrase = new Phrase();
            chunk  = new Chunk(
                "On " + inspect.getInspectionDate() + " a complaint inspection was performed at " + addressStr + ". ",
                font);
            phrase.add(chunk);
            chunk = new Chunk(
                "During the inspection violations of the Residential Rental Unit and Lodging Establishment Inspection Program were found.\n",
                font);
            phrase.add(chunk);
            pp = new Paragraph();
            pp.add(phrase);
            document.add(pp);
            chunk = new Chunk(
                "Please correct the violations cited on the enclosed inspection report within fourteen days (14) and call this office no later than                          , to schedule the required re-inspection.  Our mailing address and telephone number are listed below.\n",
                font);
            pp    = new Paragraph();
            pp.add(chunk);
            document.add(pp);
            chunk = new Chunk(
                "This directive is issued in accordance with Sections BMC 16.03.040 (c) and 16.10.040 (a) of the Residential Rental Unit and Lodging Establishment Inspection Program of Bloomington.  You have the right to appeal to the Board of Housing Quality Appeals.  If you need more than fourteen (14) days to correct the violations, or if you want to appeal any violation, an appeal form can be found at www.bloomington.in.gov/hand.  If you do not have access to the internet, you may contact HAND at 812-349-3420 and a form will be provided.\n",
                font);
            pp    = new Paragraph();
            pp.add(chunk);
            document.add(pp);
            chunk = new Chunk(
                "Please remember, it is your responsibility to contact the Housing and Neighborhood Development Department to schedule the required re-inspection.\n",
                font);
            pp    = new Paragraph();
            pp.add(chunk);
            document.add(pp);
            chunk = new Chunk(
                "If you have any questions regarding the permit process, please call weekdays between 8:00 a.m. and 5:00 p.m., at (812) 349-3420.\n",
                font);
            pp    = new Paragraph();
            pp.add(chunk);
            document.add(pp);
        }
        catch (Exception ex) {
            logger.error(ex);
            System.err.println(ex);
        }
        return msg;
    }

    //
    String writeCycleText(Document document)
    {
        String    msg = "";
        Chunk     chunk;
        Paragraph pp;
        Phrase    phrase;
        try {
            //
            // space for header
            //
            chunk  = new Chunk("RENTAL INSPECTION INFORMATION\n", fontbb);
            phrase = new Phrase(chunk);
            pp     = new Paragraph();
            pp.setAlignment(Element.ALIGN_CENTER);
            pp.add(phrase);
            document.add(pp);
            for (RentalOwner one2 : rentalOwners) {
                Owner one = one2.getOwner();
                if (one != null) {
                    phrase = new Phrase();
                    chunk  = new Chunk(one.getName() + "\n", font);
                    phrase.add(chunk);
                    chunk = new Chunk(one.getAddress() + "\n", font);
                    phrase.add(chunk);
                    chunk = new Chunk(one.getCityStateZip() + "\n\n", font);
                    phrase.add(chunk);
                    document.add(phrase);
                }
            }
            phrase = new Phrase();
            chunk  = new Chunk("RE: " + addressStr + "\n\n", font);
            phrase.add(chunk);
            document.add(phrase);
            //
            phrase = new Phrase();
            chunk  = new Chunk(
                "Please find the enclosed Rental Inspection Report which contains pertinent information about the Cycle Inspection that was recently conducted at the above referenced property. The inspector has listed all noted violations and recommendations on the enclosed Rental Inspection Report. ",
                font);
            phrase.add(chunk);
            chunk = new Chunk(
                "You have sixty (60) days from the date of this letter to correct the violations listed on the report.\n",
                fontb);
            phrase.add(chunk);
            pp = new Paragraph();
            pp.add(phrase);
            document.add(pp);

            //
            chunk = new Chunk(
                "Once violations have been corrected, it is your responsibility to call the Housing and Neighborhood Development office within this 60 day window but no later than                       to schedule a re-inspection. You have the right to appeal any violation of Bloomington Municipal Code Title 16 noted on the rental inspection report to the Board of Housing Quality Appeals.\n",
                font);

            pp    = new Paragraph();
            pp.add(chunk);
            document.add(pp);
            chunk = new Chunk(
                "This report is issued in accordance with BMC 16.10.020 and 16.10.040 of the Residential Rental Unit and Lodging Establishment Inspection Program. Residential Rental Occupancy Permits will not be issued until all interior and exterior violations have been corrected, and all fees have been paid. Bloomington Municipal Code requires that all violations of all Titles of the BMC must be in compliance before a permit will be issued. Please be advised that non-compliance by the deadlines listed in this letter may limit the permit period to a maximum of three (3) years.\n",
                font);

            pp    = new Paragraph();
            pp.add(chunk);
            document.add(pp);
            //
            pp    = new Paragraph();
            chunk = new Chunk(
                "If the owner's or agent's contact information has changed since your last inspection, please submit a new registration form to the HAND Department. The registration must be signed by the owner of the property, not the agent. All rental forms and documents can be found at www.bloomington.in.gov/hand. If you do not have access to the internet, you may contact ",
                font);
            pp.add(chunk);
            chunk = new Chunk("HAND at 812-349-3420 and forms will be provided.\n", fontb);
            pp.add(chunk);
            document.add(pp);
            //
            chunk = new Chunk(
                "If you have any questions regarding the permit process, please call weekdays between 8:00 a.m. and 5:00 p.m., at (812) 349-3420.\n",
                font);
            pp    = new Paragraph();
            pp.add(chunk);
            document.add(pp);
        }
        catch (Exception ex) {
            logger.error(ex);
            System.err.println(ex);
        }
        return msg;
    }

    String writeForTenant(Document document)
    {
        String    msg = "";
        Chunk     chunk;
        Paragraph pp;
        Phrase    phrase;
        try {
            //
            // space for header
            //
            //
            for (RentalOwner one2 : rentalOwners) {
                Owner one = one2.getOwner();
                if (one != null) {
                    phrase = new Phrase();
                    chunk  = new Chunk(one.getName() + "\n", font);
                    phrase.add(chunk);
                    chunk = new Chunk(one.getAddress() + "\n", font);
                    phrase.add(chunk);
                    chunk = new Chunk(one.getCityStateZip() + "\n\n", font);
                    phrase.add(chunk);
                    document.add(phrase);
                }
            }
            chunk = new Chunk("RE:NOTICE OF " + inspectionType + "\n\n", fontbb);
            document.add(chunk);
            chunk = new Chunk("Dear Resident(s)\n\n", font);
            document.add(chunk);
            chunk = new Chunk("On " + inspect.getInspectionDate() + ", a complaint inspection was performed at "
                + addressStr
                + ".  During the inspection violations of the Bloomington Housing Code were found.  Enclosed is the inspection report which cites violations that are the responsibility of the resident(s) to correct.  Please correct the violations within ____________ days and contact this office no later than                                   	to schedule the required re-inspection.  Our mailing address and telephone number are listed below.\n",
                font);
            pp    = new Paragraph();
            pp.add(chunk);
            document.add(pp);
            chunk = new Chunk(
                "This directive is issued in accordance with BMC 16.10.020(a) and 16.10.040(a) of the Residential Rental Unit and Lodging Establishment Inspection Program.  You have the right to appeal to the Board of Housing Quality Appeals.  If you need more than ___________ days to correct the violations, or if you want to appeal any violation, an appeal form can be found at www.bloomington.in.gov/hand.  If you do not have access to the internet, you may contact HAND at 812-349-3420 and a form will be provided.\n",
                font);
            pp    = new Paragraph();
            pp.add(chunk);
            document.add(pp);
            chunk = new Chunk(
                "Please remember, it is your responsibility to contact the Housing and Neighborhood Development Office to schedule the required re-inspection.\n",
                font);
            pp    = new Paragraph();
            pp.add(chunk);
            document.add(pp);
            chunk = new Chunk(
                "If you have any questions regarding the permit process, please call weekdays between 8:00 a.m. and 5:00 p.m., at (812) 349-3420.\n",
                font);
            pp    = new Paragraph();
            pp.add(chunk);
            document.add(pp);
        }
        catch (Exception ex) {
            logger.error(ex);
            System.err.println(ex);
        }
        return msg;
    }

    //
    String writeShelterText(Document document)
    {
        String    msg = "";

        Chunk     chunk;
        Paragraph pp;
        Phrase    phrase;
        try {
            //
            // space for header
            //
            //
            chunk  = new Chunk("RENTAL INSPECTION INFORMATION\n", fontbb);
            phrase = new Phrase(chunk);
            pp     = new Paragraph();
            pp.setAlignment(Element.ALIGN_CENTER);
            pp.add(phrase);
            document.add(pp);
            for (RentalOwner one2 : rentalOwners) {
                Owner one = one2.getOwner();
                if (one != null) {
                    phrase = new Phrase();
                    chunk  = new Chunk(one.getName() + "\n", font);
                    phrase.add(chunk);
                    chunk = new Chunk(one.getAddress() + "\n", font);
                    phrase.add(chunk);
                    chunk = new Chunk(one.getCityStateZip() + "\n\n", font);
                    phrase.add(chunk);
                    document.add(phrase);
                }
            }
            phrase = new Phrase();
            chunk  = new Chunk("RE: " + addressStr + "\n\n", font);
            phrase.add(chunk);
            document.add(phrase);

            chunk = new Chunk(
                "Please find the enclosed Rental Inspection Report which contains pertinent information about the Cycle Inspection that was recently conducted at the above referenced property. The inspector has listed all noted violations and recommendations on the enclosed Rental Inspection Report. You have sixty (60) days from the date of this letter to correct the violations listed on the report.\n",
                font);
            pp    = new Paragraph();
            pp.add(chunk);
            document.add(pp);

            chunk = new Chunk(
                "Once violations have been corrected, it is your responsibility to call the Housing and Neighborhood Development office within this 60 day window but no later than                       to schedule a re-inspection. You have the right to appeal any violation of Bloomington Municipal Code Title 16 noted on the rental inspection report to the Board of Housing Quality Appeals",
                font);

            pp    = new Paragraph();
            pp.add(chunk);
            document.add(pp);

            chunk = new Chunk(
                "This report is issued in accordance with BMC 16.10.020 and 16.10.040 of the Residential Rental Unit and Lodging Establishment Inspection Program. Residential Rental Occupancy Permits will not be issued until all interior and exterior violations have been corrected, and all fees have been paid. Bloomington Municipal Code requires that all violations of all Titles of the BMC must be in compliance before a permit will be issued. Please be advised that non-compliance by the deadlines listed in this letter may limit the permit period to a maximum of three (3) years.\n",
                font);
            pp    = new Paragraph();
            pp.add(chunk);
            document.add(pp);

            pp    = new Paragraph();

            chunk = new Chunk(
                "If the owner's or agent's contact information has changed since your last inspection, please submit a new registration form to the HAND Department. The registration must be signed by the owner of the property, not the agent. All rental forms and documents can be found at www.bloomington.in.gov/hand. If you do not have access to the internet, you may contact ",
                font);
            pp.add(chunk);
            chunk = new Chunk("HAND at 812-349-3420 and forms will be provided.\n", fontb);
            pp.add(chunk);
            document.add(pp);

            chunk = new Chunk(
                "If you have any questions regarding the permit process, please call weekdays between 8:00 a.m. and 5:00 p.m., at (812) 349-3420.\n",
                font);

            pp    = new Paragraph();
            pp.add(chunk);
            document.add(pp);
        }
        catch (Exception ex) {
            logger.error(ex);
            System.err.println(ex);
        }
        return msg;
    }

    /**
     * writes header and footer not used because it was propagating to all pages and
     * HAND did not like that
     */
    String writeHeader2(Document document)
    {
        String    msg = "";
        Phrase    phrase;
        Paragraph pp;
        try {
            com.lowagie.text.Image image = com.lowagie.text.Image.getInstance(image_url);
            // image_url = http://10.50.103.12/rentpro/js/images/hand_logo.png

            image.scaleAbsolute(50, 50);
            image.setAlignment(Element.ALIGN_MIDDLE);
            image.setAlignment(Element.ALIGN_CENTER);
            pp = new Paragraph(new Phrase(new Chunk(image, 0, 0)));
            pp.setAlignment(Element.ALIGN_CENTER);
            pp.add(new Chunk("\nCity Of Bloomington\nHousing and Neighborhood Development\n", fontlb));
            //
            document.setHeader(new RtfHeaderFooter(pp));
            Table table = new Table(3);
            table.setBorder(0);
            table.setPadding(0);
            table.setSpacing(0);
            table.getDefaultCell().setBorder(0);
            table.setWidth(100);
            RtfCell cell = new RtfCell(new Phrase(new Chunk("401 N Morton St", fonts)));
            cell.setBorders(new RtfBorderGroup(Rectangle.TOP, RtfBorder.BORDER_SINGLE, 2, java.awt.Color.BLACK));
            cell.setLeading(0f);
            table.addCell(cell);
            phrase = new Phrase(new Phrase(new Chunk("City Hall", fonts)));
            pp     = new Paragraph(phrase);
            pp.setAlignment(Element.ALIGN_CENTER);
            cell = new RtfCell(pp);
            cell.setBorders(new RtfBorderGroup(Rectangle.TOP, RtfBorder.BORDER_SINGLE, 2, java.awt.Color.BLACK));
            cell.setLeading(0f);

            table.addCell(cell);
            cell = new RtfCell(new Phrase(new Chunk("Rental Inspection (812) 349-3420", fonts)));

            cell.setBorders(new RtfBorderGroup(Rectangle.TOP, RtfBorder.BORDER_SINGLE, 2, java.awt.Color.BLACK));
            cell.setLeading(0f);
            table.addCell(cell);
            // second row
            cell = new RtfCell(new Phrase(new Chunk("Bloomington, IN 47404", fonts)));
            cell.setBorder(0);
            cell.setLeading(0f);
            table.addCell(cell);
            cell = new RtfCell(new Phrase(new Chunk(" ", fonts)));
            cell.setBorder(0);
            cell.setLeading(0f);
            table.addCell(cell);
            cell = new RtfCell(new Phrase(new Chunk("Neighborhood Division (812) 349-3421", fonts)));
            cell.setBorder(0);
            cell.setLeading(0f);
            table.addCell(cell);
            //
            // third row
            cell = new RtfCell(new Phrase(new Chunk("Fax (812) 349-3582", fonts)));
            cell.setBorder(0);
            cell.setLeading(0f);
            table.addCell(cell);
            phrase = new Phrase(new Chunk("bloomington.in.gov", fonts));
            pp     = new Paragraph(phrase);
            pp.setAlignment(Element.ALIGN_CENTER);
            // cell = new RtfCell(new Phrase(new Chunk("bloomington.in.gov",fonts)));
            cell = new RtfCell(pp);
            cell.setBorder(0);
            cell.setLeading(0f);
            table.addCell(cell);
            cell = new RtfCell(new Phrase(new Chunk("Housing Division (812) 349-3401", fonts)));
            cell.setBorder(0);
            cell.setLeading(0f);
            table.addCell(cell);
            document.setFooter(new RtfHeaderFooter(table));
        }
        catch (Exception ex) {
            logger.error(ex);
            msg += ex;
        }
        return msg;
    }
}
