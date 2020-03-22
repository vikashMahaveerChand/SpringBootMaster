package com.example.demo.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeEntity;
import com.example.demo.service.EmployeeService;
import com.example.demo.validation.validator.ValidCustomerLocation;
 
@RestController
@RequestMapping("/employees")
public class EmployeeController
{
    @Autowired
    EmployeeService service;
 
    //@GetMapping
    @GetMapping(path="/getAllEmployeesDB", produces = "application/json")
    public ResponseEntity<List<EmployeeEntity>> getAllEmployees() {
        List<EmployeeEntity> list = service.getAllEmployees();
 
        return new ResponseEntity<List<EmployeeEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("getEmployeesDB/{id}")
    public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable("id") Integer id)
                                                    throws RecordNotFoundException {
        EmployeeEntity entity = service.getEmployeeById(id);
 
        return new ResponseEntity<EmployeeEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }
    
	/*
	 * @PostMapping(path= "/addEmployee", consumes = "application/json", produces =
	 * "application/json") public ResponseEntity<Object> addEmployee(
	 * 
	 * @RequestHeader(name = "X-COM-PERSIST", required = false) String
	 * headerPersist,
	 * 
	 * @RequestHeader(name = "X-COM-LOCATION", required = false, defaultValue =
	 * "ASIA") String headerLocation,
	 * 
	 * @RequestBody Employee employee) throws Exception { //Generate resource id
	 * Integer id = employeeDao.getAllEmployees().getEmployeeList().size() + 1;
	 * employee.setId(id);
	 * 
	 * //add resource employeeDao.addEmployee(employee);
	 * 
	 * //Create resource location URI location =
	 * ServletUriComponentsBuilder.fromCurrentRequest() .path("/{id}")
	 * .buildAndExpand(employee.getId()) .toUri();
	 * 
	 * //Send location in response return ResponseEntity.created(location).build();
	 * }
	 */
 
    @PostMapping(path= "/addEmployeeDB", consumes = "application/json", produces = "application/json")
    public ResponseEntity<EmployeeEntity> createOrUpdateEmployee(@Valid @ValidCustomerLocation @RequestBody EmployeeEntity employee)
                                                    throws RecordNotFoundException {
        EmployeeEntity updated = service.createOrUpdateEmployee(employee);
        return new ResponseEntity<EmployeeEntity>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public HttpStatus deleteEmployeeById(@PathVariable("id") Integer id)
                                                    throws RecordNotFoundException {
        service.deleteEmployeeById(id);
        return HttpStatus.FORBIDDEN;
    }
    
    @PostMapping("/customers")
	public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) throws RecordNotFoundException {
		// save to database
		Customer newCustomer = service.createOrUpdateCustomer(customer);
		if (newCustomer != null) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(newCustomer.getCustomerId()).toUri();
			//return ResponseEntity.created(location).build();
			return new ResponseEntity<Customer>(newCustomer, new HttpHeaders(), HttpStatus.OK);
		} else {
			return ResponseEntity.unprocessableEntity().build();
		}

	}
    
	/*
	 * @ResponseStatus(HttpStatus.BAD_REQUEST)
	 * 
	 * @ExceptionHandler(MethodArgumentNotValidException.class) public Map<String,
	 * String> handleValidationExceptions( MethodArgumentNotValidException ex) {
	 * Map<String, String> errors = new HashMap<>();
	 * ex.getBindingResult().getAllErrors().forEach((error) -> { String fieldName =
	 * ((FieldError) error).getField(); String errorMessage =
	 * "-->"+error.getDefaultMessage(); errors.put(fieldName, errorMessage); });
	 * return errors; }
	 * 
	 * @ResponseStatus(HttpStatus.BAD_REQUEST)
	 * 
	 * @ExceptionHandler(ConstraintViolationException.class) public Map<String,
	 * String> constraintValidationExceptions( MethodArgumentNotValidException ex) {
	 * Map<String, String> errors = new HashMap<>();
	 * ex.getBindingResult().getAllErrors().forEach((error) -> { String fieldName =
	 * ((FieldError) error).getField(); String errorMessage =
	 * "@@-->"+error.getDefaultMessage(); errors.put(fieldName, errorMessage); });
	 * return errors; }
	 */
 
}