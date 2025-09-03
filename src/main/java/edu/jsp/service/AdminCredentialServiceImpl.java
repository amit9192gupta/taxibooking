package edu.jsp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.jsp.dao.AdminDao;
import edu.jsp.model.Admin;

@Service
public class AdminCredentialServiceImpl implements AdminCredentialService {
	
	private AdminDao adminDao;
	
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}



	@Autowired
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}



	@Override
	public String checkAdminCredentials(String oldUsername, String oldPassword) {
		Optional<Admin> byUsername = adminDao.findByUsername(oldUsername);
		if(byUsername.isPresent()) {
			Admin admin = byUsername.get();//contains enPassword and user name
			boolean matches = passwordEncoder.matches(oldPassword, admin.getPassword());
			if(matches) {
				return "Success";
			}else {
				return "Wrong old Credentials";
			}
		}else {
			return "Wrong old credentials";
		}
	}



	@Override
	public String  updateAdminCredentials(String newUsername, String newPassword,String oldUsername) {
		int updateCredentials = adminDao.updateCredentials(newUsername, passwordEncoder.encode(newPassword) , oldUsername);
		if(updateCredentials==1) {
			return "Credential updated succesfully";
		}
		return "failed to update try again";
	}

}
