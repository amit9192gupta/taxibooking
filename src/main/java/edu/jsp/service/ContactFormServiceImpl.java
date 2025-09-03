package edu.jsp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.jsp.dao.ContactFormCrud;
import edu.jsp.model.ContactForm;
@Service
public class ContactFormServiceImpl implements ContactFormService{

	private ContactFormCrud contactFormCrud;
	
	@Autowired 
	public void setContactFormCrud(ContactFormCrud contactFormCrud) {
		this.contactFormCrud = contactFormCrud;
	}

	@Override
	public ContactForm saveContactFormService(ContactForm contactForm) {
		return contactFormCrud.save(contactForm);
	}
	
	public List<ContactForm> readAllContactsService(){
		return contactFormCrud.findAll();
	}

	@Override
	public void deleteByIdService(Integer id) {
		contactFormCrud.deleteById(id);
	}

}
