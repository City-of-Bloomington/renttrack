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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

import in.bloomington.rental.model.Bill;
import in.bloomington.rental.service.BillService;
import in.bloomington.rental.model.Receipt;
import in.bloomington.rental.service.ReceiptService;
import org.springframework.context.annotation.Scope;

@Controller
@Scope("session")
public class ReceiptController {

		String message = null;
		final static String[] payMethods = {"Cash","Check","Money Order","Credit Card"};		
		@Autowired
		private BillService billService;
		@Autowired
		private ReceiptService receiptService;		

		@GetMapping("/receipts")
		public String receiptsView(Locale locale, Model model) {
				
				model.addAttribute("receipts", receiptService.getAll());
				if(message != null)
						model.addAttribute("message", message);
				return "receipts";
		}
		// get by id
		@GetMapping("/receiptView/{id}")
		public String getReceipt(@PathVariable("id") int id,
											Model model) {
				Receipt receipt = receiptService.get(id);
				model.addAttribute("receipt", receipt);
				if(message != null)
						model.addAttribute("message", message);
				return "receiptView";
		}
		// get by id
		@GetMapping("/receiptPrint/{id}")
		public String printReceipt(@PathVariable("id") int id,
											Model model) {
				Receipt receipt = receiptService.get(id);
				int bill_id = receipt.getBill().getId();
				Bill bill = billService.get(bill_id);
				bill.getReceipts();
				receipt.setBill(bill);
				model.addAttribute("receipt", receipt);				
				model.addAttribute("bill", bill);				
				if(message != null)
						model.addAttribute("message", message);
				return "receiptPrint";
		}		
		// edit by id
		@GetMapping("/receiptEdit/{id}")
		public String editReceipt(@PathVariable("id") int id,
											Model model) {
				Receipt receipt = receiptService.get(id);
				model.addAttribute("receipt", receipt);
				model.addAttribute("payMethods", payMethods);
			 return "receiptEdit";
		}
		// get by id
		@GetMapping("/receiptDelete/{id}")
		public String deleteReceipt(@PathVariable("id") int id) {
				System.err.println(" id "+id);
				Receipt receipt = receiptService.get(id);
				int bill_id = -1;
				Bill bill = receipt.getBill();
				if(bill != null)
						bill_id = bill.getId();
				receiptService.delete(id);
				message = "Receipt deleted successfully";
			 return "redirect:/billView/"+bill_id;
		}		
		// save
		@PostMapping("/receiptUpdate")
		public String updateReceipt(@ModelAttribute("receipt") @Valid Receipt receipt,
														 BindingResult result, Model model) {
				if (result.hasErrors()) {
						System.err.println(" errors "+result.toString());
						return "redirect:/receiptEdit/"+receipt.getId();
				}
				receiptService.update(receipt.getId(), receipt);
			  int bill_id = receipt.getBill().getId();
				Bill bill = billService.get(bill_id);
				if(bill.getAmountDue() <= 0.01){
						System.err.println(" updateing bill "+bill.getId());
						bill.setStatus("Paid");
						billService.update(bill_id, bill);
				}
				receipt.setBill(bill);
				message = "Receipt updated successfully";
				model.addAttribute("receipt", receipt);
				model.addAttribute("payMethods", payMethods);
				return "receiptEdit"; 
		}
		// 
		@GetMapping("/receiptNew/{bill_id}")
		public String receiptForm(@PathVariable("bill_id") int bill_id, Locale locale, Model model) {
				Bill bill = billService.get(bill_id);
				bill.getReceipts();
				Receipt receipt = new Receipt();
				receipt.setBill(bill);
				model.addAttribute("receipt", receipt);				
				model.addAttribute("bill", bill);
				model.addAttribute("payMethods", payMethods);
				return "receiptNew";
   }
		// save
		@PostMapping("/receiptSave")
		public String saveReceipt(@ModelAttribute("receipt") @Valid Receipt receipt,
														 BindingResult result, Model model) {
				if (result.hasErrors()) {
						return "redirect:/receiptNew";
				}
				int rec_no = receiptService.getNextReceiptNo();
				try{
						receipt.setReceiptNo(rec_no);
						receiptService.save(receipt);
						int bill_id = receipt.getBill().getId();
						Bill bill = billService.get(bill_id);				
						if(bill.getAmountDue() <= 0.01){
								System.err.println(" updateing bill "+bill.getId());
								bill.setStatus("Paid");
								billService.update(bill_id, bill);
						}
						receipt.setBill(bill);				
						model.addAttribute("receipt", receipt);
						model.addAttribute("payMethods", payMethods);
						message = "Receipt saved successfully";
				}catch(Exception ex){
						System.err.println(" receipt error "+ex);
				}
				return "receiptEdit";
		}

}
