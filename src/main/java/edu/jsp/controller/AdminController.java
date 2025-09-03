package edu.jsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.jsp.model.ServiceForm;
import edu.jsp.service.AdminCredentialService;
import edu.jsp.service.BookingFormService;
import edu.jsp.service.ContactFormService;
import edu.jsp.service.ServiceFormService;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	private ContactFormService contactFormService;
	
	private AdminCredentialService adminCredentialService;
	
	private BookingFormService bookingFormService;
	
	private ServiceFormService serviceFormService;
	
	@Autowired
	public void setServiceFormService(ServiceFormService serviceFormService) {
		this.serviceFormService = serviceFormService;
	}

	@Autowired
	public void setBookingFormService(BookingFormService bookingFormService) {
		this.bookingFormService = bookingFormService;
	}

	@Autowired
	public void setAdminCredentialService(AdminCredentialService adminCredentialService) {
		this.adminCredentialService = adminCredentialService;
	}

	@Autowired
	public void setContactFormService(ContactFormService contactFormService) {
		this.contactFormService = contactFormService;
	}

	@GetMapping("dashboard")
	public String adminDashboard() {
		return "admin/dashboard";
	}
	
	@GetMapping("readAllContacts")
	public String readAllContacts(Model model) {
		model.addAttribute("allcontacts", contactFormService.readAllContactsService());
		
		return "admin/readallcontacts";
	}
	
	@GetMapping("deleteContact/{id}")
	public String deleteContact(@PathVariable int id,RedirectAttributes redirectAttribute ) {
	
		contactFormService.deleteByIdService(id);
		redirectAttribute.addFlashAttribute("message"," Contact Deleted Succesfully ");
		return "redirect:/admin/readAllContacts";
	}
	
    @GetMapping("changeCredentials")
	public String changeCredentialsView() {
		return "admin/changeCredentials";
	}
    
    @PostMapping("changeCredentials")
   	public String changeCredentials(
   			@RequestParam("oldUsername") String oldUsername,
   			@RequestParam("oldPassword") String oldPassword,
   			@RequestParam("newUsername") String newUsername,
   			@RequestParam("newPassword") String newPassword,
   			RedirectAttributes redirectAttribute
   			) {
    	
    	String result = adminCredentialService.checkAdminCredentials(oldUsername, oldPassword);
    	if(result.equals("Success")) {
    		result = adminCredentialService.updateAdminCredentials(newUsername, newPassword, oldUsername);
    			redirectAttribute.addFlashAttribute("message",result);
    		}else {
    			redirectAttribute.addFlashAttribute("message",result);    			
    		}

   		return "redirect:/admin/dashboard";
}
	@GetMapping("readAllBooking")
	public String readAllBooking(Model model) {
		model.addAttribute("bookingForm", bookingFormService.readAllBookingFormService());
		
		return "admin/readallbookingform";
	}
	@GetMapping("deleteBookingForm/{id}")
	public String deleteBookingFrom(@PathVariable int id,RedirectAttributes redirectAttribute ) {
	
		bookingFormService.deleteByIdService(id);
		redirectAttribute.addFlashAttribute("message"," booking Deleted Succesfully ");
		return "redirect:/admin/readAllBooking";
	}
	
	@GetMapping("addService")
	public String addServiceView() {
		return "admin/addservice";
	}
	
	@InitBinder
	public  void stopBinding(WebDataBinder webDataBinder) {
		webDataBinder.setDisallowedFields("image");
	}
	
	@PostMapping("addService")
	public String addService(@ModelAttribute ServiceForm serviceForm ,@RequestParam("image") MultipartFile multiPartFile,RedirectAttributes redirectAttribute) {
		String originalFilename = multiPartFile.getOriginalFilename();
		serviceForm.setImage(originalFilename);
		ServiceForm service=null;
		try {
			service = serviceFormService.addService(serviceForm, multiPartFile);
			if(service!=null) {
				redirectAttribute.addFlashAttribute("message","service added succesfully");
			}else {
				redirectAttribute.addFlashAttribute("message","something went wrong");
			}
		} catch (Exception e) {
			redirectAttribute.addFlashAttribute("message","something went wrong");

		}
		return "redirect:/admin/addService";
	}
	
	@GetMapping("readAllServices")
	public String readAllServices(Model model) {
		model.addAttribute("services", serviceFormService.readAllServices());
		
		return "admin/readallservices";
	}
	@GetMapping("deleteServiceForm/{id}")
	public String deleteService(@PathVariable int id,RedirectAttributes redirectAttribute ) {
	
		serviceFormService.deleteByIdService(id);
		redirectAttribute.addFlashAttribute("message"," service Deleted Succesfully ");
		return "redirect:/admin/readAllServices";
	}
	
   	}
	

