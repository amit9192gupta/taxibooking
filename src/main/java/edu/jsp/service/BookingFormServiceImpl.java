package edu.jsp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.jsp.dao.BookingFormCrud;
import edu.jsp.model.BookingForm;
@Service
public class BookingFormServiceImpl implements BookingFormService{
	
    private BookingFormCrud bookingFormCrud;
    
    
	@Autowired
	public void setBookingForm(BookingFormCrud bookingFormCrud) {
		this.bookingFormCrud = bookingFormCrud;
	}



	@Override
	public BookingForm saveBookingForm(BookingForm bookingForm) {
		BookingForm form = bookingFormCrud.save(bookingForm);
		return form;
	}



	@Override
	public List<BookingForm> readAllBookingFormService() {
		return bookingFormCrud.findAll();
	}
	
	@Override
	public void deleteByIdService(int id) {
        bookingFormCrud.deleteById(id);	
	}

	
}
