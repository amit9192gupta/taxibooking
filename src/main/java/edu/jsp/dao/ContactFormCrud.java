package edu.jsp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import edu.jsp.model.ContactForm;
@EnableJpaRepositories(basePackages = "edu.jsp.dao")
@Repository
public interface ContactFormCrud extends JpaRepository<ContactForm, Integer> {

	public <S extends ContactForm> S save(S entity);
	
	@Override
	public  List<ContactForm> findAll();
	
    @Override
    public void deleteById(Integer id) ;
    	
}
