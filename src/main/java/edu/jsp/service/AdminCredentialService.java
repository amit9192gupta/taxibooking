package edu.jsp.service;

public interface AdminCredentialService {

	public String checkAdminCredentials(String oldUsername,String oldPassword);
	
	public String updateAdminCredentials(String newUsername,String newPassword,String oldUsername);
}
