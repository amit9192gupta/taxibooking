package edu.jsp.service;

import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.jsp.dao.ServiceFormCrud;
import edu.jsp.model.ServiceForm;
import jakarta.transaction.Transactional;
@Service
public class ServiceFormServiceImpl implements ServiceFormService {
	
	private ServiceFormCrud serviceFormCrud;
	
	@Autowired
	public void setServiceFormCrud(ServiceFormCrud serviceFormCrud) {
		this.serviceFormCrud = serviceFormCrud;
	}




	@Transactional(rollbackOn  = Exception.class)
	@Override
	public ServiceForm addService(ServiceForm serviceForm, MultipartFile multiPartFile) throws Exception {
		ServiceForm save=null;
		try {
			save=serviceFormCrud.save(serviceForm);
			if(save!=null) {
			String path="C:\\Users\\asus\\STS_Project\\taxiBooking\\src\\main\\resources\\static\\myserviceimg\\"+ multiPartFile.getOriginalFilename();
			byte[] bytes = multiPartFile.getBytes();
			try(FileOutputStream fileOutputStream = new FileOutputStream(path)){
				fileOutputStream.write(bytes);

			}
			}
		}catch(Exception e ) {
			save=null;
			System.out.println(e.getMessage());
			throw e;
		}
		return save;
	}




	@Override
	public List<ServiceForm> readAllServices() {
		// TODO Auto-generated method stub
		return serviceFormCrud.findAll();
	}




	@Override
	public void deleteByIdService(int id) {
		serviceFormCrud.deleteById(id);
		
	}

}
