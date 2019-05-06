package in.bloomington.rental.controller;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import in.bloomington.rental.model.AttachBase;
import in.bloomington.rental.model.Attachment;
import in.bloomington.rental.model.Inspection;
import in.bloomington.rental.model.Rental;
import in.bloomington.rental.service.AttachmentService;
import in.bloomington.rental.service.InspectionService;
import in.bloomington.rental.service.RentalService;
import in.bloomington.rental.util.GeneralHelper;
import in.bloomington.rental.util.Helper;

@Controller
public class AttachmentController
{

    String                        message = null;
    private static final Logger   logger  = LogManager.getLogger(AttachmentController.class);
    @Autowired
    private AttachmentService    attachmentService;
    @Autowired
    private RentalService         rentalService;
    @Autowired
    private InspectionService     inspectionService;
    @Autowired
    private GeneralHelper         ghelper;

    // @Autowired
    // private RentalService inspectionService;
    //
    @GetMapping("/attachments/{type}/{id}")
    public String attachmentsView(@PathVariable("type") String type,
                                  @PathVariable("id"  ) int    id,
                                                        Model  model)
    {

        if (type.equals("rental")) {
            model.addAttribute("attachments", attachmentService.findByRentalId(id));
        }
        else {
            model.addAttribute("attachments", attachmentService.findByInspectionId(id));
        }
        if (message != null) model.addAttribute("message", message);
        return "attachments";
    }

    // get by id
    @GetMapping("/attachment/{id}")
    public String getAttachment(@PathVariable("id") int id, Model model)
    {
        Attachment attachment = attachmentService.get(id);
        model.addAttribute("attachment", attachment);
        return "attachmentView";
    }

    // delete by id
    @GetMapping("/attachmentDelete/{id}")
    public String deleteAttachment(@PathVariable("id") int id)
    {
        int        rentalId     = 0,
                   inspectionId = 0;
        Attachment attachment   = attachmentService.get(id);
        Rental     rental       = attachment.getRental();
        if (rental != null) {
            rentalId = rental.getId();
        }
        else {
            inspectionId = attachment.getInspection().getId();
        }
        attachmentService.delete(attachment);
        message = "Attachment deleted successfully";
        
        return rentalId > 0
            ? "redirect:/view/" + rentalId
            : "redirect:/inspectionView/" + inspectionId;
    }

    @GetMapping("/attachmentNew/{type}/{id}")
    public String attachmentNew(@PathVariable("type") String type,
                                @PathVariable("id")   int    id,
                                                      Model  model)
    {
        AttachBase base = new AttachBase();
        base.setType(type);
        base.setId(id);
        model.addAttribute("base", base);
        return "uploadForm";
    }

    @PostMapping("/uploadFile")
    public String doUpload(@RequestParam("file" ) MultipartFile file,
                           @RequestParam("id"   ) int           id,
                           @RequestParam("notes") String        notes,
                           @RequestParam("type" ) String        type)
    {
        String fileName = null;
        if (file == null || file.isEmpty()) {
            message = "Please select a file to upload";
            return "redirect:attachmentNew/" + type + "/" + id;
        }
        ghelper.populatePaths();
               fileName  = file.getOriginalFilename();
        String ret_str   = "";
        String file_path = ghelper.getFilePath();
        String groupName = ghelper.getGroupName();
        int    year      = Helper.getCurrentYear();
        String file_ext  = Helper.getFileExtensionFromName(fileName);
        String newName   = genNewFileName(file_ext, type);
        
        try {
            byte[] bytes   = file.getBytes();
            String dirPath = file_path + "/" + year + "/";
            //
            // check if the dir exists, if not create
            //
            String back    = Helper.checkFilePath(dirPath, groupName);
            if (!back.equals("")) {
                message += back;
                logger.error(back);
            }

            Path path = Paths.get(dirPath + newName);
            Files.write(path, bytes);
            Attachment one = new Attachment();
            one.setFileName   (newName   );
            one.setOldFileName(fileName  );
            one.setNotes      (notes     );
            one.setDate       (new Date());
            if (type.equals("rental")) {
                Rental rental = rentalService.get(id);
                one.setRental(rental);
                ret_str = "/view/" + id;
            }
            else {
                Inspection inspection = inspectionService.get(id);
								Rental rental = inspection.getRental();
								Integer rentalId = rental.getId();
								one.setRentalId(rentalId);
                one.setInspection(inspection);
								
                ret_str = "/inspection/" + id;
            }
            attachmentService.save(one);
            message += "Uploaded Successfully";
        }
        catch (Exception e) {
            e.printStackTrace();
            message += e;
        }
        return "redirect:" + ret_str;
    }

    @RequestMapping(value = "/attachDownload/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> doDownload(@PathVariable int id)
    {
        Attachment one = attachmentService.get(id);
        if (one != null) {
            try {
                String year     = one.getYearFromDate();
                ghelper.populatePaths();
                String      filePath    = ghelper.getFilePath();
                String      fullPath    = filePath + year + "/" + one.getFileName();
                File        file        = new File(fullPath);
                String      fileType    = Helper.findFileType(file);
                HttpHeaders respHeaders = new HttpHeaders();
                respHeaders.setContentType(MediaType.valueOf(fileType));
                respHeaders.setContentLength(file.length());
                respHeaders.setContentDispositionFormData("attachment", one.getOldFileName());

                InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
                return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
            }
            catch (Exception ex) {
                System.err.println(ex);
            }
        }
        return null;
    }

    String genNewFileName(String file_ext, String type)
    {
        return UUID.randomUUID().toString() + '.' + file_ext;
    }
}
