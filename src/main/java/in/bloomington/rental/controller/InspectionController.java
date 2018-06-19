package in.bloomington.rental.controller;

import java.util.Locale;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javax.validation.Valid;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Scope;
import in.bloomington.rental.model.RentalStructure;
import in.bloomington.rental.service.RentalStructureService;
import in.bloomington.rental.model.Rental;
import in.bloomington.rental.model.RentUser;
import in.bloomington.rental.model.InspectionType;
import in.bloomington.rental.model.Inspection;
import in.bloomington.rental.model.InspectionFileLog;
import in.bloomington.rental.util.RtfWriter;
import in.bloomington.rental.service.RentalService;
import in.bloomington.rental.service.InspectionTypeService;
import in.bloomington.rental.service.InspectionService;
import in.bloomington.rental.service.UserService;
import in.bloomington.rental.service.InspectionFileLogService;
import org.springframework.core.io.InputStreamResource;
import in.bloomington.rental.util.GeneralHelper;
import in.bloomington.rental.util.Helper;

@Controller
@Scope("session")
public class InspectionController {

		String message = null;
		final static String[] foundationTypes = {"Basement","Slab","Cellar","Crawl Space","Other"};
		final static String[] heatSources = {"Electric","Gas","Electric/Gas","Other"};
		@Autowired
		private HttpServletRequest request;
		@Autowired
		private InspectionService inspectionService;
		@Autowired
		private InspectionFileLogService inspectionFileLogService;		
		@Autowired
		private RentalService rentalService;
		@Autowired
		private InspectionTypeService inspectionTypeService;
		@Autowired		
		private UserService userService;
		@Autowired
		private GeneralHelper ghelper;
		@Autowired
		private Helper helper;
		@Autowired
		private HttpSession session;
		
		static Inspection inspection = null;
		static List<InspectionType> inspectionTypes = null;
		static List<RentUser> inspectors = new ArrayList<>();
		
		@GetMapping("/inspections/{rental_id}")
		public String inspectionsView(@PathVariable("rental_id") int rental_id, Model model) {
				
				model.addAttribute("inspections", inspectionService.findByRentalId(rental_id));
				if(message != null)
						model.addAttribute("message", message);
				return "inspections";
		}
		// get by id
		@GetMapping("/inspection/{id}")
		public String getInspection(@PathVariable("id") int id,
											Model model) {
				Inspection inspection = inspectionService.get(id);
				model.addAttribute("inspection", inspection);
				return "inspectionView";
		}
		@GetMapping("/inspectionApprove/{id}")
		public String getInspectionApprove(@PathVariable("id") int id,
											Model model) {
				RentUser user = null;
				if(session != null){
						user = (RentUser)session.getAttribute("user");
				}						
				Inspection inspection = inspectionService.get(id);
				inspection.setApproved('y');
				inspection.setApprovedDate(new Date());
				inspection.setApprover(user);
				inspectionService.update(id, inspection);
				model.addAttribute("inspection", inspection);
				return "inspectionView";
		}		
		// edit by id
		@GetMapping("/inspectEdit/{id}")
		public String editInspection(@PathVariable("id") int id,
											Model model) {
				Inspection inspection = inspectionService.get(id);
				model.addAttribute("inspection", inspection);
				if(inspectionTypes == null){
						inspectionTypes = inspectionTypeService.list();
				}
				if(inspectionTypes != null){
						model.addAttribute("inspectionTypes", inspectionTypes);
				}
				if(inspectors.size() == 0){
						inspectors = userService.getInspectors();
				}
				model.addAttribute("inspectors",inspectors);
				model.addAttribute("foundationTypes", foundationTypes);
				model.addAttribute("heatSources",heatSources);
				return "inspectionEdit";
		}
		// edit by id
		@GetMapping("/inspectionCreateFile/{id}")
		public String createFileInspection(@PathVariable("id") int id,
											Model model) {
				ghelper.populatePaths();
				Inspection inspection = inspectionService.get(id);
				int rid = inspection.getRental().getId();
				Rental rental = null;						
				if(rid > 0){
						rental = rentalService.get(rid);
				}
				// number of inspection + 1
				int cnt = inspectionFileLogService.findCountByRentalId(rid)+1;
				String path = ghelper.getInspectionFilePath();
				String groupName = ghelper.getGroupName();
				String imageUrl = ghelper.getImageUrl();
				String fileNameAndPath = inspection.createFileName(path, groupName, cnt);
				//System.err.println(" image url "+imageUrl);
				//System.err.println(" file and path "+fileNameAndPath);
				//System.err.println(" rtf writing");
				RtfWriter writer = new RtfWriter(rental,
																				 inspection,
																				 imageUrl,
																				 fileNameAndPath
																				 );
				String str = writer.writeAll();
				if(!str.equals("")){
						System.err.println(" writing error "+str);
				}
				else{
						RentUser user = null;
						if(session != null){
								user = (RentUser)session.getAttribute("user");
						}								
						inspectionService.update(id, inspection);
						InspectionFileLog fileLog =
								new InspectionFileLog(rid, id, user, new Date(), inspection.getInspectFile());
						inspectionFileLogService.save(fileLog);
				}
				model.addAttribute("inspection", inspection);
				
				if(inspectionTypes == null){
						inspectionTypes = inspectionTypeService.list();
				}
				if(inspectionTypes != null){
						model.addAttribute("inspectionTypes", inspectionTypes);
				}
				if(inspectors.size() == 0){
						inspectors = userService.getInspectors();
				}
				model.addAttribute("inspectors",inspectors);
				return "inspectionEdit";
		}		
		// get by id
		@GetMapping("/inspectionDelete/{id}")
		public String deleteInspection(@PathVariable("id") int id) {
				System.err.println(" id "+id);
				Inspection inspection = inspectionService.get(id);
				int rentalId = inspection.getRental().getId();
				inspectionService.delete(id);
				message = "Inspection deleted successfully";
				return "redirect:/view/"+rentalId;
		}		
		// save
		@PostMapping("/inspectionUpdate")
		public String updateInspection(@ModelAttribute("inspection") @Valid Inspection inspection,
														 BindingResult result, Model model) {
				if (result.hasErrors()) {
						System.err.println(" errors "+result.toString());
						return "redirect:/inspectionEdit/"+inspection.getId();
				}
				inspectionService.update(inspection.getId(), inspection);
				message = "Inspection updated successfully";
				Integer rentalId = inspection.getRental().getId();
				return "redirect:/view/"+rentalId;
		}
		
		
		@GetMapping("/inspectionNew/{id}")
		public String inspectionForm(
															@PathVariable("id") int id,Model model) {
				if(inspection == null){
						inspection = new Inspection();
				}
				Rental rental = rentalService.get(id);
				inspection.setRental(rental);
				model.addAttribute("inspection", inspection);
				if(inspectionTypes == null){
						inspectionTypes = inspectionTypeService.list();
				}
				if(inspectionTypes != null){
						model.addAttribute("inspectionTypes", inspectionTypes);
				}
				if(inspectors.size() == 0){
						inspectors = userService.getInspectors();
				}
				if(inspectors != null){
						model.addAttribute("inspectors",inspectors);
				}				
				this.inspection = null;
				return "inspectionNew";
		}
		// save
		@PostMapping("/inspectionSave")
		public String saveInspection(
																@ModelAttribute("inspection")
																@Valid Inspection inspection,
																BindingResult result,
																Model model) {
      if (result.hasErrors()) {
					int rentalId = inspection.getId();					
         return "redirect:/inspectionNew/"+rentalId;
      }
			message = inspection.validateInspection();
			if(message.equals("")){
					inspectionService.save(inspection);
					int id = inspection.getId();
					message = "Inspection saved successfully";
					this.inspection = null; // clean up
					return "redirect:/inspection/"+id;
			}
			else{
					int rentalId = inspection.getRental().getId();
					this.inspection = inspection;
					return "redirect:/inspectionNew/"+rentalId;					
			}
		}
		
