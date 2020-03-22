package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.core.annotation.Order;

import com.example.demo.validation.validator.UniqueLogin;

@Entity
@Table(name="TBL_EMPLOYEES")
public class EmployeeEntity {

	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.IDENTITY)
	 */
    
    @Id
	@SequenceGenerator(name="TBL_EMPLOYEES_ID_SEQ", sequenceName="TBL_EMPLOYEES_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.IDENTITY ,generator="TBL_EMPLOYEES_ID_SEQ")
	@Column(name = "TBL_EMPLOYEES_ID")
    private Integer id;
    
    
    @Column(name="first_name")
    @NotBlank(message = "First Name is Mandatory")
    private String firstName;
    
    @Column(name="last_name")
    @NotBlank(message = "Last Name is Mandatory")
    private String lastName;
    
    @Column(name="email", nullable=false, length=200)
    private String email;
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    @Override
    public String toString() {
        return "EmployeeEntity [id=" + id + ", firstName=" + firstName + 
                ", lastName=" + lastName + ", email=" + email   + "]";
    }
}