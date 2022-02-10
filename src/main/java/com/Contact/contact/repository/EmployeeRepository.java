package com.Contact.contact.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.Contact.contact.entity.Contact;
import com.Contact.contact.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	
}
