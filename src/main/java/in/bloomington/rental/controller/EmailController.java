package in.bloomington.rental.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import in.bloomington.rental.model.Address;
import in.bloomington.rental.model.EmailDetailLog;
import in.bloomington.rental.model.EmailLog;
import in.bloomington.rental.model.Owner;
import in.bloomington.rental.model.Rental;
import in.bloomington.rental.model.RentalOwner;
import in.bloomington.rental.service.AddressService;
import in.bloomington.rental.service.EmailDetailLogService;
import in.bloomington.rental.service.EmailLogService;
import in.bloomington.rental.service.OwnerService;
import in.bloomington.rental.service.RentalService;
import in.bloomington.rental.util.EmailHandle;
import in.bloomington.rental.util.Emailer;
import in.bloomington.rental.util.GeneralHelper;
import in.bloomington.rental.util.Helper;

@Controller
@Scope("session")
public class EmailController {

		private static final Logger logger = LogManager.getLogger(EmailController.class);		
		String message = null;
		
		@Autowired
		private OwnerService ownerService;
		@Autowired
		private RentalService rentalService;
		@Autowired
		private EmailLogService emailLogService;
		@Autowired
		private EmailDetailLogService emailDetailLogService;		
		@Autowired
		private AddressService addressService;		
		@Autowired
		private GeneralHelper ghelper;		
		@Autowired
		private HttpSession session;		
		@GetMapping("/ownerExpireEmail")
		public String ownerExpireEmail(Locale locale, Model model) {
				model.addAttribute("emailer", new Emailer());
				if(message != null)
						model.addAttribute("message", message);
				List<EmailLog> emailLogs = emailLogService.getAll();
				if(emailLogs != null && emailLogs.size() > 0){
						model.addAttribute("emailLogs", emailLogs);
				}
				return "ownerExpireEmail";
		}
		@GetMapping("/emailLogDetails/{id}")
		public String emailLogDetails(@PathVariable("id") int id,
																	Model model) {
				if(message != null)
						model.addAttribute("message", message);
				EmailLog emailLog = emailLogService.get(id);
				if(emailLog != null){
						model.addAttribute("emailLog", emailLog);
				}
				else{
						message = "Email log details not found ";
						return "redirect:/ownerExpireEmail";
				}
				return "emailLogDetails";
		}		
		@PostMapping("/expireEmailSend")
		public String doSend(@RequestParam("file") MultipartFile file,
												 @RequestParam("subject") String subject,
												 @RequestParam("cc") String cc,
												 @RequestParam("dateFrom") String dateFrom,
												 @RequestParam("dateTo") String dateTo,
												 @RequestParam("intro") String intro,
												 @RequestParam("para1") String para1,
												 @RequestParam("para2") String para2,
												 @RequestParam("action") String action,
												 Model model){
				Emailer emailer = new Emailer();
				emailer.setSubject(subject);
				emailer.setCc(cc);
				emailer.setDateFrom(dateFrom);
				emailer.setDateTo(dateTo);
				emailer.setIntro(intro);
				emailer.setPara1(para1);
				emailer.setPara2(para2);
				if(action.equals("Check")){
						List<Owner> owners = checkInvalidEmails(dateFrom, dateTo);
						if(owners.size() > 0){
								message = "Found "+owners.size()+" with invalid emails";
								model.addAttribute("owners", owners);
								model.addAttribute("message", message);										
								return "owners";								
						}
						else{
								message = "No invalid email found";
								model.addAttribute("message", message);
								return "messageOnly";
						}
				}
				File file2 = null;
				String fileName = null, filePath = null;
				if(file != null && !file.isEmpty()){
						ghelper.populatePaths();
						int year = Helper.getCurrentYear();						
						filePath = ghelper.getFilePath();
						String groupName = ghelper.getGroupName();
						filePath += "email/";
						String back = Helper.checkFilePath(filePath, groupName);
						filePath += year+"/";
						back = Helper.checkFilePath(filePath, groupName);						
						if(!back.equals("")){
								logger.error(back);
						}
						fileName = file.getOriginalFilename();				
						try{
								byte[] bytes = file.getBytes();
								String fullPath = filePath+fileName;
								System.err.println(" path "+fullPath);
								Path path = Paths.get(fullPath);
								Files.write(path, bytes);
								
								file2 = new File(fullPath);
						}
						catch (Exception e) {
								e.printStackTrace();
								message  += e;
						}
				}
				String str = composeAndSend(dateFrom,
																		dateTo,
																		emailer,
																		fileName,
																		filePath);
				//
				return "redirect:/ownerExpireEmail";
		}
		List<Owner> checkInvalidEmails(String dateFrom,
																	 String dateTo){
				List<Owner> owners = ownerService.findOwnersForExpireEmail(dateFrom,dateTo);
				List<Owner> ownersBadEmail = new ArrayList<>();
				if(owners != null && owners.size() > 0){
						for(Owner owner:owners){
								if(owner.hasEmail() && !owner.hasValidEmail()){
										ownersBadEmail.add(owner);
										System.err.println(" owner "+owner.getId()+" "+owner.getName()+" "+owner.getEmail());
								}
						}
				}
				return ownersBadEmail;
		}
	  String composeAndSend(String dateFrom,
													String dateTo,
													Emailer emailer,
													String fileName,
													String filePath){
				String back = "";
				String log_from = "hand@bloomington.in.gov";
				EmailLog emailLog =  new EmailLog(null, 
																					new Date(),
																					"Expire",
																					emailer.getEmailFrom());
				emailLogService.save(emailLog);
				Map<Owner, Set<Rental>> map = new HashMap<>();
				List<Rental> rentals = rentalService.findExpireDate(dateFrom, dateTo);
				if(rentals != null && rentals.size() > 0){
						for(Rental rental:rentals){
								// some rentals has agent only with email
								boolean ownerWithEmail = false;
								if(rental.hasOwners()){
										List<RentalOwner> ro = rental.getRentalOwners();
										if(ro != null && ro.size() > 0){
												for(RentalOwner rr:ro){
														Owner owner = rr.getOwner();
														if(owner.hasValidEmail()){
																ownerWithEmail = true;
																if(map.containsKey(owner)){
																		Set<Rental> set = map.get(owner);
																		set.add(rental);
																		map.put(owner, set);
																}
																else{
																		Set<Rental> set = new HashSet<>();
																		set.add(rental);
																		map.put(owner, set);
																}
														}
												}
										}
								}
								// if owner has no email
								if(!ownerWithEmail){
										if(rental.hasAgent()){
												Owner agent = rental.getAgent();
												if(agent.hasValidEmail()){
														if(map.containsKey(agent)){
																Set<Rental> set = map.get(agent);
																set.add(rental);
																map.put(agent, set);
														}
														else{
																Set<Rental> set = new HashSet<>();
																set.add(rental);
																map.put(agent, set);
														}
												}
										}
								}
						}
				}
				if(map.size() > 0){
						for(Owner owner:map.keySet()){
								Set<Rental> set = map.get(owner);
								String to="", bcc="", emailText="";
								// for logging
								String rentalIds="",ownerIds="", agentIds="";
								if(owner.hasValidEmail()){
										List<String> emails = owner.getEmailList();
										ownerIds = ""+owner.getId();
										if(emails != null && emails.size() > 0){
												for(String email:emails){
														if(to.equals("")){
																to = email;
														}
														else{ // avoid dups
																if(to.indexOf(email) == -1 &&
																	 bcc.indexOf(email) == -1){
																		if(!bcc.equals("")){
																				bcc +=",";
																		}
																		bcc += email;
																}
														}
												}
										}
								}
								emailText = Helper.getToday()+"\n\n";
								emailText += emailer.getIntro()+"\n\n";
								for(Rental rental:set){
										String rid =""+ rental.getId();
										if(rentalIds.indexOf(rid) == -1){
												if(!rentalIds.equals("")) rentalIds += ",";
												rentalIds += rid;
										}
										if(rental.hasAgent()){
												Owner agent = rental.getAgent();
												if(agent.hasValidEmail()){
														String aid =""+agent.getId();
														if(agentIds.indexOf(aid) == -1){
																if(!agentIds.equals("")) agentIds += ",";
																agentIds += aid;
														}
														List<String> emails = agent.getEmailList();
														if(emails != null && emails.size() > 0){
																for(String email:emails){
																		if(to.equals("")){
																				to = email;
																		}
																		else{
																				if(bcc.indexOf(email) == -1 &&
																					 to.indexOf(email) == -1){
																						if(!bcc.equals("")){
																								bcc +=",";
																						}
																						bcc += email;
																				}
																		}
																}
														}
												}
										}
										// rental.prepareAddresses();
										List<Address> addresses = addressService.findByRentalId(rental.getId());
										if(addresses != null){
												for(Address addr:addresses){
														emailText += "ID: "+rental.getId()+", Expires: "+rental.getPermitExpiresFr()+", "+addr.getStreetAddress()+"\n";
												}
										}
								}
								emailText += "\n"; 
								emailText += emailer.getPara1()+"\n\n";
								emailText += emailer.getPara2()+"\n\n";
								//
								String error = null;
								String cc = emailer.getCc();
								if(cc != null && cc.indexOf("optional") > -1){
										cc = null;
								}
								if(fileName != null && !fileName.equals("")){
										EmailHandle ehl = new EmailHandle(to,
																											emailer.getEmailFrom(),
																											emailer.getSubject(),
																											emailText,
																											cc,
																											bcc);
										System.err.println(ehl);
										// error = ehl.send();
								}
								else{
										EmailHandle ehl = new EmailHandle(to,
																											emailer.getEmailFrom(),
																											emailer.getSubject(),
																											emailText,
																											cc,
																											bcc,
																											fileName,
																											filePath);
										System.err.println(ehl);
										// error = ehl.sendWAttach();
								}
								EmailDetailLog eml =
										new EmailDetailLog(emailLog,
																			 to,
																			 cc,
																			 bcc,
																			 ownerIds,
																			 agentIds,
																			 rentalIds,
																			 error
																			 );
								emailDetailLogService.save(eml);
								/*
								System.err.println(" to "+to);
								System.err.println(" bcc "+bcc);
								System.err.println(" msg "+emailText);
								System.err.println(" rentalIds "+rentalIds);
								System.err.println(" ownerIds "+ownerIds);
								System.err.println(" agentIds "+agentIds);
								*/
						}
				}
				return back;				
		}

}

