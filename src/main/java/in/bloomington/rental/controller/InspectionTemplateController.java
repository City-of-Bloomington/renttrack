package in.bloomington.rental.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import in.bloomington.rental.model.InspectionTemplate;
import in.bloomington.rental.model.RentUser;
import in.bloomington.rental.model.RentalStructure;
import in.bloomington.rental.model.RentalUnit;
import in.bloomington.rental.model.TemplateComponent;
import in.bloomington.rental.model.UnitRoom;
import in.bloomington.rental.service.InspectionTemplateService;
import in.bloomington.rental.service.RentalStructureService;
import in.bloomington.rental.service.TemplateComponentService;
@Controller
@Scope("session")
public class InspectionTemplateController {

		String message = null;
		String[] component1Level = {"Exterior Components","Garage","Unit","Hallway","Mechanic Closet","Other"};
		String[] component2Level_1 = {"Exterior Components","Garage","Unit","Main Level","Living Room","Hallway","Mechanic Closet","Other"};
		String[] component2Level_2 = {"2nd Level","Living Room","Hallway","Mechanic Closet"};
		String[] component3Level_1 = {"Exterior Components","Garage","Unit","Main Level","Hallway","Mechanic Closet","Other"};
		String[] component3Level_2 = {"Basement","Mechanic Closet"};
		String[] component3Level_3 = {"2nd Leval","Mechanic Closet","Hallway"};		
		
