package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.model.EmployeeEntity;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.EmployeeRepository;
 
@Service
public class EmployeeService {
     
    @Autowired
    EmployeeRepository repository;
    
    @Autowired
    CustomerRepository customerRepository;
     
    public List<EmployeeEntity> getAllEmployees()
    {
        List<EmployeeEntity> employeeList = repository.findAll();
         
        if(employeeList.size() > 0) {
            return employeeList;
        } else {
            return new ArrayList<EmployeeEntity>();
        }
    }
     
    public EmployeeEntity getEmployeeById(Integer id) throws RecordNotFoundException
    {
        Optional<EmployeeEntity> employee = repository.findById(id);
         
        if(employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
     
    public EmployeeEntity createOrUpdateEmployee(EmployeeEntity entity) throws RecordNotFoundException
    {
    	/*
        Optional<EmployeeEntity> employee = repository.findById(entity.getId());
         
        if(employee.isPresent())
        {
            EmployeeEntity newEntity = employee.get();
            newEntity.setEmail(entity.getEmail());
            newEntity.setFirstName(entity.getFirstName());
            newEntity.setLastName(entity.getLastName());
 
            newEntity = repository.save(newEntity);
             
            return newEntity;
        } else {
            entity = repository.save(entity);
             
            return entity;
        }
        */
    	//EmployeeEntity entity = null;
    	if(entity.getId()!=null && entity.getId()>0) {
    		
    	}else {
    		//entity = repository.save(entity);
    		//entity = repository.saveAndFlush(entity);
    	}
    	entity = repository.saveAndFlush(entity);
    	return entity;
    }
     
    public void deleteEmployeeById(Integer id) throws RecordNotFoundException
    {
        Optional<EmployeeEntity> employee = repository.findById(id);
         
        if(employee.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
    
    public Customer createOrUpdateCustomer(Customer entity) throws RecordNotFoundException
    {
    	
    	if(entity.getCustomerId() !=null && entity.getCustomerId()>0) {
    		
    	}else {
    		//entity = repository.save(entity);
    		//entity = repository.saveAndFlush(entity);
    	}
    	entity = customerRepository.saveAndFlush(entity);
    	return entity;
    }
}