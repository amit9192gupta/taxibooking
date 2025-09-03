package edu.jsp.service;

import java.util.List;

import edu.jsp.model.BookingForm;

public interface BookingFormService {

	public BookingForm saveBookingForm(BookingForm bookingForm);
		
	public List<BookingForm> readAllBookingFormService();
	
	public void deleteByIdService(int id);
}
