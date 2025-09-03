package edu.jsp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.jsp.model.BookingForm;
import edu.jsp.model.ContactForm;
import edu.jsp.model.ServiceForm;
import edu.jsp.service.BookingFormService;
import edu.jsp.service.ContactFormService;
import edu.jsp.service.ServiceFormService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class MyController {
	private  ContactFormService contactFormService;
	
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
	public void setContactFormService(ContactFormService contactFormService) {
		this.contactFormService=contactFormService;
	}

	@GetMapping(path = { "/", "home", "welcome", "index" })
	public String welcomeview(HttpServletRequest req, Model model) {
		String requestURI = req.getRequestURI();
		model.addAttribute("mycurrentpage", requestURI);
		model.addAttribute("bookingForm",new BookingForm());
		return "index";
	}
	
	@PostMapping("bookingform")
	public String bookingform(@Valid @ModelAttribute BookingForm bookingForm,BindingResult bindingResult,Model m,RedirectAttributes redirectAttribute) {
		if(bindingResult.hasErrors()) {
			m.addAttribute("bindingResult",bindingResult);
			return "index";
		}else if((bookingForm.getAdult()+bookingForm.getChildren())>4){
			m.addAttribute("message","the total no of adult and children cannot exceed 4");
			return "index";
		}
		
		BookingForm savedBookingForm=bookingFormService.saveBookingForm(bookingForm);
		if(savedBookingForm!=null) {
			redirectAttribute.addFlashAttribute("message", "submitted succesfully");
		}else {
			redirectAttribute.addFlashAttribute("message", "something went wrong");
		}
		return "redirect:/index";
	}


	@GetMapping("about")
	public String aboutview(HttpServletRequest req, Model model) {
		String requestURI = req.getRequestURI();
		model.addAttribute("mycurrentpage", requestURI);
		return "about";

	}

	@GetMapping("cars")
	public String carsview(HttpServletRequest req, Model model) {
		String requestURI = req.getRequestURI();
		model.addAttribute("mycurrentpage", requestURI);
		return "cars";
	}

	@GetMapping("services")
	public String serviceview(HttpServletRequest req, Model model) {
		String requestURI = req.getRequestURI();
		model.addAttribute("mycurrentpage", requestURI);
	    List<ServiceForm> allServices = serviceFormService.readAllServices();
	    model.addAttribute("allservices", allServices);
		return "services";
	}

	@GetMapping("contacts")
	public String contactview(HttpServletRequest req, Model model) {
		String requestURI = req.getRequestURI();
		model.addAttribute("mycurrentpage", requestURI);
		model.addAttribute("contactForm", new ContactForm());
		return "contacts";
	}
	
	@GetMapping("/login")
	public String adminLoginView(HttpServletRequest request,Model model) {
		ServletContext servletContext = request.getServletContext();
		Object attribute = servletContext.getAttribute("logout");
		if(attribute instanceof Boolean) {
			model.addAttribute("logout",attribute);
			servletContext.removeAttribute("logout");
		}
		return "adminlogin";
	}
	
	@PostMapping("contactform")
	public String contactForm(@Valid @ModelAttribute ContactForm contactForm,BindingResult bindingResult,Model m,RedirectAttributes redirectAttribute) {
		if(bindingResult.hasErrors()) {
			m.addAttribute("bindingResult",bindingResult);
			return "contacts";
		}
		ContactForm savedContactForm=contactFormService.saveContactFormService(contactForm);
		if(savedContactForm!=null) {
			redirectAttribute.addFlashAttribute("message", "submitted succesfully");
		}else {
			redirectAttribute.addFlashAttribute("message", "something went wrong");
		}
		System.out.println(contactForm);
		return "redirect:/contacts";
	}

}
