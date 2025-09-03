package edu.jsp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.jsp.model.ServiceForm;

@Repository
public interface ServiceFormCrud  extends JpaRepository<ServiceForm ,Integer>{

     @Override
    public <S extends ServiceForm> S save(S entity) ;
     
    @Override
    public List<ServiceForm> findAll() ;
    
    @Override
    public void deleteById(Integer id) ;
    	
}
