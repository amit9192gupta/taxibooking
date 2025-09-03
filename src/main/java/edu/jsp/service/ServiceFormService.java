package edu.jsp.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import edu.jsp.model.ServiceForm;

public interface ServiceFormService {

	public ServiceForm addService(ServiceForm serviceForm,MultipartFile multiPartFile) throws Exception;
	
	public List<ServiceForm> readAllServices();
	
	public void deleteByIdService(int id);
}