		//
		@RequestMapping(value = "/inspectDownload/{id}", method = RequestMethod.GET)
		public ResponseEntity<InputStreamResource> doDownload(@PathVariable int id){
				Inspection one = inspectionService.get(id);
				if(one != null){
						try{
								String fileName = one.getInspectFile();
								ghelper.populatePaths();
								String filePath = ghelper.getInspectionFilePath();
								String fullPath = filePath+fileName;
								System.err.println(" path "+fullPath);
								File file = new File(fullPath);
								String fileType = helper.findFileType(file);
								HttpHeaders respHeaders = new HttpHeaders();
								respHeaders.setContentType(MediaType.valueOf(fileType));
								respHeaders.setContentLength(file.length());
								respHeaders.setContentDispositionFormData("attachment", one.getFileNameOnly());

								InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
								return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
						}catch(Exception ex){
								System.err.println(ex);
						}
				}
				return null;
		}		
		@PostMapping("/inspectionUpload")
		public String inspectUpload(@RequestParam("file") MultipartFile file,
																@RequestParam("id") int id,
																Model model){
				String fileName = null;
				Inspection inspection = inspectionService.get(id);
				if(file == null || file.isEmpty()){
						model.addAttribute("inspection", inspection);
						message = "Please select a file to upload";
						return "redirect:inspection/"+id;
				}
				ghelper.populatePaths();
				int rid = inspection.getRental().getId();
				int cnt = inspectionFileLogService.findCountByRentalId(rid)+1;
				System.err.println(" cnt "+cnt);
				String insp_path = ghelper.getInspectionFilePath();
				String groupName = ghelper.getGroupName();
				String origFileName = file.getOriginalFilename();
				String fileNameAndPath = inspection.createFileName(insp_path, groupName, cnt);
				System.err.println(" orig file "+origFileName);
				System.err.println(" new file "+fileNameAndPath);
				try{
            byte[] bytes = file.getBytes();
            Path path = Paths.get(fileNameAndPath);						
						Files.write(path, bytes);
						//
						inspectionService.update(id, inspection);
						RentUser user = null;
						if(session != null){
								user = (RentUser)session.getAttribute("user");
						}		
						InspectionFileLog fileLog =
								new InspectionFileLog(rid, id, user, new Date(), inspection.getInspectFile());
						inspectionFileLogService.save(fileLog);						
						model.addAttribute("inspection", inspection);
						message += "Uploaded Successfully";
				} catch (Exception e) {
						e.printStackTrace();
						message  += e;
				}
				return "redirect:inspection/"+id;
		}
}