		@Autowired
		private InspectionTemplateService templateService;
		@Autowired
		private TemplateComponentService componentService;		
		@Autowired
		private RentalStructureService structureService;
		@Autowired
		private HttpSession session;
		//
		// edit by id
		@GetMapping("/templateEdit/{id}")
		public String editTemplate(@PathVariable("id") int id,
											Model model) {
				InspectionTemplate template = templateService.get(id);
				model.addAttribute("template", template);
				if(message != null){
						model.addAttribute("message", message);
						message = null;
				}
			 return "/templateEdit";
		}
		@GetMapping("/componentEdit/{id}")
		public String editComponent(@PathVariable("id") int id,
											Model model) {
				TemplateComponent component = componentService.get(id);
				model.addAttribute("component", component);
				if(message != null){
						model.addAttribute("message", message);
						message = null;
				}
			 return "/templateComponentEdit";
		}		
		@PostMapping("/templateComponentDelete")
		public String componentDelete(@ModelAttribute("template") @Valid InspectionTemplate template,
														 BindingResult result, Model model) {
				if (result.hasErrors()) {
						System.err.println(" errors "+result.toString());
						return "redirect:template/"+template.getId();
				}
				if(template.hasDelIds()){
						int[] del_ids = template.getDel_ids();
						for(int jj:del_ids){
								componentService.delete(jj);
						}
						message = "deleted successfully";
				}
				int id = template.getId();
				return "redirect:/templateEdit/"+id;
		}
		//
		// update one item
		//
		@PostMapping("/componentUpdate")
		public void componentUpdate(@ModelAttribute("component") @Valid TemplateComponent component,
														 BindingResult result, Model model) {
				if (result.hasErrors()) {
						System.err.println(" errors "+result.toString());
						return;
				}
				componentService.update(component.getId(), component);
				message = "updated successfully";
				return ;// "redirect:templateEdit/"+id;
		}
		// save one item 
		@PostMapping("/componentSave")
		public void componentSave(@ModelAttribute("component") @Valid TemplateComponent component,
														 BindingResult result, Model model) {
				if (result.hasErrors()) {
						System.err.println(" errors "+result.toString());
						message = "error "+result.toString();
						return;
				}
				// need fix
				componentService.save(component);
				message = "updated successfully";
		}		
		@GetMapping("/templateNew/{rid}")
		public String templateNew(@PathVariable("rid") int rid,
															Locale locale, Model model){
				//
				// check if we have already a template for this rental
				//
				List<InspectionTemplate> templates = templateService.findByRentalId(rid);
				if(templates != null && templates.size() > 0){
						InspectionTemplate template = templates.get(0);// the first
						model.addAttribute("template", template);
						return "/template";						
				}
				// start a new one
				return "redirect:/templateNext/"+rid+"/0/0/0/0";
		}
		/**
		 * tid template id
		 * bid building id
		 */
		@GetMapping("/componentNew/{tid}/{bid}")
		public String componentNew(@PathVariable("tid") int tid,
															 @PathVariable("bid") int bid,
															 Locale locale, Model model){
				try{
						InspectionTemplate template = templateService.get(tid);
						int rid = template.getRentalId();
						TemplateComponent component = new TemplateComponent();
						component.setInspectionTemplate(template);
						component.setBuildingNum(bid);
						RentalStructure structure = null;
						int floorCnt = 1;
						int unitCnt = 1;
						List<RentalStructure> structures = structureService.findByRentalId(rid);
						if(structures != null && structures.size() > 0){
								structure = structures.get(bid);
								if(structure != null){
										floorCnt = structure.getStoryCnt();
										if(structure.hasUnits()){
												unitCnt = structure.getRentalUnits().size();
										}
								}
						}
						model.addAttribute("component", component);
						model.addAttribute("unitCnt", unitCnt);
						model.addAttribute("floorCnt", floorCnt);
				}catch(Exception ex){
						System.err.println(" error "+ex);
				}
				// start a new one
				return "/componentNew";
		}		
		/**
		 * rid:rentalId
		 * bid:buildingId
		 * uid:unitId
		 * fid:floorId
		 */
		@GetMapping("/templateNext/{rid}/{bid}/{uid}/{fid}/{id}")
		public String templateNext(@PathVariable("rid") int rid,
															 @PathVariable("bid") int bid,
															 @PathVariable("uid") int uid,
															 @PathVariable("fid") int fid,
															 @PathVariable("id") int id,
															 Locale locale, Model model) {
				int bedrooms = 1;
				int bathrooms = 1;
				int floorCnt = 1;
				int unitCnt = 1;
				int buildingCnt = 1;
				String floorInfo = "";
				String unitInfo = "";
				String identifier = "";
				List<UnitRoom> rooms = null;
				try{
						List<RentalStructure> structures = structureService.findByRentalId(rid);
						buildingCnt = structures.size();
						List<RentalUnit> units = new ArrayList<>();
						//
						// start with the uid unit
						//
						RentalUnit unit = null;
						if(structures.size() > 0){
								RentalStructure structure = structures.get(bid);
								floorCnt = structure.getStoryCnt();								
								if(structure.hasUnits()){
										units = structure.getRentalUnits();
										unitCnt = units.size();
										unit = units.get(uid);
										identifier = unit.getIdentifier();
										if(unit.hasRooms()){
												rooms = unit.getUnitRooms();
										}
										if(unit.hasBathrooms()){
												bathrooms = unit.getBathrooms();
										}
								}
						}
						if(floorCnt < 1) floorCnt = 1;
						List<String> list = new ArrayList<>();												
						if(floorCnt == 1){
								floorInfo = " Unit components";
								for(String str:component1Level){
										if(str.equals("Unit")){
												str += " "+identifier;
										}
										list.add(str);
								}
								for(int jj=0;jj<bathrooms;jj++){
										list.add("Bathroom");
								}
								if(rooms != null){
										for(UnitRoom one:rooms){
												list.add(one.getInfo());
										}
								}
						}
						else if(floorCnt == 2){
								if(fid == 0){
										floorInfo = " Unit first floor components, in next screen you will provide the second level components";
										for(String str:component2Level_1){
												if(str.equals("Unit")){
														str += " "+identifier;
												}
												list.add(str);
										}
								}
								else if(fid == 1){
										floorInfo = "Unit second floor components";
										for(String str:component2Level_2){
												list.add(str);
										}
								}
								for(int jj=0;jj<bathrooms;jj++){
										list.add("Bathroom");
								}
								if(rooms != null){
										for(UnitRoom one:rooms){
												list.add(one.getInfo());
										}
								}
						}
						else if(floorCnt == 3){
								if(fid == 0){
										floorInfo = "Unit main floor components, in next screen you will provide the other level components";										
										for(String str:component3Level_1){
												if(str.equals("Unit")){
														str += " "+identifier;
												}
												list.add(str);
										}
								}
								else if(fid == 1){
										floorInfo = "Unit basement components";													
										for(String str:component3Level_2){
												list.add(str);
										}
								}
								else if(fid == 2){
										floorInfo = "Unit second level components";			
										for(String str:component3Level_3){
												list.add(str);
										}
								}								
								for(int jj=0;jj<bathrooms;jj++){
										list.add("Bathroom");
								}
								if(rooms != null){
										for(UnitRoom one:rooms){
												list.add(one.getInfo());
										}
								}
						}
						unitInfo = "Please provide the components of building "+(bid+1)+" - unit "+(uid+1);
						model.addAttribute("components", list);
						//
						InspectionTemplate template = null;
						if(id > 0){
								template = templateService.get(id);
						}
						else {
								template = new InspectionTemplate();
						}
						template.setBuildingCnt(buildingCnt);
						template.setRentalId(rid);
						template.setBuildingNum(bid);// all defaults
						template.setUnitNum(uid);
						template.setFloorNum(fid);
						model.addAttribute("template", template);
						model.addAttribute("unitInfo",unitInfo);
						model.addAttribute("floorInfo",floorInfo);
						if(message != null){
								model.addAttribute("message", message);
								message = null;
						}
				}
				catch(Exception ex){
						System.err.println(" template control "+ex);
				}
				if(floorCnt > 1 || buildingCnt > 1 || unitCnt > 1){
						return "/templateNew"; // for Save						
						// return "/templateNewToNext"; // for Next
				}
				else{
						return "/templateNew"; // for Save
				}
		}
		//
		// save
		@PostMapping("/templateSave")
		public String templateSave(@ModelAttribute("template") @Valid InspectionTemplate template,
															 BindingResult result,
															 Model model
															 ) {
      if (result.hasErrors()) {
					int rid = template.getRentalId();
         return "redirect:templateNew/"+rid;
      }
			int floorCnt = 1;
			int unitCnt = 1;
			int buildingCnt = 1;
			int bid = 0;
			int uid = 0;
			int fid = 0;
			int rid = template.getRentalId();
			int id = template.getId();
			String retStr = "redirect:view/"+rid;
			RentUser user = null;
			if(session != null){
					user = (RentUser)session.getAttribute("user");
			}
			try{
					if(id == 0){
							template.setUser(user);
							template.setDate(new Date());
							templateService.save(template);
							id = template.getId();
					}
					if(template.hasComponents()){
							String[] components = template.getComponents();
							bid = template.getBuildingNum();
							uid = template.getUnitNum();
							fid = template.getFloorNum();
							for(String str:components){
									if(str != null && !str.trim().equals("")){
											TemplateComponent tt = new TemplateComponent(template,
																													 bid,
																													 uid,
																													 fid,
																													 str);
											componentService.save(tt);
									}
							}
					}
					List<RentalStructure> structures = structureService.findByRentalId(rid);
					buildingCnt = structures.size();
					List<RentalUnit> units = new ArrayList<>();
					if(structures.size() > 0){
							RentalStructure structure = structures.get(bid);
							floorCnt = structure.getStoryCnt();								
							if(structure.hasUnits()){
									units = structure.getRentalUnits();
									unitCnt = units.size();
							}
					}
					if(fid+1 < floorCnt){ // next will be the next floor
							int nextFid = fid + 1;
							retStr = "redirect:/templateNext/"+rid+"/0/0/"+nextFid+"/"+id;
					}
					else if(uid+1 < unitCnt){ // next will be next unit
							int nextUid = uid + 1;
							retStr = "redirect:/templateNext/"+rid+"/0/"+nextUid+"/"+fid+"/"+id;
					}
					else if(bid+1 < buildingCnt){ // next building
							int nextBid = bid + 1;
							retStr = "redirect:/templateNext/"+rid+"/"+nextBid+"/"+uid+"/"+fid+"/"+id;
					}
					message = "Saved successfully";					
			}
			catch(Exception ex){
					System.err.println(ex);
			}
      return retStr;
   }
}

