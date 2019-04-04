package in.bloomington.rental.controller;

import java.util.Locale;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;
import org.json.JSONArray;
import in.bloomington.rental.model.Item;
import in.bloomington.rental.model.Address;
import in.bloomington.rental.service.AddressService;
import in.bloomington.rental.service.RentalService;
import in.bloomington.rental.model.RentalUnit;
import in.bloomington.rental.service.RentalUnitService;
import in.bloomington.rental.util.AddressCheck;

@Controller
public class AddressController {

		String message = null;
		
		@Autowired
		private AddressService addressService;
		@Autowired
		private RentalService rentalService;
		@Autowired
		private RentalUnitService unitService;
		@Autowired
		private AddressCheck check;
		
		@GetMapping("/addresss")
		public String addresssView(Locale locale, Model model) {
				
				model.addAttribute("addresss", addressService.getAll());
				if(message != null)
						model.addAttribute("message", message);
				return "addresss";
		}
		// get by id
		@GetMapping("/address/{id}")
		public String getAddress(@PathVariable("id") int id,
											Model model) {
				Address address = addressService.get(id);
				model.addAttribute("address", address);
			 return "addressView";
		}
		// edit by id
		@GetMapping("/addressEdit/{id}")
		public String editAddress(@PathVariable("id") int id,
											Model model) {
				Address address = addressService.get(id);
				model.addAttribute("address", address);
			 return "addressEdit";
		}
		// get by id
		@GetMapping("/addressDelete/{id}")
		public String deleteAddress(@PathVariable("id") int id) {
				System.err.println(" id "+id);
				Address address = addressService.get(id);
				Integer rentalId = address.getRentalId();
				addressService.delete(address);
				message = "Address deleted successfully";
				if(rentalId != null && rentalId > 0){
						return "redirect:/view/"+rentalId;
				}
				else {
						return "redirect:/addresses";
				}
		}		
		// save
		@PostMapping("/addressUpdate")
		public String updateAddress(@ModelAttribute("address") @Valid Address address,
																BindingResult result,
																Model model) {
				if (result.hasErrors()) {
						System.err.println(" errors "+result.toString());
						return "redirect:/addressEdit/"+address.getId();
				}
				addressService.update(address.getId(), address);
				message = "Address updated successfully";
				Integer rentalId = address.getRentalId();
				if(rentalId != null && rentalId > 0){
						return "redirect:/view/"+rentalId;
				}
				else
						return "redirect:/addresses";
		}
		//
		@GetMapping("/addressNew/{type}/{id}")
		public String addressNewWithUnit(@PathVariable("type") String type,@PathVariable("id") int id,Model model) {
				Address address = new Address();
				if(type.equals("unit"))
						address.setUnitId(id);
				else
						address.setRentalId(id);
				model.addAttribute("address", address);
				return "addressNew";
   }
		//
		@GetMapping("/addressNew")
		public String addressNew(Model model) {
				Address address = new Address();
				model.addAttribute("address", address);
				return "popAddress";
   }
		//
		@PostMapping("/addressPopFind")
   public String popFindAddress(@ModelAttribute("address") @Valid Address address,
         BindingResult result, Model model) {
      if (result.hasErrors()) {
         return "popAddress";
      }
			List<Address> addresses = null;
			Address foundAddress = null;
			if(check != null){
					check.setAddress(address);
					check.findSimilarAddr();
					if(check.foundAddresses()){
							addresses = check.getAddresses();
					}
			}
			if(addresses != null){
					if(addresses.size() == 1){
							foundAddress = addresses.get(0);
					}
					else if(addresses.size() > 1){
							if(check.findExactMatch()){
									foundAddress = check.getExactMatchAddress();
							}
					}
			}
			if(foundAddress != null){
					addressService.save(foundAddress);
					model.addAttribute("address", foundAddress);
					return "popAddressEdit";
			}
			else if(addresses != null){
					model.addAttribute("addresses", addresses);
					return "pickAddress"; 					
			}
			model.addAttribute("address", address);			
			return "popAddress";
   }
		//
		@PostMapping("/addressPopSave")
   public String savePopAddress(@ModelAttribute("address") @Valid Address address,
         BindingResult result, Model model) {
      if (result.hasErrors()) {
         return "redirect:/popAddress";
      }
			addressService.save(address);
			int id = address.getId();
			System.err.println(" address id "+id);
			message = "Address saved successfully";
			return "popAddressEdit"; 
   }
		
		// edit by id
		@GetMapping("/addressPopEdit/{id}")
		public String editPopAddress(@PathVariable("id") int id,
											Model model) {
				Address address = addressService.get(id);
				model.addAttribute("address", address);
			 return "popAddress";
		}		
		// save
		@PostMapping("/addressSave")
   public String saveAddress(@ModelAttribute("address") @Valid Address address,
         BindingResult result, Model model) {
      if (result.hasErrors()) {
					System.err.println(result);
         return "redirect:/newAddress";
      }
			boolean newFlag = false;
			if(address.isNew()){
					newFlag = true;
					check.setAddress(address);
					String back = check.findSimilarAddr();
					if(back.equals("")){
							addressService.save(address);
							message = "Address saved successfully";
					}
					else{
							message += "error "+back;
					}
			}
			Integer unitId = address.getUnitId();
			if(unitId != null){
					RentalUnit unit = unitService.get(unitId);
					if(unit != null){
							unit.setAddress(address);
							unitService.update(unitId, unit);
					}
					return "redirect:/unit/"+unitId;					
			}
			else {
					Integer rentalId = address.getRentalId();
					if(newFlag){
							// we save when new only so that we do not
							// override the old rental id
							addressService.update(address.getId(), address);
					}
					return "redirect:/view/"+rentalId;			
			}
   }

}
@RestController		
class AddressServiceController {
		
		@Autowired
		private AddressService addressService;
		@GetMapping(value="/addressService", produces = "application/json")
		public String addressService(@RequestParam("term") String term,
															 Locale locale, Model model) {
				String json = "";
				if(term != null && term.length() > 2){
						List<Item> addresses = addressService.getList(term);
						if(addresses != null && addresses.size() > 0){
								json = buildJson(addresses);
						}
				}
				return json;
		}
		private String buildJson(List<Item> ones){
				String json = "";
				JSONArray jArr = new JSONArray();
				for(Item one:ones){
						// if(!json.equals("")) json += ",";
						// json += "{\"id\":"+one.getId()+",\"value\":"+JSONObject.quote(one.getName())+"}";
						JSONObject jObj = new JSONObject();
						jObj.put("id", one.getId());
						jObj.put("value",one.getName());
						jArr.put(jObj);
				}
				json = jArr.toString();
				return json;
		}		
}
