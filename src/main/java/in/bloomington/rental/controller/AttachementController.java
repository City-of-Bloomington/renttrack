package in.bloomington.rental.controller;

import java.util.Locale;
import java.util.List;
import java.util.Date;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import in.bloomington.rental.model.AttachBase;
import in.bloomington.rental.model.Attachement;
import in.bloomington.rental.service.AttachementService;
import in.bloomington.rental.model.AttachementSeq;
import in.bloomington.rental.service.AttachementSeqService;
import in.bloomington.rental.model.Rental;
import in.bloomington.rental.service.RentalService;
import in.bloomington.rental.model.Inspection;
import in.bloomington.rental.service.InspectionService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.core.io.InputStreamResource;
import in.bloomington.rental.util.GeneralHelper;
import in.bloomington.rental.util.Helper;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.nio.file.*;
// import in.bloomington.rental.model.Inspection;
// import in.bloomington.rental.service.InspectionService;


@Controller
public class AttachementController {

		String message = null;
		private static final Logger logger = LogManager.getLogger(AttachementController.class);				
		@Autowired
		private AttachementService attachementService;
		@Autowired
		private AttachementSeqService attachementSeqService;		
		@Autowired
		private RentalService rentalService;
		@Autowired
		private InspectionService inspectionService;		
		@Autowired
		private HttpServletRequest request;
		@Autowired
		private GeneralHelper ghelper;
		@Autowired
		private Helper helper;
		
		// @Autowired
		// private RentalService inspectionService;
		//
		@GetMapping("/attachements/{type}/{id}")
		public String attachementsView(@PathVariable("type") String type,
																	 @PathVariable("id") int id,
																	 Model model) {

				if(type.equals("rental")){
						model.addAttribute("attachements", attachementService.findByRentalId(id));
				}
				else{
						model.addAttribute("attachements", attachementService.findByInspectionId(id));
				}
				if(message != null)
						model.addAttribute("message", message);
				return "attachements";
		}
		// get by id
		@GetMapping("/attachement/{id}")
		public String getAttachement(@PathVariable("id") int id,
											Model model) {
				Attachement attachement = attachementService.get(id);
				model.addAttribute("attachement", attachement);
			 return "attachementView";
		}
		// delete by id
		@GetMapping("/attachementDelete/{id}")
		public String deleteAttachement(@PathVariable("id") int id) {
				System.err.println(" id "+id);
				int rentalId = 0, inspectionId = 0;
				Attachement attachement = attachementService.get(id);
				Rental rental = attachement.getRental();
				if(rental != null){
						rentalId = rental.getId();
				}
				else{
						inspectionId = attachement.getInspection().getId();
				}
				attachementService.delete(attachement);
				message = "Attachement deleted successfully";
				if(rentalId > 0){
						return "redirect:/view/"+rentalId;
				}
				else {
						return "redirect:/inspectionView/"+inspectionId;
				}
		}		
		//
		@GetMapping("/attachementNew/{type}/{id}")
		public String attachementNew(@PathVariable("type") String type,
																 @PathVariable("id") int id,
																 Model model) {
				AttachBase base = new AttachBase();
				base.setType(type);
				base.setId(id);
				model.addAttribute("base", base);
				return "uploadForm";
   }

		@PostMapping("/uploadFile")
		public String doUpload(@RequestParam("file") MultipartFile file,
													 @RequestParam("id") int id,
													 @RequestParam("notes") String notes,
													 @RequestParam("type") String type){
				String fileName = null;
				if(file == null || file.isEmpty()){
						message = "Please select a file to upload";
						return "redirect:attachementNew/"+type+"/"+id;
				}
				String ret_str="";
				int new_id=0;
				ghelper.populatePaths();
				String file_path = ghelper.getFilePath();
				String groupName = ghelper.getGroupName();
				fileName = file.getOriginalFilename();				
				int year = Helper.getCurrentYear();
				String file_ext = Helper.getFileExtensionFromName(fileName);
				String newName = genNewFileName(file_ext, type);
				try{
            byte[] bytes = file.getBytes();
						String dirPath= file_path+"/"+year+"/";
						//
						// check if the dir exists, if not create
						//
						String back = Helper.checkFilePath(dirPath, groupName);
						if(!back.equals("")){
								message += back;
 								logger.error(back);
						}
						//
            Path path = Paths.get(dirPath + newName);
            Files.write(path, bytes);
						Attachement one = new Attachement();
						one.setFileName(newName);
						one.setOldFileName(fileName);
						one.setNotes(notes);
						one.setDate(new Date());
						if(type.equals("rental")){
								Rental rental = rentalService.get(id);
								one.setRental(rental);
								ret_str = "/view/"+id;
						}
						else{
								Inspection inspection = inspectionService.get(id);
								one.setInspection(inspection);
								ret_str = "/inspection/"+id;
						}
						attachementService.save(one);
						new_id = one.getId();
						message += "Uploaded Successfully";
				} catch (Exception e) {
						e.printStackTrace();
						message  += e;
				}
				return "redirect:"+ret_str;
		}
		@RequestMapping(value = "/attachDownload/{id}", method = RequestMethod.GET)
		public ResponseEntity<InputStreamResource> doDownload(@PathVariable int id){
				Attachement one = attachementService.get(id);
				if(one != null){
						try{
								String fileName = one.getOldFileName();
								String year = one.getYearFromDate();
								ghelper.populatePaths();
								String filePath = ghelper.getFilePath();
								String fullPath = filePath+year+"/"+one.getFileName();
								File file = new File(fullPath);
								String fileType = helper.findFileType(file);
								HttpHeaders respHeaders = new HttpHeaders();
								respHeaders.setContentType(MediaType.valueOf(fileType));
								respHeaders.setContentLength(file.length());
								respHeaders.setContentDispositionFormData("attachment", one.getOldFileName());

								InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
								return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
						}catch(Exception ex){
								System.err.println(ex);
						}
				}
				return null;
		}
		String genNewFileName(String file_ext, String type){
				String file_name = "";
				AttachementSeq seq = new AttachementSeq();
				attachementSeqService.save(seq);
				int new_id = seq.getId();
				if(file_ext == null)
						file_ext="";
				file_name = type+"_"+new_id+"."+file_ext; 
				return file_name;
		}


}

