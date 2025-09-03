package edu.jsp.service;

import java.util.List;

import edu.jsp.model.ContactForm;

public interface ContactFormService {
 public ContactForm saveContactFormService(ContactForm contactForm);
 
 public List<ContactForm> readAllContactsService();
 
 public void deleteByIdService(Integer id);
}
