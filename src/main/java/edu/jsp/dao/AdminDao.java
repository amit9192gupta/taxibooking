package edu.jsp.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.jsp.model.Admin;
import jakarta.transaction.Transactional;

@Repository
public interface AdminDao  extends JpaRepository<Admin, Integer>{

	Optional<Admin> findByUsername(String username);
	
	@Modifying
	@Transactional
	@Query(value="update admin set username=:newUsername ,password=:newPassword where username=:oldUsername" ,nativeQuery=true)
	public int updateCredentials(
			@Param("newUsername") String newUsername,
			@Param("newPassword") String newPassword,
			@Param("oldUsername") String oldUsername
			);
}
