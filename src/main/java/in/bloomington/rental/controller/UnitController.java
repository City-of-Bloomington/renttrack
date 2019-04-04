package in.bloomington.rental.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import in.bloomington.rental.model.RentalStructure;
import in.bloomington.rental.model.RentalUnit;
import in.bloomington.rental.model.UnitRoom;
import in.bloomington.rental.service.RentalStructureService;
import in.bloomington.rental.service.RentalUnitService;
import in.bloomington.rental.service.UnitRoomService;

@Controller
public class UnitController {

		String message = null;
		String[] roomTypes = {"Bedroom","Kitchen","Living Room","Dining Room"};
		@Autowired
		private RentalStructureService structureService;
		@Autowired
		private RentalUnitService unitService;
		@Autowired
		private UnitRoomService roomService;
		
		@GetMapping("/units/{structure_id}")
		public String unitsView(@PathVariable("structure_id") int structure_id, Model model) {
				
				model.addAttribute("units", unitService.findByStructureId(structure_id));
				if(message != null)
						model.addAttribute("message", message);
				return "units";
		}
		// get by id
		@GetMapping("/unit/{id}")
		public String getStructure(@PathVariable("id") int id,
											Model model) {
				RentalUnit unit = unitService.get(id);
				model.addAttribute("unit", unit);
				return "unitView";
		}
		
		// edit by id
		@GetMapping("/unitEdit/{id}")
		public String editUnit(@PathVariable("id") int id,
											Model model) {
				RentalUnit unit = unitService.get(id);
				model.addAttribute("unit", unit);
				return "unitEdit";
		}
		// get by id
		@GetMapping("/unitDelete/{id}")
		public String deleteUnit(@PathVariable("id") int id) {
				System.err.println(" id "+id);
				RentalUnit unit = unitService.get(id);
				int structureId = unit.getRentalStructure().getId();
				unitService.delete(unit.getId());
				message = "Structure deleted successfully";
				return "redirect:/structure/"+structureId;
		}
		
		// save
		@PostMapping("/unitUpdate")
		public String updateUnit(@ModelAttribute("unit") @Valid RentalUnit unit,
														 BindingResult result, Model model) {
				if (result.hasErrors()) {
						System.err.println(" errors "+result.toString());
						return "redirect:/unitEdit/"+unit.getId();
				}
				unitService.update(unit.getId(), unit);
				message = "Unit updated successfully";
				return "redirect:/unitEdit/"+unit.getId();
		}
		@GetMapping("/unitNew/{id}")
		public String unitForm(
															@PathVariable("id") int id,Model model) {
				RentalUnit unit = new RentalUnit();
				RentalStructure structure = structureService.get(id);
				int unitIdent = structure.getUnitsCount()+1; // new identifier
				unit.setIdentifier(""+unitIdent); // new identifier = cnt+1
				unit.setRentalStructure(structure);
				model.addAttribute("unit", unit);
				return "unitNew";
		}
		// save
		@PostMapping("/unitSave")
   public String saveUnit(@ModelAttribute("unit") @Valid RentalUnit unit,
         BindingResult result, Model model) {
      if (result.hasErrors()) {
         return "redirect:/unitNew";
      }
      unitService.save(unit);
			int id = unit.getId();
			message = "Unit saved successfully";
			return "redirect:/unitEdit/"+id; 
   }
		@GetMapping("/roomNew/{id}")
		public String roomNew(@PathVariable("id") int id,
											Model model) {
				RentalUnit unit = unitService.get(id);
				int roomCnt = unit.getRoomCount()+1;
				UnitRoom room = new UnitRoom();
				room.setIdentifier(""+roomCnt);
				room.setRentalUnit(unit);
				model.addAttribute("room", room);
				model.addAttribute("types",roomTypes);
				return "roomNew";
		}
		@GetMapping("/roomEdit/{id}")
		public String roomEdit(@PathVariable("id") int id,
											Model model) {
				UnitRoom room = roomService.get(id);
				model.addAttribute("room", room);
				model.addAttribute("types",roomTypes);
				return "roomEdit";
		}
		// save
		@PostMapping("/roomSave")
   public String saveRoom(@ModelAttribute("room") @Valid UnitRoom room,
         BindingResult result, Model model) {
      if (result.hasErrors()) {
         return "redirect:/roomNew";
      }
      roomService.save(room);
			int id = room.getRentalUnit().getId();
			message = "Unit saved successfully";
			return "redirect:/unit/"+id; 
   }
		// save
		@PostMapping("/roomUpdate")
   public String updateRoom(@ModelAttribute("room") @Valid UnitRoom room,
         BindingResult result, Model model) {
      if (result.hasErrors()) {
         return "redirect:/roomEdit/"+room.getId();
      }
      roomService.update(room.getId(), room);
			int id = room.getRentalUnit().getId();
			message = "Unit saved successfully";
			return "redirect:/unit/"+id; 
   }		
		// get by id
		@GetMapping("/roomDelete/{id}")
		public String deleteRoom(@PathVariable("id") int id) {
				UnitRoom room = roomService.get(id);
				int unitId = room.getRentalUnit().getId();
				roomService.delete(id);
				message = "Structure deleted successfully";
				return "redirect:/unit/"+unitId;
		}		

}
